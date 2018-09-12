package com.yijian.staff.mvp.main.mine.qrcode;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.jwsd.libzxing.QRCodeManager;
import com.yijian.staff.R;
import com.yijian.staff.net.requestbody.AccessStatisticsRequestBody;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.RoleVoBean;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.net.response.ResultStringObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.widget.NavigationBar;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

public class MyQRCodeActivity extends MvcBaseActivity {


    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_gender)
    ImageView ivGender;
    @BindView(R.id.tv_role)
    TextView tvRole;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    @BindView(R.id.refresh)
    LinearLayout refresh;
    private User user;
    private MyCountDownTimer timer;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_my_qrcode;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        String version = CommonUtil.getAccessStatisticsVersionName(this) + " " + CommonUtil.getVersionCode(this);
        AccessStatisticsRequestBody body = new AccessStatisticsRequestBody("app_qr_code", version);
        HttpManager.postAccessStatistics(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
        int statusBarHeight = CommonUtil.getStatusBarHeight(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(0, statusBarHeight, 0, 0);
        rootView.setLayoutParams(params);
        StatusBarUtil.setTranslucentForImageView(this, 0, null);

        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigation_bar2);
        navigationBar.setTitle("二维码");
        navigationBar.setNavigationBarBackgroudColor(android.R.color.transparent);
        navigationBar .hideLeftSecondIv();
        navigationBar.hideBottomLine();
        navigationBar.getmTitleView().setTextColor(Color.parseColor("#ffffff"));
        navigationBar .setBackClickListener(this);
        ImageView firstLeftIv = navigationBar.getFirstLeftIv();
        Glide.with(this).load(R.mipmap.white_arrow_back).into(firstLeftIv);
        user = DBManager.getInstance().queryUser();

        if (user != null) {
            tvName.setText(user.getName());
            // 1 会籍客服 2教练  3会籍总监 4教练总监 5操课教练 6行政  7店长
            RoleVoBean roleVoBean = DBManager.getInstance().queryRoleVoBean();
            tvRole.setText(roleVoBean.getRoleName());

            ImageLoader.setHeadImageResource(user.getHeadImg(), this, ivHeader);
            String sex = user.getSex();
            int sexId;
            if (sex.equals("男")) {
                sexId = R.mipmap.lg_man;
            } else {
                sexId = R.mipmap.lg_women;
            }
            Glide.with(this).load(sexId).into(ivGender);
        }
        initQRCode();
    }


    private void initQRCode() {

        HttpManager.postHasHeaderNoParam(HttpManager.QUERY_ENTRANCE_QR, new ResultStringObserver(getLifecycle()) {
            @Override
            public void onSuccess(String result) {
                showQR(result);

            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });
    }


    public void showTimeOutView(boolean isShow) {
        refresh.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }


    private void showQR(String result) {
        showTimeOutView(false);
        Bitmap qrCode = QRCodeManager.getInstance().createQRCode(result, CommonUtil.dp2px(this,240), CommonUtil.dp2px(this,240));
        iv.setImageBitmap(qrCode);
        if (timer != null) {
            timer.cancel();
        }
        timer = new MyCountDownTimer(1000 * 29, 1000);
        timer.setActivity(this);
        timer.start();
    }

    @Override
    protected void onDestroy() {
        if (timer != null) {
            timer.cancel();
        }
        super.onDestroy();
    }


    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(enterAnim, 0);
    }

    @OnClick(R.id.refresh)
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.refresh:
                initQRCode();
                break;
        }
    }



    public static class MyCountDownTimer extends CountDownTimer {
        private WeakReference<MyQRCodeActivity> weakReference;

        public void setActivity(MyQRCodeActivity activity) {
            weakReference = new WeakReference<MyQRCodeActivity>(activity);
        }

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            if (weakReference.get() != null) {
                weakReference.get().showTimeOutView(true);
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, android.R.anim.fade_out);
    }

}
