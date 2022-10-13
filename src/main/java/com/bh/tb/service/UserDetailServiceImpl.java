package com.bh.tb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bh.tb.model.User;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
  
  @Autowired
  UserService userService;

  @Override
  public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
    User user = userService.findUserByLoginId(loginId);
		if(user != null && user.isEnabled() ) {
      return user;
    } else {
      throw new UsernameNotFoundException("username not found");
    }
  }
  
}
