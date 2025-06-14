package com.mitulnakrani.url_shortener.web.controllers;

import com.mitulnakrani.url_shortener.ApplicationProperties;
import com.mitulnakrani.url_shortener.domain.entities.ShortUrlsEntity;
import com.mitulnakrani.url_shortener.domain.entities.UsersEntity;
import com.mitulnakrani.url_shortener.domain.exception.ShortUrlNotFoundException;
import com.mitulnakrani.url_shortener.domain.models.CreateShortUrlCmd;
import com.mitulnakrani.url_shortener.domain.models.ShortUrlsEntityDto;
import com.mitulnakrani.url_shortener.domain.services.ShortUrlService;
import com.mitulnakrani.url_shortener.web.dtos.CreateShortUrlForm;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

@Controller
public class HomeControlller {

    private final ShortUrlService shortUrlService;
    private final ApplicationProperties properties;
    private final SecurityUtils securityUtils;

    public HomeControlller(ShortUrlService shortUrlService, ApplicationProperties properties, SecurityUtils securityUtils) {
        this.shortUrlService = shortUrlService;
        this.properties = properties;
        this.securityUtils = securityUtils;
    }

    @GetMapping("/")
    public String home(Model model)
    {
//      List<ShortUrlsEntity> shortUrls = shortUrlRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        UsersEntity currentUser = securityUtils.getCurrentUser(); // This line is to ensure the current user is fetched, if needed later.
        List<ShortUrlsEntityDto> shortUrls = shortUrlService.getAllPublicShortUrls();
        model.addAttribute("shortUrls", shortUrls);
        model.addAttribute("baseUrl", properties.baseUrl());
        model.addAttribute("createShortUrlForm", new CreateShortUrlForm(""));
        return "index";
    }


    @PostMapping("/shorten")
    String createShortUrl(@ModelAttribute("createShortUrlForm") @Valid CreateShortUrlForm form,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          Model model) {
        if(bindingResult.hasErrors()) {
            List<ShortUrlsEntityDto> shortUrls = shortUrlService.getAllPublicShortUrls();
            model.addAttribute("shortUrls", shortUrls);
            model.addAttribute("baseUrl", properties.baseUrl());
            return "index";
        }

        try{
            CreateShortUrlCmd cmd = new CreateShortUrlCmd(form.originalURL());
            var shortUrlDto = shortUrlService.createShortUrl(cmd);
            redirectAttributes.addFlashAttribute("successMessage", "Short URL created successfully!   " + properties.baseUrl()+ "/s/" + shortUrlDto.shortKey());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create short URL: " + e.getMessage());
        }
        return "redirect:/";
    }


    @GetMapping("/s/{shortKey}")
    String redirectToOriginalUrl(@PathVariable String shortKey) {
        Optional<ShortUrlsEntityDto> shortUrlsEntityDtoOptional = shortUrlService.accessShortUrl(shortKey);
        if (shortUrlsEntityDtoOptional.isEmpty()) {
            throw new ShortUrlNotFoundException("Invalid short key: " + shortKey);
        }
        ShortUrlsEntityDto shortUrl = shortUrlsEntityDtoOptional.get();
        return "redirect:" + shortUrl.originalUrl();
    }

    @GetMapping("/login")
    String loginForm(){
        return "login";
    }

}
