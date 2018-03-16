package com.yijian.staff.mvp.mine.calendartable;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;

/**
 * 周视图
 */
public class WeekFragment extends Fragment {

    private static WeekFragment weekFragment;
    public static WeekFragment getInstance(){
        if(weekFragment == null){
            weekFragment = new WeekFragment();
        }
        return weekFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_week, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }

}
