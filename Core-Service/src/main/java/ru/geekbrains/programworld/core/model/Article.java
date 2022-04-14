package ru.geekbrains.programworld.core.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "articles")
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content")
    private String content;
}
