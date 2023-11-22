package com.henriquebarucco.luizalabs.entrypoints.file;

import com.henriquebarucco.luizalabs.core.usecases.FileReaderInteractor;
import com.henriquebarucco.luizalabs.entrypoints.file.mapper.FilesDTOMapper;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class FilesControllerTest {

    @MockBean
    private FileReaderInteractor fileReaderInteractor;
    @MockBean
    private FilesDTOMapper filesDTOMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testShouldReturn200InProcessFile() {
        byte[] fileContent = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308".getBytes();

        try {
            mockMvc.perform(
                            MockMvcRequestBuilders.multipart("/v1/files")
                                    .file(new MockMultipartFile("file", "file.txt", MediaType.TEXT_PLAIN_VALUE, fileContent))
                    ).andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn().getResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testShouldReturn400InProcessFile() {
        byte[] fileContent = "".getBytes();

        try {
            mockMvc.perform(
                            MockMvcRequestBuilders.multipart("/v1/files")
                                    .file(new MockMultipartFile("file", "file.txt", MediaType.TEXT_PLAIN_VALUE, fileContent))
                    ).andExpect(MockMvcResultMatchers.status().is4xxClientError())
                    .andReturn().getResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}