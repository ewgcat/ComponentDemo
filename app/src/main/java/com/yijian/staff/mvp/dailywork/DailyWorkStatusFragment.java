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
 * 考勤情况
 */
public class DailyWorkStatusFragment extends Fragment {

    private static DailyWorkStatusFragment dailyWorkStatusFragment;
    public static DailyWorkStatusFragment getInstance(){
        if(dailyWorkStatusFragment == null){
            dailyWorkStatusFragment = new DailyWorkStatusFragment();
        }
        return dailyWorkStatusFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_work_status, container, false);
    }

}
