package com.yijian.workspace.workspace.static_assessment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yijian.staff.R;
import com.yijian.workspace.base.mvc.MvcBaseActivity;
import com.yijian.workspace.workspace.bean.StaticRequestBody;
import com.yijian.workspace.workspace.commen.ShareTestActivity;
import com.yijian.workspace.workspace.utils.ActivityUtils;
import com.yijian.workspace.workspace.utils.GlideApp;
import com.yijian.workspace.workspace.utils.HttpManagerWorkSpace;
import com.yijan.commonlib.net.response.ResultJSONObjectObserver;
import com.yijan.commonlib.net.response.ResultStringObserver;
import com.yijian.staff.widget.NavigationBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class StaticAssessmentActivity extends MvcBaseActivity {

    @BindView(R.id.iv_positive)
    ImageView iv_positive;
    @BindView(R.id.iv_side)
    ImageView iv_side;
    int type = 0;
    private static StaticAssessmentActivity activity;
    private StaticRequestBody staticRequestBody;

    public StaticRequestBody getStaticRequestBody() {
        if (staticRequestBody != null) {
            return staticRequestBody;
        }
        staticRequestBody = new StaticRequestBody();
        return staticRequestBody;
    }

    public static StaticAssessmentActivity getInstance() {
        return activity;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_static_assessment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        activity = this;
        initTitle();
    }

    private void initTitle() {
        NavigationBar navigationBar = findViewById(R.id.navigation_bar);
        navigationBar.setTitle("静态评估");
        navigationBar .hideLeftSecondIv();
        navigationBar .setBackClickListener(this);
    }

    @OnClick({R.id.tv_positive, R.id.tv_side, R.id.tv_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_positive: //正面
                type = 0;
                Bundle bundlePositive = new Bundle();
                bundlePositive.putInt("type", 0);
                ActivityUtils.startActivity(this, StaticPhotoActivity.class, bundlePositive);
                break;
            case R.id.tv_side: //侧面
                type = 1;
                Bundle bundleSide = new Bundle();
                bundleSide.putInt("type", 1);
                ActivityUtils.startActivity(this, StaticPhotoActivity.class, bundleSide);
                break;
            case R.id.tv_finish: //完成
                subData();
                break;
            default:
        }
    }

    private void subData(){
        showLoading();
        String positivePath = getCacheDir() + "/img_positive.jpg";
        String sidePath = getCacheDir() + "/img_side.jpg";
        HttpManagerWorkSpace.upLoadImageListHasParam(HttpManagerWorkSpace.WORKSPACE_UPLOAD_FILE__URL, 10, new ResultJSONObjectObserver(getLifecycle()) {
                    @Override
                    public void onSuccess(JSONObject result) {
                        try {
                            JSONArray jsonArray = result.getJSONArray("dataList");
                            String imgUrl1 = jsonArray.getString(0);
                            String imgUrl2 = jsonArray.getString(1);
                            staticRequestBody.setUrl1(imgUrl1);
                            staticRequestBody.setUrl2(imgUrl2);
                            pushData();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(String msg) {
                        hideLoading();
                        Toast.makeText(StaticAssessmentActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }, positivePath, sidePath);
    }

    private void pushData() {
        HttpManagerWorkSpace.postStaticInfo(staticRequestBody, new ResultStringObserver(getLifecycle()) {

            @Override
            public void onSuccess(String result) {
                hideLoading();
                Toast.makeText(StaticAssessmentActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("recordId", result);
                ActivityUtils.startActivity(StaticAssessmentActivity.this, ShareTestActivity.class, bundle);
                finish();
            }

            @Override
            public void onFail(String msg) {
                hideLoading();
                Toast.makeText(StaticAssessmentActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        File file = new File(getCacheDir() + (type == 0 ? "/img_positive.jpg" : "/img_side.jpg"));
        GlideApp.with(this).load(file).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(type == 0 ? iv_positive : iv_side);
    }
}
