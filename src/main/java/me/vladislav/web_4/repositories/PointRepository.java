package me.vladislav.web_4.repositories;

import me.vladislav.web_4.models.Point;
import me.vladislav.web_4.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Integer> {
    @Override
    <S extends Point> S save(S entity);

    List<Point> getPointsByOwner(User owner);
}
