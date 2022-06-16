package ru.geekbrains.programworld.core.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "articles")
@Data
@NamedEntityGraph(name = "Article.images",
        attributeNodes = @NamedAttributeNode("images")
)

@NamedQueries({
        @NamedQuery(name = "Article.findByIdWithRate", query = "SELECT u FROM Article u JOIN FETCH u.ratings WHERE u.id = :id"),
        @NamedQuery(name = "Article.findAllByProgLanguageWithComments", query = "SELECT DISTINCT article FROM Article article LEFT JOIN FETCH article.comments WHERE article.progLanguage = :program_language"),
        @NamedQuery(name = "Article.findAllByProgLanguageWithCommentsAndRating", query = "SELECT DISTINCT article FROM Article article LEFT JOIN FETCH article.ratings WHERE article IN :articlesWithComments"),
        @NamedQuery(name = "Article.findAllForUser", query = "SELECT article FROM Article article")
})

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

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Rating> ratings;

}
