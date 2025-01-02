package com.example.login_auth_api.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login_auth_api.domain.user.User;
import com.example.login_auth_api.dtos.LoginRequestDTO;
import com.example.login_auth_api.dtos.RegisterRequestDTO;
import com.example.login_auth_api.dtos.ResponseAuthDTO;
import com.example.login_auth_api.exceptions.ApiException;
import com.example.login_auth_api.infra.security.TokenService;
import com.example.login_auth_api.repositories.UserRepository;

import lombok.AllArgsConstructor;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<ResponseAuthDTO> login(@RequestBody LoginRequestDTO data) {
    User user = this.userRepository.findByEmail(data.email())
    .orElseThrow(() -> new ApiException("User not found", 404));
  
    if (passwordEncoder.matches(data.password(), user.getPassword())) {
      String token = this.tokenService.generateToken(user);
      return ResponseEntity.ok(new ResponseAuthDTO(user.getName(), token));
    }
    return ResponseEntity.badRequest().build();
  }

  @PostMapping("/register")
  public ResponseEntity<ResponseAuthDTO> register(@RequestBody RegisterRequestDTO data) {
    Optional<User> user = this.userRepository.findByEmail(data.email());
    
    if (user != null) {
      new ApiException("User already exists", 409);
    }

    User newUser = new User();
    newUser.setName(data.name());
    newUser.setEmail(data.email());
    newUser.setPassword(passwordEncoder.encode(data.password()));

    this.userRepository.save(newUser);
    String token = this.tokenService.generateToken(newUser);
    return ResponseEntity.ok(new ResponseAuthDTO(newUser.getName(), token));
  }
}
