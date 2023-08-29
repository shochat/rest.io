package io.model;

import lombok.Data;

@Data
public class Request {
 private String title;
 private String totalResults;
 private String searchTerms;
 private float count;
 private float startIndex;
 private String inputEncoding;
 private String outputEncoding;
 private String safe;
 private String cx;
}