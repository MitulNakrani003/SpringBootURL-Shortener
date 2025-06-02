package com.mitulnakrani.url_shortener.domain.repositories;

import com.mitulnakrani.url_shortener.domain.entities.UsersEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersEntityRepository extends JpaRepository<UsersEntity, Long> {
    Optional<UsersEntity> findByEmail(String email);
}