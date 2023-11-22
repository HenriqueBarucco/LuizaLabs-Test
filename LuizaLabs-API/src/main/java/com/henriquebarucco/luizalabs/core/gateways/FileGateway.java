package com.henriquebarucco.luizalabs.core.gateways;

import com.henriquebarucco.luizalabs.core.entity.ProcessedFiles;

import java.io.InputStream;

public interface FileGateway {
    ProcessedFiles processFile(InputStream inputStream);
    void processLine(String line);
}
