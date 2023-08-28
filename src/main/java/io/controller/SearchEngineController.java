package io.controller;

import io.logic.google.GoogleSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchEngineController {
    @Autowired
    GoogleSearcher googleSearcher;
    @GetMapping("/{query}")
    public String querySearchEngine(@PathVariable String query) {
        String result = googleSearcher.searchByQuery(query);
        return result;
    }

}
