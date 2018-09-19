package com.yijian.staff.mvp.workspace;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.yijian.staff.R;
import com.yijian.staff.mvp.workspace.static_assessment.StaticAssessmentActivity;
import com.yijian.staff.mvp.workspace.static_assessment.StaticPhotoActivity;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;
import com.yijian.staff.mvp.workspace.widget.CommenPopupWindow;
import com.yijian.staff.mvp.workspace.commen.WorkSpaceSearchActivity;
import com.yijian.staff.widget.NavigationBar2;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = "/test/workspace")
public class WorkSpaceActivity extends AppCompatActivity {

    private CommenPopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workspace);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle(){
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
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

}
