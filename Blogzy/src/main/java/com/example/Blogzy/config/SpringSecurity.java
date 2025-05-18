package com.example.Blogzy.config;

import com.example.Blogzy.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    private final JwtFilter jwtFilter;

    public SpringSecurity(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/login", "/users/signUp").permitAll()
//                        .requestMatchers( "/users/search").hasRole("ADMIN")
                        .requestMatchers("/users/viewProfiles", "/users/updateProfile", "/users/deleteProfile").authenticated()
                        .requestMatchers("/feed/createContent", "/feed/getAll").authenticated()
                        .requestMatchers("/feedLikes/likeAPost", "/feedLikes/totalLikes", "/feedLikes/getLikedFeeds").authenticated()
                        .requestMatchers("/feedComments/commentOnPost", "/feedComments/getComments", "/feedComments/deleteComment").authenticated()
                        .requestMatchers("/replies/replyToComment", "/replies/deleteReply").authenticated()
                        .requestMatchers("/likeComment/likeAMessage").authenticated()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
