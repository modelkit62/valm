package com.example.cullera.controller;


import com.example.cullera.model.RequestDataAttribute;
import com.example.cullera.service.CulleraService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CulleraController.class)
public class ControlerTest {

    @MockBean
    private CulleraService culleraService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void contextLoad(){}

    @Test
    public void successfulFlow() throws Exception {

        RequestDataAttribute requestDataAttribute = RequestDataAttribute.builder().valor("2").build();

        String contentAsString = mockMvc.perform(post("/cullera/{id}", 2)
                .content(objectMapper.writeValueAsString(requestDataAttribute))
                .contentType("application/json"))
                .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();

        System.out.println(contentAsString + " VOID!");

        ArgumentCaptor<RequestDataAttribute> captor = ArgumentCaptor.forClass(RequestDataAttribute.class);
        verify(culleraService, times(1)).getAttributest("2");
        // assertThat(captor.getValue().getValor()).isEqualTo("2");

    }


}
