package com.tumoji.tumoji.data.tag.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Author   : perqin
 * Date     : 16-12-5
 */

public class TagModel implements Serializable {
    @SerializedName("name")
    private String tagName;
    @SerializedName("description")
    private String description;

    public TagModel() {
        this("", "");
    }

    public TagModel(String tagName, String description) {
        this.tagName = tagName;
        this.description = description;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public TagModel withTagName(String tagName) {
        setTagName(tagName);
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TagModel withDescriptioin(String description) {
        setDescription(description);
        return this;
    }
}
