package com.mitulnakrani.url_shortener.web.controllers;

import com.mitulnakrani.url_shortener.domain.entities.ShortUrlsEntity;
import com.mitulnakrani.url_shortener.domain.models.ShortUrlsEntityDto;
import com.mitulnakrani.url_shortener.domain.services.ShortUrlService;
import com.mitulnakrani.url_shortener.web.dtos.CreateShortUrlForm;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import jakarta.validation.Valid;

@Controller
public class HomeControlller {

    private final ShortUrlService shortUrlService;

    public HomeControlller(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping("/")
    public String home(Model model)
    {
//      List<ShortUrlsEntity> shortUrls = shortUrlRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        List<ShortUrlsEntityDto> shortUrls = shortUrlService.getAllPublicShortUrls();
        model.addAttribute("shortUrls", shortUrls);
        model.addAttribute("baseUrl", "http://localhost:8080/");
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
            model.addAttribute("baseUrl", "http://localhost:8080/");
            return "index";
        }
        //TODO: Implement the logic to create a short URL
        if(2==1)
        {
            redirectAttributes.addFlashAttribute("successMessage", "Short URL created successfully!");
        }
        else{
            redirectAttributes.addFlashAttribute("errorMessage", "Short URL already exists!");
        }

        return "redirect:/";
    }



}
