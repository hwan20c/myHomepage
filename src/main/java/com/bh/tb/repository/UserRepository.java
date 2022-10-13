package com.bh.tb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bh.tb.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

  User findByLoginId(String name);
  
}