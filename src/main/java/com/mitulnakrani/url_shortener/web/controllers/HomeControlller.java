package com.mitulnakrani.url_shortener.web.controllers;

import com.mitulnakrani.url_shortener.domain.entities.ShortUrlsEntity;
import com.mitulnakrani.url_shortener.domain.services.ShortUrlService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

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
        List<ShortUrlsEntity> shortUrls = shortUrlService.getAllPublicShortUrls();
        model.addAttribute("shortUrls", shortUrls);
        model.addAttribute("baseUrl", "http://localhost:8080/"); // Change this to your base URL
        return "index";
    }



}
