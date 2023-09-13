package io.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    String createToken(UserDetails userDetails);

    boolean isTokenValid(String token);

    boolean isTokenExpired(String token);

    UserDetails getTokenData(String token);
}
