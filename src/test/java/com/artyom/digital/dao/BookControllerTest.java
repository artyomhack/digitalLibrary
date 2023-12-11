package com.artyom.digital.dao;

import com.artyom.digital.controller.PersonController;
import com.artyom.digital.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonController personController;

    @Test
    public void check() throws Exception {
        mockMvc.perform(get("/book/create"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getInfo() throws Exception {
        mockMvc.perform(get("/book/info/12"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
