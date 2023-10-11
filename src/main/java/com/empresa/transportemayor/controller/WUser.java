package com.empresa.transportemayor.controller;

import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/wuser")
@CrossOrigin(origins = "*")
public class WUser {

  @GetMapping()
  public Map<String, String> getUser() {
    return Map.of("User", SecurityContextHolder.getContext().getAuthentication().getName());
  }
}
