package com.mitulnakrani.url_shortener.domain.repositories;

import com.mitulnakrani.url_shortener.domain.entities.ShortUrlsEntity;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShortUrlRepository extends JpaRepository<ShortUrlsEntity, Long> {

    @Query("SELECT s FROM ShortUrlsEntity s LEFT JOIN fetch s.createdBy WHERE s.isPrivate = false ORDER BY s.createdAt DESC")
    List<ShortUrlsEntity> findPublicShortUrls();
}

