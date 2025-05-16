package com.mitulnakrani.url_shortener.domain.repositories;

import com.mitulnakrani.url_shortener.domain.entities.ShortUrlsEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrlsEntity, Long> {

}
