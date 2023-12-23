package com.omnia.salusbackend.controllers;


import com.omnia.salusbackend.service.analyser.TriggerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@Slf4j
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    final private TriggerService triggerService;

    @PostMapping("/upload/")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            var path = "home/salus/java/files";
            File destFile = new File(path + file.getOriginalFilename());
            file.transferTo(destFile);
            triggerService.update(path + file.getOriginalFilename());

            return ResponseEntity.ok(file.getOriginalFilename());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(400).build();
        }
    }

}
