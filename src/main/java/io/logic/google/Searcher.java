package io.logic.google;

import io.model.search.CustomSearchResults;

public interface Searcher {
    CustomSearchResults byQuery(String query);
}
