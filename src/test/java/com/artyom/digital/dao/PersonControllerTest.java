package com.artyom.digital.dao;

import com.artyom.digital.controller.PersonController;
import com.artyom.digital.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void check() throws Exception {
        mockMvc.perform(get("/hello"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void showForm() throws Exception {
        mockMvc.perform(get("/person/create"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getInfo() throws Exception {
        mockMvc.perform(get("/person/info/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Rollback
    public void createPerson() throws Exception{
        Person person = new Person();
        person.setFullName("");
        person.setAge("-34");

        String json = objectMapper.writeValueAsString(person);

        mockMvc.perform(
                post("/person/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(view().name("person/form"))
                .andDo(print())
                .andReturn();
    }
}
