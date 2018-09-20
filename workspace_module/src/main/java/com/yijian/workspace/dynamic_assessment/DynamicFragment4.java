package com.yijian.workspace.dynamic_assessment;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.yijian.workspace.R;
import com.yijian.workspace.base.BaseSpaceFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DynamicFragment4 extends BaseSpaceFragment {


    private DynamicAssessmentActivity dynamicAssessmentActivity;
    private Context mContext;
    private DynamicCheckGroup dynamic_positive1_footer; //足部 扁平足
    private DynamicCheckGroup dynamic_positive1_knee; //膝盖 拇指外翻
    private DynamicCheckGroup dynamic_bg1_leftfooter; //足部-左脚跟
    private DynamicCheckGroup dynamic_bg1_rightfooter; //足部-右脚跟


    private DynamicCheckGroup dynamic_positive2_leftknee; //膝盖-左膝
    private DynamicCheckGroup dynamic_positive2_rightknee; //膝盖-右膝


    private DynamicCheckGroup dynamic_bg3_jjg; //肩   肩胛骨外翻
    private DynamicCheckGroup dynamic_bg3_jiansz; //肩   食指与隆锥距离远


    private DynamicCheckGroup dynamic_bg4_jsb; //肩部  手臂不受控制外展
    private DynamicCheckGroup dynamic_bg4_jj; //肩部  肩胛上提




    @Override
    public int getLayoutId() {
        return R.layout.fragment_dynamic_fragment4;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initView() {
        initIdRes();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initIdRes() {
        View rootView=getRootView();

        dynamic_positive1_footer = rootView.findViewById(R.id.dynamic_positive1_footer);
        dynamic_positive1_knee = rootView.findViewById(R.id.dynamic_positive1_knee);
        dynamic_bg1_leftfooter = rootView.findViewById(R.id.dynamic_bg1_leftfooter);
        dynamic_bg1_rightfooter = rootView.findViewById(R.id.dynamic_bg1_rightfooter);
        List<String> list_dynamic_positive1_footer = new ArrayList<>();
        list_dynamic_positive1_footer.add("左足");
        list_dynamic_positive1_footer.add("右足");
        dynamic_positive1_footer.createChekTab(list_dynamic_positive1_footer);
        List<String> list_dynamic_positive1_knee = new ArrayList<>();
        list_dynamic_positive1_knee.add("左足");
        list_dynamic_positive1_knee.add("右足");
        dynamic_positive1_knee.createChekTab(list_dynamic_positive1_knee);
        List<String> list_dynamic_bg1_leftfooter = new ArrayList<>();
        list_dynamic_bg1_leftfooter.add("内斜");
        list_dynamic_bg1_leftfooter.add("外斜");
        dynamic_bg1_leftfooter.createChekTab(list_dynamic_bg1_leftfooter);
        List<String> list_dynamic_bg1_rightfooter = new ArrayList<>();
        list_dynamic_bg1_rightfooter.add("内斜");
        list_dynamic_bg1_rightfooter.add("外斜");
        dynamic_bg1_rightfooter.createChekTab(list_dynamic_bg1_rightfooter);

        dynamic_positive2_leftknee = rootView.findViewById(R.id.dynamic_positive2_leftknee);
        dynamic_positive2_rightknee = rootView.findViewById(R.id.dynamic_positive2_rightknee);
        List<String> list_dynamic_positive2_leftknee = new ArrayList<>();
        list_dynamic_positive2_leftknee.add("内扣");
        list_dynamic_positive2_leftknee.add("外翻");
        dynamic_positive2_leftknee.createChekTab(list_dynamic_positive2_leftknee);
        List<String> list_dynamic_positive2_rightknee = new ArrayList<>();
        list_dynamic_positive2_rightknee.add("内扣");
        list_dynamic_positive2_rightknee.add("外翻");
        dynamic_positive2_rightknee.createChekTab(list_dynamic_positive2_rightknee);

        dynamic_bg3_jjg = rootView.findViewById(R.id.dynamic_bg3_jjg);
        dynamic_bg3_jiansz = rootView.findViewById(R.id.dynamic_bg3_jiansz);
        List<String> list_dynamic_bg3_jjg = new ArrayList<>();
        list_dynamic_bg3_jjg.add("左肩");
        list_dynamic_bg3_jjg.add("右肩");
        dynamic_bg3_jjg.createChekTab(list_dynamic_bg3_jjg);
        List<String> list_dynamic_bg3_jiansz = new ArrayList<>();
        list_dynamic_bg3_jiansz.add("左手");
        list_dynamic_bg3_jiansz.add("右手");
        dynamic_bg3_jiansz.createChekTab(list_dynamic_bg3_jiansz);

        dynamic_bg4_jsb = rootView.findViewById(R.id.dynamic_bg4_jsb);
        dynamic_bg4_jj = rootView.findViewById(R.id.dynamic_bg4_jj);
        List<String> list_dynamic_bg4_jsb = new ArrayList<>();
        list_dynamic_bg4_jsb.add("左肩");
        list_dynamic_bg4_jsb.add("右肩");
        dynamic_bg4_jsb.createChekTab(list_dynamic_bg4_jsb);
        List<String> list_dynamic_bg4_jj = new ArrayList<>();
        list_dynamic_bg4_jj.add("左肩");
        list_dynamic_bg4_jj.add("右肩");
        dynamic_bg4_jj.createChekTab(list_dynamic_bg4_jj);

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
        if (DynamicAssessmentActivity.STEP4.equals(type)) {
            if(dynamicAssessmentActivity == null){
                dynamicAssessmentActivity = (DynamicAssessmentActivity) mContext;
            }

            dynamicAssessmentActivity.getDynamicRequestBody().setZl1ZmZb(dynamic_positive1_footer.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setZl1ZmXg(dynamic_positive1_knee.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setZl1BmZbl(dynamic_bg1_leftfooter.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setZl1BmZbr(dynamic_bg1_rightfooter.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setZl2ZmXgzx(dynamic_positive2_leftknee.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setZl2ZmXgzx(dynamic_positive2_rightknee.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setZl3BmJgwf(dynamic_bg3_jjg.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setZl3BmSzjl(dynamic_bg3_jiansz.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setZl4BmSbkz(dynamic_bg4_jsb.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setZl4BmJxst(dynamic_bg4_jj.getCheckedResult());

            dynamicAssessmentActivity.judgeNext();
        }

    }

}
