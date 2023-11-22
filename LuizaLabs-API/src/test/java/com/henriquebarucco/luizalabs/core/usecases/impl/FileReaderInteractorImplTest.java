package com.henriquebarucco.luizalabs.core.usecases.impl;

import com.henriquebarucco.luizalabs.core.gateways.FileGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class FileReaderInteractorImplTest {

    @InjectMocks
    private FileReaderInteractorImpl fileReaderInteractorImpl;

    @Mock
    private FileGateway fileGateway;

    @Mock
    private InputStream inputStream;

    @Test
    public void testShouldProcessFile() {
        fileReaderInteractorImpl.processFile(inputStream);

        then(fileGateway).should().processFile(inputStream);
    }
}