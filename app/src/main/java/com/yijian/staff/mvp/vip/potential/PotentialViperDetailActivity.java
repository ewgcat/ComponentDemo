package com.yijian.staff.mvp.vip.potential;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.yijian.staff.R;

/**
 * 潜在会员或意向会员 基本信息
 */
public class PotentialViperDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucentForImageView(this, 0, null);
        setContentView(R.layout.activity_potential_and_intent_viper_detail);

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
