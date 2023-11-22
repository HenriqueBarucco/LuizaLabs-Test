package com.henriquebarucco.luizalabs.entrypoints.order;

import com.henriquebarucco.luizalabs.core.usecases.OrderInteractor;
import com.henriquebarucco.luizalabs.entrypoints.UserDTOMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @MockBean
    private OrderInteractor orderInteractor;
    @MockBean
    private UserDTOMapper userDTOMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testShouldReturn200InGetAllOrders() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                get("/v1/orders")
        ).andReturn().getResponse();

        assertEquals(200,response.getStatus());
    }

    @Test
    void testShouldReturn200InGetAllOrdersByDate() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                get("/v1/orders?startDate=2023-11-01&endDate=2023-12-01")
        ).andReturn().getResponse();

        assertEquals(200,response.getStatus());
    }

    @Test
    void testShouldReturn200InGetAllOrdersById() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                get("/v1/orders/1")
        ).andReturn().getResponse();

        assertEquals(200,response.getStatus());
    }
}