package com.yijian.staff.mvp.reception.reception_step_ycm.step5;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.bean.ReceptionStastuBean;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStatusChange;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStepActivity;
import com.yijian.staff.mvp.reception.step2.KeFuReceptionStepTwoContract;
import com.yijian.staff.mvp.reception.step2.KeFuReceptionStepTwoPresenter;
import com.yijian.staff.mvp.reception.step5.ReceptionStepFiveContract;
import com.yijian.staff.mvp.reception.step5.ReceptionStepFivePresenter;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultNullObserver;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.ScanBodyView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by The_P on 2018/4/20.
 */

public class Step5Fragment_Sale extends Fragment implements ReceptionStepFiveContract.View {
    private RecptionerInfoBean consumerBean;
    private String memberId;
    private ReceptionStepFivePresenter presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        consumerBean = arguments.getParcelable("recptionerInfoBean");
        if (consumerBean==null)return;
        memberId = consumerBean.getId();
        NavigationBar2 navigationBar2 = ((ReceptionStepActivity) getActivity()).getNavigationBar2();

        navigationBar2.setmRightTvText("完成");
        navigationBar2.getmRightTv().setVisibility(View.VISIBLE);
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter!=null)presenter.getStatus(true);
            }
        });

        navigationBar2.getBackLL().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statusChangeLisenter!=null)statusChangeLisenter.ReceptionStep5Back();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step5_sale, container, false);

        presenter = new ReceptionStepFivePresenter(getContext());
        presenter.setView(this);
        return view;
    }


    private ReceptionStatusChange statusChangeLisenter;

    public void setStatusChangeLisenter(ReceptionStatusChange statusChangeLisenter) {
        this.statusChangeLisenter = statusChangeLisenter;
    }



    @Override
    public void showStatus(ReceptionStastuBean receptionStastuBean) {
        if (statusChangeLisenter!=null)statusChangeLisenter.ReceptionCompleted( receptionStastuBean.getOperatorType());
    }

    @Override
    public void ShowEndProcess() {
        presenter.getStatus(false);
    }

    @Override
    public void needEndProcess() {
        presenter.endProcess(memberId);
    }
}
