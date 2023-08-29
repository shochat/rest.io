package io.model;

import lombok.Data;

import java.util.List;

@Data
public class Item {
 private String kind;
 private String title;
 private String htmlTitle;
 private String link;
 private String displayLink;
 private String snippet;
 private String htmlSnippet;
 private String cacheId;
 private String formattedUrl;
 private String htmlFormattedUrl;
 private PageMap Pagemap;
 private String mime;
 private String fileFormat;
 private List<Label> labels;
}