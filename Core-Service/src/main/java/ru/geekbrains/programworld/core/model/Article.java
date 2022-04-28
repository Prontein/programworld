package ru.geekbrains.programworld.core.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;

@Entity
@Table(name = "articles")
@Data
@NamedEntityGraph(name = "Article.images",
        attributeNodes = @NamedAttributeNode("images")
)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @Column(name = "prog_language")
    private String progLanguage;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Lob
    private byte[] content;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Image> images;
}
