package com.yijian.staff.mvp.coach.intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.yijian.staff.R;
import com.yijian.staff.widget.NavigationBar2;

/**
 * 潜在会员或意向会员 基本信息
 */
public class CoachIntentViperDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potential_and_intent_viper_detail);
        NavigationBar2 navigationbar2 = findViewById(R.id.vip_intent_navigation_bar2);
        navigationbar2.setBackClickListener(this);
        navigationbar2.hideLeftSecondIv();
    }


}
