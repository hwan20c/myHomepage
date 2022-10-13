package com.bh.tb.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String login() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    for(GrantedAuthority auth: authentication.getAuthorities()) {
      if(auth.getAuthority().equals("ROLE_ADMIN")) {
        return "redirect:/";
      }
    }
    return "auth/login";
  }
  
}
