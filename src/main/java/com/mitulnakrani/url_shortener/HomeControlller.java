package com.mitulnakrani.url_shortener;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeControlller {

    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("title", "URL Shortener - using Thymeleaf");
        return "index";
    }



}
