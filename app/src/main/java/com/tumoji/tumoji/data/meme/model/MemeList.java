package com.tumoji.tumoji.data.meme.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by souler on 16-12-15.
 */
public class MemeList implements Serializable {
    private List<MemeModel> memes;

    public List<MemeModel> getMemes() {
        return memes;
    }

    public void setMemes(List<MemeModel> memes) {
        this.memes = memes;
    }

    @Override
    public String toString() {
        return "Memes:" + memes;
    }
}
