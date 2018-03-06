package com.yijian.staff.mvp.huifang.task.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/5 19:39:24
 */
@SuppressLint("ValidFragment")
public class CommonHuiFangTaskFragment extends Fragment {
    private Context context;
    protected int viewId;
    private int type;


    public CommonHuiFangTaskFragment(Context context, int viewId, int type) {
        this.context = context;
        this.viewId = viewId;
        this.type = type;
    }

    public CommonHuiFangTaskFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(viewId, container, false);
        initComponent(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refresh(true);
    }

    public void initComponent(View view) {

    }

    public void refresh(boolean isShowLoading) {

    }

    public void loadMore(boolean isShowLoading) {

    }

}
