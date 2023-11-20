package com.henriquebarucco.luizalabs.entrypoints.file;

import com.henriquebarucco.luizalabs.core.usecases.FileReaderInteractor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "File Controller", description = "Operações relacionadas aos arquivos.")
@RestController
@RequestMapping("v1/files")
public class FilesController {
    private final FileReaderInteractor fileReaderInteractor;

    public FilesController(FileReaderInteractor fileReaderInteractor) {
        this.fileReaderInteractor = fileReaderInteractor;
    }

    @Operation(summary = "Send file.", description = "Popula o banco de dados com o arquivo base.")
    @RequestMapping(method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<String> processFile(
            @RequestPart("file") MultipartFile file
    ) {
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