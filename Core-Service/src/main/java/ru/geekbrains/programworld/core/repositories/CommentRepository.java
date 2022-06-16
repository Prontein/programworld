package ru.geekbrains.programworld.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.programworld.core.model.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comments where article_id = ?1", nativeQuery = true)
    List<Comment> findAllWithArticleId(Long article_id);

}
