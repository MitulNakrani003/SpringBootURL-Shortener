package com.mitulnakrani.url_shortener.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "short_urls")
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String shortUrl;

    @Column(nullable = false)
    private String longUrl;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User created_by;


    private Boolean isDeleted = false;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime expiresAt;

    private Long clickCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
