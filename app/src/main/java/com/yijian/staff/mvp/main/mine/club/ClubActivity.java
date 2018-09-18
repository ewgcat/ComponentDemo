package com.yijian.staff.mvp.main.mine.club;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.jaeger.library.StatusBarUtil;
import com.yijan.commonlib.umeng.SharePopupWindow;
import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.net.requestbody.AccessStatisticsRequestBody;
import com.yijian.staff.bean.ClubDetailBean;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.main.mine.qualification.GlideImageLoader;
import com.yijian.staff.mvp.webview.BaseWebViewActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResponseObserver;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.GlideCircleTransform;
import com.yijian.staff.util.Logger;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ClubActivity extends BaseWebViewActivity {

    private static final String TAG = ClubActivity.class.getSimpleName();
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.tv_club_name)
    TextView tvClubName;
    @BindView(R.id.tv_des)
    TextView tvDes;
    private SharePopupWindow sharePopupWindow;
    private String name;


    @Override
    protected int getLayoutID() {
        StatusBarUtil.setTranslucentForImageView(this, 0, null);

        return R.layout.activity_club;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        String version = CommonUtil.getAccessStatisticsVersionName(this) + " " + CommonUtil.getVersionCode(this);
        AccessStatisticsRequestBody body = new AccessStatisticsRequestBody("app_club", version);
        HttpManager.postAccessStatistics(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });


        HttpManager.getHasHeaderNoParam(HttpManager.CLUB_DETAIL_URL, new ResponseObserver<ClubDetailBean>(getLifecycle()) {
            @Override
            public void onSuccess(ClubDetailBean clubDetailBean) {
                updateUI(clubDetailBean);

            }

            @Override
            public void onFail(String msg) {

            }
        });

    }
    List<String> imageList=new ArrayList<>();

    private void updateUI(ClubDetailBean clubDetailBean) {
        String s = SharePreferenceUtil.getImageUrl() + clubDetailBean.getLogoPath();

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.placeholder)
                .transform(new GlideCircleTransform())
                .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(ClubActivity.this).load(s).apply(options).into(ivLogo);

        if (!TextUtils.isEmpty(clubDetailBean.getName())) {
            name = clubDetailBean.getName();
            tvClubName.setText(name);
        }
        List<ClubDetailBean.PicsBean> pics = clubDetailBean.getPics();
        if (pics!=null&&pics.size()>0){
            for (int i = 0; i < pics.size(); i++) {
                ClubDetailBean.PicsBean picsBean = pics.get(i);
                String path = SharePreferenceUtil.getImageUrl()+picsBean.getPath();
                Logger.i(TAG,"path="+path);
                imageList.add(path);
            }
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
            banner.setImages(imageList);
            banner.start();

        }
        tvDes.setText("        "+clubDetailBean.getDes());

    }


    @OnClick({R.id.ll_back, R.id.ll_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_share:
                showShareDialog();
                break;
        }
    }
    private void showShareDialog() {
        if (sharePopupWindow == null) {
            sharePopupWindow = new SharePopupWindow(this);
            User user = DBManager.getInstance().queryUser();
            sharePopupWindow.setData(BuildConfig.WORKSPACE_H5_HOST+"#/bappclub?mc="+user.getMerchantId(), name, null, null);
        }
        sharePopupWindow.show(getWindow().getDecorView());
    }
}
