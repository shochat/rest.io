package io.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PageMap {
    @JsonProperty("cse_thumbnail")
    private List<CseThumbnail> cseThumbnail;
    private WebPage webPage;
    private List<MetaTag> metaTags;
    private CseImage cseImage;


}
