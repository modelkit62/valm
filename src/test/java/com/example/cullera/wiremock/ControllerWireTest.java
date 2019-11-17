package com.example.cullera.wiremock;

import com.example.cullera.controller.CulleraController;
import com.example.cullera.model.RequestDataAttribute;
import com.example.cullera.service.CulleraService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CulleraController.class)
public class ControllerWireTest {

    @MockBean
    private CulleraService culleraService;

    @Rule
    public WireMockRule rule = new WireMockRule(8089);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void happyFlow() throws Exception {

        stubFor(any(anyUrl()).willReturn(okJson("1000")));

        RequestDataAttribute requestDataAttribute = RequestDataAttribute.builder().valor("2").build();

        String contentAsString = mockMvc.perform(post("/cullera/{id}", 2)
                .content(objectMapper.writeValueAsString(requestDataAttribute))
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(contentAsString);

    }


}
