package com.henriquebarucco.luizalabs.dataprovider.gateways.file;

import com.henriquebarucco.luizalabs.core.entity.Order;
import com.henriquebarucco.luizalabs.core.entity.ProcessedFiles;
import com.henriquebarucco.luizalabs.core.entity.Product;
import com.henriquebarucco.luizalabs.core.entity.User;
import com.henriquebarucco.luizalabs.core.exceptions.FileProcessException;
import com.henriquebarucco.luizalabs.core.usecases.OrderInteractor;
import com.henriquebarucco.luizalabs.core.usecases.UserInteractor;
import com.henriquebarucco.luizalabs.dataprovider.gateways.file.factory.FileObject;
import com.henriquebarucco.luizalabs.dataprovider.gateways.file.factory.FileObjectFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileReaderGatewayTest {

    @InjectMocks
    private FileReaderGateway fileReaderGateway;
    @Mock
    private UserInteractor userInteractor;
    @Mock
    private OrderInteractor orderInteractor;
    @Mock
    private FileObjectFactory fileObjectFactory;

    @Test
    public void testShouldProcessLine() {
        String line = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";

        FileObject fileObject = new FileObject(70L, "Palmer Prosacco", 75L, 3L, 1836.74, LocalDate.of(2021, 3, 8));
        User user = new User(70L, "Palmer Prosacco");
        Order order = new Order(75L, LocalDate.of(2021, 3, 8));

        when(fileObjectFactory.create(line)).thenReturn(fileObject);
        when(userInteractor.getUser(anyLong(), anyString())).thenReturn(user);
        when(orderInteractor.createOrder(any(User.class), any(Order.class))).thenReturn(order);

        fileReaderGateway.processLine(line);

        verify(fileObjectFactory, times(1)).create(line);
        verify(userInteractor, times(1)).getUser(anyLong(), anyString());
        verify(orderInteractor, times(1)).createOrder(any(User.class), any(Order.class));
        verify(orderInteractor, times(1)).addProductToOrder(any(Order.class), any(Product.class));
    }

    @Test
    public void testProcessFile_SuccessfulProcessing() {
        String fileContent = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));

        FileObject fileObject = new FileObject(70L, "Palmer Prosacco", 75L, 3L, 1836.74, LocalDate.of(2021, 3, 8));
        User user = new User(70L, "Palmer Prosacco");
        Order order = new Order(75L, LocalDate.of(2021, 3, 8));

        when(fileObjectFactory.create(anyString())).thenReturn(fileObject);
        when(userInteractor.getUser(anyLong(), anyString())).thenReturn(user);
        when(orderInteractor.createOrder(any(User.class), any(Order.class))).thenReturn(order);

        fileReaderGateway.processFile(inputStream);

        verify(fileObjectFactory, times(1)).create(anyString());
        verify(userInteractor, times(1)).getUser(anyLong(), anyString());
        verify(orderInteractor, times(1)).createOrder(any(User.class), any(Order.class));
    }

    @Test
    public void testShouldProcessFileWithException() {
        InputStream inputStream = new ByteArrayInputStream("Invalid file content".getBytes(StandardCharsets.UTF_8));

        when(fileObjectFactory.create(anyString())).thenThrow(new FileProcessException("Invalid line", null));

        ProcessedFiles processedFiles = fileReaderGateway.processFile(inputStream);

        assertNotEquals(0, processedFiles.getErrors().size());
    }
}