package com.mitulnakrani.url_shortener.web.controllers;

import com.mitulnakrani.url_shortener.domain.entities.UsersEntity;
import com.mitulnakrani.url_shortener.domain.repositories.UsersEntityRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityUtils {

    private final UsersEntityRepository userEntityRepository;

    public SecurityUtils(UsersEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }


    public UsersEntity getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            return userEntityRepository.findByEmail(email).orElse(null);
        }
        return null;
    }
}
