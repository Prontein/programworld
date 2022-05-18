package ru.geekbrains.programworld.api.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CommentDTO {
    private Long id;
    private Long article_Id;
    @NotNull(message = "Имя автора не указано")
    private String username;
    @NotNull(message = "Невозможно отправить пустой комментарий")
    @Length(min = 3, max = 2000, message = "Комментарий  должен содержать 3 - 2000 символов")
    private String content;

    public CommentDTO() {
    }

    public CommentDTO(Long id,String username, String content) {
        this.id = id;
        this.username = username;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticle_Id() {
        return article_Id;
    }

    public void setArticle_Id(Long article_Id) {
        this.article_Id = article_Id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
