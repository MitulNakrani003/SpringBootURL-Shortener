package com.mitulnakrani.url_shortener.web;

import com.mitulnakrani.url_shortener.domain.exception.ShortUrlNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(ShortUrlNotFoundException.class)
    String handleShortUrlNotFoundException(ShortUrlNotFoundException ex) {
        log.error("Short URL not found: {}", ex.getMessage());
        return "error/404"; // Return the view name for 404 error
    }

    @ExceptionHandler(Exception.class)
    String handleException(Exception ex) {
        log.error("An unexpected error occurred: {}", ex.getMessage());
        return "error/500"; // Return the view name for 500 error
    }
}
