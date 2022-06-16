package ru.geekbrains.programworld.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.geekbrains.programworld.api.dtos.ArticleDTO;
import ru.geekbrains.programworld.api.dtos.ArticleUploadResponse;
import ru.geekbrains.programworld.api.exceptions.ResourceNotFoundException;
import ru.geekbrains.programworld.core.model.Article;
import ru.geekbrains.programworld.core.service.ArticleService;
import ru.geekbrains.programworld.core.utils.Converter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final Converter converter;

    @GetMapping
    public List<ArticleDTO> getAllArticles(@RequestParam(name="program_language", required = false) String program_language, @RequestParam(name="view_all_for_user", required = false) boolean getArticlesForAllLanguageType) {
        if (program_language != null || getArticlesForAllLanguageType) return articleService.findAllArticlesForClient(program_language).stream().map(a -> converter.articleToDtoForClient(a,program_language)).collect(Collectors.toList());
        return articleService.findAllArticles().stream().map(o -> converter.articleToDto(o)).collect(Collectors.toList());
    }

    @PostMapping(value = "/upload/db", consumes = { "multipart/form-data" })
    public ArticleUploadResponse uploadArticleToDb(@RequestPart("file")MultipartFile multipartFile, @RequestPart("new_article") ArticleDTO articleDTO) {
        Article article = articleService.uploadToDb(multipartFile,articleDTO);
        ArticleUploadResponse articleUploadResponse = new ArticleUploadResponse();

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
    }

    @PutMapping("/{id}")
    public ArticleUploadResponse editingArticleInDb(@PathVariable Long id,@RequestPart("fileEdit")MultipartFile multipartFile, @RequestPart("selectArticle") ArticleDTO articleDTO) {
        Article article = articleService.editingInDb(id,multipartFile,articleDTO);
        ArticleUploadResponse articleUploadResponse = new ArticleUploadResponse();

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
        articleUploadResponse.setMessage("File Editing Successfully");
        return articleUploadResponse;
    }

    @GetMapping("/{program_language}")
    public ResponseEntity findById(@RequestParam(name="programId", required = false) Long programId) {
        Article article = articleService.findById(programId).orElseThrow(() -> new ResourceNotFoundException("Article id = " + programId + " not found"));

        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(article.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + article.getFileName())
                .body(article.getContent());
    }

}
