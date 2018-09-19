package com.yijan.workspace.static_assessment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yijan.commonlib.mvp.base.mvc.MvcBaseActivity;
import com.yijan.workspace.bean.StaticRequestBody;
import com.yijan.workspace.commen.ShareTestActivity;
import com.yijan.workspace.utils.ActivityUtils;
import com.yijan.workspace.utils.HttpManagerWorkSpace;
import com.yijan.workspace.utils.StreamUtils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;



public class StaticAssessmentActivity extends MvcBaseActivity {

    @BindView(R.id.iv_positive)
    ImageView iv_positive;
    @BindView(R.id.iv_side)
    ImageView iv_side;
    int type = 0;
    private static StaticAssessmentActivity activity;
    private StaticRequestBody staticRequestBody;
    private String positivePath, sidePath;

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
        positivePath = getCacheDir() + "/img_positive.jpg";
        sidePath = getCacheDir() + "/img_side.jpg";
        activity = this;
        initTitle();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.navigation_bar);
        navigationBar2.setTitle("静态评估");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
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

        File positiveFile = new File(positivePath);
        File sideFile = new File(sidePath);
        if(positiveFile.exists() && sideFile.exists()){
            showLoading();
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

        }else{
            Toast.makeText(StaticAssessmentActivity.this,"请上传正面照和侧面照",Toast.LENGTH_SHORT).show();
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StreamUtils.deleteFile(positivePath, sidePath);
    }
}
