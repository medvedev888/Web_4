package me.vladislav.web_4.services;

import lombok.RequiredArgsConstructor;
import me.vladislav.web_4.dto.PointDTO;
import me.vladislav.web_4.dto.UserDTO;
import me.vladislav.web_4.dto.response.PointResponseDTO;
import me.vladislav.web_4.models.Point;
import me.vladislav.web_4.models.User;
import me.vladislav.web_4.repositories.PointRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;

    public PointDTO addPoint(PointDTO pointDTO) {
        //TODO: need to change this block
        Point result = pointRepository.save(new Point(
                pointDTO.getX(),
                pointDTO.getY(),
                pointDTO.getR(),
                true,
                pointDTO.getOwner()
        ));
        return new PointDTO(result.getX(),
                result.getY(),
                result.getR(),
                result.getResult(),
                result.getOwner());
    }

    public List<PointResponseDTO> getListOfPoint(User owner) {
        List<Point> listOfPoints = pointRepository.getPointsByOwner(owner);
        List<PointResponseDTO> listOfPointsDTO = new ArrayList<>();
        for(Point point : listOfPoints) {
            listOfPointsDTO.add(new PointResponseDTO(point.getX(), point.getY(), point.getR(), point.getResult()));
        }
        return listOfPointsDTO;
    }

}
