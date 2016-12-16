package com.tumoji.tumoji.data.tag.model;


import java.io.Serializable;
import java.util.List;

/**
 * Created by souler on 16-12-15.
 */
public class TagList implements Serializable {
    private List<TagModel> tags;

    public List<TagModel> getTags() {
        return tags;
    }

    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Tags:" + tags;
    }
}
