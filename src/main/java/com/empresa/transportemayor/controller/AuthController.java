package com.empresa.transportemayor.controller;

import com.empresa.transportemayor.dto.AuthResponseDto;
import com.empresa.transportemayor.dto.LoginDto;
import com.empresa.transportemayor.dto.RegisterDto;
import com.empresa.transportemayor.exception.ExceptionApp;
import com.empresa.transportemayor.models.Role;
import com.empresa.transportemayor.models.UserEntity;
import com.empresa.transportemayor.repository.RoleRepository;
import com.empresa.transportemayor.repository.UserRepository;
import com.empresa.transportemayor.security.JWTGenerator;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final JWTGenerator jwtGenerator;

  @PostMapping("login")
  public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtGenerator.generaToken(authentication);
    return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
  }

  @PostMapping("register")
  public ResponseEntity<Map<String, String>> register(@RequestBody RegisterDto registerDto) {
    if (userRepository.existsByUsername(registerDto.getUsername())) {
      throw new ExceptionApp("Username exist in db!", HttpStatus.CONFLICT.toString());
    }

    UserEntity user = new UserEntity();
    user.setUsername(registerDto.getUsername());
    user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

    Role roles = roleRepository.findByName("USER").get();
    user.setRoles(List.of(roles));

    userRepository.save(user);
    return new ResponseEntity<>(
        Map.of("Message", "User Created: " + registerDto.getUsername()), HttpStatus.CREATED);
  }
}
