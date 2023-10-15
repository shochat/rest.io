package io.config;


import io.config.security.AuthEntryPointJwt;
import io.config.security.AuthTokenFilter;
import io.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Autowired
  UserService userService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;
  @Autowired
  PasswordEncoder passwordEncoder;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userService);
    authProvider.setPasswordEncoder(passwordEncoder);

    return authProvider;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.csrf(AbstractHttpConfigurer::disable)
            .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth ->
                    auth.requestMatchers("/auth/**").permitAll()
                            .requestMatchers("/password/**").permitAll()
                            .requestMatchers("/swagger/**").permitAll()
                            .requestMatchers("/password.html").permitAll()
                            .requestMatchers("/form").permitAll()
                            .requestMatchers("/favicon.ico").permitAll()
                            .requestMatchers("/error").permitAll()
                            .requestMatchers("/css/**").permitAll()
                            .requestMatchers("/js/**").permitAll()
                            .anyRequest().authenticated()
            );

    http.authenticationProvider(authenticationProvider());

    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}