package io.service;

import io.config.security.UserDetailsImpl;
import io.model.message.User;
import io.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetailsImpl loadUserByUsername(String username) {
    return UserDetailsImpl.build(userRepository.findByUsername(username).orElseGet(User::new));
  }
}