package com.mitulnakrani.url_shortener.domain.models;

import java.io.Serializable;

/**
 * DTO for {@link com.mitulnakrani.url_shortener.domain.entities.UsersEntity}
 */
public record UsersEntityDto(long id, String name) implements Serializable {
}