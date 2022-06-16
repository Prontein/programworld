package ru.geekbrains.programworld.api.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleDTO {
    private Long id;
    private String fileName;
    private String fileType;
    private String author;
    private String title;
    private String progLanguage;
    private List<ImageDTO> images;
    private int numberOfComments;
    private int numberOfRatings;
    private double averageRating;
    private LocalDateTime createdAt;

    public ArticleDTO() {
    }

    public ArticleDTO(Long id, String progLanguage, String author, String title, LocalDateTime createdAt) {
        this.id = id;
        this.progLanguage = progLanguage;
        this.author = author;
        this.title = title;
        this.createdAt = createdAt;
    }

    public ArticleDTO(Long id, String author, String title, String progLanguage, int numberOfComments, int numberOfRatings, double averageRating, LocalDateTime createdAt) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.progLanguage = progLanguage;
        this.numberOfComments = numberOfComments;
        this.numberOfRatings = numberOfRatings;
        this.averageRating = averageRating;
        this.createdAt = createdAt;
    }

    public ArticleDTO(Long id, String author, String title, String progLanguage, String fileName, String fileType, List<ImageDTO> images) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.progLanguage = progLanguage;
        this.fileName = fileName;
        this.fileType = fileType;
        this.images = images;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProgLanguage() {
        return progLanguage;
    }

    public void setProgLanguage(String progLanguage) {
        this.progLanguage = progLanguage;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
