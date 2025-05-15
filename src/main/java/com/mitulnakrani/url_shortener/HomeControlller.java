package com.mitulnakrani.url_shortener;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HomeControlller {

    @GetMapping("/")

    public String home() {
    return "{\"message\": \"Welcome to the URL Shortener API!\"}";
    }

    @GetMapping("/about")
    public String about() {
        return "about.html";
    }

}
