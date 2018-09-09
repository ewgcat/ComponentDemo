package com.yijian.staff.mvp.base.seepic;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.yijian.staff.R;
import com.yijian.staff.constant.Constant;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.widget.NavigationBar;


public class SeePicActivity extends MvcBaseActivity {

    protected NavigationBar mNavigationBar;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_see_pic;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        String path = getIntent().getStringExtra(Constant.KEY_SEE_PIC_PATH);

        mNavigationBar = (NavigationBar) findViewById(R.id.NavigationBar);
        mNavigationBar.setTitle("查看图片");
        mNavigationBar.hideLeftSecondIv();
        mNavigationBar.setBackClickListener(this);

        PhotoView photoView = findViewById(R.id.photo_view);
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.placeholder)
                .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(SeePicActivity.this).load(path).apply(options).into(photoView);
    }


}
