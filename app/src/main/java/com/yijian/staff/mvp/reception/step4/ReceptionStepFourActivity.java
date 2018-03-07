package com.yijian.staff.mvp.reception.step4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.utils.DensityUtils;
import com.yijian.staff.R;
import com.yijian.staff.bean.KeCheng;
import com.yijian.staff.mvp.goodsdetail.GoodsRightSupportActivity;
import com.yijian.staff.mvp.reception.step5.ReceptionStepFiveActivity;
import com.yijian.staff.util.system.StatusBarUtils;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.TimeBar;

import java.util.ArrayList;
import java.util.List;

public class ReceptionStepFourActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_step_four);
        initView();
    }
    private void initView() {

        NavigationBar2 navigationBar2 = findViewById(R.id.step_four_navigation_bar2);
        navigationBar2.getFirstLeftIv().setOnClickListener(this);
        navigationBar2.getSecondLeftIv().setOnClickListener(this);
        navigationBar2.getmRightTv().setOnClickListener(this);
        navigationBar2.setTitle("订单详情(4/5)");
        navigationBar2.setmRightTvText("下一步");

        TimeBar timeBar = findViewById(R.id.step_four_timebar);
        timeBar.showTimeBar(4);


        FontStyle.setDefaultTextSize(DensityUtils.sp2px(this, 5));
        SmartTable table = (SmartTable<KeCheng>) findViewById(R.id.table);
        List<KeCheng> keChengList = new ArrayList<KeCheng>();
        KeCheng keCheng1 = new KeCheng("12:00-18:00", "30", "30", "30", "30", "30", "30", "30");
        keChengList.add(keCheng1);
        KeCheng keCheng2 = new KeCheng("20:00-24:00", "30", "30", "30", "30", "30", "30", "30");
        keChengList.add(keCheng2);
        table.setData(keChengList);
        table.getConfig().setShowTableTitle(false);
        table.getConfig().setShowXSequence(false);
        table.getConfig().setShowYSequence(false);

        findViewById(R.id.tv_chakanxiangqing).setOnClickListener(this);
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
                Intent intent = new Intent(ReceptionStepFourActivity.this, ReceptionStepFiveActivity.class);
                startActivity(intent);

                break;
                case R.id.tv_chakanxiangqing:
                Intent i = new Intent(ReceptionStepFourActivity.this, GoodsRightSupportActivity.class);
                startActivity(i);

                break;
        }
    }
}
