package ru.geekbrains.programworld.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.programworld.core.model.Rating;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query(value = "SELECT * FROM ratings where article_id = ?1", nativeQuery = true)
    List<Rating> findAllWithArticleId(Long article_id);

}
