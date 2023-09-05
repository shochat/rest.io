package io.model.search;

import lombok.Data;

import java.util.List;

@Data
public class Promotion {
    private String title;
    private String htmlTitle;
    private String link;
    private String displayLink;
    private List<BodyLine> bodyLines;
    private PromotionImage image;
}
