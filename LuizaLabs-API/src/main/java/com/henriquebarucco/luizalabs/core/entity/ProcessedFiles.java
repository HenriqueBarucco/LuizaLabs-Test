package com.henriquebarucco.luizalabs.core.entity;

import java.util.ArrayList;
import java.util.List;

public class ProcessedFiles {
    private String message;
    private Integer totalProcessed;
    private List<String> errors;

    public ProcessedFiles() {
        this.message = "File processed successfully";
        this.totalProcessed = 0;
        this.errors = new ArrayList<>();
    }

    public String getMessage() {
        return message;
    }

    public Integer getTotalProcessed() {
        return totalProcessed;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public void addTotalProcessed() {
        this.totalProcessed++;
    }
}
