package com.mitulnakrani.url_shortener.domain.services;

import com.mitulnakrani.url_shortener.ApplicationProperties;
import com.mitulnakrani.url_shortener.domain.entities.ShortUrlsEntity;
import com.mitulnakrani.url_shortener.domain.models.CreateShortUrlCmd;
import com.mitulnakrani.url_shortener.domain.models.ShortUrlsEntityDto;
import com.mitulnakrani.url_shortener.domain.repositories.ShortUrlRepository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true) // Ensure that this service is transactional, so that all operations are atomic
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;
    private final EntityMapper entityMapper;
    private final ApplicationProperties properties;

    public ShortUrlService(ShortUrlRepository shortUrlRepository, EntityMapper entityMapper, ApplicationProperties properties) {
        this.shortUrlRepository = shortUrlRepository;
        this.entityMapper = entityMapper;
        this.properties = properties;
    }

    public List<ShortUrlsEntityDto> getAllPublicShortUrls() {
        // This method should interact with the repository to fetch all short URLs
        // For now, returning an empty list
        return shortUrlRepository.findPublicShortUrls().stream().map(entityMapper::toShortUrlsEntityDto).toList();
    }

    @Transactional  // This method should be transactional to ensure that the creation of a short URL is atomic
    public ShortUrlsEntityDto createShortUrl(CreateShortUrlCmd cmd) {
        if(properties.validationEnabled())
        {
            boolean urlExists = UrlExistanceValidator.isUrlExists(cmd.originalURL());
            if (!urlExists) {
                throw new RuntimeException("Invalid URL provided: " + cmd.originalURL());
            }
        }
        var shortKey = generateUniqueShortKey();
        var myshortUrl = new ShortUrlsEntity();
        myshortUrl.setOriginalUrl(cmd.originalURL());
        myshortUrl.setShortKey(shortKey);
        myshortUrl.setPrivate(false);
        myshortUrl.setExpiresAt(java.time.LocalDateTime.ofInstant(Instant.now().plus(properties.defaultExpirationDays(), java.time.temporal.ChronoUnit.DAYS), java.time.ZoneId.systemDefault()));
        myshortUrl.setCreatedAt(java.time.LocalDateTime.now());
        myshortUrl.setClickCount(0);
        shortUrlRepository.save(myshortUrl);
        return entityMapper.toShortUrlsEntityDto(myshortUrl);
    }

    private String generateUniqueShortKey() {
        String shortKey;
        do {
            shortKey = generateRandomShortKey();
        } while (shortUrlRepository.existsByShortKey(shortKey));
        return shortKey;
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SHORT_KEY_LENGTH = 6;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomShortKey() {
        StringBuilder sb = new StringBuilder(SHORT_KEY_LENGTH);
        for (int i = 0; i < SHORT_KEY_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    @Transactional
    public Optional<ShortUrlsEntityDto> accessShortUrl(String shortKey) {
        Optional<ShortUrlsEntity> shortUrlEntityOptional = shortUrlRepository.findByShortKey(shortKey);
        if (shortUrlEntityOptional.isEmpty()) {
            return Optional.empty();
        }
        ShortUrlsEntity shortUrlEntity = shortUrlEntityOptional.get();
        if(shortUrlEntity.getExpiresAt() != null && shortUrlEntity.getExpiresAt().isBefore(java.time.LocalDateTime.now())) {
            return Optional.empty();
        }
        shortUrlEntity.setClickCount(shortUrlEntity.getClickCount() + 1);
        shortUrlRepository.save(shortUrlEntity); // Save the updated click count
        return shortUrlEntityOptional.map(entityMapper::toShortUrlsEntityDto);
    }
}
