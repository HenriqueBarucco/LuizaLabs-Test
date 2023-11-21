package com.henriquebarucco.luizalabs.dataprovider.gateways.file;

import com.henriquebarucco.luizalabs.core.entity.Order;
import com.henriquebarucco.luizalabs.core.entity.Product;
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
        Order order = orderInteractor.createOrder(user, new Order(object.getOrderId(), object.getPurchaseDate()));

        orderInteractor.addProductToOrder(order, new Product(object.getProductId(), object.getProductValue()));

        System.out.println(object);
    }
}
