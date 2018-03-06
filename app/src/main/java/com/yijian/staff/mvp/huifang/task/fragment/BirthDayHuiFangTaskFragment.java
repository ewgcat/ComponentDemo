package com.yijian.staff.mvp.huifang.task.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

import com.yijian.staff.R;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/5 19:48:02
 */
@SuppressLint("ValidFragment")
public class BirthDayHuiFangTaskFragment extends CommonHuiFangTaskFragment {
    public BirthDayHuiFangTaskFragment() {
        super();
        viewId = R.layout.fragment_birthday_hui_fang_list;
    }

    public BirthDayHuiFangTaskFragment(Context context, int type) {
        super(context, R.layout.fragment_birthday_hui_fang_list, type);
    }

}
