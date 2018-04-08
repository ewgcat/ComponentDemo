package com.yijian.staff.mvp.huiji.potential;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.OptionsPickerView;
import com.yijian.staff.R;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.addpotential.AddPotentialRequestBody;
import com.yijian.staff.net.response.ResultObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.widget.LastInputEditText;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


@Route(path = "/test/7")
public class AddPotentialActivity extends AppCompatActivity {

    @BindView(R.id.et_name)
    LastInputEditText etName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.et_phone)
    LastInputEditText etPhone;
    private OptionsPickerView optionsPickerView;

    private int sex = 1;//1 男  2女

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_potential);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.add_potential_activity_navigation_bar);
        navigationBar2.setTitle("添加潜在");
        navigationBar2.setmRightTvText("完成");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendRequest();
            }


        });


        ArrayList<String> sexDescList = new ArrayList<>();
        sexDescList.add("男");
        sexDescList.add("女");

        optionsPickerView = new OptionsPickerView.Builder(AddPotentialActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvSex.setText(sexDescList.get(options1));
                sex = options1+1;
            }
        }).build();
        optionsPickerView.setPicker(sexDescList);
        optionsPickerView.setSelectOptions(0);
    }

    private void sendRequest() {

        String name = etName.getText().toString();
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
            Toast.makeText(AddPotentialActivity.this,"名字不能为空!",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(AddPotentialActivity.this,"手机号不能为空!",Toast.LENGTH_SHORT).show();
            return;
        }

        if (CommonUtil.isPhoneFormat(phone)) {
            AddPotentialRequestBody addPotentialRequestBody=new AddPotentialRequestBody(phone,name,sex);
            HttpManager.postAddPotential(addPotentialRequestBody, new ResultObserver() {
                @Override
                public void onSuccess(JSONObject result) {
                    hideKeyBoard(etPhone);
                    finish();
                }
                @Override
                public void onFail(String msg) {
                    Toast.makeText(AddPotentialActivity.this,msg,Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(AddPotentialActivity.this,"手机号码不正确!",Toast.LENGTH_SHORT).show();
            return;
        }

    }

    @OnClick({R.id.tv_sex})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sex:
                hideKeyBoard(view);
                optionsPickerView.show();
                break;
        }
    }

    /**
     * 隐藏键盘
     */
    public void hideKeyBoard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    /**
     * 显示键盘
     */
    public void showKeyBoard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(v, 0);

        }
    }
}
