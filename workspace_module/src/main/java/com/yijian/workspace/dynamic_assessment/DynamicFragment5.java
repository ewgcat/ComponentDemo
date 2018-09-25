package com.yijian.workspace.dynamic_assessment;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.view.View;

import com.yijian.workspace.R;
import com.yijian.workspace.base.BaseSpaceFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DynamicFragment5 extends BaseSpaceFragment {


    private DynamicAssessmentActivity dynamicAssessmentActivity;
    private Context mContext;

    private DynamicCheckGroup dynamic_positive_leftknee; //膝盖-左膝
    private DynamicCheckGroup dynamic_positive_rightknee; //膝盖-右膝
    private DynamicCheckGroup dynamic_positive_jian; //肩部 高低肩
    private DynamicCheckGroup dynamic_side_yao_pg; //腰椎-盆骨-髋-盆骨
    private DynamicCheckGroup dynamic_side_yao_fb; //腰椎-盆骨-髋-腹部
    private DynamicCheckGroup dynamic_bg_translate; //整体  整体偏移
    private DynamicCheckGroup dynamic_bg_yb; //腰部  腰部两侧（多裂肌下段）



    @Override
    public int getLayoutId() {
        return R.layout.fragment_dynamic_fragment5;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initView() {
        initIdRes();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initIdRes() {
        View rootView=getRootView();

        dynamic_positive_leftknee = rootView.findViewById(R.id.dynamic_positive_leftknee);
        dynamic_positive_rightknee = rootView.findViewById(R.id.dynamic_positive_rightknee);
        dynamic_positive_jian = rootView.findViewById(R.id.dynamic_positive_jian);
        List<String> list_dynamic_positive_leftknee = new ArrayList<>();
        list_dynamic_positive_leftknee.add("内扣");
        list_dynamic_positive_leftknee.add("外翻");
        dynamic_positive_leftknee.createChekTab(list_dynamic_positive_leftknee);
        List<String> list_dynamic_positive_rightknee = new ArrayList<>();
        list_dynamic_positive_rightknee.add("内扣");
        list_dynamic_positive_rightknee.add("外翻");
        dynamic_positive_rightknee.createChekTab(list_dynamic_positive_rightknee);
        List<String> list_dynamic_positive_jian = new ArrayList<>();
        list_dynamic_positive_jian.add("左肩偏高");
        list_dynamic_positive_jian.add("右肩偏高");
        dynamic_positive_jian.createChekTab(list_dynamic_positive_jian);

        dynamic_side_yao_pg = rootView.findViewById(R.id.dynamic_side_yao_pg);
        dynamic_side_yao_fb = rootView.findViewById(R.id.dynamic_side_yao_fb);
        List<String> list_dynamic_side_yao_pg = new ArrayList<>();
        list_dynamic_side_yao_pg.add("后倾");
        dynamic_side_yao_pg.createChekTab(list_dynamic_side_yao_pg);
        List<String> list_dynamic_side_yao_fb = new ArrayList<>();
        list_dynamic_side_yao_fb.add("突出");
        dynamic_side_yao_fb.createChekTab(list_dynamic_side_yao_fb);

        dynamic_bg_translate = rootView.findViewById(R.id.dynamic_bg_translate);
        dynamic_bg_yb = rootView.findViewById(R.id.dynamic_bg_yb);
        List<String> list_dynamic_bg_translate = new ArrayList<>();
        list_dynamic_bg_translate.add("向左");
        list_dynamic_bg_translate.add("向右");
        dynamic_bg_translate.createChekTab(list_dynamic_bg_translate);
        List<String> list_dynamic_bg_yb = new ArrayList<>();
        list_dynamic_bg_yb.add("变宽变硬");
        dynamic_bg_yb.createChekTab(list_dynamic_bg_yb);

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
        if (DynamicAssessmentActivity.STEP5.equals(type)) {
            if(dynamicAssessmentActivity == null){
                dynamicAssessmentActivity = (DynamicAssessmentActivity) mContext;
            }

            dynamicAssessmentActivity.getDynamicRequestBody().setYlZmZx(dynamic_positive_leftknee.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setYlZmYx(dynamic_positive_rightknee.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setYlZmZxj(dynamic_positive_jian.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setYlCmZt(dynamic_side_yao_pg.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setYlCmYb(dynamic_side_yao_fb.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setYlBmZt(dynamic_bg_translate.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setYlBmYb(dynamic_bg_yb.getCheckedResult());



            dynamicAssessmentActivity.judgeNext();
        }

    }

}