package com.yijian.workspace.dynamic_assessment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.workspace.face.BitmapFaceUtils;
import com.yijian.workspace.utils.ActivityUtils;
import com.yijian.workspace.utils.StreamUtils;
import com.yijian.workspace.R;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static com.bumptech.glide.request.RequestOptions.centerCropTransform;

public class DynamicDragPointActivity extends MvcBaseActivity implements View.OnClickListener {

    ImageView iv_pendding;
    RelativeLayout rel_container;
    DynamicPointView pointView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_dynamic_drag_point;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        File file = new File(getCacheDir() + "/img_dynamic.jpg");
        RequestOptions options = centerCropTransform().priority(Priority.HIGH).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(this).load(file).apply(options).into(iv_pendding);
        initTitle();
    }

    private void initTitle() {
        iv_pendding = findViewById(R.id.iv_pendding);
        rel_container = findViewById(R.id.rel_container);
        pointView = findViewById(R.id.pointView);
        findViewById(R.id.iv_finish).setOnClickListener(this);
        NavigationBar navigationBar = findViewById(R.id.navigation_bar);
        navigationBar.setTitle("盆骨图打点");
        navigationBar.hideLeftSecondIv();
        navigationBar.setBackClickListener(this);
    }

  

    @Override
    public void onClick(View v) {
        pointView.clearSelCircle();
        rel_container.setDrawingCacheEnabled(true);
        Bitmap bitmap = rel_container.getDrawingCache();
        Bitmap rotateBitmap = BitmapFaceUtils.rotateBitmap(bitmap, -90);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        rotateBitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] datas = baos.toByteArray();
        StreamUtils.createFileWithByte(datas, getCacheDir() + "/img_dynamic.jpg");
        String result = pointView.getMinArea();
        Bundle bundle = new Bundle();
        bundle.putString("area", result);
        ActivityUtils.startActivity(DynamicDragPointActivity.this, DynamicAssessmentActivity.class, bundle);
        finish();
    }
}
