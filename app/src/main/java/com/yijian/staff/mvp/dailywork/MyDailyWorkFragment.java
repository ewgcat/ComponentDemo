package com.yijian.staff.mvp.dailywork;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;

/**
 * 我的考勤
 */
public class MyDailyWorkFragment extends Fragment {

    private static MyDailyWorkFragment myDailyWorkFragment;
    public static MyDailyWorkFragment getInstance(){
        if(myDailyWorkFragment == null){
            myDailyWorkFragment = new MyDailyWorkFragment();
        }
        return myDailyWorkFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_daily_work, container, false);
    }

}
