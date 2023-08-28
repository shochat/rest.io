package io.logic.google;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GoogleSearcher {
    @Autowired
    private RestTemplate client;
    public String searchByQuery(String query) {
        ResponseEntity<String> response = client.getForEntity("https://www.googleapis.com/customsearch/v1?key=AIzaSyA9kyfTrlb_PKYxGxRQoXW9NH6Wiy5eeIE&q=what%20are%20the%20uses%20of%20renewable%20energy&cx=d59111001aa984af3&start=11", String.class);
        String body =  response.getBody();
        return body;
    }
}
