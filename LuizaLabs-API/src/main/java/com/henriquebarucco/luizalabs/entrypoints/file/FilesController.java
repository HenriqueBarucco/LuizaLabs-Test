package com.henriquebarucco.luizalabs.entrypoints.file;

import com.henriquebarucco.luizalabs.core.usecases.FileReaderInteractor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("v1/files")
public class FilesController {
    private final FileReaderInteractor fileReaderInteractor;

    public FilesController(FileReaderInteractor fileReaderInteractor) {
        this.fileReaderInteractor = fileReaderInteractor;
    }

    @PostMapping
    public ResponseEntity<String> processFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Please upload a file");
            }

            System.out.println(file.getOriginalFilename());

            fileReaderInteractor.processFile(file.getInputStream());

            return ResponseEntity.ok("File processed successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the file");
        }
    }
}