package com.tumoji.tumoji.network.body;

/**
 * Author: perqin
 * Date  : 1/7/17
 */

public class PostExpressionsReq {
    public String title;
    public String image;

    public PostExpressionsReq(String title, String image) {
        this.title = title;
        this.image = image;
    }
}
