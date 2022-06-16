package ru.geekbrains.programworld.api.dtos;

public class RatingDTO {
    private Long id;
    private Long article_Id;
    private String username;
    private int userScore;
    private double averageRating;
    private int numberOfRatings;
    boolean isUserAlreadySetScore;

    public RatingDTO() {
    }

    public RatingDTO(Long article_Id, String username, int userScore) {
        this.userScore = userScore;
        this.username = username;
        this.article_Id = article_Id;
    }

    public RatingDTO(double averageRating, int numberOfRatings, boolean isUserAlreadySetScore) {
        this.averageRating = averageRating;
        this.numberOfRatings = numberOfRatings;
        this.isUserAlreadySetScore = isUserAlreadySetScore;
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

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public boolean isUserAlreadySetScore() {
        return isUserAlreadySetScore;
    }

    public void setUserAlreadySetScore(boolean userAlreadySetScore) {
        isUserAlreadySetScore = userAlreadySetScore;
    }

}
