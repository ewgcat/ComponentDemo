package com.yijian.workspace.sport;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.yijian.workspace.R;
import com.yijian.workspace.base.BaseSpaceFragment;
import com.yijian.workspace.utils.ActivityUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SportFragment1 extends BaseSpaceFragment implements View.OnClickListener {

    TextView tv_sex;
    TextView tv_birth;
    TextView tv_height;
    EditText et_weight;
    EditText et_juanfu;
    EditText et_jian_weight;
    EditText et_jian_weight_num;
    EditText et_jian_xiongdaji;
    EditText et_xiongdaji_num;
    EditText et_jian_gong2ji;
    EditText et_gong2ji_num;
    EditText et_gong3ji;
    EditText et_gong3ji_num;
    EditText et_yaobeijiqun;
    EditText et_yaobeijiqun_num;
    EditText et_datuijiqun;
    EditText et_datuijiqun_num;
    EditText et_tiaoyuan;

    List<String> manHeightList = new ArrayList<>(); //男的身高集合  150--190
    List<String> womenHeightList = new ArrayList<>(); //女的身高集合  150--180
    List<String> weightList = new ArrayList<>(); //体重
    List<String> weightDecimalList = new ArrayList<>(); //体重
    private SportTestActivity sportTestActivity;
    private Context mContext;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sport_fragment1;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sportTestActivity = (SportTestActivity) context;
        this.mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sportTestActivity = null;
    }

    @Override
    public void initView() {
        initIdRes();
        initData();
    }

    private void initIdRes(){
        View rootView=getRootView();

        tv_sex = rootView.findViewById(R.id.tv_sex);
        tv_birth = rootView.findViewById(R.id.tv_birth);
        tv_height = rootView.findViewById(R.id.tv_height);
        et_weight = rootView.findViewById(R.id.et_weight);
        et_juanfu = rootView.findViewById(R.id.et_juanfu);
        et_jian_weight = rootView.findViewById(R.id.et_jian_weight);
        et_jian_weight_num = rootView.findViewById(R.id.et_jian_weight_num);
        et_jian_xiongdaji = rootView.findViewById(R.id.et_jian_xiongdaji);
        et_xiongdaji_num = rootView.findViewById(R.id.et_xiongdaji_num);
        et_jian_gong2ji = rootView.findViewById(R.id.et_jian_gong2ji);
        et_gong2ji_num = rootView.findViewById(R.id.et_gong2ji_num);
        et_gong3ji = rootView.findViewById(R.id.et_gong3ji);
        et_gong3ji_num = rootView.findViewById(R.id.et_gong3ji_num);
        et_yaobeijiqun = rootView.findViewById(R.id.et_yaobeijiqun);
        et_yaobeijiqun_num = rootView.findViewById(R.id.et_yaobeijiqun_num);
        et_datuijiqun = rootView.findViewById(R.id.et_datuijiqun);
        et_datuijiqun_num = rootView.findViewById(R.id.et_datuijiqun_num);
        et_tiaoyuan = rootView.findViewById(R.id.et_tiaoyuan);

                rootView.findViewById(R.id.rel_birthday).setOnClickListener(this);
                rootView.findViewById(R.id.rel_height).setOnClickListener(this);
    }

    private void initData() {
        tv_birth.setText(ActivityUtils.workSpaceVipBean.getBirthday());
        tv_sex.setText("1".equals(ActivityUtils.workSpaceVipBean.getGender()) ? "男" : "女");
        for (int i = 150; i <= 190; i++) {
            manHeightList.add(String.valueOf(i));
        }
        for (int i = 150; i <= 180; i++) {
            womenHeightList.add(String.valueOf(i));
        }
        for (int i = 30; i < 100; i++) {
            weightList.add(i + "");
        }
        weightDecimalList.add(".0");
        weightDecimalList.add(".1");
        weightDecimalList.add(".2");
        weightDecimalList.add(".3");
        weightDecimalList.add(".4");
        weightDecimalList.add(".5");
        weightDecimalList.add(".6");
        weightDecimalList.add(".7");
        weightDecimalList.add(".8");
        weightDecimalList.add(".9");
    }



    /**
     * 选项弹出框
     */
    private void manualPickedView(final List<String> opts, String defaultValue, String title, final TextView tv) {
        OptionsPickerView pvNoLinkOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv.setText(opts.get(options1));
            }
        }).build();

        pvNoLinkOptions.setNPicker(opts, null, null);
        pvNoLinkOptions.setSelectOptions(opts.indexOf(defaultValue));
        pvNoLinkOptions.setTitleText(title);
        pvNoLinkOptions.show();
    }

    @Override
    public void update(Object data) {
        Map<String, String> map = (Map<String, String>) data;
        String type = map.get("type");
        if (SportTestActivity.STEP1.equals(type)) {
            //            SportStepRequedtBody sportStepRequedtBody = new SportStepRequedtBody();
            sportTestActivity = (SportTestActivity) mContext;
            sportTestActivity.getSportStepRequedtBody().setGender(Integer.parseInt(ActivityUtils.workSpaceVipBean.getGender()));
            if (TextUtils.isEmpty(tv_birth.getText().toString())) {
                Toast.makeText(mContext,"生日不能为空",Toast.LENGTH_SHORT).show();
                return;
            } else {
                sportTestActivity.getSportStepRequedtBody().setBirthday(tv_birth.getText().toString());
            }
            if (TextUtils.isEmpty(tv_height.getText().toString())) {
                Toast.makeText(mContext,"身高不能为空",Toast.LENGTH_SHORT).show();
                return;
            } else {
                sportTestActivity.getSportStepRequedtBody().setHeight(Integer.parseInt(tv_height.getText().toString()));
            }
            if (TextUtils.isEmpty(et_weight.getText().toString())) {
                Toast.makeText(mContext,"体重不能为空",Toast.LENGTH_SHORT).show();
                return;
            } else {
                sportTestActivity.getSportStepRequedtBody().setWeight(Double.parseDouble(et_weight.getText().toString()));
            }
            if(TextUtils.isEmpty(et_juanfu.getText().toString())){
                Toast.makeText(mContext,"卷腹次数不能为空",Toast.LENGTH_SHORT).show();
                return;
            }else{
                sportTestActivity.getSportStepRequedtBody().setJf(Integer.parseInt(et_juanfu.getText().toString()));
            }
            if(TextUtils.isEmpty(et_jian_weight.getText().toString())){
                Toast.makeText(mContext,"肩上推举重量不能为空",Toast.LENGTH_SHORT).show();
                return;
            }else{
                sportTestActivity.getSportStepRequedtBody().setJstjZl(Double.parseDouble(et_jian_weight.getText().toString()));
            }
            if(TextUtils.isEmpty(et_jian_weight_num.getText().toString())){
                Toast.makeText(mContext,"肩上推举次数不能为空",Toast.LENGTH_SHORT).show();
                return;
            }else{
                sportTestActivity.getSportStepRequedtBody().setJstjCount(Integer.parseInt(et_jian_weight_num.getText().toString()));
            }
            if(TextUtils.isEmpty(et_jian_xiongdaji.getText().toString())){
                Toast.makeText(mContext,"平板卧推重量不能为空",Toast.LENGTH_SHORT).show();
                return;
            }else{
                sportTestActivity.getSportStepRequedtBody().setPbwtZl(Double.parseDouble(et_jian_xiongdaji.getText().toString()));
            }
            if(TextUtils.isEmpty(et_xiongdaji_num.getText().toString())){
                Toast.makeText(mContext,"平板卧推数量不能为空",Toast.LENGTH_SHORT).show();
                return;
            }else{
                sportTestActivity.getSportStepRequedtBody().setPbwtCount(Integer.parseInt(et_xiongdaji_num.getText().toString()));
            }
            if(TextUtils.isEmpty(et_jian_gong2ji.getText().toString())){
                Toast.makeText(mContext,"肱二头肌手臂向心收缩重量不能为空",Toast.LENGTH_SHORT).show();
                return;
            }else{
                sportTestActivity.getSportStepRequedtBody().setSbxxssZl(Double.parseDouble(et_jian_gong2ji.getText().toString()));
            }
            if(TextUtils.isEmpty(et_gong2ji_num.getText().toString())){
                Toast.makeText(mContext,"肱二头肌手臂向心收缩次数不能为空",Toast.LENGTH_SHORT).show();
                return;
            }else{
                sportTestActivity.getSportStepRequedtBody().setSbxxssCount(Integer.parseInt(et_gong2ji_num.getText().toString()));
            }
            if(TextUtils.isEmpty(et_gong3ji.getText().toString())){
                Toast.makeText(mContext,"肱三头肌手臂向心收缩重量不能为空",Toast.LENGTH_SHORT).show();
                return;
            }else{
                sportTestActivity.getSportStepRequedtBody().setSbxxssZl2(Double.parseDouble(et_gong3ji.getText().toString()));
            }
            if(TextUtils.isEmpty(et_gong3ji_num.getText().toString())){
                Toast.makeText(mContext,"肱三头肌手臂向心收缩次数不能为空",Toast.LENGTH_SHORT).show();
                return;
            }else{
                sportTestActivity.getSportStepRequedtBody().setSbxxssCount2(Integer.parseInt(et_gong3ji_num.getText().toString()));
            }

            if(TextUtils.isEmpty(et_yaobeijiqun.getText().toString())){
                Toast.makeText(mContext,"直体负重硬拉重量不能为空",Toast.LENGTH_SHORT).show();
                return;
            }else{
                sportTestActivity.getSportStepRequedtBody().setZtfzylZl(Double.parseDouble(et_yaobeijiqun.getText().toString()));
            }
            if(TextUtils.isEmpty(et_yaobeijiqun_num.getText().toString())){
                Toast.makeText(mContext,"直体负重硬拉次数不能为空",Toast.LENGTH_SHORT).show();
                return;
            }else{
                sportTestActivity.getSportStepRequedtBody().setZtfzylCount(Integer.parseInt(et_yaobeijiqun_num.getText().toString()));
            }

            if(TextUtils.isEmpty(et_datuijiqun.getText().toString())){
                Toast.makeText(mContext,"负重深蹲重量不能为空",Toast.LENGTH_SHORT).show();
                return;
            }else{
                sportTestActivity.getSportStepRequedtBody().setFzsdZl(Double.parseDouble(et_datuijiqun.getText().toString()));
            }
            if(TextUtils.isEmpty(et_datuijiqun_num.getText().toString())){
                Toast.makeText(mContext,"负重深蹲次数不能为空",Toast.LENGTH_SHORT).show();
                return;
            }else{
                sportTestActivity.getSportStepRequedtBody().setFzsdCount(Integer.parseInt(et_datuijiqun_num.getText().toString()));
            }

            if(TextUtils.isEmpty(et_tiaoyuan.getText().toString())){
                Toast.makeText(mContext,"立定跳远距离不能为空",Toast.LENGTH_SHORT).show();
                return;
            }else{
                sportTestActivity.getSportStepRequedtBody().setLdty(Double.parseDouble(et_tiaoyuan.getText().toString()));
            }
            sportTestActivity.getSportStepRequedtBody().setMemberId(ActivityUtils.workSpaceVipBean.getMemberId());
            sportTestActivity.judgeNext(SportTestActivity.TYPE_STEP_NEXT_SUCCESS);
            /*sportStepRequedtBody.setStep(1);
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

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.rel_birthday) {
            TimePickerView pvTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    tv_birth.setText(simpleDateFormat.format(date));
                }
            }).setType(new boolean[]{true, true, true, false, false, false})
                    .build();
            pvTime.show();

        } else if (i == R.id.rel_height) {
            manualPickedView("男".equals(tv_sex.getText().toString()) ? manHeightList : womenHeightList,
                    "0", "选择身高", tv_height);

        } else {
        }
    }
}
