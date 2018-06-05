package com.yijian.staff.mvp.reception.reception_step_ycm.step3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStatusChange;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStepActivity;
import com.yijian.staff.widget.NavigationBar2;

/**
 * Created by The_P on 2018/4/24.
 */

public class Step3Fragment_Coach_NoData extends Fragment {

    private String tips;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) tips = arguments.getString("tips");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step3_coach_nodata, container, false);
        TextView tip = view.findViewById(R.id.tv);
        if (!TextUtils.isEmpty(tips)) tip.setText(tips);
        return view;
    }


}
