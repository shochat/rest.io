package io.service;

import org.springframework.security.core.userdetails.UserDetails;

public class JwtService implements AuthService {
    @Override
    public String createToken(UserDetails userDetails) {

        return null;
    }

    @Override
    public boolean isTokenValid(String token) {
        return false;
    }

    @Override
    public boolean isTokenExpired(String token) {
        return false;
    }

    @Override
    public UserDetails getTokenData(String token) {
        return null;
    }
}
