package ru.job4j.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UrlDTO {
    @JsonProperty("url")
    private String url;
    @JsonProperty("redirectType")
    private int redirectType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(int redirectType) {
        this.redirectType = redirectType;
    }
}
