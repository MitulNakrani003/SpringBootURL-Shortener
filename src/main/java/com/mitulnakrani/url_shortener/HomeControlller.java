package com.mitulnakrani.url_shortener;

import com.mitulnakrani.url_shortener.domain.entities.ShortUrlsEntity;
import com.mitulnakrani.url_shortener.domain.repositories.ShortUrlRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class HomeControlller {


    private final ShortUrlRepository shortUrlRepository;

    public HomeControlller(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    @GetMapping("/")
    public String home(Model model)
    {
        List<ShortUrlsEntity> shortUrls = shortUrlRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        model.addAttribute("shortUrls", shortUrls);
        model.addAttribute("baseUrl", "http://localhost:8080/"); // Change this to your base URL
        return "index";
    }



}
