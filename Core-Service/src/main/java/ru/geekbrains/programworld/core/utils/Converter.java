package ru.geekbrains.programworld.core.utils;

import org.springframework.stereotype.Component;
import ru.geekbrains.programworld.api.dtos.ArticleDTO;
import ru.geekbrains.programworld.api.dtos.CommentDTO;
import ru.geekbrains.programworld.api.dtos.ImageDTO;
import ru.geekbrains.programworld.api.dtos.RatingDTO;
import ru.geekbrains.programworld.core.model.Article;
import ru.geekbrains.programworld.core.model.Comment;
import ru.geekbrains.programworld.core.model.Image;
import ru.geekbrains.programworld.core.model.Rating;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Converter {
    public ArticleDTO articleToDto(Article article) {
        return new ArticleDTO(article.getId(), article.getAuthor(), article.getTitle(), article.getProgLanguage(), article.getFileName(),article.getFileType(), article.getImages().stream().map(this::imageDTO).collect(Collectors.toList()));
    }

    public ImageDTO imageDTO(Image image) {
        return new ImageDTO(image.getId(), image.getFileName(), image.getFileType());
    }

    public ArticleDTO articleToDtoForClient(Article article,String program_language) {
        if (program_language == null) return new ArticleDTO (article.getId(),article.getProgLanguage(), article.getAuthor(), article.getTitle(),article.getCreatedAt());
        return new ArticleDTO(article.getId(), article.getAuthor(), article.getTitle(), article.getProgLanguage(),article.getComments().size(),
                article.getRatings().size(), getAverageRating(article.getRatings())* 20, article.getCreatedAt());
    }

    public CommentDTO commentToDTO(Comment comment) {
        return new CommentDTO(comment.getId(), comment.getUsername(), comment.getContent());
    }

    public RatingDTO ratingToDTO(List<Rating> ratingForArticle, String username) {
        return new RatingDTO(getAverageRating(ratingForArticle) * 20 , ratingForArticle.size(),ratingForArticle.stream().anyMatch(rating -> rating.getUsername().equals(username)));
    }

    public double getAverageRating (List<Rating> ratingForArticle) {
        return (ratingForArticle.stream().mapToInt(Rating::getUserScore).sum()*1.0)/(ratingForArticle.size()*1.0);
    }

}
