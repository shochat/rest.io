package io.logic.google;

import io.model.search.CustomSearchResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GoogleSearcher implements Searcher{
    private final RestTemplate client;
    @Value("${google.search.engine.id}")
    private String googleSearchEnginId;

    @Value("${google.search.api.key}")
    private String googleSearchApiKey;
    @Value("${google.search.api.base.url}")
    private String googleSearchApiBaseUrl;
    @Autowired
    public GoogleSearcher(RestTemplate restTemplate) {
        this.client = restTemplate;
    }

    public CustomSearchResults byQuery(String query) {
        String url = String.format("%s?key=%s&cx=%s&q=%s&start=11", googleSearchApiBaseUrl, googleSearchApiKey, googleSearchEnginId, query);
        ResponseEntity<CustomSearchResults> response = client.getForEntity(url, CustomSearchResults.class);
        return response.getBody();
    }
}
