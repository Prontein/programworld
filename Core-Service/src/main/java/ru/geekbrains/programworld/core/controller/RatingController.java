package ru.geekbrains.programworld.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.programworld.api.dtos.CommentDTO;
import ru.geekbrains.programworld.api.dtos.RatingDTO;
import ru.geekbrains.programworld.core.model.Rating;
import ru.geekbrains.programworld.core.service.RatingService;
import ru.geekbrains.programworld.core.utils.Converter;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/ratings")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;
    private final Converter converter;

    @GetMapping("/{article_Id}")
    public RatingDTO getRatingForArticle(@PathVariable Long article_Id, @RequestHeader(required = false) String username) {
       return converter.ratingToDTO(ratingService.getRatingForArticle(article_Id),username);
    }

    @PostMapping
    public void addUserScore(@RequestBody RatingDTO ratingDTO) {
        ratingService.addUserScore(ratingDTO);
    }
}
