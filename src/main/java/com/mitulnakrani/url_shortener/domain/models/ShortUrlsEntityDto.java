package com.mitulnakrani.url_shortener.domain.models;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.mitulnakrani.url_shortener.domain.entities.ShortUrlsEntity}
 */
public record ShortUrlsEntityDto(long id,
                                 String shortKey,
                                 String originalUrl,
                                 boolean isPrivate,
                                 LocalDateTime expiresAt,
                                 UsersEntityDto createdBy,
                                 long clickCount,
                                 LocalDateTime createdAt
) implements Serializable {
}