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

import com.yijian.workspace.R;
import com.yijian.workspace.base.BaseSpaceFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DynamicFragment6 extends BaseSpaceFragment {


    private DynamicAssessmentActivity dynamicAssessmentActivity;
    private Context mContext;

    private DynamicCheckGroup dynamic_lower_leg; //小腿
    private DynamicCheckGroup dynamic_big_leg; //大腿
    private DynamicCheckGroup dynamic_leg_lie; //腿（躺着时）
    private DynamicCheckGroup dynamic_leg_sit; //腿（坐着时）


    @Override
    public int getLayoutId() {
        return R.layout.fragment_dynamic_fragment6;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initView() {
        initIdRes();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initIdRes() {
        dynamic_lower_leg = findView(R.id.dynamic_lower_leg);
        dynamic_big_leg = findView(R.id.dynamic_big_leg);
        List<String> list_dynamic_lower_leg = new ArrayList<>();
        list_dynamic_lower_leg.add("左腿偏长");
        list_dynamic_lower_leg.add("右腿偏长");
        dynamic_lower_leg.createChekTab(list_dynamic_lower_leg);
        List<String> list_dynamic_big_leg = new ArrayList<>();
        list_dynamic_big_leg.add("左腿偏长");
        list_dynamic_big_leg.add("右腿偏长");
        dynamic_big_leg.createChekTab(list_dynamic_big_leg);

        dynamic_leg_lie = findView(R.id.dynamic_leg_lie);
        List<String> list_dynamic_leg_lie = new ArrayList<>();
        list_dynamic_leg_lie.add("左腿偏长");
        list_dynamic_leg_lie.add("右腿偏长");
        dynamic_leg_lie.createChekTab(list_dynamic_leg_lie);

        dynamic_leg_sit = findView(R.id.dynamic_leg_sit);
        List<String> list_dynamic_leg_sit = new ArrayList<>();
        list_dynamic_leg_sit.add("左腿偏长");
        list_dynamic_leg_sit.add("右腿偏长");
        dynamic_leg_sit.createChekTab(list_dynamic_leg_sit);

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
        if (DynamicAssessmentActivity.STEP6.equals(type)) {
            if(dynamicAssessmentActivity == null){
                dynamicAssessmentActivity = (DynamicAssessmentActivity) mContext;
            }

            dynamicAssessmentActivity.getDynamicRequestBody().setPt1Xt(dynamic_lower_leg.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setPt1Dt(dynamic_big_leg.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setPt2Tt(dynamic_leg_lie.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setPt3Zt(dynamic_leg_sit.getCheckedResult());

            dynamicAssessmentActivity.judgeNext();
        }

    }

}