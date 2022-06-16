package ru.geekbrains.programworld.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.programworld.api.dtos.RatingDTO;
import ru.geekbrains.programworld.api.exceptions.ResourceNotFoundException;
import ru.geekbrains.programworld.core.exceptions.RatingAlreadyExistException;
import ru.geekbrains.programworld.core.model.Article;
import ru.geekbrains.programworld.core.model.Rating;
import ru.geekbrains.programworld.core.repositories.RatingRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final ArticleService articleService;

    @Transactional
    public void addUserScore(RatingDTO ratingDTO) {
        Article article = articleService.findById(ratingDTO.getArticle_Id()).orElseThrow(() -> new ResourceNotFoundException("Article id = " + ratingDTO.getArticle_Id() + " not found"));

        Optional<Article> ArticleWithRate = articleService.findByIdWithRate(ratingDTO.getArticle_Id()).stream().
                filter(article1 -> article1.getRatings().stream().anyMatch(rating -> rating.getUsername().equals(ratingDTO.getUsername()))).findAny();

        if (ArticleWithRate.isEmpty()) {
            Rating rating = new Rating(ratingDTO.getUserScore(), ratingDTO.getUsername());
            rating.setArticle(article);
            ratingRepository.save(rating);
        } else throw new RatingAlreadyExistException("Невозможно повторно поставить оценку");
    }

    @Transactional
    public List<Rating> getRatingForArticle(Long article_id) {
        return ratingRepository.findAllWithArticleId(article_id);
    }

}
