package me.vladislav.web_4.services;

import lombok.RequiredArgsConstructor;
import me.vladislav.web_4.dto.PointDTO;
import me.vladislav.web_4.models.Point;
import me.vladislav.web_4.repositories.PointRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;

    public PointDTO addPoint(PointDTO pointDTO) {
        Point result = pointRepository.save(new Point(
                pointDTO.getX(),
                pointDTO.getY(),
                pointDTO.getR(),
                pointDTO.getResult(),
                pointDTO.getOwner()
                ));
        return new PointDTO(result.getX(),
                result.getY(),
                result.getR(),
                result.getResult(),
                result.getOwner());
    }


}
