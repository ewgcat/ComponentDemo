package com.yijian.staff.mvp.seepic;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.yijian.staff.R;
import com.yijian.staff.mvp.mine.BundleKeyConstant;
import com.yijian.staff.widget.CustomViewpager;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.util.ArrayList;
import java.util.List;



public class SeePicActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    protected NavigationBar mNavigationbar;
    protected CustomViewpager mPager;
    protected ArrayList<String> mdatas = new ArrayList<>();
    protected List<ImageView> imageViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_pic);

        mdatas = getIntent().getStringArrayListExtra(BundleKeyConstant.KEY_SEE_PIC_ARRAY);

        mNavigationbar = (NavigationBar) findViewById(R.id.navigationbar);
        mNavigationbar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        mNavigationbar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));

        mPager = (CustomViewpager) findViewById(R.id.vp_see_pic);

            mPager.setVisibility(View.VISIBLE);
            mPager.setAdapter(new ImageAdapter());
            mPager.addOnPageChangeListener(this);





    }

    @Override
    protected void onDestroy() {
        mPager.removeOnPageChangeListener(this);
        super.onDestroy();

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        mNavigationbar.setTitle("" + (position + 1) + "/" + mdatas.size(), "#ffffff");
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class ImageAdapter extends PagerAdapter {
        public ImageAdapter() {
            for (int i = 0; i < mdatas.size(); i++) {
                ImageView imageView = new ImageView(SeePicActivity.this);


                RequestOptions options = new RequestOptions()
                        .placeholder(R.mipmap.placeholder)
                        .error(R.mipmap.placeholder)
                        .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(SeePicActivity.this).load(mdatas.get(i)).apply(options).into(imageView);
                PhotoViewAttacher mAttacher = new PhotoViewAttacher(imageView);

                imageViews.add(imageView);

               
            }
        }

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViews.get(position));
            return imageViews.get(position);
        }
    }

}
