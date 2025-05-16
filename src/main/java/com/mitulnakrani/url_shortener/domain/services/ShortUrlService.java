package com.mitulnakrani.url_shortener.domain.services;

import com.mitulnakrani.url_shortener.domain.entities.ShortUrlsEntity;
import com.mitulnakrani.url_shortener.domain.repositories.ShortUrlRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;

    public ShortUrlService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public List<ShortUrlsEntity> getAllPublicShortUrls() {
        // This method should interact with the repository to fetch all short URLs
        // For now, returning an empty list
        return shortUrlRepository.findPublicShortUrls();
    }
}
