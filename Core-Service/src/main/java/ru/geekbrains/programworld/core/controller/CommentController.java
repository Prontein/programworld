package ru.geekbrains.programworld.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.programworld.api.dtos.CommentDTO;
import ru.geekbrains.programworld.core.exceptions.DataValidationException;
import ru.geekbrains.programworld.core.service.CommentService;
import ru.geekbrains.programworld.core.utils.Converter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final Converter converter;

    @PostMapping
    public void addComment(@RequestBody @Validated CommentDTO commentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        commentService.createComment(commentDTO);
    }

    @GetMapping()
    public List<CommentDTO> getCommentsForArticle(@RequestParam(name="article_Id") Long article_Id) {
        System.out.println(article_Id);
       return commentService.getCommentsForArticle(article_Id).stream().map(o -> converter.commentToDTO(o)).collect(Collectors.toList());
    }

    @DeleteMapping
    public void deleteComment(@RequestParam(name="comment_Id") Long comment_Id) {
        commentService.deleteComment(comment_Id);
    }

}
