package com.yijian.staff.mvp.mine.qualification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yijian.staff.R;
import com.yijian.staff.util.DensityUtil;
import com.yijian.staff.widget.NavigationBar2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyQualificationActivity extends AppCompatActivity {

    @BindView(R.id.qualificationContent)
    MyQualificatioinContent qualificationContent;
    @BindView(R.id.lin_iv)
    LinearLayout lin_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualification);
        ButterKnife.bind(this);
        initTitle();
        initData();
    }

    private void initData() {
        List<String> list = new ArrayList<String>();
        list.add("北京赛普高级私人教练");
        list.add("毕业于赛普健身学院");
        list.add("北京市健美协会全能私人教练证书");
        qualificationContent.setContent(list);
        ImageView iv1 = new ImageView(this);
        FrameLayout.LayoutParams ivFlp1 = new FrameLayout.LayoutParams(DensityUtil.dip2px(this,200), FrameLayout.LayoutParams.MATCH_PARENT);
        ivFlp1.setMargins(DensityUtil.dip2px(this,13),0,0,0);
        iv1.setLayoutParams(ivFlp1);
        iv1.setImageResource(R.mipmap.placeholder);

        ImageView iv2 = new ImageView(this);
        FrameLayout.LayoutParams ivFlp2 = new FrameLayout.LayoutParams(DensityUtil.dip2px(this,200), FrameLayout.LayoutParams.MATCH_PARENT);
        ivFlp2.setMargins(DensityUtil.dip2px(this,13),0,0,0);
        iv2.setLayoutParams(ivFlp2);
        iv2.setImageResource(R.mipmap.placeholder);

        ImageView iv3 = new ImageView(this);
        iv3.setImageResource(R.mipmap.placeholder);
        FrameLayout.LayoutParams ivFlp3 = new FrameLayout.LayoutParams(DensityUtil.dip2px(this,200), FrameLayout.LayoutParams.MATCH_PARENT);
        ivFlp3.setMargins(DensityUtil.dip2px(this,13),0,0,0);
        iv3.setLayoutParams(ivFlp3);

        lin_iv.addView(iv1);
        lin_iv.addView(iv2);
        lin_iv.addView(iv3);
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.reception_activity_navigation_bar);
        navigationBar2.setTitle("资格证书");
        navigationBar2.setmRightTvText("编辑");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyQualificationActivity.this,EditQualificationActivity.class));
            }
        });
    }
}
