package com.mitulnakrani.url_shortener.domain.entities;

import java.sql.Timestamp;
import java.util.Objects;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "short_urls")
public class ShortUrlsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "short_key", nullable = false, length = 10)
    private String shortKey;
    @Basic
    @Column(name = "original_url", nullable = false)
    private String originalUrl;
    @Basic
    @Column(name = "is_private", nullable = false)
    private boolean isPrivate;
    @Basic
    @Column(name = "expires_at", nullable = true)
    private Timestamp expiresAt;
    @Basic
    @Column(name = "created_by", nullable = true)
    private Long createdBy;
    @Basic
    @Column(name = "click_count", nullable = false)
    private long clickCount;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShortKey() {
        return shortKey;
    }

    public void setShortKey(String shortKey) {
        this.shortKey = shortKey;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Timestamp getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Timestamp expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public long getClickCount() {
        return clickCount;
    }

    public void setClickCount(long clickCount) {
        this.clickCount = clickCount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortUrlsEntity that = (ShortUrlsEntity) o;
        return id == that.id && isPrivate == that.isPrivate && clickCount == that.clickCount && Objects.equals(shortKey, that.shortKey) && Objects.equals(originalUrl, that.originalUrl) && Objects.equals(expiresAt, that.expiresAt) && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shortKey, originalUrl, isPrivate, expiresAt, createdBy, clickCount, createdAt);
    }
}
