package com.henriquebarucco.luizalabs.entrypoints.user;

import com.henriquebarucco.luizalabs.core.usecases.UserInteractor;
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
class UserControllerTest {

    @MockBean
    private UserInteractor userInteractor;
    @MockBean
    private UserDTOMapper userDTOMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testShouldReturn200InGetAllUsers() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                get("/v1/users")
        ).andReturn().getResponse();

        assertEquals(200,response.getStatus());
    }

    @Test
    void testShouldReturn200InGetAllUsersByDate() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                get("/v1/users?startDate=2023-11-01&endDate=2023-12-01")
        ).andReturn().getResponse();

        assertEquals(200,response.getStatus());
    }

    @Test
    void testShouldReturn200InGetAllUsersById() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                get("/v1/users/1")
        ).andReturn().getResponse();

        assertEquals(200,response.getStatus());
    }
}