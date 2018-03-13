package com.yijian.staff.mvp.resourceallocation;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.resourceallocation.bean.HistoryResourceAllocationInfo;
import com.yijian.staff.mvp.resourceallocation.bean.HuijiInfo;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择会籍
 */
public class SelectHuiJiActivity extends AppCompatActivity {

    @BindView(R.id.rev_select_huiji)
    RecyclerView rev_select_huiji;
    private List<HuijiInfo> huijiInfoList=new ArrayList<>();

    @BindView(R.id.tv_comment_order)
    TextView tv_comment_order;  //评分排序
    @BindView(R.id.tv_business_order)
    TextView tv_business_order;   //业绩排序
    @BindView(R.id.tv_letter_order)
    TextView tv_letter_order; //字母排序

    @BindView(R.id.iv_comment_order_up)
    ImageView iv_comment_order_up;
    @BindView(R.id.iv_comment_order_down)
    ImageView iv_comment_order_down;

    @BindView(R.id.iv_business_order_up)
    ImageView iv_business_order_up;
    @BindView(R.id.iv_business_order_down)
    ImageView iv_business_order_down;

    @BindView(R.id.iv_letter_order_up)
    ImageView iv_letter_order_up;
    @BindView(R.id.iv_letter_order_down)
    ImageView iv_letter_order_down;


    private int orderResId; //排序ID
    private boolean isDown = true; //箭头是否向下的标识位


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_hui_ji);
        ButterKnife.bind(this);
        initView();
        initHuijiInfoList();
    }

    private void initView() {
        NavigationBar navigationBar = findViewById(R.id.vip_over_navigation_bar);
        navigationBar.setTitle("选择会籍","#ffffff");
        navigationBar.getmRightTextView().setText("确定");
        navigationBar.getmRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
    }

    private void initHuijiInfoList() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("headerUrl", "");
            jsonObject.put("name", "张三三");
            jsonObject.put("name", "1");
            jsonObject.put("commentGrade", "3.5");
            jsonObject.put("businessProgress", "0.8");
            jsonObject.put("actionTargetProgress", "0.5");
            jsonObject.put("vipServiceNum", "150");
            jsonObject.put("potentialServiceNum", "260");
            for (int i = 0; i < 20; i++) {
                HuijiInfo huijiInfo = new HuijiInfo(jsonObject);
                huijiInfoList.add(huijiInfo);
            }


            LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
            //设置RecyclerView 布局
            rev_select_huiji.setLayoutManager(layoutmanager);
            SelectHuiJiAdapter historyResourceAllocationAdatper = new SelectHuiJiAdapter(this, huijiInfoList);
            rev_select_huiji.setAdapter(historyResourceAllocationAdatper);
        } catch (JSONException e) {
            Logger.i("TEST", "JSONException: " + e);

        }
    }

    @OnClick({R.id.lin_comment_order,R.id.lin_businees_order,R.id.lin_letter_order})
    public void click(View v){
        switch(v.getId()){
            case R.id.lin_comment_order: //评分排序
                setTopBarStyle(R.id.lin_comment_order);
                break;
            case R.id.lin_businees_order: //业绩排序
                setTopBarStyle(R.id.lin_businees_order);
                break;
            case R.id.lin_letter_order: //字母排序
                setTopBarStyle(R.id.lin_letter_order);
                break;
        }
    }

    private void setTopBarStyle(int selectId) {
        tv_comment_order.setTextColor(Color.parseColor("#666666"));
        tv_business_order.setTextColor(Color.parseColor("#666666"));
        tv_letter_order.setTextColor(Color.parseColor("#666666"));

        iv_comment_order_up.setImageResource(R.mipmap.fp_shang);
        iv_comment_order_down.setImageResource(R.mipmap.fp_xia);
        iv_business_order_up.setImageResource(R.mipmap.fp_shang);
        iv_business_order_down.setImageResource(R.mipmap.fp_xia);
        iv_letter_order_up.setImageResource(R.mipmap.fp_shang);
        iv_letter_order_down.setImageResource(R.mipmap.fp_xia);

        switch(selectId){
            case R.id.lin_comment_order: //评分排序
                tv_comment_order.setTextColor(Color.parseColor("#1997f8"));
                if(selectId == orderResId){
                    iv_comment_order_up.setImageResource(isDown?R.mipmap.fp_shang:R.mipmap.fp_shanglan);
                    iv_comment_order_down.setImageResource(isDown?R.mipmap.fp_xialan:R.mipmap.fp_xia);
                }else{
                    iv_comment_order_up.setImageResource(R.mipmap.fp_shang);
                    iv_comment_order_down.setImageResource(R.mipmap.fp_xialan);
                }
                isDown = !isDown;
                break;
            case R.id.lin_businees_order: //业绩排序
                tv_business_order.setTextColor(Color.parseColor("#1997f8"));
                if(selectId == orderResId){
                    iv_business_order_up.setImageResource(isDown?R.mipmap.fp_shang:R.mipmap.fp_shanglan);
                    iv_business_order_down.setImageResource(isDown?R.mipmap.fp_xialan:R.mipmap.fp_xia);
                }else{
                    iv_business_order_up.setImageResource(R.mipmap.fp_shang);
                    iv_business_order_down.setImageResource(R.mipmap.fp_xialan);
                }
                isDown = !isDown;
                break;
            case R.id.lin_letter_order: //字母排序
                tv_letter_order.setTextColor(Color.parseColor("#1997f8"));
                if(selectId == orderResId){
                    iv_letter_order_up.setImageResource(isDown?R.mipmap.fp_shang:R.mipmap.fp_shanglan);
                    iv_letter_order_down.setImageResource(isDown?R.mipmap.fp_xialan:R.mipmap.fp_xia);
                }else{
                    iv_letter_order_up.setImageResource(R.mipmap.fp_shang);
                    iv_letter_order_down.setImageResource(R.mipmap.fp_xialan);
                }
                isDown = !isDown;
                break;
        }
        orderResId = selectId;
    }

}
