package com.yijian.staff.mvp.reception.reception_step_ycm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.ReceptionActivity;
import com.yijian.staff.mvp.reception.bean.RecptionerInfoBean;
import com.yijian.staff.mvp.reception.reception_step_ycm.step1.Step1Fragment_Message;
import com.yijian.staff.mvp.reception.reception_step_ycm.step1.Step1Fragment_Sale;
import com.yijian.staff.mvp.reception.reception_step_ycm.step2.CancelReasonDialog;
import com.yijian.staff.mvp.reception.reception_step_ycm.step2.Step2Fragment_Coach;
import com.yijian.staff.mvp.reception.reception_step_ycm.step2.Step2Fragment_Message;
import com.yijian.staff.mvp.reception.reception_step_ycm.step2.Step2Fragment_Sale;
import com.yijian.staff.mvp.reception.reception_step_ycm.step2.Step2Fragment_Sale_Physical;
import com.yijian.staff.mvp.reception.reception_step_ycm.step3.Step3Fragment_Coach;
import com.yijian.staff.mvp.reception.reception_step_ycm.step3.Step3Fragment_Leader;
import com.yijian.staff.mvp.reception.reception_step_ycm.step3.Step3Fragment_Sale;
import com.yijian.staff.mvp.reception.reception_step_ycm.step4.Step4Fragment_Message;
import com.yijian.staff.mvp.reception.reception_step_ycm.step4.Step4Fragment_Sale;
import com.yijian.staff.mvp.reception.reception_step_ycm.step5.Step5Fragment_Sale;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.TimeBar;

/**
 * Created by The_P on 2018/4/20.
 */

public class ReceptionStepActivity extends AppCompatActivity implements ReceptionStatusChange {
    private static final String TAG = "ReceptionStepActivity";
    private Step1Fragment_Sale step1Fragment_sale;
    private RecptionerInfoBean recptionerInfoBean;
    private Step2Fragment_Sale step2Fragment_sale;
    private int userRole;
    private Step2Fragment_Coach step2Fragment_coach;
    private Step3Fragment_Sale step3Fragment_sale;
    private Step3Fragment_Coach step3Fragment_coach;
    private Step3Fragment_Leader step3Fragment_leader;
    private Step4Fragment_Sale step4Fragment_sale;
    private Step5Fragment_Sale step5Fragment_sale;
    private TimeBar timeBar;
    private NavigationBar2 navigationBar2;
    private Step2Fragment_Sale_Physical step2Fragment_sale_physical;
    private Step1Fragment_Message step1Fragment_message;
    private Step2Fragment_Message step2Fragment_message;
    private Step4Fragment_Message step4Fragment_message;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_step_ycm);

        Intent intent = getIntent();
        if (intent.hasExtra(ReceptionActivity.CONSUMER)) {
            recptionerInfoBean = intent.getParcelableExtra(ReceptionActivity.CONSUMER);
        } else {
            Toast.makeText(ReceptionStepActivity.this, "获取客户信息失败,请重新进入接待流程", Toast.LENGTH_SHORT).show();
            return;
        }


