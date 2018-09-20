package com.yijian.workspace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.workspace.utils.ActivityUtils;
import com.yijian.workspace.widget.CommenPopupWindow;
import com.yijian.workspace.commen.WorkSpaceSearchActivity;

@Route(path = "/workspace/workspace")
public class WorkSpaceActivity extends MvcBaseActivity implements View.OnClickListener {

    private CommenPopupWindow popupWindow;

    @Override
    protected void initView(@org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initTitle();

                findViewById(R.id.fl_sport_behavior).setOnClickListener(this);
                findViewById(R.id.fl_perfect).setOnClickListener(this);
                findViewById(R.id.fl_static).setOnClickListener(this);
                findViewById(R.id.fl_sport).setOnClickListener(this);

    }

    private void initTitle(){
        NavigationBar navigationBar = findViewById(R.id.navigation_bar);
        navigationBar.hideLeftSecondIv();
        navigationBar.setBackClickListener(this);
        navigationBar.setTitle("私教P.O.S工具");
    }


    public void popDialog(){
        if(popupWindow == null){
            popupWindow = new CommenPopupWindow(WorkSpaceActivity.this, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            },R.layout.pop_oppen,new int[]{R.id.tv_sure});
        }
        popupWindow.showAtBottom(getWindow().getDecorView());
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_workspace;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.fl_sport_behavior) {
            ActivityUtils.isShareJump = false;
            ActivityUtils.moduleType = ActivityUtils.MODULE_SPORT;
            startActivity(new Intent(this, WorkSpaceSearchActivity.class));

        } else if (i == R.id.fl_perfect) {
            ActivityUtils.isShareJump = false;
            ActivityUtils.moduleType = ActivityUtils.MODULE_PERFECT;
            startActivity(new Intent(this, WorkSpaceSearchActivity.class));

        } else if (i == R.id.fl_static) {//                popDialog();
            ActivityUtils.isShareJump = false;
            ActivityUtils.moduleType = ActivityUtils.MODULE_STATIC_EVALUATE;
            ActivityUtils.startActivity(this, WorkSpaceSearchActivity.class);

        } else if (i == R.id.fl_sport) {//                popDialog();
            ActivityUtils.isShareJump = false;
            ActivityUtils.moduleType = ActivityUtils.MODULE_DYNAMIC_EVALUATE;
            ActivityUtils.startActivity(this, WorkSpaceSearchActivity.class);

        } else {
        }
    }
}
