package com.mertalptekin.springbootrestapp.presentation.controller;


import com.mertalptekin.springbootrestapp.application.auth.RegisterRequest;
import com.mertalptekin.springbootrestapp.application.auth.TokenRequest;
import com.mertalptekin.springbootrestapp.application.auth.TokenResponse;
import com.mertalptekin.springbootrestapp.domain.entity.User;
import com.mertalptekin.springbootrestapp.infra.repository.IUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

// /api/v1/auth izin ver.
// Kullanıcı oluşturma token üretme, token refresh etme ve benzeri işlemler burada yapılacak.
@RestController
@RequestMapping("api/auth")
public class AuthController {


    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;

    public AuthController(PasswordEncoder passwordEncoder, IUserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String test(){
        return "Auth Controller is working...";
    }


    @PostMapping("token")
    public ResponseEntity<TokenResponse> token(@RequestBody TokenRequest request){
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){

        User usr = new User();
        usr.setUsername(request.username());
        usr.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(usr);

        // Kullanıcı veritabanın kaydı

        return ResponseEntity.ok().build();
    }

}
