package com.shopping.admin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.admin.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.net.URI;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminBS implements AdminBSI{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public int addNewItem(Item item) throws Exception {
        //1 if added, 0 if not added
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(item);
        HttpEntity <String> request = new HttpEntity <String> (jsonStr, headers);
        URI uri = new URI("http://localhost:58007/item/add");
        ResponseEntity<String> ret = restTemplate.postForEntity(uri, request, String.class);
        return ret != null ? 1:0;
    }

    @Override
    public int removeItemById(Long itemId) throws Exception {
        Map< String, Long > params = new HashMap< String, Long >();
        params.put("id",itemId);
        restTemplate.delete("http://localhost:58007/item/delete/id/{id}", params);
        return 1;
    }

    @Override
    public int updateItem(Item item) throws Exception {
        restTemplate.put("http://localhost:58007/item/update", item);
        return 1;
    }
}
