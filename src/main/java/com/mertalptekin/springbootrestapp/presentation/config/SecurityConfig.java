package com.mertalptekin.springbootrestapp.presentation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    // Kimlik Doğrulama için UserDetails Servisten kullanıcı bilgilerini sorgulayacağım altyapı katmanı
    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationFilter authenticationFilter;

    public SecurityConfig(AuthenticationProvider authenticationProvider, AuthenticationFilter authenticationFilter) {
        this.authenticationProvider = authenticationProvider;
        this.authenticationFilter = authenticationFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // web uygulamasında hangi endpointlere login oladan gireceğimiz yöneteceğiz.
        // http.csrf(AbstractHttpConfigurer::disable); // Form istekleri www.urlencoded değil application/json bundan dolayı bu ayarı kapattık. Gelenek web uygulamalrındaki güvenlik ayarı.
        http.cors(AbstractHttpConfigurer::disable);
//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Rest servislerde kullanılan session modeli
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/api/demo/**").permitAll()
                        .requestMatchers(("/api/auth/**")).permitAll()
                        .anyRequest().authenticated());
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(authenticationFilter,
                org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);


        return http.build();

    }
}
