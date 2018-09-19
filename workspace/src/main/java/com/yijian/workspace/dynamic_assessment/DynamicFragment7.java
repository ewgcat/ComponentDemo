package com.yijian.workspace.dynamic_assessment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yijian.workspace.R;
import com.yijian.workspace.base.BaseSpaceFragment;
import com.yijian.workspace.utils.ActivityUtils;

import java.io.File;
import java.util.Map;


public class DynamicFragment7 extends BaseSpaceFragment {

    private DynamicAssessmentActivity dynamicAssessmentActivity;
    private Context mContext;
    private ImageView iv_result; //结果图片
    private TextView tv_take_picture;
    private TextView tv_take_finish;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dynamic_fragment7;
    }

    @Override
    public void initView() {
        iv_result = findView(R.id.iv_result);
        tv_take_picture = findView(R.id.tv_take_picture);
        tv_take_finish = findView(R.id.tv_take_finish);
        tv_take_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dynamicAssessmentActivity = (DynamicAssessmentActivity) getActivity();
                ActivityUtils.startActivity(dynamicAssessmentActivity, DynamicPhotoActivity.class);
            }
        });
        tv_take_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dynamicAssessmentActivity = (DynamicAssessmentActivity) getActivity();
                dynamicAssessmentActivity.judgeNext();
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dynamicAssessmentActivity = (DynamicAssessmentActivity) context;
        this.mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dynamicAssessmentActivity = null;
    }

    @Override
    public void update(Object data) {

        Map<String, String> map = (Map<String, String>) data;
        String type = map.get("type");
        if (DynamicAssessmentActivity.STEP7_IMG.equals(type)) {
            if(dynamicAssessmentActivity == null){
                dynamicAssessmentActivity = (DynamicAssessmentActivity) mContext;
            }
            File file = new File(dynamicAssessmentActivity.getCacheDir() + "/img_dynamic.jpg");
            //若该文件存在
            if (file.exists()) {
                final Bitmap bitmap= BitmapFactory.decodeFile(dynamicAssessmentActivity.getCacheDir() + "/img_dynamic.jpg");
                float bWidth = bitmap.getWidth();
                float bHeight = bitmap.getHeight();
                final float scale = bWidth / bHeight;
                iv_result.post(new Runnable() {
                    @Override
                    public void run() {
                        float ivWidth = iv_result.getWidth();
                        float ivHeight = ivWidth / scale;
                        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) iv_result.getLayoutParams();
                        lp.width = (int) ivWidth;
                        lp.height = (int) ivHeight;
                        iv_result.setLayoutParams(lp);
                        iv_result.setImageBitmap(bitmap);
                    }
                });
            }
        }

    }

}
