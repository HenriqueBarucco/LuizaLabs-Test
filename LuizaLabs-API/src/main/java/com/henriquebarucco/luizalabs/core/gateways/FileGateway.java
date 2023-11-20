package com.henriquebarucco.luizalabs.core.gateways;

import java.io.InputStream;

public interface FileGateway {
    void processFile(InputStream inputStream);
    void processLine(String line);
}
