package ru.geekbrains.programworld.api.dtos;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class ArticleDTO {
    private Long id;

    private String content;

    public ArticleDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArticleDTO(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
