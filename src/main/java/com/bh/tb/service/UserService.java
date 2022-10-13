package com.bh.tb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.tb.model.User;
import com.bh.tb.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  
  public User findUserByLoginId(String loginId) {
    return userRepository.findByLoginId(loginId);
  }

  public User findById(int id) {
		return userRepository.findById(id).get();
	}

}
