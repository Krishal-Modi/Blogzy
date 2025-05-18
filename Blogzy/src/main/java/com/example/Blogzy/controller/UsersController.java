package com.example.Blogzy.controller;

import com.example.Blogzy.mapper.UsersMapper;
import com.example.Blogzy.model.UsersModel;
import com.example.Blogzy.service.CustomUserDetailsService;
import com.example.Blogzy.service.UsersService;
import com.example.Blogzy.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    private final UsersMapper usersMapper;

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    @PostMapping("/signUp")
    public ResponseEntity<UsersModel> signUp(@RequestBody UsersModel usersModel){
        return ResponseEntity.ok(usersService.newUser(usersModel));
    }


    @GetMapping("/viewProfiles")
    public List<UsersModel> getUser(@RequestParam(required = false) String search,
                                    @RequestHeader("Authorization") String tokenHeader){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return usersService.viewProfile(search);
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<?> updateUser(@RequestHeader("Authorization") String tokenHeader,
                                        @RequestBody UsersModel updatedUserModel) {
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return ResponseEntity.ok(usersService.updateUser(authenticatedEmail, updatedUserModel));
    }


    @DeleteMapping("/deleteProfile")
    public void deleteId(@RequestHeader("Authorization") String tokenHeader)
    {
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        usersService.deleteUser(authenticatedEmail);
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsersModel userModel) {
        try {
            // Wrap email and password into UsernamePasswordAuthenticationToken
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getPassword()));

            UserDetails userDetails = userDetailsService.loadUserByUsername(userModel.getEmail());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect Email or Password");
        }
    }

}