package com.myweb.basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@RestController
public class UploadFileController {


    @PostMapping("/product/upload_image")
    public ResponseEntity<String> uploadProductImage(@RequestParam("file") MultipartFile file) {
        final String UPLOAD_DIR = "src/main/resources/static/uploads/";
        if (file.isEmpty()) {
            return new ResponseEntity<>("Missing file", HttpStatus.BAD_REQUEST);
        }
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);

            // If folder is not exist
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String formatDate = formatDate();
            String newFileName = formatDate + "_" + file.getOriginalFilename();
//            String newFileName = formatDate.concat(Objects.requireNonNull(file.getOriginalFilename()));

            // Upload file
            Path filePath = uploadPath.resolve(newFileName);
            Files.copy(file.getInputStream(), filePath);
            return new ResponseEntity<>(filePath.toUri().toString(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public String formatDate() {
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");

        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("After formatting: " + formattedDate);
        return formattedDate;
    }


}