package com.example.cullera.service;

import com.example.cullera.model.ResponseDataAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CulleraService {

    RestTemplate restTemplate = new RestTemplate();

    public ResponseDataAttributes getAttributest(String id) {

        ResponseDataAttributes forObject = restTemplate.getForObject("http://arest.me/api/sites/" + id, ResponseDataAttributes.class);

        return forObject;

    }
}
