package com.yijian.staff.mvp.mine.selectheadicon;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.constant.Constant;
import com.yijian.staff.util.FileUtil;
import com.yijian.staff.util.Logger;
import com.yijian.staff.util.PictureUtil;

import java.io.File;

public class ClipActivity extends Activity implements View.OnClickListener {
    private ClipImageLayout mClipImageLayout;
    private String path;
    private ProgressDialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clipimage);
        //这步必须要加
        //设置加载dialog,作为加载数据的提示
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setTitle("请稍后...");
        mClipImageLayout = (ClipImageLayout) findViewById(R.id.id_clipImageLayout);

        String path = getIntent().getStringExtra("path");
        Logger.i(TAG, "path=" + path);
        if (TextUtils.isEmpty(path) || !(new File(path).exists())) {
            Toast.makeText(this, "找不到图片，请选择其他图片", Toast.LENGTH_SHORT).show();
            return;
        } else {

            Bitmap bitmap = PictureUtil.modifyImageDisplay(path);
            if (bitmap == null) {
                Toast.makeText(this, "图片加载失败", Toast.LENGTH_SHORT).show();
                return;
            } else {
                mClipImageLayout.setBitmap(bitmap);
            }
        }
        findViewById(R.id.ll_back).setOnClickListener(this);
        findViewById(R.id.id_action_clip).setOnClickListener(this);
    }

    private final static String TAG = "ClipActivity";
    private final static int START_SAVE = 0;
    private final static int END_SAVE = 1;
    private final static int FAIL_SAVE = 2;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            int state = msg.what;
            switch (state) {
                case START_SAVE:
                    loadingDialog.show();
                    break;
                case END_SAVE:
                    Logger.i(TAG, "保存到本地成功");

                    //上传
                    uploadHeadIcon();
                    break;
                case FAIL_SAVE:
                    Logger.i(TAG, "保存失败");
                    loadingDialog.dismiss();
                    finish();
                    break;
            }
            return false;
        }
    });


    /**
     * 由于上传请求成功后会删除文件，所以要上传副本保存原文件
     */
    private void uploadHeadIcon() {
        String uploadheadIconPath = getExternalCacheDir().toString() + "/head/upload/head.png";

        Intent intent = new Intent();
        intent.putExtra(Constant.KEY_SEE_PIC_PATH,uploadheadIconPath);
        setResult(RESULT_OK, intent);
        finish();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;

            case R.id.id_action_clip:
                new Thread(() -> {

                    Message start = handler.obtainMessage();
                    start.what = START_SAVE;
                    handler.sendMessage(start);

                    // 裁剪
                    Bitmap bitmap = mClipImageLayout.clip();

                    //保存
                    String headIconPath = getExternalCacheDir().toString() + "/head/head.png";
                    String uploadheadIconPath = getExternalCacheDir().toString() + "/head/upload/head.png";
                    String imageDir1 = getExternalCacheDir().toString() + "/head";
                    String imageDir2 = getExternalCacheDir().toString() + "/head/upload";
                    boolean imageFileExisted1 = FileUtil.createHeadIconFile(imageDir1, "head.png");
                    boolean imageFileExisted2 = FileUtil.createHeadIconFile(imageDir2, "head.png");

                    if (imageFileExisted1 && imageFileExisted2) {
                        boolean isSavaSuccessed1 = PictureUtil.savePhotoToCach(bitmap, headIconPath);
                        boolean isSavaSuccessed2 = PictureUtil.savePhotoToCach(bitmap, uploadheadIconPath);
                        if (isSavaSuccessed1 && isSavaSuccessed2) {
                            Message end = handler.obtainMessage();
                            end.what = END_SAVE;
                            handler.sendMessage(end);
                        } else {
                            Message fail = handler.obtainMessage();
                            fail.what = FAIL_SAVE;
                            handler.sendMessage(fail);
                        }
                    } else {
                        Message fail = handler.obtainMessage();
                        fail.what = FAIL_SAVE;
                        handler.sendMessage(fail);
                    }
                }).start();

                break;
        }
    }
}
