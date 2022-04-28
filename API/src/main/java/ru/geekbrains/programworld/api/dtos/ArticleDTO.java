package ru.geekbrains.programworld.api.dtos;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;


public class ArticleDTO {
    private Long id;
    private String fileName;
    private String fileType;
    private String author;
    private String title;
    private String progLanguage;
    private List<ImageDTO> images;

    public ArticleDTO() {
    }

    public ArticleDTO(Long id, String author, String title, String progLanguage) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.progLanguage = progLanguage;
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
}
