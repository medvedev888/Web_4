package me.vladislav.web_4.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.vladislav.web_4.dto.UserDTO;
import me.vladislav.web_4.exceptions.UserAlreadyExistException;
import me.vladislav.web_4.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody UserDTO userDTO,
            HttpSession session
    ) {
        System.out.println("register method is working");
        String login = userDTO.getLogin();
        System.out.println("login: " + login);

        try {
            userService.registerNewUserAccount(userDTO);
            session.setAttribute("userID", userService.getUserByLogin(login).getId());
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

}
