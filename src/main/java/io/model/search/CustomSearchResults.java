package io.model.search;

import lombok.Data;

import java.util.List;

@Data
public class CustomSearchResults {
    private String kind;
    private Url url;
    private Queries queries;
    private List<Promotion> promotions;
    private Context context;
    private List<Item> items;
}
