package com.yijian.staff.mvp.main.mine.editpassword;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class EditPasswordActivity extends MvcBaseActivity {


    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_passwd)
    EditText etPasswd;
    @BindView(R.id.et_re_passwd)
    EditText etRePasswd;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_edit_password;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.edit_password_activity_navigation_bar2);
        navigationBar2.setTitle("修改密码");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);

        String name = SharePreferenceUtil.getUserName();
        if (!TextUtils.isEmpty(name)) {
            tvUserName.setText(name);
        }
    }


    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        String username = tvUserName.getText().toString();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            showToast("原密码不能为空！");
            return;
        }
        String psd = etPasswd.getText().toString();
        if (TextUtils.isEmpty(psd)) {
            showToast("新密码不能为空！");
            return;
        }
        String psd2 = etRePasswd.getText().toString();
        if (TextUtils.isEmpty(psd2)) {
            showToast("确认密码不能为空！");
            return;
        }
        if (!psd.equals(psd2)) {
            showToast("2次输入的密码不同");
            return;
        }
        if (!CommonUtil.isPassWordFormat(psd)) {
            showToast("新密码格式不正确,密码是数字和字母的6-20位组合！");
            return;
        }
        if (!CommonUtil.isPassWordFormat(psd2)) {
            showToast("确认密码格式不正确,密码是数字和字母的6-20位组合！");
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("newPwd", psd);
        params.put("confirmPwd", psd2);
        params.put("originalPwd", password);
        params.put("username", username);
        HttpManager.postHasHeaderHasParam(HttpManager.EDIT_PASSWORD_URL, params, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });
    }
}
