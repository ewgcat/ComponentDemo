package com.yijian.staff.mvp.login;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.fastjson.JSONArray;
import com.jaeger.library.StatusBarUtil;
import com.yijian.commonlib.db.DBManager;
import com.yijian.commonlib.db.bean.User;
import com.yijian.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;

import com.yijian.staff.db.ClubDBManager;
import com.yijian.staff.db.bean.OthermodelVo;
import com.yijian.staff.db.bean.RoleVoBean;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.forgetpassword.ForgetPasswordActivity;
import com.yijian.staff.mvp.main.MainActivity;
import com.yijian.staff.bean.PermissionBean;
import com.yijian.staff.mvp.permission.PermissionUtils;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.login.LoginRequestBody;
import com.yijian.commonlib.prefs.SharePreferenceUtil;
import com.yijian.staff.util.AndroidAdjustResizeBugFix;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.DensityUtil;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.TableView;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/test/login")
public class LoginActivity extends MvcBaseActivity implements AndroidAdjustResizeBugFix.CallKeyBoardStatu{


    private static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.tab_view)
    TableView tab_view;
    @BindView(R.id.rel_container)
    RelativeLayout rel_container;
    private int containerHeight;
    private int difference;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initView(Bundle savedInstanceState) {
        ViewTreeObserver vto =ll_content.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){

            @Override
            public void onGlobalLayout() {
                ll_content.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                containerHeight =ll_content.getMeasuredHeight();
            }
        });
        AndroidAdjustResizeBugFix.assistActivity(this, this);
        StatusBarUtil.setTranslucentForImageView(this, 0, null);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        rel_container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!(v instanceof EditText)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
                return false;
            }
        });
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
                        SharePreferenceUtil.setHostUrl(BuildConfig.HOST);
                        SharePreferenceUtil.setImageUrl(BuildConfig.FILE_HOST);
                        SharePreferenceUtil.setH5Url(BuildConfig.H5_HOST);
                        break;
                    case 1: //工作室
                        SharePreferenceUtil.setWorkSpaceHost(false);
                        SharePreferenceUtil.setHostUrl(BuildConfig.WORKSPACE_HOST);
                        SharePreferenceUtil.setImageUrl(BuildConfig.WORKSPACE_FILE_HOST);
                        SharePreferenceUtil.setH5Url(BuildConfig.H5_HOST);
                        break;
                    default:
                }
            }
        });
        tab_view.setCurrentPosition(SharePreferenceUtil.isWorkSpaceVersion()? 1 : 0);
    }



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
                        ClubDBManager.getInstance().insertOrReplaceRoleVoBean(new RoleVoBean(roleVo));
                        JSONObject homePageModelVO = JsonUtil.getJsonObject(result, "homePageModelVO");
                        JSONObject othermodelVo = JsonUtil.getJsonObject(homePageModelVO, "othermodelVo");

                        ClubDBManager.getInstance().insertOrReplaceOthermodelVo(new OthermodelVo(othermodelVo));

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

    @Override
    public void callkeyboardstatu(boolean showFlag, int heightDifference) {
        if(showFlag){
            difference = heightDifference - (DensityUtil.getScreenHeight(this) - containerHeight);
            ObjectAnimator.ofFloat(ll_content,"translationY",-difference).setDuration(300).start();
        }else{
            if(difference > 0){
                ObjectAnimator.ofFloat(ll_content,"translationY", 0).setDuration(300).start();
            }
        }
    }
}