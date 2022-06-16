package ru.geekbrains.programworld.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.programworld.api.exceptions.ResourceNotFoundException;
import ru.geekbrains.programworld.core.model.Article;
import ru.geekbrains.programworld.core.model.Image;
import ru.geekbrains.programworld.core.repositories.ImageRepository;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ImageService {
    @Value("${upload.path}")
    private String uploadPath;
    private final ImageRepository imageRepository;
    private final ArticleService articleService;

    @Transactional
    public void upload(MultipartFile file, Long articleID) {

        Article article = articleService.findById(articleID).orElseThrow(() -> new ResourceNotFoundException("Article id = " + articleID + " not found"));
        String imgFolder = article.getProgLanguage() + articleID;
        createImgFolder(file,imgFolder);

        try {
            byte[] data = file.getBytes();
            Path path = Paths.get(uploadPath + "/" + imgFolder + "/uploaded_" + file.getOriginalFilename());
            Files.write(path,data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image image = new Image();
        image.setFileName(file.getOriginalFilename());
        image.setFileType(file.getContentType());
        image.setFileFolder(uploadPath + "/" + imgFolder);
        image.setArticle(article);

        imageRepository.save(image);
    }

    private void createImgFolder(MultipartFile file, String imgFolder) {
        if (file != null) {
            File uploadDir = new File(uploadPath + "/" + imgFolder);
            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }
        }
    }

    public void deleteImage(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image id = " + id + " not found"));
        (new File(image.getFileFolder() +"/uploaded_" + image.getFileName())).delete();
        imageRepository.deleteById(id);
    }

}
