package com.tumoji.tumoji.data.meme.model;

/**
 * Author   : perqin
 * Date     : 16-12-5
 */

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Stand for a meme resource.
 */
public class MemeModel implements Serializable {
    // Meme id (for API server)
    @SerializedName("id")
    private String memeId;
    // Meme displayed title
    @SerializedName("title")
    private String title;
    // Author user id of this meme
    // NOTE: May be replaced with UserModel someday
    @SerializedName("authorId")
    private String authorId;
    // Meme image download URL
    @SerializedName("image")
    @Deprecated
    private String imageUrl;
    // Contain the path to the downloaded meme, or the download URL of un-downloaded meme
    private Uri memeUri;
    private int memeWidth;
    private int memeHeight;
    private boolean liked;
    private boolean reported;
    private int likeCount;
    private int reportCount;
    private boolean downloaded;

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

    @Deprecated
    public String getImageUrl() {
        return imageUrl;
    }

    @Deprecated
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Deprecated
    public MemeModel withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public MemeModel withLiked(boolean liked) {
        setLiked(liked);
        return this;
    }

    public boolean isReported() {
        return reported;
    }

    public void setReported(boolean reported) {
        this.reported = reported;
    }

    public MemeModel withReported(boolean reported) {
        setReported(reported);
        return this;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public MemeModel withLikeCount(int likeCount) {
        setLikeCount(likeCount);
        return this;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public MemeModel withReportCount(int reportCount) {
        setReportCount(reportCount);
        return this;
    }

    public boolean isDownloaded() {
        return downloaded;
    }

    public void setDownloaded(boolean downloaded) {
        this.downloaded = downloaded;
    }

    public MemeModel withSaved(boolean saved) {
        setDownloaded(saved);
        return this;
    }

    public Uri getMemeUri() {
        return memeUri;
    }

    public void setMemeUri(Uri memeUri) {
        this.memeUri = memeUri;
    }

    public int getMemeWidth() {
        return memeWidth;
    }

    public void setMemeWidth(int memeWidth) {
        this.memeWidth = memeWidth;
    }

    public int getMemeHeight() {
        return memeHeight;
    }

    public void setMemeHeight(int memeHeight) {
        this.memeHeight = memeHeight;
    }
}
