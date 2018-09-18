package com.yijian.staff.mvp.workspace.perfect;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.face.BitmapFaceUtils;
import com.yijian.staff.mvp.workspace.utils.ActivityUtils;
import com.yijian.staff.mvp.workspace.utils.HttpManagerWorkSpace;
import com.yijian.staff.mvp.workspace.utils.StreamUtils;
import com.yijan.commonlib.net.response.ResultJSONObjectObserver;
import com.yijian.staff.widget.NavigationBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PerfectActivity extends MvcBaseActivity {

    private SurfaceView surfaceView;
    private Camera mCamera;
    private SurfaceHolder mHolder;
    private static final int REQUEST_CAMERA_CODE = 0x100;

    @BindView(R.id.iv_take)
    ImageView iv_take;
    @BindView(R.id.iv_cancel)
    ImageView iv_cancel;
    @BindView(R.id.iv_sure)
    ImageView iv_sure;
    @BindView(R.id.space_view)
    Space space_view;
    byte[] imgData = null;
    private int screenOritation = 0;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_perfect;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        OrientationEventListener mOrientationListener = new OrientationEventListener(this,
                SensorManager.SENSOR_DELAY_NORMAL) {

            @Override
            public void onOrientationChanged(int orientation) {
                if (orientation == OrientationEventListener.ORIENTATION_UNKNOWN) {
                    return;
                }
                if (orientation > 350 || orientation < 10) { //0度
                    orientation = 0;
                    PerfectActivity.this.screenOritation = orientation + 90;
                } else if (orientation > 80 && orientation < 100) { //90度
                    orientation = 90;
                    PerfectActivity.this.screenOritation = orientation + 90;
                } else if (orientation > 170 && orientation < 190) { //180度
                    orientation = 180;
                    PerfectActivity.this.screenOritation = orientation + 90;
                } else if (orientation > 260 && orientation < 280) { //270度
                    orientation = 270;
                    PerfectActivity.this.screenOritation = orientation + 90;
                }
                PerfectActivity.this.screenOritation = orientation + 90;
//                Log.e("Test", "orientation====" + orientation);
            }
        };
        if (mOrientationListener.canDetectOrientation()) {
            Log.v("Test", "Can detect orientation");
            mOrientationListener.enable();
        } else {
            Log.v("Test", "Cannot detect orientation");
            mOrientationListener.disable();
        }
        initTitle();
        initUi();
    }

    private void initTitle() {
        NavigationBar navigationBar = findViewById(R.id.navigation_bar);
        navigationBar.setTitle("完美围度拍照");
        navigationBar .hideLeftSecondIv();
        TextView rightTv =  navigationBar.getmRightTv();
        rightTv.setText("跳过");
        rightTv.setTextColor(getResources().getColor(R.color.blue));
        navigationBar .setBackClickListener(this);
    }

    private void initUi() {
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

    @OnClick({R.id.right_tv, R.id.iv_take, R.id.iv_cancel, R.id.iv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_tv: //跳过
                ActivityUtils.startActivity(this,PerfectTestActivity.class);
                break;
            case R.id.iv_take: //拍照
                mCamera.takePicture(null, null, new Camera.PictureCallback(){
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        iv_take.setVisibility(View.GONE);
                        iv_cancel.setVisibility(View.VISIBLE);
                        iv_sure.setVisibility(View.VISIBLE);
                        space_view.setVisibility(View.VISIBLE);
                        mCamera.stopPreview();
                        imgData = data;

                    }
                } );
                break;
            case R.id.iv_cancel: //取消
                mCamera.startPreview();
                iv_take.setVisibility(View.VISIBLE);
                iv_cancel.setVisibility(View.GONE);
                iv_sure.setVisibility(View.GONE);
                space_view.setVisibility(View.GONE);
                break;
            case R.id.iv_sure: //确定
                showLoading();
                Bitmap bitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
                Bitmap roateBitmap = BitmapFaceUtils.rotateBitmap(bitmap, screenOritation);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                roateBitmap.compress(Bitmap.CompressFormat.JPEG, 30, baos);
                byte[] datas = baos.toByteArray();
                StreamUtils.createFileWithByte(datas, getCacheDir() + "/img_perfect.jpg");

                HttpManagerWorkSpace.upLoadImageHasParam(HttpManagerWorkSpace.WORKSPACE_UPLOAD_FILE__URL, getCacheDir()+"/img_perfect.jpg", 10, new ResultJSONObjectObserver(getLifecycle()) {
                    @Override
                    public void onSuccess(JSONObject result) {
                        try {
                            hideLoading();
                            roateBitmap.recycle();
                            bitmap.recycle();
                            JSONArray jsonArray = result.getJSONArray("dataList");
                            String imgUrl = jsonArray.getString(0);
                            Bundle bundle = new Bundle();
                            bundle.putString("imgUrl",imgUrl);
                            ActivityUtils.startActivity(PerfectActivity.this,PerfectTestActivity.class,bundle);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(String msg) {
                        hideLoading();
                        Toast.makeText(PerfectActivity.this,msg,Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            default:
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
                Log.e("Test2", "surfaceCreated()....");
                if (mCamera != null) {
                    mCamera.stopPreview();
                    mCamera.setPreviewCallback(null);
                    mCamera.release();
                }
                mCamera = Camera.open(0);
                try {
                    mCamera.setPreviewDisplay(mHolder);
                    int measuredWidth = surfaceView.getMeasuredWidth();
                    int measuredHeight = surfaceView.getMeasuredHeight();
                    setCameraParms(mCamera, measuredWidth, measuredHeight);
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
                Log.e("Test2", "surfaceDestroyed()....");
                if (mCamera != null) {
                    mCamera.stopPreview();
                    mCamera.setPreviewCallback(null);
                    mCamera.release();
                    mCamera = null;
                }

            }
        });
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
        List<Camera.Size> pictureSizeList = parameters.getSupportedPictureSizes();
        Camera.Size pictureSize = getProperSize(pictureSizeList, (float) height / width);
        if (null == pictureSize) {
            pictureSize = parameters.getPictureSize();
        }
        float w = pictureSize.width;
        float h = pictureSize.height;
        parameters.setPictureSize(pictureSize.width, pictureSize.height);
        surfaceView.setLayoutParams(new FrameLayout.LayoutParams((int) (height * (h / w)), height));
        List<Camera.Size> previewSizeList = parameters.getSupportedPreviewSizes();
        Camera.Size preSize = getProperSize(previewSizeList, (float) height / width);
        if (null != preSize) {
            parameters.setPreviewSize(preSize.width, preSize.height);
        }
        parameters.setJpegQuality(100);
        if (parameters.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            // 连续对焦
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        }
//        parameters.setPictureSize(width,height);
        parameters.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_AUTO);
        camera.cancelAutoFocus();
        camera.setDisplayOrientation(90);
        camera.setParameters(parameters);
    }

    private Camera.Size getProperSize(List<Camera.Size> pictureSizes, float screenRatio) {
        Camera.Size result = null;
        for (Camera.Size size : pictureSizes) {
            float currenRatio = ((float) size.width) / size.height;
            if (currenRatio - screenRatio == 0) {
                result = size;
                break;
            }
        }
        if (null == result) {
            for (Camera.Size size : pictureSizes) {
                float curRatio = ((float) size.width) / size.height;
                if (curRatio == 4f / 3) {
                    result = size;
                    break;
                }
            }
        }
        return result;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                recreate();
            }else{
                openSurfaceView();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Test2", "onStop()....");
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
        Log.e("Test2", "onRestart()....");
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
                int measuredWidth = surfaceView.getMeasuredWidth();
                int measuredHeight = surfaceView.getMeasuredHeight();
                setCameraParms(mCamera, measuredWidth, measuredHeight);
                mCamera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
