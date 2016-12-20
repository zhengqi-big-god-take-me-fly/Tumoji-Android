package com.tumoji.tumoji.common;

import com.tumoji.tumoji.data.meme.model.MemeModel;

import java.util.ArrayList;

/**
 * Author: Cindy
 * Date  : 2016/12/19
 */

public class DemoMemeStore {
    public static ArrayList<MemeModel> getAllMemes() {
        ArrayList<MemeModel> memeModels = new ArrayList<>();

        String[] title = new String[] {"送狗粮", "重启试试", "关你屁事", "管他呢", "你喜欢犯花痴的我吗", "恋爱的酸臭味", "被子不让我起床", "除了我谁要你" };
        String[] gifTitles = new String[] { "你知错了吗", "龙猫" };

        for (int i = 1; i <= 40; i++) {
            MemeModel memeModel = new MemeModel()
                    .withMemeId("jq" + i)
                    .withAuthorId("1")
                    .withImageUrl("http://t.perqin.com/img/" + i + ".jpg")
                    .withTitle("title:" + i)
                    .withLiked(true)
                    .withLikeCount(20)
                    .withReported(false)
                    .withReportCount(4);
            memeModels.add(memeModel);
        }
        for (int i = 41; i <= 48; i++) {
            MemeModel memeModel = new MemeModel()
                    .withMemeId("jq" + i)
                    .withAuthorId("1")
                    .withImageUrl("http://t.perqin.com/img/" + i + ".jpg")
                    .withTitle(title[i-41])
                    .withLiked(true)
                    .withLikeCount(30+i)
                    .withReported(false)
                    .withReportCount(2);

            memeModels.add(memeModel);
        }
        for (int i = 49; i <= 50; i++) {
            MemeModel memeModel = new MemeModel()
                    .withMemeId("jq" + i)
                    .withAuthorId("1")
                    .withImageUrl("http://t.perqin.com/img/" + i + ".gif")
                    .withTitle(gifTitles[i - 49])
                    .withLiked(false)
                    .withLikeCount(40)
                    .withReported(true)
                    .withReportCount(7);
            memeModels.add(memeModel);
        }
        return memeModels;


    }

    public static ArrayList<MemeModel> getXiongbenMemes() {
        ArrayList<MemeModel> allMemes = getAllMemes();
        ArrayList<MemeModel> memeModels1 = new ArrayList<>();
        for (int i = 24; i < 48; i++) {
            if (i != 26) memeModels1.add(allMemes.get(i));
        }
        return memeModels1;
    }
}
