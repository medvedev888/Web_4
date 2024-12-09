package me.vladislav.web_4.repositories;

import me.vladislav.web_4.models.Point;
import me.vladislav.web_4.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Integer> {
    @Override
    <S extends Point> S save(S entity);

    List<Point> getPointsByOwner(User owner);

    void deleteAllByOwner(User owner);

    @Modifying
    @Query("UPDATE Point p SET p.r = :newR, p.result = :result WHERE p.id = :id")
    void updateRAndResultByPointId(@Param("newR") double newR, @Param("result") boolean result, @Param("id") int id);

}
