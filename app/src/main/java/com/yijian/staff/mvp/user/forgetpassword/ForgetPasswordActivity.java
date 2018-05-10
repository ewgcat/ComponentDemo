package com.yijian.staff.mvp.user.forgetpassword;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.CountDownTimerUtils;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPasswordActivity extends MvcBaseActivity {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_phonenum)
    EditText etPhonenum;

    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_getcode)
    TextView tvGetcode;
    @BindView(R.id.et_passwd)
    EditText etPasswd;
    @BindView(R.id.et_re_passwd)
    EditText etRePasswd;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.forget_password_activity_navigation_bar2);
        navigationBar2.setTitle("忘记密码");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
    }

    @OnClick({R.id.tv_getcode, R.id.btn_send})
    public void onViewClicked(View view) {

        String account = etAccount.getText().toString();
        String telephone = etPhonenum.getText().toString().trim();
        String verificationCode = etCode.getText().toString().trim();
        String newPwd = etPasswd.getText().toString().trim();
        String confirmPwd = etRePasswd.getText().toString().trim();
        switch (view.getId()) {
            case R.id.tv_getcode:

                if (TextUtils.isEmpty(account) || TextUtils.isEmpty(telephone)) {
                    showToast("账号和手机号不能为空!");
                    return;
                } else {
                    if (CommonUtil.isPhoneFormat(telephone)) {
                        tvGetcode.setEnabled(false);
                        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(tvGetcode, 30000, 1000);
                        countDownTimerUtils.start();
                        showBlueProgress();
                        HttpManager.getCode(account, telephone, new ResultJSONObjectObserver() {
                            @Override
                            public void onSuccess(JSONObject result) {
                                showToast("验证码已发送!");
                                hideBlueProgress();
                            }

                            @Override
                            public void onFail(String msg) {
                                tvGetcode.setEnabled(true);
                                countDownTimerUtils.cancel();
                                countDownTimerUtils.onFinish();
                                showToast(msg);
                                hideBlueProgress();

                            }
                        });
                    } else {
                        showToast("输入的手机号不正确,请重新输入!");

                    }
                }

                break;
            case R.id.btn_send:
                if (TextUtils.isEmpty(account)) {
                    showToast("账号不能为空!");
                    return;
                }
                if (TextUtils.isEmpty(telephone)) {
                    showToast("手机号不能为空!");
                    return;
                }
                if (TextUtils.isEmpty(verificationCode)) {
                    showToast("验证码不能为空!");
                    return;
                }
                if (TextUtils.isEmpty(newPwd) || TextUtils.isEmpty(confirmPwd)) {
                    showToast("密码不能为空!");
                    return;
                }
                if (!newPwd.equals(confirmPwd)) {
                    showToast("2次输入的密码不同!");
                    return;
                }
                if (!CommonUtil.isPassWordFormat(newPwd)) {
                    showToast("新密码格式不正确,密码是数字和字母的6-20位组合！");
                    return;
                }
                if (!CommonUtil.isPassWordFormat(confirmPwd)) {
                    showToast("确认密码格式不正确，密码是数字和字母的6-20位组合！");
                    return;
                }
                if (CommonUtil.isPhoneFormat(telephone)) {
                    showBlueProgress();
                    HttpManager.resetPassword(account, telephone, verificationCode, newPwd, confirmPwd, new ResultJSONObjectObserver() {
                        @Override
                        public void onSuccess(JSONObject result) {
                            hideBlueProgress();
                            finish();
                        }

                        @Override
                        public void onFail(String msg) {
                            showToast(msg);
                            hideBlueProgress();
                        }
                    });
                } else {
                    showToast("输入的手机号不正确,请重新输入！");
                }
                break;
        }
    }
}
