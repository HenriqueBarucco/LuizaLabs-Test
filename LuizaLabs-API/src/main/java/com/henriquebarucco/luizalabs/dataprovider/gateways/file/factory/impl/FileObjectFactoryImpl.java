package com.henriquebarucco.luizalabs.dataprovider.gateways.file.factory.impl;

import com.henriquebarucco.luizalabs.dataprovider.gateways.file.factory.FileObject;
import com.henriquebarucco.luizalabs.dataprovider.gateways.file.factory.FileObjectFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileObjectFactoryImpl implements FileObjectFactory {

    @Override
    public FileObject create(String line) {
        Long userId = Long.parseLong(line.substring(0, 10).trim());
        String name = line.substring(10, 55).trim();
        Long orderId = Long.parseLong(line.substring(55, 65).trim());
        Long productId = Long.parseLong(line.substring(65, 75).trim());
        Double productValue = Double.parseDouble(line.substring(75, 87).trim());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate purchaseDate = LocalDate.parse(line.substring(87).trim(), formatter);

        return new FileObject(userId, name, orderId, productId, productValue, purchaseDate);
    }
}
