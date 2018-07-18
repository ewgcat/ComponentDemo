package com.yijian.staff.mvp.workspace.perfect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.workspace.commen.ShareTestActivity;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;
import com.yijian.staff.widget.NavigationBar2;

import butterknife.OnClick;

public class PerfectTestActivity extends MvcBaseActivity {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_perfect_test;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initTitle();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.setTitle("完美围度测试");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
    }

    @OnClick({R.id.btn_finish})
    public void onViewClicked(View view) {
        switch(view.getId()){
            case R.id.btn_finish: //完成
                ActivityUtils.startActivity(this, ShareTestActivity.class);
                finish();
                break;
        }
    }

}
