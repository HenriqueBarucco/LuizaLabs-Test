package com.henriquebarucco.luizalabs.core.usecases.impl;

import com.henriquebarucco.luizalabs.core.gateways.FileGateway;
import com.henriquebarucco.luizalabs.core.usecases.FileReaderInteractor;

import java.io.InputStream;

public class FileReaderInteractorImpl implements FileReaderInteractor {
    private final FileGateway fileGateway;

    public FileReaderInteractorImpl(FileGateway fileGateway) {
        this.fileGateway = fileGateway;
    }

    public void processFile(InputStream inputStream) {
        fileGateway.processFile(inputStream);
    }
}
