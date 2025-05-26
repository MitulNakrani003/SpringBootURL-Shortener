package com.mitulnakrani.url_shortener.domain.exception;

public class ShortUrlNotFoundException extends RuntimeException {

    public ShortUrlNotFoundException(String shortKey) {
        super("Short URL with key '" + shortKey + "' not found. ");
    }
}
