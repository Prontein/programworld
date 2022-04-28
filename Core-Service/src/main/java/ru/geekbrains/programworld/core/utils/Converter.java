package ru.geekbrains.programworld.core.utils;

import org.springframework.stereotype.Component;
import ru.geekbrains.programworld.api.dtos.ArticleDTO;
import ru.geekbrains.programworld.api.dtos.ImageDTO;
import ru.geekbrains.programworld.core.model.Article;
import ru.geekbrains.programworld.core.model.Image;


import java.util.stream.Collectors;

@Component
public class Converter {
    public ArticleDTO articleToDto(Article article) {
        return new ArticleDTO(article.getId(), article.getAuthor(), article.getTitle(), article.getProgLanguage(), article.getFileName(),article.getFileType(), article.getImages().stream().map(this::imageDTO).collect(Collectors.toList()));
    }

    public ImageDTO imageDTO(Image image) {
        return new ImageDTO(image.getId(), image.getFileName(), image.getFileType());
    }

    public ArticleDTO articleToDtoForClient(Article article) {
        return new ArticleDTO(article.getId(), article.getAuthor(), article.getTitle(), article.getProgLanguage());
    }

}
