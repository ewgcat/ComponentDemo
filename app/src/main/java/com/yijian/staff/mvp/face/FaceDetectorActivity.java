package com.yijian.staff.mvp.face;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.staff.R;
import com.yijian.staff.bean.AccessStatisticsRequestBody;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.net.response.ResultStringObserver;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.LoadingProgressDialog;
import com.yijian.staff.util.system.ScreenUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import jp.wasabeef.glide.transformations.BlurTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


public class FaceDetectorActivity extends AppCompatActivity implements Camera.PreviewCallback {

    private static final String TAG = FaceDetectorActivity.class.getSimpleName();
    private static final int REQUEST_CAMERA_CODE = 0x100;
    private SurfaceView surfaceView;
    private Camera mCamera;
    private SurfaceHolder mHolder;
    private DrawFacesView facesView;
    private ImageView iv_test;
    private ImageView btn_start_face;
    private String face_session;
    private Camera.Face[] faces;
    private int screenOritation = 0;
    private FaceInfoPanel faceInfoPanel;
    private Bitmap resizeBmp;

    /**
     * 测试弹出框
     */
    private void showPanel(List<FaceDetail> faceDetails) {
        faceInfoPanel = new FaceInfoPanel(this, faceDetails);
        faceInfoPanel.showAtLocation(getWindow().getDecorView(), Gravity.TOP, 0, 0);
        btn_start_face.setVisibility(View.GONE);
        faceInfoPanel.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                iv_test.setImageBitmap(null);
                btn_start_face.setVisibility(View.VISIBLE);
                facesView.removeRect();
                if (mCamera != null) {
                    mCamera.startPreview();
                    mCamera.startFaceDetection();
                    return;
                }
//                restartCamera();
            }
        });
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, FaceDetectorActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String version = CommonUtil.getAccessStatisticsVersionName(this) + " " + CommonUtil.getVersionCode(this);
        AccessStatisticsRequestBody body=new AccessStatisticsRequestBody("app_face_recognition",version);
        HttpManager.postAccessStatistics(body, new ResultJSONObjectObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onFail(String msg) {

            }
        });

        OrientationEventListener mOrientationListener = new OrientationEventListener(this,
                SensorManager.SENSOR_DELAY_NORMAL) {

            @Override
            public void onOrientationChanged(int orientation) {
                if (orientation == OrientationEventListener.ORIENTATION_UNKNOWN) {
                    return;
                }
                if (orientation > 350 || orientation < 10) { //0度
                    orientation = 0;
                    FaceDetectorActivity.this.screenOritation = orientation + 90;
                } else if (orientation > 80 && orientation < 100) { //90度
                    orientation = 90;
                    FaceDetectorActivity.this.screenOritation = orientation + 90;
                } else if (orientation > 170 && orientation < 190) { //180度
                    orientation = 180;
                    FaceDetectorActivity.this.screenOritation = orientation + 90;
                } else if (orientation > 260 && orientation < 280) { //270度
                    orientation = 270;
                    FaceDetectorActivity.this.screenOritation = orientation + 90;
                }
                FaceDetectorActivity.this.screenOritation = orientation + 90;
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

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_face2);
        initViews();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_CODE);
                }
                return;
            }
            loginData();
            //openSurfaceView();
        }
    }

    /**
     * 把摄像头的图像显示到SurfaceView
     */
    private void openSurfaceView() {
        btn_start_face.setVisibility(View.VISIBLE);
        surfaceView.setVisibility(View.VISIBLE);
        facesView.setVisibility(View.VISIBLE);

        mHolder = surfaceView.getHolder();
        mHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.e("Test2", "surfaceCreated()....");
                if (faceInfoPanel == null || !faceInfoPanel.isShowing()) {
                    if (mCamera != null) {
                        mCamera.stopPreview();
                        mCamera.setPreviewCallback(null);
                        mCamera.release();
                    }
                    mCamera = Camera.open(0);
                    try {
                        mCamera.setFaceDetectionListener(new FaceDetectorListener());
//                        mCamera.setPreviewDisplay(holder);
                        mCamera.setPreviewDisplay(mHolder);
                        int measuredWidth = surfaceView.getMeasuredWidth();
                        int measuredHeight = surfaceView.getMeasuredHeight();
                        setCameraParms(mCamera, measuredWidth, measuredHeight);
                        mCamera.startPreview();
                        startFaceDetection(); // re-start face detection feature
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.e(TAG, "surfaceChanged.....");
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

    private Bitmap bitmap2;

    private void initViews() {
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        facesView = (DrawFacesView) findViewById(R.id.drawFacesView);
        iv_test = (ImageView) findViewById(R.id.iv_test);
        findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCamera != null) {
                    mCamera.setPreviewCallback(null);
                    mCamera.stopPreview();
                    mCamera.release();
                    mCamera = null;
                }
                finish();
            }
        });
        btn_start_face = findViewById(R.id.btn_start_face);
        btn_start_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_start_face.setEnabled(false);
                if (faces != null && faces.length > 0) {

                    mCamera.takePicture(null, null, new Camera.PictureCallback() {
                        @Override
                        public void onPictureTaken(byte[] data, Camera camera) {
                            clearFaceRect(false);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    mCamera.stopPreview();
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    Bitmap roateBitmap = BitmapFaceUtils.rotateBitmap(bitmap, screenOritation);
                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                    roateBitmap.compress(Bitmap.CompressFormat.JPEG, 30, baos);
                                    byte[] datas = baos.toByteArray();
                                    bitmap2 = BitmapFactory.decodeByteArray(datas, 0, datas.length);

                                    int bitmapHeight = bitmap2.getHeight();
                                    int bitmapWidth = bitmap2.getWidth();
                                    double sWidth = ScreenUtil.getScreenWidth(FaceDetectorActivity.this);
                                    double sHeight = ScreenUtil.getScreenHeight(FaceDetectorActivity.this);
                                    double scale = sHeight / sWidth;
                                    //截屏上传后 显示高斯模糊照片
                                    resizeBmp = Bitmap.createBitmap(bitmap2, 0, 0, (int) (bitmapHeight / (scale)), bitmapHeight);

                                    FaceDetectorActivity.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            LoadingProgressDialog.showBlueProgress(FaceDetectorActivity.this);
                                            int radius = 10;
                                            if(bitmapWidth*bitmapHeight <= 320*240){
                                                radius = 1;
                                            }
                                            RequestOptions options = RequestOptions.bitmapTransform(new BlurTransformation(radius, 4));
                                            Glide.with(FaceDetectorActivity.this).load(resizeBmp)
                                                    .apply(options).into(iv_test);
                                            try {
                                                baos.close();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            //调用搜索接口
                                            postData(datas);
                                        }
                                    });

                                }
                            }).start();

                        }
                    });


                } else {
                    Toast.makeText(FaceDetectorActivity.this, "没有搜索到可识别的人脸", Toast.LENGTH_SHORT).show();
                    btn_start_face.setEnabled(true);
                }

            }
        });
    }


    /**
     * 验证登录
     */
    private void loginData() {

        HttpManager.getHasHeaderNoParam(HttpManager.GET_FACE_LOGIN_SESSION, new ResultStringObserver(getLifecycle()) {
            @Override
            public void onSuccess(String result) {
                LoadingProgressDialog.hideLoading(FaceDetectorActivity.this);
                if (!TextUtils.isEmpty(result)) {
                    face_session = result;
                    openSurfaceView();
                } else {
                    Message msg = mHandler.obtainMessage();
                    msg.what = USER_VERIFICATION_USER_FAIL;
                    mHandler.sendMessage(msg);
                }
            }

            @Override
            public void onFail(String msg) {
                Message msg2 = mHandler.obtainMessage();
                msg2.what = USER_VERIFICATION_USER_FAIL;
                mHandler.sendMessage(msg2);
            }
        });

    }


    /**
     * 人脸搜索
     *
     * @param imgData
     */
    private void postData(byte[] imgData) {

        // Log信息拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("http", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //设置 Debug Log 模式
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(2 * 60000, TimeUnit.SECONDS)
                .readTimeout(2 * 60000, TimeUnit.SECONDS)
                .writeTimeout(2 * 60000, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor).build();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", "req");
            jsonObject.put("name", "AdvancedFaceSearch");
            jsonObject.put("session", face_session);

            JSONObject bodyJsonObj = new JSONObject();
            bodyJsonObj.put("max_result", "3");
            bodyJsonObj.put("min_score", "0");
            bodyJsonObj.put("image", "msg://0");
            JSONArray jsonArray = new JSONArray();
            jsonArray.put("10");
            bodyJsonObj.put("face_set", jsonArray);
//            bodyJsonObj.put("face_set", "10");

            jsonObject.put("body", bodyJsonObj);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        RequestBody fileBody = RequestBody.create(null, imgData);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("message", jsonObject.toString())
                .addFormDataPart("files", "face_img.jpg", fileBody)
                .build();


        Request request = new Request.Builder()
                .url("http://47.98.175.166:8080/")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Test", e.getMessage());
                Message msg = mHandler.obtainMessage();
                msg.what = USER_GET_FACE_SEARCH_FAIL;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //响应消息体
                String content = response.body().string();
                Log.e("Test", content);
                try {
                    JSONObject resultJOb = new JSONObject(content);
                    int code = resultJOb.getInt("code");
                    if (code == 0) {
//                        JSONObject bodyJOb = resultJOb.getJSONObject("body");
                        List<FaceBean> faceBeans = new ArrayList<>();
                        JSONArray bodyJarray = resultJOb.getJSONArray("body");
                        for (int i = 0; i < bodyJarray.length(); i++) {
                            JSONObject jsonObject1 = bodyJarray.getJSONObject(i);
                            JSONArray bodyResultJarray = jsonObject1.getJSONArray("face_results");
                            for (int j = 0; j < bodyResultJarray.length(); j++) {
                                JSONObject jsonObject2 = bodyResultJarray.getJSONObject(j);
                                JSONArray bodyResultSubJarray = jsonObject2.getJSONArray("face_set_results");
                                faceBeans.addAll(com.alibaba.fastjson.JSONArray.parseArray(bodyResultSubJarray.toString(), FaceBean.class));
                            }
                        }


                        StringBuffer sb = new StringBuffer();
                        if (faceBeans.size() > 0) {

                            int count = 0;
                            for (FaceBean faceBean : faceBeans) {
                                if (faceBean.getScore() > 0.8) {
                                    sb.append(faceBean.getPeople_id() + ",");
                                    count++;
                                }
                            }
                            if (count > 0) {
                                getMemberDatas(sb.toString().substring(0, sb.toString().length() - 1));
                                return;
                            }
                            Message msg = mHandler.obtainMessage();
                            msg.what = USER_TEST_FACE_LIBRARY_NO;
                            mHandler.sendMessage(msg);
                        } else {
                            Message msg = mHandler.obtainMessage();
                            msg.what = USER_TEST_FACE_NO;
                            mHandler.sendMessage(msg);
                        }


                    } else {
                        Log.e("Test", "请求结果....识别失败");
                        Message msg = mHandler.obtainMessage();
                        msg.what = USER_TEST_FACE_FAIL;
                        msg.arg1 = code;
                        mHandler.sendMessage(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Message msg = mHandler.obtainMessage();
                    msg.what = USER_TEST_FACE_EXCEPTION;
                    mHandler.sendMessage(msg);
                }

            }
        });

    }


    /**
     * 获取会员用户信息
     */
    public void getMemberDatas(String ids) {

        Map<String, String> param = new HashMap<>();
        param.put("memberIds", ids);
        HttpManager.postHasHeaderHasParam(HttpManager.GET_FACE_MENBERSHOWINFO, param, new ResultJSONArrayObserver(getLifecycle()) {
            @Override
            public void onSuccess(JSONArray result) {
                if (result.length() > 0) {
                    List<FaceDetail> faceDetails = com.alibaba.fastjson.JSONArray.parseArray(result.toString(), FaceDetail.class);
                    Message msg = mHandler.obtainMessage();
                    msg.what = USER_GET_VIP_INFO_SUCCESS;
                    msg.obj = faceDetails;
                    mHandler.sendMessage(msg);
                    return;
                }
                Message msg = mHandler.obtainMessage();
                msg.what = USER_GET_VIP_INFO_NO;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onFail(String msg) {
                Message msgHandle = mHandler.obtainMessage();
                msgHandle.what = USER_GET_VIP_INFO_FAIL;
                mHandler.sendMessage(msgHandle);
            }
        });

    }

    private void clearFaceRect(boolean isStart) {
        facesView.isRemove(true);
        facesView.removeRect();
        if (isStart && mCamera != null) {
            mCamera.startPreview();
            mCamera.startFaceDetection();
            return;
        }
    }

    private final int USER_VERIFICATION_USER_FAIL = 0;//验证失败
    private final int USER_TEST_FACE_NO = 1;//没有检测到人脸
    private final int USER_TEST_FACE_FAIL = 2;//识别失败 (如果调用搜索 code 返回101，session过期)
    private final int USER_TEST_FACE_EXCEPTION = 3;//识别异常
    private final int USER_TEST_FACE_LIBRARY_NO = 6;//返回的数据里面的相似度都小于0.8
    private final int USER_GET_VIP_INFO_NO = 4;//没有获取到对应会员数据
    private final int USER_GET_VIP_INFO_FAIL = 5;//获取到对应会员数据失败
    private final int USER_GET_VIP_INFO_SUCCESS = 7;//获取对应会员数据成功
    private final int USER_GET_FACE_SEARCH_FAIL = 8;//人脸搜索连接失败

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case USER_VERIFICATION_USER_FAIL:
                    Toast.makeText(FaceDetectorActivity.this, "用户验证失败", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case USER_TEST_FACE_NO:
                    iv_test.setImageBitmap(null);
                    Toast.makeText(FaceDetectorActivity.this, "没有检测到人脸", Toast.LENGTH_SHORT).show();
                    btn_start_face.setEnabled(true);
                    clearFaceRect(true);
                    break;
                case USER_TEST_FACE_FAIL:
                    iv_test.setImageBitmap(null);
                    Toast.makeText(FaceDetectorActivity.this, "识别失败", Toast.LENGTH_SHORT).show();
                    btn_start_face.setEnabled(true);
                    clearFaceRect(true);
                    int code = msg.arg1;
                    if(code == 101){ //session过期,重新刷新session
                        loginData();
                    }
                    break;
                case USER_TEST_FACE_EXCEPTION:
                    iv_test.setImageBitmap(null);
                    Toast.makeText(FaceDetectorActivity.this, "识别异常", Toast.LENGTH_SHORT).show();
                    btn_start_face.setEnabled(true);
                    clearFaceRect(true);
                    break;
                case USER_TEST_FACE_LIBRARY_NO:
                    iv_test.setImageBitmap(null);
                    Toast.makeText(FaceDetectorActivity.this, "人脸库没找到相应的人员", Toast.LENGTH_SHORT).show();
                    btn_start_face.setEnabled(true);
                    clearFaceRect(true);
                    break;
                case USER_GET_VIP_INFO_NO:
                    iv_test.setImageBitmap(null);
                    Toast.makeText(FaceDetectorActivity.this, "没有获取到对应会员数据", Toast.LENGTH_SHORT).show();
                    btn_start_face.setEnabled(true);
                    clearFaceRect(true);
                    break;
                case USER_GET_VIP_INFO_FAIL:
                    iv_test.setImageBitmap(null);
                    Toast.makeText(FaceDetectorActivity.this, "获取到对应会员数据失败", Toast.LENGTH_SHORT).show();
                    btn_start_face.setEnabled(true);
                    clearFaceRect(true);
                    break;
                case USER_GET_FACE_SEARCH_FAIL:
                    iv_test.setImageBitmap(null);
                    Toast.makeText(FaceDetectorActivity.this, "人脸服务器连接失败", Toast.LENGTH_SHORT).show();
                    btn_start_face.setEnabled(true);
                    clearFaceRect(true);
                    break;
                case USER_GET_VIP_INFO_SUCCESS:
                    showPanel((List<FaceDetail>) msg.obj);
                    btn_start_face.setEnabled(true);
                    break;

            }
            bitmap2.recycle();
            resizeBmp.recycle();
            LoadingProgressDialog.hideLoading(FaceDetectorActivity.this);

        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                recreate();
            }
        }
    }

    //上次记录的时间戳
    long lastRecordTime = System.currentTimeMillis();

    //上次记录的索引
    int darkIndex = 0;
    //一个历史记录的数组，255是代表亮度最大值
    long[] darkList = new long[]{255, 255, 255, 255};
    //扫描间隔
    int waitScanTime = 300;

    //亮度低的阀值
    int darkValue = 60;

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
//        Log.e("Test","onPreview....."+data.length);
//        setCameraDisplayOrientation(this, Camera.CameraInfo.CAMERA_FACING_BACK,camera);

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastRecordTime < waitScanTime) {
            return;
        }
        lastRecordTime = currentTime;

        int width = camera.getParameters().getPreviewSize().width;
        int height = camera.getParameters().getPreviewSize().height;
        //像素点的总亮度
        long pixelLightCount = 0L;
        //像素点的总数
        long pixeCount = width * height;
        //采集步长，因为没有必要每个像素点都采集，可以跨一段采集一个，减少计算负担，必须大于等于1。
        int step = 10;
        //data.length - allCount * 1.5f的目的是判断图像格式是不是YUV420格式，只有是这种格式才相等
        //因为int整形与float浮点直接比较会出问题，所以这么比
        if (Math.abs(data.length - pixeCount * 1.5f) < 0.00001f) {
            for (int i = 0; i < pixeCount; i += step) {
                //如果直接加是不行的，因为data[i]记录的是色值并不是数值，byte的范围是+127到—128，
                // 而亮度FFFFFF是11111111是-127，所以这里需要先转为无符号unsigned long参考Byte.toUnsignedLong()
                pixelLightCount += ((long) data[i]) & 0xffL;
            }
            //平均亮度
            long cameraLight = pixelLightCount / (pixeCount / step);
            //更新历史记录
            int lightSize = darkList.length;
            darkList[darkIndex = darkIndex % lightSize] = cameraLight;
            darkIndex++;
            boolean isDarkEnv = true;
            //判断在时间范围waitScanTime * lightSize内是不是亮度过暗
            for (int i = 0; i < lightSize; i++) {
                if (darkList[i] > darkValue) {
                    isDarkEnv = false;
                }
            }
//            Log.e(TAG, "摄像头环境亮度为 ： " + cameraLight);
            if (!isFinishing()) {
                //亮度过暗就提醒
                if (isDarkEnv) {
//                    Log.e(TAG, "亮度还行");
                } else {
//                    Log.e(TAG, "亮度过低");
                }
            }
        }


    }

    /**
     * 脸部检测接口
     */
    private class FaceDetectorListener implements Camera.FaceDetectionListener {
        @Override
        public void onFaceDetection(Camera.Face[] faces, Camera camera) {
//            Log.e("Test","face.size==="+faces.length);
            Log.e("Test", "onFaceDetection.......face");
//            if (isFaceDetector) {
            FaceDetectorActivity.this.faces = faces;
            if (faces.length > 0) {
                Camera.Face face = faces[0];
                Rect rect = face.rect;
                Log.e("FaceDetection", "可信度：" + face.score + "face detected: " + faces.length +
                        " Face 1 Location X: " + rect.centerX() +
                        "Y: " + rect.centerY() + "   " + rect.left + " " + rect.top + " " + rect.right + " " + rect.bottom);
                Log.e("tag", "【FaceDetectorListener】类的方法：【onFaceDetection】: ");
                Matrix matrix = updateFaceRect();
                facesView.updateFaces(matrix, faces);
            } else {
                // 只会执行一次
                Log.e("tag", "【FaceDetectorListener】类的方法：【onFaceDetection】: " + "没有脸部");
                facesView.removeRect();
            }
        }

//        }
    }

    /**
     * 因为对摄像头进行了旋转，所以同时也旋转画板矩阵
     * 详细请查看{@link Camera.Face#rect}
     *
     * @return
     */
    private Matrix updateFaceRect() {
        Matrix matrix = new Matrix();
        Camera.CameraInfo info = new Camera.CameraInfo();
        // Need mirror for front camera.
        boolean mirror = (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT);
        matrix.setScale(mirror ? -1 : 1, 1);
        // This is the value for android.hardware.Camera.setDisplayOrientation.
        matrix.postRotate(90);
        // Camera driver coordinates range from (-1000, -1000) to (1000, 1000).
        // UI coordinates range from (0, 0) to (width, height).
        matrix.postScale(surfaceView.getWidth() / 2000f, surfaceView.getHeight() / 2000f);
        matrix.postTranslate(surfaceView.getWidth() / 2f, surfaceView.getHeight() / 2f);
        return matrix;
    }

    public void startFaceDetection() {
        // Try starting Face Detection
        Camera.Parameters params = mCamera.getParameters();
        // start face detection only *after* preview has started
        if (params.getMaxNumDetectedFaces() > 0) {
            // mCamera supports face detection, so can start it:
            try {
                mCamera.startFaceDetection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.e("tag", "【FaceDetectorActivity】类的方法：【startFaceDetection】: " + "不支持");
        }
    }

    /**
     * 在摄像头启动前设置参数
     *
     * @param camera
     * @param width
     * @param height
     */
    private void setCameraParms(Camera camera, int width, int height) {
        // 获取摄像头支持的pictureSize列表
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> pictureSizeList = parameters.getSupportedPictureSizes();
        // 从列表中选择合适的分辨率
        Camera.Size pictureSize = getProperSize(pictureSizeList, (float) height / width);
        if (null == pictureSize) {
            pictureSize = parameters.getPictureSize();
        }
        // 根据选出的PictureSize重新设置SurfaceView大小
        float w = pictureSize.width;
        float h = pictureSize.height;
        parameters.setPictureSize(pictureSize.width, pictureSize.height);

        surfaceView.setLayoutParams(new FrameLayout.LayoutParams((int) (height * (h / w)), height));

        // 获取摄像头支持的PreviewSize列表
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
        camera.setPreviewCallback(this);
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
        if (faceInfoPanel == null || (faceInfoPanel != null && (!faceInfoPanel.isShowing()))) {

            restartCamera();

        }
    }

    private void restartCamera() {
        if (mCamera == null) {
            mCamera = Camera.open(0);
            try {
                mCamera.setFaceDetectionListener(new FaceDetectorListener());
                mCamera.setPreviewDisplay(mHolder);
                int measuredWidth = surfaceView.getMeasuredWidth();
                int measuredHeight = surfaceView.getMeasuredHeight();
                setCameraParms(mCamera, measuredWidth, measuredHeight);
                mCamera.startPreview();
                startFaceDetection(); // re-start face detection feature
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
