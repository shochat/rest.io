package io.service;

import io.config.security.UserDetailsImpl;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {
  UserDetailsImpl loadUserByUsername(String username);

}