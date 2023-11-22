package com.henriquebarucco.luizalabs.entrypoints.file.mapper;

import com.henriquebarucco.luizalabs.core.entity.ProcessedFiles;
import com.henriquebarucco.luizalabs.entrypoints.file.dto.ProcessedFilesResponse;

public class FilesDTOMapper {

    public ProcessedFilesResponse toProcessedFilesResponse(ProcessedFiles processedFiles) {
        return new ProcessedFilesResponse(
                processedFiles.getMessage(),
                processedFiles.getTotalProcessed(),
                processedFiles.getErrors()
        );
    }
}
