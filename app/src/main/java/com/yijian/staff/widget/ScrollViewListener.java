package com.yijian.staff.widget;

import android.view.ViewGroup;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/4 09:48:51
 */
public interface ScrollViewListener {
    void onScrollChanged(ViewGroup viewGroup, int x, int y,
                         int oldx, int oldy);
}
