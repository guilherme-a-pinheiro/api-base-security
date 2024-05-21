package com.pinheiro.loginauthapi.controllers;

import com.pinheiro.loginauthapi.domain.User.User;
import com.pinheiro.loginauthapi.dtos.LoginRequestDTO;
import com.pinheiro.loginauthapi.dtos.RegisterRequestDTO;
import com.pinheiro.loginauthapi.dtos.ResponseDTO;
import com.pinheiro.loginauthapi.infra.security.TokenService;
import com.pinheiro.loginauthapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody RegisterRequestDTO data){
        if (userRepository.findByEmail(data.email()) != null){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = passwordEncoder.encode(data.password());
        User newUser = new User(data.name(), data.email(), encryptedPassword);
        userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody LoginRequestDTO data){
        User user = userRepository.findByEmail(data.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(user.getPassword(), data.password())){
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok().body(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