//        Log.e(TAG, "onCreate: " + recptionerInfoBean.toString());
        userRole = SharePreferenceUtil.getUserRole();
        initView();

        initFragment();

        showView();

    }

    private void showView() {

        Integer status = recptionerInfoBean.getStatus();

        if (status == null) return;
        Bundle bundle = new Bundle();
        bundle.putParcelable("recptionerInfoBean", recptionerInfoBean);

        switch (status) {
            case 0://UNKNOWN(0, "未知"),
            case 10://  NOBEGIN(10, "未开始接待"),
                showStep1Fragment(bundle);
                break;

            case 20:// SALEFINISHQS(20, "会籍完成问卷调查录入"),
            case 21: //SALESENDCOACH(21, "会籍选择发送给教练"),
            case 32://  MEMBERREJECT(32, "会员拒绝录入数据发送回会籍"),
                showStep2Fragment(bundle);
                break;
            case 31:// COACHSENDBACKSALE(31, "教练录完体测数据发送回会籍"),
            case 30:// SALEJUMPCOACH(30, "会籍跳过教练"),
            case 33://SALETOCOACH(33, "会员没购买意愿，会籍TO教练"),
            case 34:// COACHTOSALE(34, "教练接待会员，会员同意购买,TO回会籍"),
            case 35:// COACHTOLEADER(35, "教练接待会员，会员不同意购买,TO领导 "),
            case 36:// LEADERTOSALE(36, "领导接待会员,TO回会籍 "),
            case 40://  SALEFINISHCON(40, "会籍完成产品报价，签订合同中”),
                showStep3Fragment(bundle);
                break;
            case 41:// SALEFINISHCON(41, “已签订合同”),
                showStep4Fragment(bundle);
                break;
            case 50://ORDERDETAILNEXT(50, "订单详情点击下一步"),
                showStep5Fragment(bundle);
                break;
            case 51:// LASTSENDCOACH(51, "会籍最后发送给教练");
                break;

        }


    }


    private void initView() {
        navigationBar2 = findViewById(R.id.navigation_bar2);
//        navigationBar2.setNavigationBarBackgroudColor(R.color.blue);
//        navigationBar2.setTitleColor(R.color.white);
        timeBar = findViewById(R.id.step_timebar);
//        timeBar.showTimeBar(1);

//        FrameLayout content = findViewById(R.id.content);



    }




    private void initFragment() {

        step1Fragment_sale = new Step1Fragment_Sale();
        step1Fragment_sale.setStatusChangeLisenter(this);
        step1Fragment_message = new Step1Fragment_Message();


        step2Fragment_sale = new Step2Fragment_Sale();
        step2Fragment_sale.setStatusChangeLisenter(this);

        step2Fragment_sale_physical = new Step2Fragment_Sale_Physical();
        step2Fragment_sale_physical.setStatusChangeLisenter(this);


        step2Fragment_coach = new Step2Fragment_Coach();
        step2Fragment_message = new Step2Fragment_Message();


        step3Fragment_sale = new Step3Fragment_Sale();
        step3Fragment_sale.setStatusChangeLisenter(this);

        step3Fragment_coach = new Step3Fragment_Coach();

        step3Fragment_leader = new Step3Fragment_Leader();

        step4Fragment_sale = new Step4Fragment_Sale();
        step4Fragment_sale.setStatusChangeLisenter(this);
        step4Fragment_message = new Step4Fragment_Message();


        step5Fragment_sale = new Step5Fragment_Sale();
        step5Fragment_sale.setStatusChangeLisenter(this);
    }

    public void showStep1Fragment(Bundle bundle) {
        navigationBar2.setTitle("填写问卷(1/5)");
        timeBar.showTimeBar(1);

        if (userRole == 1) {
            step1Fragment_sale.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content, step1Fragment_sale).commitAllowingStateLoss();
        } else if (userRole == 2) {

            getSupportFragmentManager().beginTransaction().replace(R.id.content, step1Fragment_message).commitAllowingStateLoss();
        } else if (userRole == 3) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content, step1Fragment_message).commitAllowingStateLoss();
        }


    }

    public void showStep2Fragment(Bundle bundle) {
        navigationBar2.setTitle("体测录入(2/5)");
        timeBar.showTimeBar(2);

        if (userRole == 1) {

            Integer status = recptionerInfoBean.getStatus();
            if ( status ==20||status==21||status==32){
                step2Fragment_sale.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, step2Fragment_sale).commitAllowingStateLoss();
            }else {
                step2Fragment_sale_physical.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, step2Fragment_sale_physical).commitAllowingStateLoss();
            }

        } else if (userRole == 2) {
            step2Fragment_coach.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content, step2Fragment_coach).commitAllowingStateLoss();
        } else if (userRole == 3) {
//            step2Fragment_message
            getSupportFragmentManager().beginTransaction().replace(R.id.content, step2Fragment_message).commitAllowingStateLoss();
        }


    }

    public void showStep3Fragment(Bundle bundle) {
        navigationBar2.setTitle("产品报价(3/5)");
        timeBar.showTimeBar(3);

        if (userRole == 1) {
            step3Fragment_sale.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content, step3Fragment_sale).commitAllowingStateLoss();
        } else if (userRole == 2) {
            step3Fragment_coach.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content, step3Fragment_coach).commitAllowingStateLoss();
        } else if (userRole == 3) {
            step3Fragment_leader.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content, step3Fragment_leader).commitAllowingStateLoss();
        }
    }

    public void showStep4Fragment(Bundle bundle) {
        navigationBar2.setTitle("订单详情(4/5)");
        timeBar.showTimeBar(4);

        if (userRole == 1) {
            step4Fragment_sale.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content, step4Fragment_sale).commitAllowingStateLoss();
        } else if (userRole == 2) {
            step4Fragment_message.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content, step4Fragment_message).commitAllowingStateLoss();
        } else if (userRole == 3) {
            step4Fragment_message.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content, step4Fragment_message).commitAllowingStateLoss();
        }
    }

    public void showStep5Fragment(Bundle bundle) {
        navigationBar2.setTitle("合同签订(5/5)");
        timeBar.showTimeBar(5);

        if (userRole == 1) {
            step5Fragment_sale.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content, step5Fragment_sale).commitAllowingStateLoss();
        } else if (userRole == 2) {

        } else if (userRole == 3) {

        }
    }


