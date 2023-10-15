package io.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MetaTag{
    @JsonProperty("og:postal_code")
    private String ogPostalCode;
    @JsonProperty("og:image")
    private String ogImage;
    @JsonProperty("og:type")
    private String ogType;
    @JsonProperty("twitter:card")
    private String twitterCard;
    @JsonProperty("twitter:title")
    private String twitterTitle;
    @JsonProperty("og:site_name")
    private String ogSiteName;
    @JsonProperty("og:image:url")
    private String ogImageUrl;
    private String handheldfriendly;
    @JsonProperty("twitter:url")
    private String twitterUrl;
    @JsonProperty("og:locality")
    private String ogLocality;
    @JsonProperty("og:title")
    private String ogTitle;
    @JsonProperty("og:country_name")
    private String ogCountryName;
    @JsonProperty("og:description")
    private String ogDescription;
    @JsonProperty("twitter:image")
    private String twitterImage;
    @JsonProperty("og:phone_number")
    private String ogPhoneNumber;
    @JsonProperty("twitter:site")
    private String twitterSite;
    private String viewport;
    @JsonProperty("twitter:description")
    private String twitterDescription;
    private String mobileoptimized;
    @JsonProperty("og:url")
    private String ogUrl;
    @JsonProperty("og:street_address;")
    private String ogStreetAddress;
}