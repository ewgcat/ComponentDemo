package com.yijian.workspace.perfect;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.commonlib.net.response.ResultStringObserver;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.workspace.R;
import com.yijian.workspace.bean.PerfectRequestBody;
import com.yijian.workspace.commen.ShareTestActivity;
import com.yijian.workspace.net.HttpManagerWorkSpace;
import com.yijian.workspace.observe.EditActionObservable;
import com.yijian.workspace.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

public class PerfectTestActivity extends MvcBaseActivity implements View.OnClickListener {

    TextView tv_sex;
    TextView tv_height;
    TextView et_jianwei;
    TextView et_xiongwei;
    TextView et_biwei;
    TextView et_yaowei;
    TextView et_tunwei;
    TextView et_tuiwei;

    private EditActionObservable editActionObservable = new EditActionObservable();
    List<String> sexList = new ArrayList<>();
    List<String> manHeightList = new ArrayList<>(); //男的身高集合  150--190
    List<String> womenHeightList = new ArrayList<>(); //女的身高集合  150--180
    String imgUrl = "";

    @Override
    protected int getLayoutID() {
        return R.layout.activity_perfect_test;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);



        tv_sex = findViewById(R.id.tv_sex);
        tv_height = findViewById(R.id.tv_height);
        et_jianwei = findViewById(R.id.et_jianwei);
        et_xiongwei = findViewById(R.id.et_xiongwei);
        et_biwei = findViewById(R.id.et_biwei);
        et_yaowei = findViewById(R.id.et_yaowei);
        et_tunwei = findViewById(R.id.et_tunwei);
        et_tuiwei = findViewById(R.id.et_tuiwei);

        findViewById(R.id.btn_finish).setOnClickListener(this);
        findViewById(R.id.rel_height).setOnClickListener(this);
                initTitle();
        initData();
    }

    private void initData() {
        if (getIntent().getExtras() != null) {
            imgUrl = getIntent().getExtras().getString("imgUrl");
        }
        tv_sex.setText("1".equals(ActivityUtils.workSpaceVipBean.getGender()) ? "男" : "女");
        //初始化性别集合
        sexList.add("男");
        sexList.add("女");

        for (int i = 150; i <= 190; i++) {
            manHeightList.add(String.valueOf(i));
        }
        for (int i = 150; i <= 180; i++) {
            womenHeightList.add(String.valueOf(i));
        }
    }

    private void initTitle() {
        NavigationBar navigationBar = findViewById(R.id.navigation_bar);
        navigationBar.setTitle("完美围度测试");
        navigationBar.hideLeftSecondIv();
        navigationBar.setBackClickListener(this);
    }

    /**
     * 选项弹出框
     */
    private void manualPickedView(final List<String> opts, String defaultValue, String title, final TextView tv) {
        OptionsPickerView pvNoLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
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
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_finish) {
            showLoading();
            PerfectRequestBody perfectRequestBody = new PerfectRequestBody();
            perfectRequestBody.setGender(Integer.parseInt(ActivityUtils.workSpaceVipBean.getGender()));
            if (TextUtils.isEmpty(tv_height.getText().toString())) {
                Toast.makeText(PerfectTestActivity.this, "身高不能为空", Toast.LENGTH_SHORT).show();
            } else {
                perfectRequestBody.setHeight(Integer.parseInt(tv_height.getText().toString()));
            }
            if (TextUtils.isEmpty(et_jianwei.getText().toString())) {
                Toast.makeText(PerfectTestActivity.this, "肩围不能为空", Toast.LENGTH_SHORT).show();
            } else {
                perfectRequestBody.setJw(Double.parseDouble(et_jianwei.getText().toString()));
            }

            if (TextUtils.isEmpty(et_xiongwei.getText().toString())) {
                Toast.makeText(PerfectTestActivity.this, "胸围不能为空", Toast.LENGTH_SHORT).show();
            } else {
                perfectRequestBody.setXw(Double.parseDouble(et_xiongwei.getText().toString()));
            }
            if (TextUtils.isEmpty(et_yaowei.getText().toString())) {
                Toast.makeText(PerfectTestActivity.this, "腰围不能为空", Toast.LENGTH_SHORT).show();
            } else {
                perfectRequestBody.setWaist(Double.parseDouble(et_yaowei.getText().toString()));
            }
            if (TextUtils.isEmpty(et_tuiwei.getText().toString())) {
                Toast.makeText(PerfectTestActivity.this, "腿围不能为空", Toast.LENGTH_SHORT).show();
            } else {
                perfectRequestBody.setDtw(TextUtils.isEmpty(et_tuiwei.getText().toString()) ? 0 : Double.parseDouble(et_tuiwei.getText().toString()));
            }
            if (TextUtils.isEmpty(et_tunwei.getText().toString())) {
                Toast.makeText(PerfectTestActivity.this, "臀围不能为空", Toast.LENGTH_SHORT).show();
            } else {
                perfectRequestBody.setHipline(TextUtils.isEmpty(et_tunwei.getText().toString()) ? 0 : Double.parseDouble(et_tunwei.getText().toString()));
            }
            if (TextUtils.isEmpty(et_biwei.getText().toString())) {
                Toast.makeText(PerfectTestActivity.this, "臂围不能为空", Toast.LENGTH_SHORT).show();
            } else {
                perfectRequestBody.setDtunw(TextUtils.isEmpty(et_biwei.getText().toString()) ? 0 : Double.parseDouble(et_biwei.getText().toString()));
            }
            perfectRequestBody.setMemberId(ActivityUtils.workSpaceVipBean.getMemberId());
            perfectRequestBody.setUrl1(imgUrl);
            HttpManagerWorkSpace.postPerfectInfo(perfectRequestBody, new ResultStringObserver(getLifecycle()) {

                @Override
                public void onSuccess(String result) {
                    hideLoading();
                    Toast.makeText(PerfectTestActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("recordId", result);
                    ActivityUtils.startActivity(PerfectTestActivity.this, ShareTestActivity.class, bundle);
                    finish();
                }

                @Override
                public void onFail(String msg) {
                    hideLoading();
                    Toast.makeText(PerfectTestActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });

        } else if (i == R.id.rel_height) {
            manualPickedView("男".equals(tv_sex.getText().toString()) ? manHeightList : womenHeightList,
                    "0", "选择身高", tv_height);

        } else {
        }
    }

}
