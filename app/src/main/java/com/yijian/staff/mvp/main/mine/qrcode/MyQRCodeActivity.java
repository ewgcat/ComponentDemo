package com.yijian.staff.mvp.main.mine.qrcode;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.jwsd.libzxing.QRCodeManager;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.ImageLoader;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.widget.NavigationBar2;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private int role;
    private User user;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_my_qrcode;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        int statusBarHeight = CommonUtil.getStatusBarHeight(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(0, statusBarHeight, 0, 0);
        rootView.setLayoutParams(params);
        StatusBarUtil.setTranslucentForImageView(this, 0, null);

        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.navigation_bar2);
        navigationBar2.setTitle("二维码");
        navigationBar2.setNavigationBarBackgroudColor(android.R.color.transparent);
        navigationBar2.hideLeftSecondIv();
        navigationBar2.hideBottomLine();
        navigationBar2.getmTitleView().setTextColor(Color.parseColor("#ffffff"));
        navigationBar2.setBackClickListener(this);
        ImageView firstLeftIv = navigationBar2.getFirstLeftIv();
        Glide.with(this).load(R.mipmap.white_arrow_back).into(firstLeftIv);
        user = DBManager.getInstance().queryUser();

        if (user != null) {
            tvName.setText(user.getName());
            // 1 会籍客服 2教练  3会籍总监 4教练总监 5操课教练 6行政  7店长
            role = user.getRole();
            if (role == 1) {
                tvRole.setText("会籍客服");
            } else if (user.getRole() == 2) {
                tvRole.setText("教练");
            } else if (user.getRole() == 3) {
                tvRole.setText("会籍总监");
            } else if (user.getRole() == 4) {
                tvRole.setText("教练总监");
            } else if (user.getRole() == 5) {
                tvRole.setText("店长");
            }else if (user.getRole() == 6) {
                tvRole.setText("会籍经理");
            }else if (user.getRole() == 7) {
                tvRole.setText("教练经理");
            }
            ImageLoader.setImageResource(user.getHeadImg(), this, ivHeader);
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

        HashMap<String, String> params = new HashMap<>();
        params.put("type", "6");
        HttpManager.postHasHeaderHasParam(HttpManager.ABOUT_US_AND_CLUB_AND_QR_URL, params, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                String url = JsonUtil.getString(result, "url");
                String id;
                if (role==2||role==4||role==7){
                     id = url + "?action=1&coachId=" +user.getUserId() +"&shopId="+user.getShopId();
                }else {
                    id = url;
                }
                Bitmap qrCode = QRCodeManager.getInstance().createQRCode(id, 560, 560);

                iv.setImageBitmap(qrCode);
            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });
    }


}
