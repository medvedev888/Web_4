package me.vladislav.web_4.services;

import lombok.RequiredArgsConstructor;
import me.vladislav.web_4.dto.PointDTO;
import me.vladislav.web_4.dto.response.PointResponseDTO;
import me.vladislav.web_4.models.Point;
import me.vladislav.web_4.models.User;
import me.vladislav.web_4.repositories.PointRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;

    @Transactional
    public PointDTO addPoint(PointDTO pointDTO) {

        Point result = pointRepository.save(new Point(
                pointDTO.getX(),
                pointDTO.getY(),
                pointDTO.getR(),
                checkArea(pointDTO.getX(), pointDTO.getY(), pointDTO.getR()),
                pointDTO.getOwner()
        ));
        return new PointDTO(result.getX(),
                result.getY(),
                result.getR(),
                result.getResult(),
                result.getOwner());
    }

    @Transactional
    public void deleteAllPoints(User owner) {
        pointRepository.deleteAllByOwner(owner);
    }

    public List<PointResponseDTO> getListOfPoint(User owner) {
        List<Point> listOfPoints = pointRepository.getPointsByOwner(owner);
        List<PointResponseDTO> listOfPointsDTO = new ArrayList<>();
        for (Point point : listOfPoints) {
            listOfPointsDTO.add(new PointResponseDTO(point.getX(), point.getY(), point.getR(), point.getResult()));
        }
        return listOfPointsDTO;
    }

    // Calculating the result
    public boolean checkArea(double x, double y, double r) {
        boolean result = false;

        if (x >= 0 && y >= 0 && ((x * x + y * y) <= (r * r))) {
            result = true;
        } else if (x >= 0 && y <= 0 && x <= r && y >= (-1) * (r / 2)) {
            result = true;
        } else if (x <= 0 && x >= -1 * r && y <= 0 && y >= -1 * r && y >= (-1 * x) - r) {
            result = true;
        }

        return result;
    }

}
