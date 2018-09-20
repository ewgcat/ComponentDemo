package com.yijian.workspace.dynamic_assessment;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.yijian.workspace.R;
import com.yijian.workspace.base.BaseSpaceFragment;
import com.yijian.workspace.utils.ActivityUtils;

import java.util.Map;


public class DynamicFragment1  extends BaseSpaceFragment {

    private DynamicAssessmentActivity dynamicAssessmentActivity;
    private Context mContext;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dynamic_fragment1;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Test", "activity======" + getActivity());
        dynamicAssessmentActivity = (DynamicAssessmentActivity) context;
        this.mContext = context;
        Log.e("Test", "mContext======" +mContext);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dynamicAssessmentActivity = null;
    }


    @Override
    public void update(Object data) {
        Map<String, String> map = (Map<String, String>) data;
        String type = map.get("type");
        if (DynamicAssessmentActivity.STEP1.equals(type)) {
            if(dynamicAssessmentActivity == null){
                dynamicAssessmentActivity = (DynamicAssessmentActivity) mContext;
            }

            dynamicAssessmentActivity.getDynamicRequestBody().setGender(Integer.parseInt(ActivityUtils.workSpaceVipBean.getGender()));
            dynamicAssessmentActivity.getDynamicRequestBody().setBirthday(TextUtils.isEmpty(ActivityUtils.workSpaceVipBean.getBirthday()) ? "" : ActivityUtils.workSpaceVipBean.getBirthday());
//            dynamicAssessmentActivity.getDynamicRequestBody().setBirthday("2018-08-09");
            dynamicAssessmentActivity.getDynamicRequestBody().setMemberId(ActivityUtils.workSpaceVipBean.getMemberId());
            dynamicAssessmentActivity.judgeNext();
        }

    }

}
