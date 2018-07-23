package com.yijian.staff.mvp.main.mine.club;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;
import com.yijian.staff.mvp.webview.BaseWebViewActivity;
import com.youth.banner.Banner;

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
    }




    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
