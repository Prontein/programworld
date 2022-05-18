package ru.geekbrains.programworld.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
@Data
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_score")
    private int userScore;

    @Column(name = "username")
    private String username;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;


    public Rating(int userScore, String username) {
        this.userScore = userScore;
        this.username = username;
    }
}
