package io.logic.google;

import io.model.CustomSearchResults;

public interface Searcher {
    CustomSearchResults byQuery(String query);
}
