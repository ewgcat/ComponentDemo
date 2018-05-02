package com.yijian.staff.mvp.reception.reception_step_ycm.step2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStatusChange;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStepActivity;
import com.yijian.staff.widget.NavigationBar2;

/**
 * Created by The_P on 2018/4/24.
 */

public class Step2Fragment_Sale_NoData extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavigationBar2 navigationBar2 = ((ReceptionStepActivity) getActivity()).getNavigationBar2();
        navigationBar2.setmRightTvText("下一步");
        navigationBar2.getmRightTv().setVisibility(View.VISIBLE);
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statusChangeLisenter!=null)statusChangeLisenter.ReceptionStep2ToStep3();

            }
        });


        navigationBar2.getBackLL().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statusChangeLisenter!=null)statusChangeLisenter.ReceptionStep2Back();
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step2_message_nodata, container, false);
        return view;
    }

    private ReceptionStatusChange statusChangeLisenter;

    public void setStatusChangeLisenter(ReceptionStatusChange statusChangeLisenter) {
        this.statusChangeLisenter = statusChangeLisenter;
    }
}
