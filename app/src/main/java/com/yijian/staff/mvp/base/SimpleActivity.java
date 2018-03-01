package com.yijian.staff.mvp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.yijian.staff.R;
import com.yijian.staff.application.CustomApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 无MVP的activity基类
 */

public abstract class SimpleActivity extends AppCompatActivity {

    protected Activity mContext;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        CustomApplication.getInstance().addActivity(this);
        initEventAndData();
    }


    protected void onViewCreated() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CustomApplication.getInstance().removeActivity(this);
        mUnBinder.unbind();
    }

    protected abstract int getLayout();
    protected abstract void initEventAndData();
}
