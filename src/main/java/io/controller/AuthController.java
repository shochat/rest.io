package io.controller;

import io.model.user.LoginRequest;
import io.model.user.SignupRequest;
import io.model.user.UserInfoResponse;
import io.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  UserService userService;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    UserInfoResponse userInfoResponse = userService.signin(loginRequest);
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, userInfoResponse.getCookie())
        .body(userInfoResponse);
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    Optional<?> registerResponse = userService.register(signUpRequest);
    return ResponseEntity.ok("User registered successfully!");
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = userService.signout();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body("You've been signed out!");
  }
}