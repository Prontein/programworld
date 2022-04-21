package ru.geekbrains.programworld.core.utils;

import org.springframework.stereotype.Component;
import ru.geekbrains.programworld.api.dtos.ArticleDTO;
import ru.geekbrains.programworld.core.model.Article;


import java.util.stream.Collectors;

@Component
public class Converter {
    public ArticleDTO articleToDto(Article article) {
        return new ArticleDTO(article.getId(), article.getAuthor(), article.getTitle(), article.getProgLanguage(), article.getFileName(),article.getFileType());
    }

}
