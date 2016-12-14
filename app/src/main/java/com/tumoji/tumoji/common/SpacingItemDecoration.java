package com.tumoji.tumoji.common;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Author: perqin
 * Date  : 12/14/16
 */

public class SpacingItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpacingWidth;

    public SpacingItemDecoration(int mSpacingWidth) {
        this.mSpacingWidth = mSpacingWidth;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.right = mSpacingWidth;
    }
}
