package com.walmartlabstest.babypink.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by srikaram on 06-Feb-18.
 */

public class Categories {

    private String displayName;
    @JsonProperty("@type")
    private String type;
    private List<Category> contents;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Category> getContents() {
        return contents;
    }

    public void setContents(List<Category> contents) {
        this.contents = contents;
    }
}
