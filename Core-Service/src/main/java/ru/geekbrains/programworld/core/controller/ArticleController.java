package ru.geekbrains.programworld.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.geekbrains.programworld.api.dtos.ArticleDTO;
import ru.geekbrains.programworld.api.dtos.ArticleUploadResponse;
import ru.geekbrains.programworld.api.exceptions.ResourceNotFoundException;
import ru.geekbrains.programworld.core.exceptions.DataValidationException;
import ru.geekbrains.programworld.core.model.Article;
import ru.geekbrains.programworld.core.service.ArticleService;
import ru.geekbrains.programworld.core.utils.Converter;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final Converter converter;

    @GetMapping
    public List<ArticleDTO> getAllArticles() {
        return articleService.findAllArticles().stream().map(o -> converter.articleToDto(o)).collect(Collectors.toList());
    }
    
    @PostMapping("/upload/local/{articleName}")
    public void uploadArticle(@PathVariable String articleName,@RequestParam("ImageMedias")MultipartFile multipartFile) {
        articleService.uploadToLocal(multipartFile,articleName);
    }

    @PostMapping(value = "/upload/db", consumes = { "multipart/form-data" })
//    public ArticleUploadResponse uploadArticleToDb(@RequestParam("file")MultipartFile multipartFile) {
    public ArticleUploadResponse uploadArticleToDb(@RequestPart("file")MultipartFile multipartFile, @RequestPart("new_article") ArticleDTO articleDTO) {
        Article article = articleService.uploadToDb(multipartFile,articleDTO);
        ArticleUploadResponse articleUploadResponse = new ArticleUploadResponse();
//        if (article != null) {
            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/v1/articles/")
                    .path(String.valueOf(article.getId()))
                    .toUriString();
            articleUploadResponse.setAuthor(articleDTO.getAuthor());
            articleUploadResponse.setTitle(articleDTO.getTitle());
            articleUploadResponse.setProgLanguage(articleDTO.getProgLanguage());
            articleUploadResponse.setDownloadUri(downloadUrl);
            articleUploadResponse.setArticleId(article.getId());
            articleUploadResponse.setFileType(article.getFileType());
            articleUploadResponse.setUploadStatus(true);
            articleUploadResponse.setMessage("File Upload Successfully");
            return articleUploadResponse;
//        }
//        articleUploadResponse.setMessage("Upload error!");
//        return articleUploadResponse;
    }

    @PostMapping("/edit/{id}")
    public ArticleUploadResponse editingArticleInDb(@PathVariable Long id,@RequestParam("fileEdit")MultipartFile multipartFile) {
        Article article = articleService.editingInDb(id,multipartFile);
        ArticleUploadResponse articleUploadResponse = new ArticleUploadResponse();
//        if (article != null) {
        String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/articles/")
                .path(String.valueOf(article.getId()))
                .toUriString();
        articleUploadResponse.setDownloadUri(downloadUrl);
        articleUploadResponse.setArticleId(article.getId());
        articleUploadResponse.setFileType(article.getFileType());
        articleUploadResponse.setUploadStatus(true);
        articleUploadResponse.setMessage("File Editing Successfully");
        return articleUploadResponse;
//        }
//        articleUploadResponse.setMessage("Upload error!");
//        return articleUploadResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Article article = articleService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article id = " + id + " not found"));

       return ResponseEntity.ok()
               .contentType(MediaType.parseMediaType(article.getFileType()))
               .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + article.getFileName())
               .body(article.getContent());
//               .body(new ByteArrayResource(article.getContent()));
    }
}
