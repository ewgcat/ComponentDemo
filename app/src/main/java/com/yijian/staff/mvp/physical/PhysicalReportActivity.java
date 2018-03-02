package com.yijian.staff.mvp.physical;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.util.system.StatusBarUtils;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//体测报告
public class PhysicalReportActivity extends AppCompatActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_height)
    TextView tvHeight;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_rentichenfendetail_controller)
    TextView tvRentichenfendetailController;
    @BindView(R.id.tv_weight)
    TextView tvWeight;
    @BindView(R.id.tv_tizhifang)
    TextView tvTizhifang;
    @BindView(R.id.tv_body_water_weight)
    TextView tvBodyWaterWeight;
    @BindView(R.id.tv_quzhi_weight)
    TextView tvQuzhiWeight;
    @BindView(R.id.tv_bmi)
    TextView tvBmi;
    @BindView(R.id.tv_pbf)
    TextView tvPbf;
    @BindView(R.id.tv_yaotunbi)
    TextView tvYaotunbi;
    @BindView(R.id.tv_jichudaixie)
    TextView tvJichudaixie;
    @BindView(R.id.tv_jiroukongzhi)
    TextView tvJiroukongzhi;
    @BindView(R.id.tv_zhifangkongzhi)
    TextView tvZhifangkongzhi;
    @BindView(R.id.tv_jichuceliangdetail_controller)
    TextView tvJichuceliangdetailController;
    @BindView(R.id.tv_yaowei)
    TextView tvYaowei;
    @BindView(R.id.tv_pizhihoudu)
    TextView tvPizhihoudu;
    @BindView(R.id.tv_zuobi)
    TextView tvZuobi;
    @BindView(R.id.tv_youbi)
    TextView tvYoubi;
    @BindView(R.id.tv_zuodatui)
    TextView tvZuodatui;
    @BindView(R.id.tv_youdatui)
    TextView tvYoudatui;
    @BindView(R.id.tv_zuoxiaotui)
    TextView tvZuoxiaotui;
    @BindView(R.id.tv_youxiaotui)
    TextView tvYouxiaotui;
    @BindView(R.id.tv_xueya)
    TextView tvXueya;
    @BindView(R.id.tv_jitaixintiaolv)
    TextView tvJitaixintiaolv;
    @BindView(R.id.tv_titaipinggudetail_controller)
    TextView tvTitaipinggudetailController;
    @BindView(R.id.tv_tou)
    TextView tvTou;
    @BindView(R.id.tv_jingzhui)
    TextView tvJingzhui;
    @BindView(R.id.tv_jianjiagu)
    TextView tvJianjiagu;
    @BindView(R.id.tv_xiongzhui)
    TextView tvXiongzhui;
    @BindView(R.id.tv_yaozhui)
    TextView tvYaozhui;
    @BindView(R.id.tv_gupen)
    TextView tvGupen;
    @BindView(R.id.tv_kuanguanjie)
    TextView tvKuanguanjie;
    @BindView(R.id.tv_xiguanjie)
    TextView tvXiguanjie;
    @BindView(R.id.tv_huaiguanjie)
    TextView tvHuaiguanjie;
    @BindView(R.id.tv_feiguwaike)
    TextView tvFeiguwaike;
    @BindView(R.id.ll_rentichengfen)
    LinearLayout llRentichengfen;
    @BindView(R.id.ll_jichuceliang)
    LinearLayout llJichuceliang;
    @BindView(R.id.ll_titai)
    LinearLayout llTitai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setLightStatusBar(this, 0xffffff);
        setContentView(R.layout.activity_physical_report);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.physical_report_navigation_bar);
        navigationBar.setTitle("查看体测数据", "#000000");
        navigationBar.setNavigationBarBackgroudColor(Color.parseColor("#ffffff"));
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_BLACK));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
    }



    @OnClick({R.id.tv_titaipinggudetail_controller, R.id.tv_jichuceliangdetail_controller, R.id.tv_rentichenfendetail_controller})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_rentichenfendetail_controller:
                changeDetailShow(llRentichengfen,tvRentichenfendetailController);
                break;
            case R.id.tv_jichuceliangdetail_controller:
                changeDetailShow(llJichuceliang,tvJichuceliangdetailController);
                break;
            case R.id.tv_titaipinggudetail_controller:
                changeDetailShow(llTitai,tvTitaipinggudetailController);
                break;
        }
    }

    private void changeDetailShow(LinearLayout linearLayout,TextView textView){
        if (linearLayout.getVisibility()==View.VISIBLE){
            linearLayout.setVisibility(View.GONE);
            textView.setText("展开");
            Drawable icon_zhankai = getResources().getDrawable(R.mipmap.lg_zhankai);
            icon_zhankai.setBounds(0, 0, icon_zhankai.getMinimumWidth(),icon_zhankai.getMinimumHeight());
            textView.setCompoundDrawables(null, null, icon_zhankai, null);
        }else {
            linearLayout.setVisibility(View.VISIBLE);
            textView.setText("收起");
            Drawable icon_shouqi = getResources().getDrawable(R.mipmap.lg_shouqi);
            icon_shouqi.setBounds(0, 0, icon_shouqi.getMinimumWidth(),icon_shouqi.getMinimumHeight());

            textView.setCompoundDrawables(null, null, icon_shouqi, null);
        }
    }


}
