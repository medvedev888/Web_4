package me.vladislav.web_4.controllers;

import lombok.RequiredArgsConstructor;
import me.vladislav.web_4.dto.UserDTO;
import me.vladislav.web_4.exceptions.UserAlreadyExistException;
import me.vladislav.web_4.exceptions.UserNotFoundException;
import me.vladislav.web_4.security.JwtTokenProvider;
import me.vladislav.web_4.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        String login = userDTO.getLogin();
        try {
            userService.registerNewUserAccount(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("success", true, "message", "User registered successfully"));
        } catch (UserAlreadyExistException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("success", false, "message", "User already exists"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "An unexpected error occurred"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try {
            String login = userDTO.getLogin();
            String password = userDTO.getPassword();
            if(userService.checkPassword(login, password)) {
                String token = jwtTokenProvider.generateToken(login);
                return ResponseEntity.ok(Map.of("success", true, "token", token,"message", "Login successful"));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("success", false, "message", "Invalid login or password"));
            }
        } catch (UserNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("success", false, "message", "User with this login not found"));
        }
    }

}
