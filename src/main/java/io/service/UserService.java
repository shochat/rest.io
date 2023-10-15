package io.service;

import io.config.security.UserDetailsImpl;
import io.model.user.LoginRequest;
import io.model.user.SignupRequest;
import io.model.user.UserInfoResponse;
import org.springframework.http.ResponseCookie;

import java.util.Optional;

public interface UserService extends org.springframework.security.core.userdetails.UserDetailsService {
  UserDetailsImpl loadUserByUsername(String username);

  UserInfoResponse signin(LoginRequest loginRequest);

  Optional<?> register(SignupRequest signUpRequest);

  ResponseCookie signout();

    Long extractUserIdFromToken(String mbCookie);
}