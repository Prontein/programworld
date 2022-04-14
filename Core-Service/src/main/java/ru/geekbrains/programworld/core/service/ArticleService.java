package ru.geekbrains.programworld.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.programworld.core.model.Article;
import ru.geekbrains.programworld.core.repositories.ArticleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }
}
