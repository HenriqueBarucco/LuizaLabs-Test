package com.henriquebarucco.luizalabs.entrypoints.file;

import com.henriquebarucco.luizalabs.core.entity.ProcessedFiles;
import com.henriquebarucco.luizalabs.core.exceptions.FileReaderException;
import com.henriquebarucco.luizalabs.core.usecases.FileReaderInteractor;
import com.henriquebarucco.luizalabs.entrypoints.file.dto.ProcessedFilesResponse;
import com.henriquebarucco.luizalabs.entrypoints.file.mapper.FilesDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "File Controller", description = "Operações relacionadas aos arquivos.")
@RestController
@RequestMapping("v1/files")
public class FilesController {
    private final FileReaderInteractor fileReaderInteractor;
    private final FilesDTOMapper filesDTOMapper;

    public FilesController(FileReaderInteractor fileReaderInteractor, FilesDTOMapper filesDTOMapper) {
        this.fileReaderInteractor = fileReaderInteractor;
        this.filesDTOMapper = filesDTOMapper;
    }

    @Operation(summary = "Send file.", description = "Popula o banco de dados com o arquivo base.")
    @RequestMapping(method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<ProcessedFilesResponse> processFile(
            @RequestPart("file") MultipartFile file
    ) {
        try {
            if (file.isEmpty()) {
                throw new FileReaderException("Please upload a file");
            }

            ProcessedFiles processedFiles = fileReaderInteractor.processFile(file.getInputStream());
            ProcessedFilesResponse response = filesDTOMapper.toProcessedFilesResponse(processedFiles);

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            throw new FileReaderException("Error processing file", e.getCause());
        }
    }
}