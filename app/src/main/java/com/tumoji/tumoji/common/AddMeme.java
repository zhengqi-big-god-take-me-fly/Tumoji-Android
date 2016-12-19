package com.tumoji.tumoji.common;

import com.tumoji.tumoji.data.meme.model.MemeModel;

import java.util.ArrayList;

/**
 * Created by Cindy on 2016/12/19.
 */

public class AddMeme {
    ArrayList<MemeModel> memeModels = new ArrayList<>();
    public ArrayList<MemeModel> addMeme() {
        String[] title = new String[] {"送狗粮", "重启试试", "关你屁事", "管他呢", "你喜欢犯花痴的我吗", "恋爱的酸臭味", "被子不让我起床", "除了我谁要你" };

        for (int i = 41; i <= 48; i++) {
            MemeModel memeModel = new MemeModel()
                    .withMemeId("jq" + i)
                    .withAuthorId("1")
                    .withImageUrl("http://v.perqin.com/" + i + ".jpg")
                    .withTitle(title[i-41])
                    .withLiked(true)
                    .withLikeCount(30+i)
                    .withReported(false)
                    .withReportCount(2);

            memeModels.add(memeModel);
        }
        for (int i = 1; i <= 40; i++) {
            MemeModel memeModel = new MemeModel()
                    .withMemeId("jq" + i)
                    .withAuthorId("1")
                    .withImageUrl("http://v.perqin.com/" + i + ".jpg")
                    .withTitle("title:" + i)
                    .withLiked(true)
                    .withLikeCount(20)
                    .withReported(false)
                    .withReportCount(4);
            memeModels.add(memeModel);
        }
        for (int i = 49; i <= 50; i++) {
            MemeModel memeModel = new MemeModel()
                    .withMemeId("jq" + i)
                    .withAuthorId("1")
                    .withImageUrl("http://v.perqin.com/" + i + ".gif")
                    .withTitle("title:" + i)
                    .withLiked(false)
                    .withLikeCount(40)
                    .withReported(true)
                    .withReportCount(7);
            memeModels.add(memeModel);
        }
        return memeModels;


    }

    public ArrayList<MemeModel> AddMemeList() {
        ArrayList<MemeModel> memeModels1 = new ArrayList<>();
        for (int i = 33; i <= 48; i++) {
            memeModels1.add(memeModels.get(i));
        }
        return memeModels1;
    }
}
