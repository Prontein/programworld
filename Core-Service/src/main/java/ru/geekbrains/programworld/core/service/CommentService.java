package ru.geekbrains.programworld.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.programworld.api.dtos.CommentDTO;
import ru.geekbrains.programworld.api.exceptions.ResourceNotFoundException;
import ru.geekbrains.programworld.core.model.Article;
import ru.geekbrains.programworld.core.model.Comment;
import ru.geekbrains.programworld.core.repositories.CommentRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleService articleService;

    @Transactional
    public void createComment(CommentDTO commentDTO) {
        Article article = articleService.findById(commentDTO.getArticle_Id()).orElseThrow(() -> new ResourceNotFoundException("Article id = " + commentDTO.getArticle_Id() + " not found"));

        Comment comment = new Comment(commentDTO.getUsername(), commentDTO.getContent());
        comment.setArticle(article);
        commentRepository.save(comment);
    }

    @Transactional
    public List<Comment> getCommentsForArticle(Long article_id) {
        return commentRepository.findAllWithArticleId(article_id);
    }

    public void deleteComment(Long comment_id) {
        Comment comment = commentRepository.findById(comment_id).orElseThrow(() -> new ResourceNotFoundException("Comment ID = " + comment_id + " not found"));
        commentRepository.deleteById(comment_id);
    }

}
