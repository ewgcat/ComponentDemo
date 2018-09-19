package com.yijian.clubmodule.ui.main.mine.club;

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
import com.yijian.clubmodule.ui.main.mine.qualification.GlideImageLoader;
import com.yijian.clubmodule.ui.webview.BaseWebViewActivity;
import com.yijian.commonlib.db.DBManager;
import com.yijian.commonlib.db.bean.User;
import com.yijian.commonlib.net.response.ResponseObserver;
import com.yijian.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.commonlib.umeng.SharePopupWindow;
import com.yijian.clubmodule.BuildConfig;
import com.yijian.clubmodule.R;
import com.yijian.clubmodule.net.requestbody.AccessStatisticsRequestBody;
import com.yijian.clubmodule.bean.ClubDetailBean;
import com.yijian.clubmodule.net.httpmanager.HttpManager;
import com.yijian.commonlib.prefs.SharePreferenceUtil;
import com.yijian.commonlib.util.CommonUtil;
import com.yijian.commonlib.util.GlideCircleTransform;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;




public class ClubActivity extends BaseWebViewActivity implements View.OnClickListener {

    private static final String TAG = ClubActivity.class.getSimpleName();
    Banner banner;
    ImageView ivLogo;
    TextView tvClubName;
    TextView tvDes;
    private SharePopupWindow sharePopupWindow;
    private String name;
    private String des;


    @Override
    protected int getLayoutID() {
        StatusBarUtil.setTranslucentForImageView(this, 0, null);

        return R.layout.activity_club;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        banner= findView(R. id.banner);
        tvClubName= findView(R. id.tv_club_name);
        tvDes= findView(R. id.tv_des);
        ivLogo= findView(R. id.iv_logo);
        findView(R.id.ll_back).setOnClickListener(this);
        findView(R.id.ll_share).setOnClickListener(this);

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
        des = clubDetailBean.getDes();
        tvDes.setText("        "+ des);

    }


    private void showShareDialog() {
        if (sharePopupWindow == null) {
            sharePopupWindow = new SharePopupWindow(this);
            User user = DBManager.getInstance().queryUser();
            sharePopupWindow.setData(SharePreferenceUtil.getH5Url()+"#/bappclub?mc="+user.getMerchantId(), name, null, des);
        }
        sharePopupWindow.show(getWindow().getDecorView());
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.ll_back) {
            finish();
        } else if (i == R.id.ll_share) {
            showShareDialog();

        }
    }
}
