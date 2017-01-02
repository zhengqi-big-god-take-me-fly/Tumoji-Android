package com.tumoji.tumoji.network.body;

/**
 * Author: perqin
 * Date  : 1/2/17
 */

public class PutUserAvatarReq {
    private String avatar;

    public PutUserAvatarReq(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
