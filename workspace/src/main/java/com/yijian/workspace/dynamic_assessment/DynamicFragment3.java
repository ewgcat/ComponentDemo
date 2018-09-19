package com.yijian.workspace.dynamic_assessment;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijian.staff.R;
import com.yijian.staff.mvp.workspace.base.BaseSpaceFragment;
import com.yijian.staff.mvp.workspace.sport.SportTestActivity;
import com.yijian.workspace.R;
import com.yijian.workspace.base.BaseSpaceFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DynamicFragment3 extends BaseSpaceFragment {


    private DynamicAssessmentActivity dynamicAssessmentActivity;
    private Context mContext;
    private DynamicCheckGroup dynamic_foot_out; //足部-外摆
    private DynamicCheckGroup dynamic_knee_left; //膝盖-左膝
    private DynamicCheckGroup dynamic_knee_right; //膝盖-右膝

    private DynamicCheckGroup dynamic_yao_pg; //腰椎-盆骨-髋-盆骨
    private DynamicCheckGroup dynamic_yao_fb; //腰椎-盆骨-髋-腹部
    private DynamicCheckGroup dynamic_side_header; //头

    private DynamicCheckGroup dynamic_jian; //肩部 高低肩
    private DynamicCheckGroup dynamic_bg_header; //头


    @Override
    public int getLayoutId() {
        return R.layout.fragment_dynamic_fragment3;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initView() {
        initIdRes();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initIdRes() {
        dynamic_foot_out = findView(R.id.dynamic_foot_out);
        dynamic_knee_left = findView(R.id.dynamic_knee_left);
        dynamic_knee_right = findView(R.id.dynamic_knee_right);
        List<String> list_dynamic_foot_out = new ArrayList<>();
        list_dynamic_foot_out.add("左足");
        list_dynamic_foot_out.add("右足");
        dynamic_foot_out.createChekTab(list_dynamic_foot_out);
        List<String> list_dynamic_knee_left = new ArrayList<>();
        list_dynamic_knee_left.add("内扣");
        list_dynamic_knee_left.add("外翻");
        dynamic_knee_left.createChekTab(list_dynamic_knee_left);
        List<String> list_dynamic_knee_right = new ArrayList<>();
        list_dynamic_knee_right.add("内扣");
        list_dynamic_knee_right.add("外翻");
        dynamic_knee_right.createChekTab(list_dynamic_knee_right);

        dynamic_yao_pg = findView(R.id.dynamic_yao_pg);
        dynamic_yao_fb = findView(R.id.dynamic_yao_fb);
        dynamic_side_header = findView(R.id.dynamic_side_header);
        List<String> list_dynamic_yao_pg = new ArrayList<>();
        list_dynamic_yao_pg.add("前倾");
        list_dynamic_yao_pg.add("后倾");
        dynamic_yao_pg.createChekTab(list_dynamic_yao_pg);
        List<String> list_dynamic_yao_fb = new ArrayList<>();
        list_dynamic_yao_fb.add("突出");
        dynamic_yao_fb.createChekTab(list_dynamic_yao_fb);
        List<String> list_dynamic_side_header = new ArrayList<>();
        list_dynamic_side_header.add("前伸");
        dynamic_side_header.createChekTab(list_dynamic_side_header);

        dynamic_jian = findView(R.id.dynamic_jian);
        dynamic_bg_header = findView(R.id.dynamic_bg_header);
        List<String> list_dynamic_jian = new ArrayList<>();
        list_dynamic_jian.add("左肩偏高");
        list_dynamic_jian.add("右肩偏高");
        dynamic_jian.createChekTab(list_dynamic_jian);
        List<String> list_dynamic_bg_header = new ArrayList<>();
        list_dynamic_bg_header.add("偏左");
        list_dynamic_bg_header.add("偏右");
        dynamic_bg_header.createChekTab(list_dynamic_bg_header);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dynamicAssessmentActivity = (DynamicAssessmentActivity) context;
        this.mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dynamicAssessmentActivity = null;
    }

    @Override
    public void update(Object data) {
        dynamicAssessmentActivity = (DynamicAssessmentActivity) getActivity();
        Map<String, String> map = (Map<String, String>) data;
        String type = map.get("type");
        if (DynamicAssessmentActivity.STEP3.equals(type)) {
            if(dynamicAssessmentActivity == null){
                dynamicAssessmentActivity = (DynamicAssessmentActivity) mContext;
            }

            dynamicAssessmentActivity.getDynamicRequestBody().setBtZmZbWb(dynamic_foot_out.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setBtZmXgZx(dynamic_knee_left.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setBtZmXgYx(dynamic_knee_right.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setBtCmPg(dynamic_yao_pg.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setBtCmFb(dynamic_yao_fb.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setBtCmTb(dynamic_side_header.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setBtBmGdj (dynamic_jian.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setBtBmTb(dynamic_bg_header.getCheckedResult());


            dynamicAssessmentActivity.judgeNext();
        }

    }

}