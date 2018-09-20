package com.yijian.workspace.dynamic_assessment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.yijian.commonlib.base.mvc.MvcBaseActivity;
import com.yijian.commonlib.widget.NavigationBar;
import com.yijian.workspace.R;
import com.yijian.workspace.face.BitmapFaceUtils;
import com.yijian.workspace.utils.ActivityUtils;
import com.yijian.workspace.utils.StreamUtils;
import com.yijian.commonlib.util.DensityUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class DynamicPhotoActivity extends MvcBaseActivity implements View.OnClickListener {

    private SurfaceView surfaceView;
    private Camera mCamera;
    private SurfaceHolder mHolder;
    private static final int REQUEST_CAMERA_CODE = 0x100;
    FrameLayout fl_surfaceView;
    ImageView iv_take;
    ImageView iv_cancel;
    ImageView iv_sure;
    Space space_view;
    LinearLayout fl_start;
    TextView tv_desc;
    private byte[] imgData = null;
    private int surfaceWidth,surfaceHeight,fl_surfaceViewWidth, fl_surfaceViewHeight,screenWidth,screenHeight;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_dynamic_photo;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        fl_surfaceView=  findViewById(R.id.fl_surfaceView);
        iv_take=  findViewById(R.id.iv_take);
        iv_cancel=  findViewById(R.id.iv_cancel);
        iv_sure=  findViewById(R.id.iv_sure);
        space_view=  findViewById(R.id.space_view);
        fl_start=  findViewById(R.id.fl_start);
        tv_desc=  findViewById(R.id.tv_desc);


                findViewById(R.id.right_tv).setOnClickListener(this);
                findViewById(R.id.iv_take).setOnClickListener(this);
                findViewById(R.id.iv_cancel).setOnClickListener(this);
                findViewById(R.id.iv_sure).setOnClickListener(this);
        /*****************  *********************/
        ViewTreeObserver viewTreeObserver = fl_start.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                fl_start.getViewTreeObserver()
                        .removeGlobalOnLayoutListener(this);
                surfaceWidth = surfaceView.getWidth();
                surfaceHeight = surfaceView.getHeight();
                fl_surfaceViewWidth = fl_surfaceView.getWidth();
                fl_surfaceViewHeight = fl_surfaceView.getHeight();
                screenWidth = DensityUtil.getScreenWidth(DynamicPhotoActivity.this);
                screenHeight = DensityUtil.getScreenHeight(DynamicPhotoActivity.this);
            }
        }); 
        initTitle();
        initUi();
    }

    private void initTitle() {


        NavigationBar navigationBar = findViewById(R.id.navigation_bar);
        navigationBar.setTitle("盆骨图拍照");
        navigationBar.hideLeftSecondIv();
        navigationBar.setBackClickListener(this);
    }

    private void initUi() {
        tv_desc.post(new Runnable() {
            @Override
            public void run() {
                int harlfWidth = tv_desc.getWidth()/2;
                tv_desc.setRotation(90);
                tv_desc.setTranslationX(harlfWidth);
            }
        });
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_CODE);
                }
                return;
            }
            openSurfaceView();
        }
    }

    /**
     * 把摄像头的图像显示到SurfaceView
     */
    private void openSurfaceView() {
        mHolder = surfaceView.getHolder();
        mHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (mCamera != null) {
                    mCamera.stopPreview();
                    mCamera.setPreviewCallback(null);
                    mCamera.release();
                }
                mCamera = Camera.open(0);
                try {
                    mCamera.setPreviewDisplay(mHolder);
                    setCameraParms(mCamera, surfaceWidth, surfaceHeight);
                    mCamera.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // mCamera.setPreviewCallback(null);// 防止 Method called after release()
                if (mCamera != null) {
                    mCamera.stopPreview();
                    mCamera.setPreviewCallback(null);
                    mCamera.release();
                    mCamera = null;
                }

            }
        });
    }

    private Camera.Size getCmeraPreSize(List<Camera.Size> preSizeList){
        Camera.Size retSize = null;
        for (Camera.Size size : preSizeList) {
            if(size.height > surfaceWidth && size.width > surfaceHeight){
                retSize = size;
                break;
            }
        }
        if(retSize == null){
            for (Camera.Size size : preSizeList) {
                if(size.height >= surfaceWidth && size.width >= surfaceHeight){
                    retSize = size;
                    break;
                }
            }
        }
        return retSize;
    }

    /**
     * 在摄像头启动前设置参数
     *
     * @param camera
     * @param width
     * @param height
     */
    private void setCameraParms(Camera camera, int width, int height) {
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> previewSizeList = parameters.getSupportedPreviewSizes();
        Camera.Size preSize = getCmeraPreSize(previewSizeList);
        if (null != preSize) {
            parameters.setPreviewSize(preSize.width, preSize.height);
        }
        double preWidth = preSize.width;
        double preHeight = preSize.height;
        double preScale = preHeight/preWidth;
        double sWidth = surfaceWidth;
        double sHeight = surfaceHeight;
        double surfaceScale = sWidth/sHeight;
        if(surfaceScale > preScale){
            int surfaceViewHeight = (int) (sWidth / preScale);
            surfaceView.setLayoutParams(new FrameLayout.LayoutParams(surfaceWidth, surfaceViewHeight));
        }else{
            int surfaceViewWidth = (int) (sHeight * preScale);
            surfaceView.setLayoutParams(new FrameLayout.LayoutParams(surfaceViewWidth, surfaceHeight));
        }
        List<Camera.Size> pictureSizeList = parameters.getSupportedPictureSizes();
        Camera.Size pictureSize = getCmeraPreSize(pictureSizeList);
        if (null == pictureSize) {
            pictureSize = parameters.getPictureSize();
        }
        parameters.setPictureSize(pictureSize.width, pictureSize.height);
        parameters.setJpegQuality(100);
        if (parameters.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        }
        parameters.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_AUTO);
        camera.cancelAutoFocus();
        camera.setDisplayOrientation(90);
        camera.setParameters(parameters);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                recreate();
            } else {
                openSurfaceView();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        restartCamera();
    }

    private void restartCamera() {
        if (mCamera == null) {
            mCamera = Camera.open(0);
            try {
                iv_take.setVisibility(View.VISIBLE);
                iv_cancel.setVisibility(View.GONE);
                iv_sure.setVisibility(View.GONE);
                space_view.setVisibility(View.GONE);
                mCamera.setPreviewDisplay(mHolder);
                setCameraParms(mCamera, surfaceWidth, surfaceHeight);
                mCamera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.iv_take) {
            mCamera.takePicture(null, null, new Camera.PictureCallback() {
                @Override
                public void onPictureTaken(byte[] data, Camera camera) {

                    iv_take.setVisibility(View.GONE);
                    iv_cancel.setVisibility(View.VISIBLE);
                    iv_sure.setVisibility(View.VISIBLE);
                    space_view.setVisibility(View.VISIBLE);
                    mCamera.stopPreview();
                    imgData = data;
                }
            });

        } else if (i == R.id.iv_cancel) {
            mCamera.startPreview();
            iv_take.setVisibility(View.VISIBLE);
            iv_cancel.setVisibility(View.GONE);
            iv_sure.setVisibility(View.GONE);
            space_view.setVisibility(View.GONE);

        } else if (i == R.id.iv_sure) {//                showLoading();
            Bitmap bitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
            Bitmap roateBitmap = BitmapFaceUtils.rotateBitmap(bitmap, 90);

            double scaleHeight = fl_surfaceViewHeight;
            double scaleWidth = fl_surfaceViewWidth;
            double surfaceScale = scaleWidth / scaleHeight;
            double bWidth = roateBitmap.getWidth();
            double bHeight = roateBitmap.getHeight();
            double bitmapScale = bWidth / bHeight;
            Bitmap sizeBitmap = null;
            if (bitmapScale < surfaceScale) {
                int bitmapHeight = (int) (bWidth / surfaceScale);
                sizeBitmap = Bitmap.createBitmap(roateBitmap, 0, 0, (int) bWidth, bitmapHeight);
            } else {
                int bitmapWidth = (int) (bHeight * surfaceScale);
                sizeBitmap = Bitmap.createBitmap(roateBitmap, 0, 0, (int) bitmapWidth, (int) bHeight);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            sizeBitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            byte[] datas = baos.toByteArray();
            StreamUtils.createFileWithByte(datas, getCacheDir() + "/img_dynamic.jpg");
            ActivityUtils.startActivity(DynamicPhotoActivity.this, DynamicDragPointActivity.class);

        } else {
        }
    }

}
