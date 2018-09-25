package com.yijian.clubmodule.ui.main.mine.qualification;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.SuperKotlin.pictureviewer.ImagePagerActivity;
import com.SuperKotlin.pictureviewer.PictureConfig;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yijian.commonlib.db.DBManager;
import com.yijian.commonlib.db.bean.User;
import com.yijian.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.commonlib.prefs.SharePreferenceUtil;
import com.yijian.commonlib.widget.selectphoto.ChoosePhotoView;
import com.yijian.commonlib.widget.selectphoto.ImageBean;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.net.requestbody.AccessStatisticsRequestBody;
import com.yijian.clubmodule.bean.CertificateBean;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.clubmodule.net.httpmanager.HttpManager;
import com.yijian.commonlib.util.CommonUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class MyQualificationActivity extends MvcBaseActivity {
    ChoosePhotoView choosePhotoView;
    ImageView back;
    TextView goodAtTv;
    TextView headTitle;
    Toolbar toolbar;
    AppBarLayout appbar;
    RecyclerView rv1;
    RecyclerView rv2;
    LinearLayout ll_my_zhenshu_img;


    private List<String> authList = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();

    private List<ImageBean> list = new ArrayList<>();
    private List<String> bannerImageList = new ArrayList<>();
    private Banner banner;
    private ZiLiAdapter ziLiAdapter;
    private ZiLiAdapter adapter2;


    @Override
    protected int getLayoutID() {
        setImmersionBar();
        return R.layout.activity_qualification;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        choosePhotoView = findViewById(R.id.choose_photo_view);
        back = findViewById(R.id.back);
        goodAtTv = findViewById(R.id.good_at_tv);
        headTitle = findViewById(R.id.head_title);
        toolbar = findViewById(R.id.toolbar);
        appbar = findViewById(R.id.appbar);
        rv1 = findViewById(R.id.rv1);
        rv2 = findViewById(R.id.rv2);
        ll_my_zhenshu_img = findViewById(R.id.ll_my_zhenshu_img);
        choosePhotoView.setMode(ChoosePhotoView.MODE_ONLY_SHOW);

        rv1.setLayoutManager(new LinearLayoutManager(this));
        rv2.setLayoutManager(new LinearLayoutManager(this));
        ziLiAdapter = new ZiLiAdapter(authList);
        adapter2 = new ZiLiAdapter(list2);
        rv1.setAdapter(ziLiAdapter);
        rv2.setAdapter(adapter2);
        initData();

        banner = (Banner) findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(false);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String version = CommonUtil.getAccessStatisticsVersionName(this) + " " + CommonUtil.getVersionCode(this);
        AccessStatisticsRequestBody body = new AccessStatisticsRequestBody("app_credentials", version);
        HttpManager.postAccessStatistics(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
        initToolbarHeight();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void initData() {

        User user = DBManager.getInstance().queryUser();
        HashMap<String, String> param = new HashMap<>();
        param.put("coachId", user.getUserId());
        HttpManager.postHasHeaderHasParam(HttpManager.GET_CERTIFICATE_URL, param, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {
                CertificateBean certificateBean = new Gson().fromJson(result.toString(), CertificateBean.class);
                String qualification = certificateBean.getQualification();
                if (!TextUtils.isEmpty(qualification)) {
                    String[] split = qualification.split("\\u005c\\u006e");
                    for (int i = 0; i < split.length; i++) {
                        authList.add(split[i]);
                    }
                }
                if (authList.size() == 0) {
                    authList.add("暂未录入");
                }
                ziLiAdapter.update(authList);

                String experience = certificateBean.getExperience();
                if (!TextUtils.isEmpty(experience)) {
                    String[] split = experience.split("\\u005c\\u006e");
                    for (int i = 0; i < split.length; i++) {
                        list2.add(split[i]);
                    }
                }
                if (list2.size() == 0) {
                    list2.add("暂未录入");
                }
                adapter2.update(list2);


                String skilled = certificateBean.getSkilled();
                if (!TextUtils.isEmpty(skilled)) {
                    goodAtTv.setText(skilled);
                } else {
                    goodAtTv.setText("暂未录入");
                }
                List<String> certList = certificateBean.getCertificateList();
                if (certList != null && certList.size() > 0) {
                    ll_my_zhenshu_img.setVisibility(View.VISIBLE);
                    setImageList(certList);
                } else {
                    ll_my_zhenshu_img.setVisibility(View.GONE);
                }


                List<String> coach2picList = certificateBean.getCoach2picList();

                setBanner(coach2picList);


            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });


    }


    private void setBanner(List<String> coach2picList) {
        bannerImageList.clear();
        if (coach2picList != null && coach2picList.size() > 0) {
            for (int i = 0; i < coach2picList.size(); i++) {
                bannerImageList.add(SharePreferenceUtil.getImageUrl() + coach2picList.get(i));
            }
            banner.setImages(bannerImageList);
            banner.start();
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {

                    PictureConfig config = new PictureConfig.Builder()
                            .setListData((ArrayList<String>) bannerImageList)//图片数据List<String> list
                            .setPosition(position)//图片下标（从第position张图片开始浏览）
                            .setDownloadPath("pictureviewer")//图片下载文件夹地址
                            .setIsShowNumber(true)//是否显示数字下标
                            .needDownload(true)//是否支持图片下载
                            .setPlaceHolder(R.mipmap.placeholder)//占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
                            .build();
                    ImagePagerActivity.startActivity(MyQualificationActivity.this, config);
                }
            });
        }

    }


    public void setImageList(List<String> certList) {
        list.clear();
        for (int i = 0; i < certList.size(); i++) {
            list.add(new ImageBean(SharePreferenceUtil.getImageUrl() + certList.get(i), 1));
        }
        choosePhotoView.setmPhotoPathList(list);

    }

    private void setAppbarCorlor() {
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int visibility;
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    visibility = View.VISIBLE;
                    setStatusBarColor(Color.WHITE);
                } else {
                    visibility = View.GONE;
                    setImmersionBar();
                }
                if (headTitle.getVisibility() != visibility) {
                    headTitle.setVisibility(visibility);
                    back.setSelected(visibility == View.VISIBLE);
                }
            }
        });
    }

    private void initToolbarHeight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            CollapsingToolbarLayout.LayoutParams params = (CollapsingToolbarLayout.LayoutParams) toolbar.getLayoutParams();
            int height = CommonUtil.getStatusBarHeight(getApplicationContext());
            params.setMargins(0, height <= 0 ? 75 : height, 0, 0);
            toolbar.setLayoutParams(params);
        }
    }


}
