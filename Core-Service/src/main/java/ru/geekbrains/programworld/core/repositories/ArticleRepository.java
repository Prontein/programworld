package ru.geekbrains.programworld.core.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.programworld.core.model.Article;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

    Optional<Article> findByFileName(String fileName);

    @EntityGraph(value = "Article.images")
    List<Article> findAll();

    List<Article> findAllForUser();

    Optional<Article> findByIdWithRate(@Param("id") Long article_id);

    List<Article> findAllByProgLanguageWithComments(@Param("program_language") String program_language);

    List<Article> findAllByProgLanguageWithCommentsAndRating(@Param("articlesWithComments") List<Article> articlesWithComments);
}
