package com.tumoji.tumoji.data.meme.model;

/**
 * Author   : perqin
 * Date     : 16-12-5
 */

/**
 * Stand for a meme resource.
 */
public class MemeModel {
    // Meme id (for API server)
    private String memeId;
    // Meme displayed title
    private String title;
    // Author user id of this meme
    // NOTE: May be replaced with UserModel someday
    private String authorId;
    // Meme image download URL
    private String imageUrl;

    public MemeModel() {
        this("", "", "", "");
    }

    public MemeModel(String memeId, String title, String authorId, String imageUrl) {
        this.memeId = memeId;
        this.title = title;
        this.authorId = authorId;
        this.imageUrl = imageUrl;
    }

    public String getMemeId() {
        return memeId;
    }

    public void setMemeId(String memeId) {
        this.memeId = memeId;
    }

    public MemeModel withMemeId(String memeId) {
        this.memeId = memeId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MemeModel withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public MemeModel withAuthorId(String authorId) {
        this.authorId = authorId;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public MemeModel withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
