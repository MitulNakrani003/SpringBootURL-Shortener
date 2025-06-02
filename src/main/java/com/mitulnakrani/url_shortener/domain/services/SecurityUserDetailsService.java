package com.mitulnakrani.url_shortener.domain.services;

import com.mitulnakrani.url_shortener.domain.entities.UsersEntity;
import com.mitulnakrani.url_shortener.domain.repositories.UsersEntityRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
    private final UsersEntityRepository usersEntityRepository;

    public SecurityUserDetailsService(UsersEntityRepository usersEntityRepository) {
        this.usersEntityRepository = usersEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity user = usersEntityRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole()))
        );
    }
}