//接到消息推送后 更新接待节点
    public void onCoachMeasureSucceed() {//教练体测录入完成
//        recptionerInfoBean.setStatus(31);
        Bundle bundle = new Bundle();
        bundle.putParcelable("recptionerInfoBean", recptionerInfoBean);
        showStep3Fragment(bundle);
    }

    //接到消息推送后 更新接待节点
    public void onCoachMeasureFailed() {//教练未完成体测录入

        if (step2Fragment_sale.isAdded()) {
//            recptionerInfoBean.setStatus(32);
            step2Fragment_sale.showJumpButton();//step2Fragment_sale显示跳过button
        } else {
            Bundle bundle = new Bundle();
//            recptionerInfoBean.setStatus(32);
            bundle.putParcelable("recptionerInfoBean", recptionerInfoBean);
            showStep2Fragment(bundle);
//            step2Fragment_sale.showJumpButton();
        }
        CancelReasonDialog cancelReasonDialog = new CancelReasonDialog();
        Bundle bundle1 = new Bundle();
        bundle1.putString("cancelReason", "身体不适");
        cancelReasonDialog.setArguments(bundle1);
        cancelReasonDialog.show(getFragmentManager(), "CancelReasonDialog");
    }



    @Override
    public void ReceptionStep1RequestionSaved() {
        recptionerInfoBean.setStatus(20);
        Bundle bundle = new Bundle();
        bundle.putParcelable("recptionerInfoBean", recptionerInfoBean);
        showStep2Fragment(bundle);
    }



    @Override
    public void ReceptionStep2SaleNoticeCoached() {

    }

    @Override
    public void ReceptionStep2SaleSkipCoach() {
        recptionerInfoBean.setStatus(30);
        Bundle bundle = new Bundle();
        bundle.putParcelable("recptionerInfoBean", recptionerInfoBean);
        showStep3Fragment(bundle);
    }



    @Override
    public void ReceptionStep3SaleNoticeCoached() {

    }

    @Override
    public void ReceptionStep3CoachMakeADeal() {

    }

    @Override
    public void ReceptionStep3CoachNoticeLeader() {

    }

    @Override
    public void ReceptionStep3LeaderMakeADeal() {

    }


    @Override
    public void ReceptionStep1ToStep2() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("recptionerInfoBean", recptionerInfoBean);
        showStep2Fragment(bundle);
    }

    @Override
    public void ReceptionStep2ToStep3() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("recptionerInfoBean", recptionerInfoBean);
        showStep3Fragment(bundle);
    }

    @Override
    public void ReceptionStep3SaleToStep4(Integer operatorType) {
        recptionerInfoBean.setStatus(operatorType);
        Bundle bundle = new Bundle();
        bundle.putParcelable("recptionerInfoBean", recptionerInfoBean);
        showStep4Fragment(bundle);
    }

    @Override
    public void ReceptionStep4SaleToStep5(Integer operatorType) {
        recptionerInfoBean.setStatus(operatorType);
        Bundle bundle = new Bundle();
        bundle.putParcelable("recptionerInfoBean", recptionerInfoBean);
        showStep5Fragment(bundle);
    }

    @Override
    public void ReceptionCompleted(Integer operatorType) {
//        recptionerInfoBean.setStatus();
        recptionerInfoBean.setStatus(operatorType);
        //TODO  完成整个接待
    }



    @Override
    public void ReceptionStep5Back() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("recptionerInfoBean", recptionerInfoBean);
        showStep4Fragment(bundle);
    }

    @Override
    public void ReceptionStep4Back() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("recptionerInfoBean", recptionerInfoBean);
        showStep3Fragment(bundle);
    }

    @Override
    public void ReceptionStep3Back() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("recptionerInfoBean", recptionerInfoBean);
        showStep2Fragment(bundle);
    }

    @Override
    public void ReceptionStep2Back() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("recptionerInfoBean", recptionerInfoBean);
        showStep1Fragment(bundle);
    }

    @Override
    public void ReceptionStep1Back() {
        finish();
    }


    public TimeBar getTimeBar() {
        return timeBar;
    }

    public NavigationBar2 getNavigationBar2() {
        return navigationBar2;
    }
}
