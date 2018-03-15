package com.yijian.staff.mvp.seepic;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.yijian.staff.R;
import com.yijian.staff.mvp.mine.BundleKeyConstant;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.util.ArrayList;
import java.util.List;


public class SeePicActivity extends AppCompatActivity {

    protected NavigationBar mNavigationbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_pic);

        String path = getIntent().getStringExtra(BundleKeyConstant.KEY_SEE_PIC_PATH);

        mNavigationbar = (NavigationBar) findViewById(R.id.navigationbar);
        mNavigationbar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        mNavigationbar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));
        mNavigationbar.setTitle("查看图片", "#ffffff");

        PhotoView photoView = findViewById(R.id.photo_view);
            RequestOptions options = new RequestOptions()
                    .placeholder(R.mipmap.placeholder)
                    .error(R.mipmap.placeholder)
                    .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            Glide.with(SeePicActivity.this).load(path).apply(options).into(photoView);



    }






}
