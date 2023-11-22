package com.henriquebarucco.luizalabs.core.usecases;

import com.henriquebarucco.luizalabs.core.entity.ProcessedFiles;

import java.io.InputStream;

public interface FileReaderInteractor {
    ProcessedFiles processFile(InputStream inputStream);
}
