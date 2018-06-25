package com.yijian.staff.schamefilter;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/19 18:02:40
 */
public class SchameFilterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri uri = getIntent().getData();
        ARouter.getInstance().build(uri).navigation();
        finish();
    }
}
