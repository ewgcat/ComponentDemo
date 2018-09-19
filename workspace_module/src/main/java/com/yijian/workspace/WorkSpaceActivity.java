package com.yijian.workspace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.workspace.utils.ActivityUtils;
import com.yijian.workspace.widget.CommenPopupWindow;
import com.yijian.workspace.commen.WorkSpaceSearchActivity;

@Route(path = "/workspace/workspace")
public class WorkSpaceActivity extends MvcBaseActivity {

    private CommenPopupWindow popupWindow;

    @Override
    protected void initView(@org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initTitle();

    }

    private void initTitle(){
        NavigationBar navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setTitle("私教P.O.S工具");
    }

    @OnClick({R.id.fl_sport_behavior, R.id.fl_perfect, R.id.fl_static, R.id.fl_sport})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.fl_sport_behavior: //运动表现
                ActivityUtils.isShareJump = false;
                ActivityUtils.moduleType = ActivityUtils.MODULE_SPORT;
                startActivity(new Intent(this, WorkSpaceSearchActivity.class));
                break;
            case R.id.fl_perfect: //完美维度
                ActivityUtils.isShareJump = false;
                ActivityUtils.moduleType = ActivityUtils.MODULE_PERFECT;
                startActivity(new Intent(this, WorkSpaceSearchActivity.class));
                break;
            case R.id.fl_static: //静态评估
//                popDialog();
                ActivityUtils.isShareJump = false;
                ActivityUtils.moduleType = ActivityUtils.MODULE_STATIC_EVALUATE;
                ActivityUtils.startActivity(this, WorkSpaceSearchActivity.class);
                break;
            case R.id.fl_sport: //动态评估
//                popDialog();
                ActivityUtils.isShareJump = false;
                ActivityUtils.moduleType = ActivityUtils.MODULE_DYNAMIC_EVALUATE;
                ActivityUtils.startActivity(this, WorkSpaceSearchActivity.class);
                break;
                default:
        }
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
}