package com.yijian.staff.mvp.main.mine.qualification;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.widget.selectphoto.ChoosePhotoView;
import com.yijian.staff.widget.selectphoto.ImageBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyQualificationActivity extends MvcBaseActivity {
    @BindView(R.id.choose_photo_view)
    ChoosePhotoView choosePhotoView;
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.good_at_tv)
    TextView goodAtTv;
    @BindView(R.id.head_title)
    TextView headTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.rv1)
    RecyclerView rv1;
    @BindView(R.id.rv2)
    RecyclerView rv2;


    private List<String> authList = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();

    private List<ImageBean> list = new ArrayList<>();
    private List<String> bannerImageList = new ArrayList<>();
    private Banner banner;
    private ZiLiAdapter ziLiAdapter;
    private ZiLiAdapter adapter2;


    @Override
    protected int getLayoutID() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        return R.layout.activity_qualification;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initToolbarHeight();
        setAppbarCorlor();
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
    }

    private void initData() {

        User user = DBManager.getInstance().queryUser();
        HashMap<String, String> param = new HashMap<>();
        param.put("coachId", user.getUserId());
        HttpManager.postHasHeaderHasParam(HttpManager.GET_CERTIFICATE_URL, param, new ResultJSONObjectObserver() {
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
                ziLiAdapter.update(authList);

                String experience = certificateBean.getExperience();
                if (!TextUtils.isEmpty(experience)) {
                    String[] split = experience.split("\\u005c\\u006e");
                    for (int i = 0; i < split.length; i++) {
                        list2.add(split[i]);
                    }
                }
                adapter2.update(list2);


                String skilled = certificateBean.getSkilled();
                if (!TextUtils.isEmpty(skilled)) {
                    goodAtTv.setText(skilled);
                }
                List<String> certList = certificateBean.getCertificateList();
                if (certList!=null&&certList.size()>0){
                    setImageList(certList);
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
        if (coach2picList!=null&&coach2picList.size()>0){
            for (int i = 0; i < coach2picList.size(); i++) {
                bannerImageList.add(BuildConfig.FILE_HOST + coach2picList.get(i));
            }
            banner.setImages(bannerImageList);
            banner.start();
        }

    }


    public void setImageList(List<String> certList) {
        list.clear();
        for (int i = 0; i < certList.size(); i++) {
            list.add(new ImageBean(BuildConfig.FILE_HOST + certList.get(i), 1));
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
                } else {
                    visibility = View.GONE;
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
