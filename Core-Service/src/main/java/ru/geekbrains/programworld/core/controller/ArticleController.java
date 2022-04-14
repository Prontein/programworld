package ru.geekbrains.programworld.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.programworld.api.dtos.ArticleDTO;
import ru.geekbrains.programworld.api.exceptions.ResourceNotFoundException;
import ru.geekbrains.programworld.core.model.Article;
import ru.geekbrains.programworld.core.service.ArticleService;
import ru.geekbrains.programworld.core.utils.Converter;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final Converter converter;

    @GetMapping("/{id}")
    public ArticleDTO findById(@PathVariable Long id) {
        Article article = articleService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article id = " + id + " not found"));
        return converter.articleToDto(article);
    }

}
