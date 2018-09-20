package com.yijian.workspace.static_assessment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.commonlib.net.response.ResultStringObserver;
import com.yijian.commonlib.util.GlideCircleTransform;
import com.yijian.commonlib.util.ImageLoader;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.workspace.R;
import com.yijian.workspace.bean.StaticRequestBody;
import com.yijian.workspace.commen.ShareTestActivity;
import com.yijian.workspace.net.HttpManagerWorkSpace;
import com.yijian.workspace.utils.ActivityUtils;
import com.yijian.workspace.utils.StreamUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import static com.bumptech.glide.request.RequestOptions.centerCropTransform;

public class StaticAssessmentActivity extends MvcBaseActivity implements View.OnClickListener {

    ImageView iv_positive;
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

        iv_positive = findViewById(R.id.iv_positive);
        iv_side = findViewById(R.id.iv_side);


        findViewById(R.id.tv_positive).setOnClickListener(this);
        findViewById(R.id.tv_side).setOnClickListener(this);
        findViewById(R.id.tv_finish).setOnClickListener(this);
        NavigationBar navigationBar = findViewById(R.id.navigation_bar);
        navigationBar.setTitle("静态评估");
        navigationBar.hideLeftSecondIv();
        navigationBar.setBackClickListener(this);
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

        RequestOptions options = centerCropTransform().priority(Priority.HIGH).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(this).load(file).apply(options).into(type == 0 ? iv_positive : iv_side);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StreamUtils.deleteFile(positivePath, sidePath);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_positive) {
            type = 0;
            Bundle bundlePositive = new Bundle();
            bundlePositive.putInt("type", 0);
            ActivityUtils.startActivity(this, StaticPhotoActivity.class, bundlePositive);

        } else if (i == R.id.tv_side) {
            type = 1;
            Bundle bundleSide = new Bundle();
            bundleSide.putInt("type", 1);
            ActivityUtils.startActivity(this, StaticPhotoActivity.class, bundleSide);

        } else if (i == R.id.tv_finish) {
            subData();

        } else {
        }
    }
}
