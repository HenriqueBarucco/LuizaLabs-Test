package com.henriquebarucco.luizalabs.dataprovider.gateways.file.factory;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FileObjectTest {

    @Test
    public void testHashCode() {
        FileObject fileObject = new FileObject(1L, "Henrique", 1L, 1L, 1.0, LocalDate.now());
        FileObject fileObject2 = new FileObject(2L, "Barucco", 2L, 1L, 1.0, LocalDate.now());

        assertNotEquals(fileObject.hashCode(), fileObject2.hashCode());
    }

    @Test
    public void testEquals() {
        FileObject fileObject = new FileObject(1L, "Henrique", 1L, 1L, 1.0, LocalDate.now());
        FileObject fileObject2 = new FileObject(2L, "Barucco", 2L, 1L, 1.0, LocalDate.now());

        assertNotEquals(fileObject, fileObject2);
    }

    @Test
    public void testToString() {
        FileObject fileObject = new FileObject(1L, "Henrique", 1L, 1L, 1.0, LocalDate.now());
        String expected = "DataObject{" +
                "userId=" + 1 +
                ", name='" + "Henrique" + '\'' +
                ", orderId=" + 1 +
                ", productId=" + 1 +
                ", productValue=" + 1.0 +
                ", purchaseDate=" + LocalDate.now() +
                '}';

        assertEquals(expected, fileObject.toString());
    }

}