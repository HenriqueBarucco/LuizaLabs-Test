package com.henriquebarucco.luizalabs.dataprovider.gateways.file;

import com.henriquebarucco.luizalabs.core.gateways.FileGateway;
import com.henriquebarucco.luizalabs.core.usecases.UserInteractor;
import com.henriquebarucco.luizalabs.core.entity.User;
import com.henriquebarucco.luizalabs.dataprovider.gateways.file.factory.FileObject;
import com.henriquebarucco.luizalabs.dataprovider.gateways.file.factory.FileObjectFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReaderGateway implements FileGateway {
    private final UserInteractor userInteractor;
    private final FileObjectFactory fileObjectFactory;

    public FileReaderGateway(UserInteractor userInteractor, FileObjectFactory fileObjectFactory) {
        this.userInteractor = userInteractor;
        this.fileObjectFactory = fileObjectFactory;
    }


    @Override
    public void processFile(InputStream inputStream) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                this.processLine(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error processing the file", e);
        }
    }

    @Override
    public void processLine(String line) {
        FileObject object = fileObjectFactory.create(line);
        User user = userInteractor.getUser(object.getUserId(), object.getName());

        userInteractor.addOrderToUser(user, object.getOrderId(), object.getPurchaseDate(), object.getProductId(), object.getProductValue()); // TODO - Fix this

        System.out.println(object);
    }
}
