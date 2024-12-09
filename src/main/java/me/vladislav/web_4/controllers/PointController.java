package me.vladislav.web_4.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import me.vladislav.web_4.dto.request.AddPointRequestDTO;
import me.vladislav.web_4.dto.PointDTO;
import me.vladislav.web_4.dto.response.AddPointResponseDTO;
import me.vladislav.web_4.models.User;
import me.vladislav.web_4.security.JwtTokenProvider;
import me.vladislav.web_4.services.PointService;
import me.vladislav.web_4.services.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin
public class PointController {
    private final PointService pointService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/point")
    public ResponseEntity<?> addPoint(@RequestBody AddPointRequestDTO addPointRequestDTO, HttpServletRequest httpServletRequest) {
        try {
            String token = httpServletRequest.getHeader("Authorization");
            String login = jwtTokenProvider.getLoginFromToken(token);
            User owner = userService.getUserByLogin(login);
            PointDTO pointDTO = pointService.addPoint(new PointDTO(addPointRequestDTO.getX(), addPointRequestDTO.getY(), addPointRequestDTO.getR(), owner));
            AddPointResponseDTO addPointResponseDTO = new AddPointResponseDTO(pointDTO.getX(), pointDTO.getY(), pointDTO.getR(), pointDTO.getResult());
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(Map.of("success", true, "point", addPointResponseDTO, "message", "Point added successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("success", false, "message", "Server Error"));
        }

    }


}
