package com.darkdrunk.tesco.service;

import com.darkdrunk.tesco.model.Grocery;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class GroceryFetcher {

    public Grocery fetchByProductName(String name) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String json = fetchJsonByName(name);

        JsonNode actualObj = mapper.readTree(json);
        JsonNode actualProducts = actualObj.get("uk").get("ghs").get("products").get("results");
        JsonNode currentProduct = actualProducts.get(0);

        return mapper.treeToValue(currentProduct, Grocery.class);
    }

    private String fetchJsonByName(String name) {
        String url = String.format("https://dev.tescolabs.com/grocery/products/?query=%s&offset=0&limit=1", name);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Ocp-Apim-Subscription-Key", "cd1be1ec6a5648629eae3104ec21f8b9");

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return exchange.getBody();
    }

}
