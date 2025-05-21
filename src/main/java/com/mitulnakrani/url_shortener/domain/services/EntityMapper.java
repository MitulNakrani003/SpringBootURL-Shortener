package com.mitulnakrani.url_shortener.domain.services;

import com.mitulnakrani.url_shortener.domain.entities.ShortUrlsEntity;
import com.mitulnakrani.url_shortener.domain.entities.UsersEntity;
import com.mitulnakrani.url_shortener.domain.models.ShortUrlsEntityDto;
import com.mitulnakrani.url_shortener.domain.models.UsersEntityDto;

import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    public ShortUrlsEntityDto toShortUrlsEntityDto(ShortUrlsEntity entity) {

        UsersEntityDto userentityDto = null;
        if (entity.getCreatedBy() != null) {
            userentityDto = toUsersEntityDto(entity.getCreatedBy());
        }

        return new ShortUrlsEntityDto(
                entity.getId(),
                entity.getShortKey(),
                entity.getOriginalUrl(),
                entity.isPrivate(),
                entity.getExpiresAt(),
                userentityDto,
                entity.getClickCount(),
                entity.getCreatedAt()
        );
    }

    public UsersEntityDto toUsersEntityDto(UsersEntity entity) {
        return new UsersEntityDto(
                entity.getId(),
                entity.getName()
        );
    }
}
