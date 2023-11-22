package com.henriquebarucco.luizalabs.dataprovider.gateways.file.factory.impl;

import com.henriquebarucco.luizalabs.dataprovider.gateways.file.factory.FileObject;
import com.henriquebarucco.luizalabs.dataprovider.gateways.file.factory.FileObjectFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FileObjectFactoryImplTest {

    @Test
    public void create() {
        FileObjectFactory fileObjectFactory = new FileObjectFactoryImpl();

        FileObject fileObject = new FileObject(1L, "Henrique", 2L, 1L, 50.0, LocalDate.of(2023, 11, 19));

        String line = "0000000001                                     Henrique00000000020000000001       50.0020231119";

        FileObject fileObjectCreated = fileObjectFactory.create(line);

        assertEquals(fileObject, fileObjectCreated);
    }
}