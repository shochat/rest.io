package io.controller;

import io.logic.google.Searcher;
import io.model.CustomSearchResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchEngineController {
    private final Searcher searcher;
    @Autowired
    public SearchEngineController(Searcher searcher) {
        this.searcher = searcher;
    }

    @GetMapping("/{query}")
    public CustomSearchResults querySearchEngine(@PathVariable String query) {
        return searcher.byQuery(query);
    }
}
