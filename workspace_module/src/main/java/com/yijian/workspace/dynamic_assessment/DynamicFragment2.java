package com.yijian.workspace.dynamic_assessment;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.yijian.workspace.R;
import com.yijian.workspace.base.BaseSpaceFragment;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DynamicFragment2 extends BaseSpaceFragment {


    private DynamicAssessmentActivity dynamicAssessmentActivity;
    private Context mContext;
    private DynamicCheckGroup dynamic_translate,dynamic_rotate;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dynamic_fragment2;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initView() {
        initIdRes();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initIdRes() {
        dynamic_translate = findView(R.id.dynamic_translate);
        dynamic_rotate = findView(R.id.dynamic_rotate);
        List<String> translateList = new ArrayList<>();
        translateList.add("前进");
        translateList.add("后退");
        dynamic_translate.createChekTab(translateList);
        List<String> rotateList = new ArrayList<>();
        rotateList.add("向左");
        rotateList.add("向右");
        dynamic_rotate.createChekTab(rotateList);
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
        if (DynamicAssessmentActivity.STEP2.equals(type)) {
            if(dynamicAssessmentActivity == null){
                dynamicAssessmentActivity = (DynamicAssessmentActivity) mContext;
            }
            dynamicAssessmentActivity.getDynamicRequestBody().setByWyqk(dynamic_translate.getCheckedResult());
            dynamicAssessmentActivity.getDynamicRequestBody().setByXzqk (dynamic_rotate.getCheckedResult());
            dynamicAssessmentActivity.judgeNext();
        }

    }

}