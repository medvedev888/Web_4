package me.vladislav.web_4.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import me.vladislav.web_4.dto.PointDTO;
import me.vladislav.web_4.dto.request.AddPointRequestDTO;
import me.vladislav.web_4.dto.request.UpdatePointsWithNewRDTO;
import me.vladislav.web_4.dto.response.PointResponseDTO;
import me.vladislav.web_4.models.User;
import me.vladislav.web_4.security.JwtTokenProvider;
import me.vladislav.web_4.services.PointService;
import me.vladislav.web_4.services.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
            PointResponseDTO pointResponseDTO = new PointResponseDTO(pointDTO.getX(), pointDTO.getY(), pointDTO.getR(), pointDTO.getResult());
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(Map.of("success", true, "point", pointResponseDTO, "message", "Point added successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("success", false, "message", "Server Error"));
        }
    }

    @DeleteMapping("/points")
    public ResponseEntity<?> deleteAllPoints(HttpServletRequest httpServletRequest) {
        try {
            String token = httpServletRequest.getHeader("Authorization");
            String login = jwtTokenProvider.getLoginFromToken(token);
            pointService.deleteAllPoints(userService.getUserByLogin(login));
            return ResponseEntity.ok().body(Map.of("success", true, "message", "Points successfully deleted"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("success", false, "message", "Server Error"));
        }
    }

    @GetMapping("/points")
    public ResponseEntity<?> getListOfPoints(HttpServletRequest httpServletRequest) {
        try {
            String token = httpServletRequest.getHeader("Authorization");
            String login = jwtTokenProvider.getLoginFromToken(token);
            List<PointResponseDTO> listOfPoints = pointService.getListOfPoint(userService.getUserByLogin(login));
            return ResponseEntity.ok().body(Map.of("success", true, "listOfPoints", listOfPoints, "message", "Success"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("success", false, "message", "Server Error"));
        }
    }

    @PatchMapping("/points/r")
    public ResponseEntity<?> updatePointsWithNewR(
            @RequestBody UpdatePointsWithNewRDTO updatePointsWithNewRDTO,
            HttpServletRequest httpServletRequest
    ) {
//        TODO: add validation of r-value
        try {
            String token = httpServletRequest.getHeader("Authorization");
            String login = jwtTokenProvider.getLoginFromToken(token);
            User owner = userService.getUserByLogin(login);
            double newR = updatePointsWithNewRDTO.getR();
            pointService.updatePointsWithNewR(newR, owner);
            return ResponseEntity.ok().body(Map.of("success", true, "message", "Points updated successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("success", false, "message", "Server Error"));
        }
    }

}
