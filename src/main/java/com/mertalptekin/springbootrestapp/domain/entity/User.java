package com.mertalptekin.springbootrestapp.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// UserDetails -> Spring Security'nin kullanıcı bilgilerini temsil eden arayüzüdür.

// Bir kullanıcının veritabanı kullanıcısı olup spring security tarafından tanınması için
// UserDetails arayüzünü implemente etmesi gerekir.
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // şifre alanı, hashlenmiş şifre saklanmalı


    // UserDetails interface methods ile kullanıcıya rol tanımı verdik.
    // Role tablasu olurşturmak yerine buradan verdik.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
