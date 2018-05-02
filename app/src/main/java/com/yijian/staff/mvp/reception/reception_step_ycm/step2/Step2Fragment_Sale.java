package com.yijian.staff.mvp.reception.reception_step_ycm.step2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStatusChange;
import com.yijian.staff.mvp.reception.reception_step_ycm.ReceptionStepActivity;
import com.yijian.staff.mvp.reception.step2.KeFuReceptionStepTwoContract;
import com.yijian.staff.mvp.reception.step2.KeFuReceptionStepTwoPresenter;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.ScanBodyView;

/**
 * Created by The_P on 2018/4/20.
 */

public class Step2Fragment_Sale extends Fragment implements View.OnClickListener,KeFuReceptionStepTwoContract.View {
    private ScanBodyView scanBodyView;
    private View ll_to_coach;
    private KeFuReceptionStepTwoPresenter presenter;
    private RecptionerInfoBean consumerBean;
    private TextView tvTip;
    private TextView tvNextStep;

//          case 20:// SALEFINISHQS(20, "会籍完成问卷调查录入"),
//          case 21: //SALESENDCOACH(21, "会籍选择发送给教练"),
//          case 31:// COACHSENDBACKSALE(31, "教练录完体测数据发送回会籍"),
//          case 32://  MEMBERREJECT(32, "会员拒绝录入数据发送回会籍"),
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        consumerBean = arguments.getParcelable("recptionerInfoBean");

        NavigationBar2 navigationBar2 = ((ReceptionStepActivity) getActivity()).getNavigationBar2();
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
        View view = inflater.inflate(R.layout.fragment_step2_sale, container, false);
        presenter = new KeFuReceptionStepTwoPresenter(getContext());
        presenter.setView(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        scanBodyView = view.findViewById(R.id.scan_view);

        tvNextStep = view.findViewById(R.id.tv_next_step);


        tvTip = view.findViewById(R.id.tv_tip);
        ll_to_coach = view.findViewById(R.id.ll_to_coach);

        ll_to_coach.setOnClickListener(this);
        tvNextStep.setOnClickListener(this);

        //status ==20||status==21||status==32
        if (consumerBean.getStatus()==20){//SALEFINISHQS(20, "会籍完成问卷调查录入"),

        }else if (consumerBean.getStatus()==21){//SALESENDCOACH(21, "会籍选择发送给教练"),
            tvTip.setVisibility(View.VISIBLE);
            ll_to_coach.setVisibility(View.GONE);
            tvNextStep.setVisibility(View.GONE);
        }else if (consumerBean.getStatus()==32){//MEMBERREJECT(32, "会员拒绝录入数据发送回会籍"),
            tvNextStep.setVisibility(View.GONE);
            ll_to_coach.setVisibility(View.GONE);
            tvTip.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_next_step:
                presenter.jumpBodyCheck(consumerBean.getId());
                break;
            case R.id.ll_to_coach:
                presenter.coachBodyCheck(consumerBean.getId());
                break;
        }

    }

    @Override
    public void showJumpBodyCheck() {
        scanBodyView.stopScan();
        if (statusChangeLisenter!=null)statusChangeLisenter.ReceptionStep2SaleSkipCoach();
    }

    @Override
    public void showCoachBodyCheck() {
        scanBodyView.startScan();
        tvTip.setVisibility(View.VISIBLE);
        ll_to_coach.setVisibility(View.GONE);
        tvNextStep.setVisibility(View.GONE);
    }


    private ReceptionStatusChange statusChangeLisenter;

    public void setStatusChangeLisenter(ReceptionStatusChange statusChangeLisenter) {
        this.statusChangeLisenter = statusChangeLisenter;
    }

    public void showJumpButton() {
        tvNextStep.setVisibility(View.VISIBLE);
        ll_to_coach.setVisibility(View.GONE);
        tvTip.setVisibility(View.GONE);
    }
}

