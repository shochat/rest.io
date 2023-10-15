package io.model.search;

import lombok.Data;

@Data
public class SearchInformation {
 private float searchTime;
 private String formattedSearchTime;
 private String totalResults;
 private String formattedTotalResults;
}
