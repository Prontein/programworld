package ru.geekbrains.programworld.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.programworld.core.service.ImageService;
import ru.geekbrains.programworld.core.utils.Converter;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    private final Converter converter;

    @PostMapping("/{articleID}")
    public void saveImage(@PathVariable Long articleID, @RequestParam("ImageMedias") MultipartFile multipartFile) {
        imageService.upload(multipartFile,articleID);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
    }
}
