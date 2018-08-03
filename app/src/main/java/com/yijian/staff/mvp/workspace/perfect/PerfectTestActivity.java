package com.yijian.staff.mvp.workspace.perfect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.course.preparelessons.createlession.EditActionObservable;
import com.yijian.staff.mvp.workspace.bean.PerfectRequestBody;
import com.yijian.staff.mvp.workspace.commen.ShareTestActivity;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.net.response.ResultStringObserver;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PerfectTestActivity extends MvcBaseActivity {

    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_height)
    TextView tv_height;
    @BindView(R.id.et_jianwei)
    TextView et_jianwei;
    @BindView(R.id.et_xiongwei)
    TextView et_xiongwei;
    @BindView(R.id.et_biwei)
    TextView et_biwei;
    @BindView(R.id.et_yaowei)
    TextView et_yaowei;
    @BindView(R.id.et_tunwei)
    TextView et_tunwei;
    @BindView(R.id.et_tuiwei)
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
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.setTitle("完美围度测试");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
    }

    @OnClick({R.id.btn_finish, R.id.rel_height})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_finish: //完成
                showLoading();
                PerfectRequestBody perfectRequestBody = new PerfectRequestBody();
                perfectRequestBody.setGender(Integer.parseInt(ActivityUtils.workSpaceVipBean.getGender()));
//                perfectRequestBody.setGender(1);
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
                HttpManager.postPerfectInfo(perfectRequestBody, new ResultStringObserver(getLifecycle()) {

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
                break;
            case R.id.rel_height: //性别身高
                manualPickedView("男".equals(tv_sex.getText().toString()) ? manHeightList : womenHeightList,
                        "0", "选择身高", tv_height);
                break;
            default:
        }
    }

    /**
     * 选项弹出框
     */
    private void manualPickedView(List<String> opts, String defaultValue, String title, TextView tv) {
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

}
