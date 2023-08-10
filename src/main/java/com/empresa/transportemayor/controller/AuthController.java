package com.empresa.transportemayor.controller;

import com.empresa.transportemayor.dto.LoginDto;
import com.empresa.transportemayor.dto.RegisterDto;
import com.empresa.transportemayor.models.Role;
import com.empresa.transportemayor.models.UserEntity;
import com.empresa.transportemayor.repository.RoleRepository;
import com.empresa.transportemayor.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public AuthController(
      AuthenticationManager authenticationManager,
      UserRepository userRepository,
      RoleRepository roleRepository,
      PasswordEncoder passwordEncoder) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("login")
  public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return new ResponseEntity<>("Login OK!", HttpStatus.OK);
  }

  @PostMapping("register")
  public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
    if (userRepository.existsByUsername(registerDto.getUsername())) {
      return new ResponseEntity<>("Nombre de usuario existente", HttpStatus.BAD_REQUEST);
    }

    UserEntity user = new UserEntity();
    user.setUsername(registerDto.getUsername());
    user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

    Role roles = roleRepository.findByName("USER").get();
    user.setRoles(List.of(roles));

    userRepository.save(user);
    return new ResponseEntity<>("Usuario Regitrado", HttpStatus.CREATED);
  }
}
