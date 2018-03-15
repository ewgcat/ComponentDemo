package com.yijian.staff.mvp.mine.club;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijian.staff.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClubActivity extends AppCompatActivity {

    private static final String TAG = ClubActivity.class.getSimpleName();
    @BindView(R.id.iv_club_logo)
    ImageView ivClubLogo;
    @BindView(R.id.tv_club_name)
    TextView tvClubName;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
