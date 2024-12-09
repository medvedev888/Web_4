package me.vladislav.web_4.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import me.vladislav.web_4.dto.AddPointRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin
public class PointController {
//    private final PointService pointService;

    @PostMapping("/point")
    public ResponseEntity<?> addPoint(AddPointRequestDTO addPointRequestDTO, HttpServletRequest httpServletRequest) {
        System.out.println("addPoint");
        String token = httpServletRequest.getHeader("Authorization");
        System.out.println(token);

        return ResponseEntity.ok().body(Map.of("success", true, "message", "j"));
    }


}
