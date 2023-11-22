package com.henriquebarucco.luizalabs.entrypoints.file.dto;

import java.util.List;

public record ProcessedFilesResponse(
        String message,
        Integer totalProcessed,
        List<String> errors
) {
}
