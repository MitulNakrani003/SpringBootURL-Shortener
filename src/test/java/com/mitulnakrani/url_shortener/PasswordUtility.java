package com.mitulnakrani.url_shortener;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtility {
    public static void main(String[] args) {
        // Example usage of the PasswordEncoder
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Encoded password: " + encoder.encode("secret"));
        System.out.println("Encoded password: " + encoder.encode("admin"));
    }
}
