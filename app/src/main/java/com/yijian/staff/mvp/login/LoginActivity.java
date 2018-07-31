package com.yijian.staff.mvp.login;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.fastjson.JSONArray;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.OthermodelVo;
import com.yijian.staff.db.bean.RoleVoBean;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.forgetpassword.ForgetPasswordActivity;
import com.yijian.staff.mvp.main.MainActivity;
import com.yijian.staff.bean.PermissionBean;
import com.yijian.staff.mvp.permission.PermissionUtils;
import com.yijian.staff.mvp.workspace.widget.TableView;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.login.LoginRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.AndroidKeyBoardAssit;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/test/login")
public class LoginActivity extends MvcBaseActivity {


    private static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.tab_view)
    TableView tab_view;


//    private boolean hasStartAnimation = false;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login2;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        ll_content = findViewById(R.id.ll_content);
        etAccount.setText(SharePreferenceUtil.getUserName());
        tab_view.createButton("俱乐部", "工作室");
        tab_view.setListener(new TableView.TabCallBack() {
            @Override
            public void callExchangeBack(int index) {
                switch (index) {
                    case 0: //俱乐部
                        HttpManager.setWorkSpaceHost(false);
                        break;
                    case 1: //工作室
                        HttpManager.setWorkSpaceHost(true);
                        break;
                    default:
                }
            }
        });
        tab_view.setCurrentPosition(SharePreferenceUtil.isWorkSpaceVersion()? 1 : 0);

       /* AndroidKeyBoardAssit.assistActivity(this);
        ll_content.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = ll_content.getRootView().getHeight() - ll_content.getHeight();
                if (heightDiff > CommonUtil.dp2px(LoginActivity.this, 1)) {
                    // 显示软键盘
                    startAnimation();
                } else {
                    //隐藏软键盘
                    endAnimation();
                }
            }
        });*/

    }

   /* private void startAnimation() {
        if (!hasStartAnimation) {
            hasStartAnimation = true;
            int i = CommonUtil.dp2px(this, 200);
            ObjectAnimator animator = ObjectAnimator.ofFloat(ll_content, "translationY", 0, -i);
            animator.setDuration(500);
            animator.start();

        }
    }

    private void endAnimation() {
        if (hasStartAnimation) {
            hasStartAnimation = false;
            int i = CommonUtil.dp2px(this, 200);
            ObjectAnimator animator = ObjectAnimator.ofFloat(ll_content, "translationY", -i, 0);
            animator.setDuration(500);
            animator.start();
        }

    }*/


    private void jumpToForgetPassword() {
        String account = etAccount.getText().toString();
        Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
        intent.putExtra("account", account);
        startActivity(intent);
    }

    private void login() {
        String account = etAccount.getText().toString();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            showToast("账号和密码不能为空");
        } else {
            if (CommonUtil.isPassWordFormat(password)) {
                showLoading();
                LoginRequestBody loginRequest = new LoginRequestBody(account, password);
                HttpManager.postLogin(loginRequest, new ResultJSONObjectObserver(getLifecycle()) {
                    @Override
                    public void onSuccess(JSONObject result) {
                        hideLoading();
                        User user = new User(result);
                        SharePreferenceUtil.setUserName(account);
                        SharePreferenceUtil.setUserId(user.getUserId());
                        SharePreferenceUtil.setUserRole(user.getRole());
                        DBManager.getInstance().insertOrReplaceUser(user);
                        JSONObject roleVo = JsonUtil.getJsonObject(result, "roleVo");
                        DBManager.getInstance().insertOrReplaceRoleVoBean(new RoleVoBean(roleVo));
                        JSONObject homePageModelVO = JsonUtil.getJsonObject(result, "homePageModelVO");
                        JSONObject othermodelVo = JsonUtil.getJsonObject(homePageModelVO, "othermodelVo");

                        DBManager.getInstance().insertOrReplaceOthermodelVo(new OthermodelVo(othermodelVo));

                        try {
                            //存储菜单子选项
                            List<PermissionBean> permissionBeanList = JSONArray.parseArray(homePageModelVO.getJSONArray("menuModelList").toString(), PermissionBean.class);
                            PermissionUtils.getInstance().savePermissionMenu(LoginActivity.this, permissionBeanList);
                        } catch (Exception e) {
                            Logger.i(TAG, e.getMessage());
                        }

                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }

                    @Override
                    public void onFail(String msg) {
                        hideLoading();
                        Logger.i(TAG, msg);
                        showToast(msg);
                    }
                });
            } else {
                showToast("密码格式错误，密码是数字和字母的6-20位组合！");
            }
        }
    }


    @SuppressLint("ObjectAnimatorBinding")
    @OnClick({R.id.ll_login, R.id.forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_login:
                login();
                break;
            case R.id.forget_password:
                jumpToForgetPassword();
                break;
            default:
        }
    }

}