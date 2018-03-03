package com.yijian.staff.mvp.reception.step3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yijian.staff.R;
import com.yijian.staff.mvp.reception.ReceptionHistoryAdapter;
import com.yijian.staff.mvp.reception.ReceptionInfo;
import com.yijian.staff.mvp.reception.step4.ReceptionStepFourActivity;
import com.yijian.staff.util.Logger;
import com.yijian.staff.util.system.StatusBarUtils;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.TimeBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReceptionStepThreeActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.iv_card_type)
    ImageView ivCardType;
    @BindView(R.id.iv_price)
    ImageView ivPrice;
    @BindView(R.id.iv_changguan)
    ImageView ivChangguan;
    @BindView(R.id.ll_card_type)
    LinearLayout llCardType;
    @BindView(R.id.ll_price)
    LinearLayout llPrice;
    @BindView(R.id.ll_changguan)
    LinearLayout llChangguan;
    @BindView(R.id.goods_rcv)
    RecyclerView goodsRcv;

    private List<GoodsInfo> mGoodsInfoList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setLightStatusBar(this, 0xffffff);

        setContentView(R.layout.activity_reception_step_three);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        NavigationBar2 navigationBar2 = findViewById(R.id.step_three_navigation_bar2);
        navigationBar2.findViewById(R.id.iv_first_left).setOnClickListener(this);
        navigationBar2.findViewById(R.id.iv_second_left).setOnClickListener(this);
        navigationBar2.findViewById(R.id.right_tv).setOnClickListener(this);
        navigationBar2.setTitle("产品报价(3/5)");
        navigationBar2.setmRightTvText("下一步");
        TimeBar timeBar = findViewById(R.id.step_three_timebar);
        timeBar.showTimeBar(3);


        initGoodsList();
    }

    private void initGoodsList() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("goodsName", "十周年纪念卡");
            jsonObject.put("jianshenplace", "游泳");
            jsonObject.put("yuer", "23次");
            jsonObject.put("chuzhiyouhui", "赠送20%");
            jsonObject.put("price", "1400元");
            for (int i = 0; i < 10; i++) {
                GoodsInfo goodsInfo = new GoodsInfo(jsonObject);
                mGoodsInfoList.add(goodsInfo);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
            //设置RecyclerView 布局
            goodsRcv.setLayoutManager(layoutmanager);
            Step3GoodsListAdapter goodsListAdapter = new Step3GoodsListAdapter(this, mGoodsInfoList);
            goodsRcv.setAdapter(goodsListAdapter);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.iv_first_left:
                finish();
                break;
            case R.id.iv_second_left:


                break;
            case R.id.right_tv:
                Intent intent = new Intent(ReceptionStepThreeActivity.this, ReceptionStepFourActivity.class);
                startActivity(intent);

                break;
        }
    }

    @OnClick({R.id.ll_card_type, R.id.ll_price, R.id.ll_changguan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_card_type:
                break;
            case R.id.ll_price:
                break;
            case R.id.ll_changguan:
                break;

        }
    }

}
