package com.yijian.staff.mvp.report;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaeger.library.StatusBarUtil;
import com.yijian.staff.R;
import com.yijian.staff.util.system.StatusBarUtils;

/**
 * Created by lishuaihua on 2018/2/5.
 */

@SuppressLint("ValidFragment")
public class ReportingFragment extends Fragment {
    public static ReportingFragment mReportingFragment = null;
    public static ReportingFragment getInstance() {
        if (mReportingFragment == null) {
            mReportingFragment = new ReportingFragment();
        }
        return mReportingFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_reporting, container, false);
    }



}
