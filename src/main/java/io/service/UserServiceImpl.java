package io.service;

import io.config.security.JwtUtils;
import io.config.security.UserDetailsImpl;
import io.model.message.ERole;
import io.model.message.Role;
import io.model.message.User;
import io.model.user.LoginRequest;
import io.model.user.SignupRequest;
import io.model.user.UserInfoResponse;
import io.repository.RoleRepository;
import io.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  UserRepository userRepository;
  @Autowired
  AuthenticationConfiguration authConfig;
  @Autowired
  RoleService roleService;
  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Override
  public UserDetailsImpl loadUserByUsername(String username) {
    return UserDetailsImpl.build(userRepository.findByUsername(username).orElseGet(User::new));
  }

  @Override
  @SneakyThrows
  public UserInfoResponse signin(LoginRequest loginRequest) {
    Authentication authentication = authConfig.getAuthenticationManager()
            .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

    List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

    return new UserInfoResponse(userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles,
            jwtCookie.toString());
  }

  @Override
  public Optional<?> register(SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return Optional.of("Error: Username is already taken!");
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return Optional.of("Error: Email is already in use!");
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleService.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
          switch (role) {
              case "admin" -> {
                  Role adminRole = roleService.findByName(ERole.ROLE_ADMIN)
                          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                  roles.add(adminRole);
              }
              case "mod" -> {
                  Role modRole = roleService.findByName(ERole.ROLE_MODERATOR)
                          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                  roles.add(modRole);
              }
              default -> {
                  Role userRole = roleService.findByName(ERole.ROLE_USER)
                          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                  roles.add(userRole);
              }
          }
      });
    }

    user.setRoles(roles);
    User newUser = userRepository.save(user);

    return Optional.of(newUser);
  }

  @Override
  public ResponseCookie signout() {
    return jwtUtils.getCleanJwtCookie();
  }

    @Override
    public Long extractUserIdFromToken(String mbCookie) {
      String userNameFromJwtToken = jwtUtils.getUserNameFromJwtToken(mbCookie);
      return this.loadUserByUsername(userNameFromJwtToken).getId();
    }
}