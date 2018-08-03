package com.yijian.staff.mvp.workspace.sport;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.workspace.base.BaseSpaceFragment;
import com.yijian.staff.mvp.workspace.bean.SportStepRequedtBody;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultStringObserver;

import java.util.Map;

import butterknife.BindView;

public class SportFragment3 extends BaseSpaceFragment {

    @BindView(R.id.et_qianqu)
    EditText et_qianqu;
    @BindView(R.id.et_jian_num)
    EditText et_jian_num;
    @BindView(R.id.et_xiongtui_num)
    EditText et_xiongtui_num;
    @BindView(R.id.et_tuijiqun_num)
    EditText et_tuijiqun_num;
    @BindView(R.id.et_jiaohuai_num)
    EditText et_jiaohuai_num;

    private Context mContext;
    private SportTestActivity sportTestActivity;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sport_fragment3;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sportTestActivity = null;
    }

    @Override
    public void update(Object data) {
        Map<String, String> map = (Map<String, String>) data;
        String type = map.get("type");
        if (SportTestActivity.STEP3.equals(type)) {
//            SportStepRequedtBody sportStepRequedtBody = new SportStepRequedtBody();
            sportTestActivity = (SportTestActivity) mContext;
            sportTestActivity.getSportStepRequedtBody().setGender(Integer.parseInt(ActivityUtils.workSpaceVipBean.getGender()));
            if (TextUtils.isEmpty(et_qianqu.getText().toString())) {
                Toast.makeText(mContext,"坐位体前屈距离不能为空",Toast.LENGTH_SHORT).show();
                return;
            } else {
                sportTestActivity.getSportStepRequedtBody().setZwtqq(Double.parseDouble(et_qianqu.getText().toString()));
            }
            if (TextUtils.isEmpty(et_jian_num.getText().toString())) {
                Toast.makeText(mContext,"肩部测试动作不能为空",Toast.LENGTH_SHORT).show();
                return;
            } else {
                sportTestActivity.getSportStepRequedtBody().setJbcsdz(Double.parseDouble(et_jian_num.getText().toString()));
            }
            if (TextUtils.isEmpty(et_xiongtui_num.getText().toString())) {
                Toast.makeText(mContext,"胸椎测试动作不能为空",Toast.LENGTH_SHORT).show();
                return;
            } else {
                sportTestActivity.getSportStepRequedtBody().setSjcsdz(Double.parseDouble(et_xiongtui_num.getText().toString()));
            }

            if (TextUtils.isEmpty(et_tuijiqun_num.getText().toString())) {
                Toast.makeText(mContext,"腿后肌群测试动作不能为空",Toast.LENGTH_SHORT).show();
                return;
            } else {
                sportTestActivity.getSportStepRequedtBody().setThjqcsdz(Double.parseDouble(et_tuijiqun_num.getText().toString()));
            }

            if (TextUtils.isEmpty(et_jiaohuai_num.getText().toString())) {
                Toast.makeText(mContext,"脚踝测试动作不能为空",Toast.LENGTH_SHORT).show();
                return;
            } else {
                sportTestActivity.getSportStepRequedtBody().setJgcsdz(Double.parseDouble(et_jiaohuai_num.getText().toString()));
            }

            sportTestActivity.getSportStepRequedtBody().setMemberId(ActivityUtils.workSpaceVipBean.getMemberId());
            sportTestActivity.judgeNext(SportTestActivity.TYPE_STEP_NEXT_SUCCESS);
            /*sportStepRequedtBody.setStep(3);
            showLoading();
            HttpManager.postSportInfo(sportStepRequedtBody, new ResultStringObserver() {

                @Override
                public void onSuccess(String result) {
                    hideLoading();
                    Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
                    ((SportTestActivity)getActivity()).judgeNext(SportTestActivity.TYPE_STEP_NEXT_SUCCESS);
                }

                @Override
                public void onFail(String msg) {
                    hideLoading();
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                }
            });*/
        }
    }

}
