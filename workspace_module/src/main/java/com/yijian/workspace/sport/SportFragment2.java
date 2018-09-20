package com.yijian.workspace.sport;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yijian.workspace.R;
import com.yijian.workspace.base.BaseSpaceFragment;
import com.yijian.workspace.utils.ActivityUtils;

import java.util.Map;


public class SportFragment2 extends BaseSpaceFragment {

    EditText et_zhanli;
    EditText et_jieqiu;
    EditText et_mofan;
    private Context mContext;
    private SportTestActivity sportTestActivity;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sport_fragment2;
    }

    @Override
    public void initView() {
        View rootView=getRootView();

        et_zhanli = rootView.findViewById(R.id.et_zhanli);
        et_jieqiu = rootView.findViewById(R.id.et_jieqiu);
        et_mofan = rootView.findViewById(R.id.et_mofan);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void update(Object data) {
        Map<String, String> map = (Map<String, String>) data;
        String type = map.get("type");
        if (SportTestActivity.STEP2.equals(type)) {
//            SportStepRequedtBody sportStepRequedtBody = new SportStepRequedtBody();
            sportTestActivity = (SportTestActivity) mContext;
            sportTestActivity.getSportStepRequedtBody().setGender(Integer.parseInt(ActivityUtils.workSpaceVipBean.getGender()));
            if (TextUtils.isEmpty(et_zhanli.getText().toString())) {
                Toast.makeText(mContext,"单脚闭眼站立时间不能为空",Toast.LENGTH_SHORT).show();
                return;
            } else {
                sportTestActivity.getSportStepRequedtBody().setDjbyzl(Integer.parseInt(et_zhanli.getText().toString()));
            }
            if (TextUtils.isEmpty(et_jieqiu.getText().toString())) {
                Toast.makeText(mContext,"接球次数不能为空",Toast.LENGTH_SHORT).show();
                return;
            } else {
                sportTestActivity.getSportStepRequedtBody().setJqCount(Integer.parseInt(et_jieqiu.getText().toString()));
            }
            if (TextUtils.isEmpty(et_mofan.getText().toString())) {
                Toast.makeText(mContext,"模仿动作时间不能为空",Toast.LENGTH_SHORT).show();
                return;
            } else {
                sportTestActivity.getSportStepRequedtBody().setMfCount(Integer.parseInt(et_mofan.getText().toString()));
            }
            sportTestActivity.getSportStepRequedtBody().setMemberId(ActivityUtils.workSpaceVipBean.getMemberId());
            sportTestActivity.judgeNext(SportTestActivity.TYPE_STEP_NEXT_SUCCESS);
            /*sportStepRequedtBody.setStep(2);
            showLoading();
            HttpManager.postSportInfo(sportStepRequedtBody, new ResultStringObserver() {

                @Override
                public void onSuccess(String result) {
                    hideLoading();
                    Toast.makeText(mContext, "提交成功", Toast.LENGTH_SHORT).show();
                    ((SportTestActivity)mContext).judgeNext(SportTestActivity.TYPE_STEP_NEXT_SUCCESS);
                }

                @Override
                public void onFail(String msg) {
                    hideLoading();
                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                }
            });*/
        }
    }

}
