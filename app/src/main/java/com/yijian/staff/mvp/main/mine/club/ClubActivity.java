package com.yijian.staff.mvp.main.mine.club;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.bean.AccessStatisticsRequestBody;
import com.yijian.staff.mvp.webview.BaseWebViewActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.CommonUtil;
import com.youth.banner.Banner;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClubActivity extends BaseWebViewActivity {
    private static final String TAG = ClubActivity.class.getSimpleName();
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_club_name)
    TextView tvClubName;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_club;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        String version = CommonUtil.getAccessStatisticsVersionName(this) + " " + CommonUtil.getVersionCode(this);
        AccessStatisticsRequestBody body=new AccessStatisticsRequestBody("app_club",version);
        HttpManager.postAccessStatistics(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }




    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
