package com.henriquebarucco.luizalabs.dataprovider.gateways.file;

import com.henriquebarucco.luizalabs.core.entity.Order;
import com.henriquebarucco.luizalabs.core.entity.ProcessedFiles;
import com.henriquebarucco.luizalabs.core.entity.Product;
import com.henriquebarucco.luizalabs.core.exceptions.FileProcessException;
import com.henriquebarucco.luizalabs.core.exceptions.FileReaderException;
import com.henriquebarucco.luizalabs.core.gateways.FileGateway;
import com.henriquebarucco.luizalabs.core.usecases.OrderInteractor;
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
    private final OrderInteractor orderInteractor;
    private final FileObjectFactory fileObjectFactory;

    public FileReaderGateway(UserInteractor userInteractor, OrderInteractor orderInteractor, FileObjectFactory fileObjectFactory) {
        this.userInteractor = userInteractor;
        this.orderInteractor = orderInteractor;
        this.fileObjectFactory = fileObjectFactory;
    }


    @Override
    public ProcessedFiles processFile(InputStream inputStream) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            ProcessedFiles processedFiles = new ProcessedFiles();

            while ((line = br.readLine()) != null) {
                try {
                    this.processLine(line);
                    processedFiles.addTotalProcessed();
                } catch (FileProcessException e) {
                    processedFiles.addError(e.getMessage());
                }
            }

            return processedFiles;
        } catch (IOException e) {
            throw new FileReaderException("Error processing the file", e.getCause());
        }
    }

    @Override
    public void processLine(String line) throws FileProcessException {
        try {
            FileObject object = fileObjectFactory.create(line);

            User user = userInteractor.getUser(object.getUserId(), object.getName());
            Order order = orderInteractor.createOrder(user, new Order(object.getOrderId(), object.getPurchaseDate()));

            orderInteractor.addProductToOrder(order, new Product(object.getProductId(), object.getProductValue()));
        } catch (Exception e) {
            throw new FileProcessException("Error processing the line: " + line, e.getCause());
        }
    }
}
