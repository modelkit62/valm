package com.example.cullera.integration;

import com.example.cullera.controller.CulleraController;
import com.example.cullera.model.RequestDataAttribute;
import com.example.cullera.model.ResponseDataAttributes;
import com.example.cullera.service.CulleraService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CulleraIntegrationTest {

    @MockBean
    private CulleraService culleraService;

    @Autowired
    private CulleraController culleraController;

    @Test
    public void getValue() throws Exception {

        RequestDataAttribute requestDataAttribute = RequestDataAttribute.builder().valor("z").build();
        ResponseDataAttributes responseDataAttributes = ResponseDataAttributes.builder().yearInscribed("1000").build();
        given(culleraService.getAttributest(any())).willReturn(responseDataAttributes);

        ResponseDataAttributes attributes = culleraController.getAttributes(requestDataAttribute, "2");
        // attributes.getYearInscribed(), equals("1000")
        assertThat(attributes.getYearInscribed()).isEqualTo("1000");

    }


}
