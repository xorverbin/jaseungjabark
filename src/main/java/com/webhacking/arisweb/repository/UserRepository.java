package com.webhacking.arisweb.repository;

import java.util.Optional;

import com.webhacking.arisweb.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    Optional<SiteUser> findByusername(String username);
}