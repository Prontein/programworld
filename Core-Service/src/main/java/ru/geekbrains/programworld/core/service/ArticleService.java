package ru.geekbrains.programworld.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.programworld.api.dtos.ArticleDTO;
import ru.geekbrains.programworld.api.exceptions.ResourceNotFoundException;
import ru.geekbrains.programworld.core.exceptions.ArticleAlreadyExistException;
import ru.geekbrains.programworld.core.model.Article;
import ru.geekbrains.programworld.core.repositories.ArticleRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    @Value("${upload.path}")
    private String uploadPath;
    private final ArticleRepository articleRepository;

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    @Transactional
    public Article uploadToDb(MultipartFile file, ArticleDTO articleDTO) {

        if (articleExists(file.getOriginalFilename())) {
            throw new ArticleAlreadyExistException("Такой файл уже существует: " + file.getOriginalFilename());
        }

       Article article = new Article();
        try {

            article.setContent(file.getBytes());
            article.setFileType(file.getContentType());
            article.setFileName(file.getOriginalFilename());
            article.setAuthor(articleDTO.getAuthor());
            article.setTitle(articleDTO.getTitle());
            article.setProgLanguage(articleDTO.getProgLanguage());
            return articleRepository.save(article);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Article editingInDb(Long id, MultipartFile file, ArticleDTO articleDTO) {

        Article article = articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article id = " + id + " not found"));
        try {
            article.setContent(file.getBytes());
            article.setAuthor(articleDTO.getAuthor());
            article.setTitle(articleDTO.getTitle());
            article.setProgLanguage(articleDTO.getProgLanguage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articleRepository.save(article);
    }

    private boolean articleExists(String fileName) {
        return articleRepository.findByFileName(fileName).isPresent();
    }

    @Transactional
    public List<Article> findAllArticles() {
        return articleRepository.findAll();
    }

    public Path getServerCatalogPath() {
        Path currentPath = Paths.get("materials");
        return  currentPath.normalize().toAbsolutePath();
    }

    @Transactional
    public List<Article> findAllArticlesForClient(String program_language) {
        if (program_language == null) return articleRepository.findAllForUser();

        List<Article> articlesWithComments = articleRepository.findAllByProgLanguageWithComments(program_language);
        return articleRepository.findAllByProgLanguageWithCommentsAndRating(articlesWithComments);
    }

    public Optional<Article> findByIdWithRate(Long article_id) {
        return articleRepository.findByIdWithRate(article_id);
    }
}
