package com.yijian.staff.mvp.main.work.face;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.FaceDetector;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yijian.staff.R;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.net.response.ResultStringObserver;
import com.yijian.staff.rx.RxUtil;
import com.yijian.staff.util.CommonUtil;
import com.yijian.staff.util.LoadingProgressDialog;
import com.yijian.staff.util.NotificationsUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import io.fotoapparat.Fotoapparat;
import io.fotoapparat.FotoapparatSwitcher;
import io.fotoapparat.facedetector.Rectangle;
import io.fotoapparat.facedetector.processor.FaceDetectorProcessor;
import io.fotoapparat.facedetector.view.RectanglesView;
import io.fotoapparat.parameter.LensPosition;
import io.fotoapparat.photo.BitmapPhoto;
import io.fotoapparat.preview.Frame;
import io.fotoapparat.result.PendingResult;
import io.fotoapparat.result.PhotoResult;
import io.fotoapparat.view.CameraView;
import io.reactivex.Observable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

import static io.fotoapparat.log.Loggers.fileLogger;
import static io.fotoapparat.log.Loggers.logcat;
import static io.fotoapparat.log.Loggers.loggers;
import static io.fotoapparat.parameter.selector.LensPositionSelectors.lensPosition;

public class FaceActivity extends AppCompatActivity {

    private CameraView cameraView;
    private MyRectanglesView rectanglesView;

    private FotoapparatSwitcher fotoapparatSwitcher;
    private Fotoapparat backFotoapparat;

    private ImageView iv_temp;
    private RelativeLayout rel_top;
    private String face_session;
    private ImageView btn_start_face;
    private Bitmap resultBitmap;
    private List<Rectangle> faces;

    /******************START 弹出框 **************************/

    /*****************END **********************************/

    /**********************************START  动态权限配置 ***********************************/

    private int index = 0;

    String[] permissions = {Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS};

    /**
     * 运行时请求权限
     */
    private void initRxPermissions(int i, String[] permissions) {
        RxPermissions rxPermissions = new RxPermissions(this);
        Observable.timer(0, TimeUnit.MILLISECONDS)
                .compose(rxPermissions.ensureEach(
                        permissions[i]
                ))
                .compose(RxUtil.rxObservableSchedulerHelper())
                .subscribe(permission -> {
                    index = i + 1;
                    if (permissions.length > index) {
                        initRxPermissions(index, permissions);
                    } else {
                        String msg = checkPermissions();
                        if (!TextUtils.isEmpty(msg) && msg.contains("【通知与状态栏权限】")) {
                            requestPermissions("    【通知与状态栏权限】\n");
                        } else {
                            loginData();
                        }
                    }
                });
    }

    /**
     * 请求通知权限  100
     */
    private void requestPermissions(String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(FaceActivity.this)
                .setTitle("获取必要权限")
                .setMessage(msg)
                .setPositiveButton("立即获取", (dialog, which) -> {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivityForResult(intent, 100);
                })
                .setNegativeButton("拒绝", (dialog, which) -> {
                    finish();
                }).create();

        alertDialog.setCancelable(false);
        alertDialog.show();
    }


    private String checkPermissions() {
        String msg = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !NotificationsUtil.isNotificationEnabled(this)) {
            msg = "    【通知与状态栏权限】\n";
        }
        return msg;
    }

    /**
     * 请求权限回调
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                String msg = checkPermissions();
                if (!TextUtils.isEmpty(msg)) {
                    Toast.makeText(this, "缺少" + msg, Toast.LENGTH_SHORT).show();
                }
                finish();

                break;
        }
    }

    /**********************************END  动态权限配置 ***********************************/


    /**
     * 测试弹出框
     */
    private void showPanel(Bitmap resultBitmap, List<FaceDetail> faceDetails) {
        FaceInfoPanel faceInfoPanel = new FaceInfoPanel(this, resultBitmap, faceDetails);
        faceInfoPanel.showAtLocation(getWindow().getDecorView(), Gravity.TOP, 0, 0);
        faceInfoPanel.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                fotoapparatSwitcher.start();
            }
        });
    }

    private Bitmap rotateBitmap(Bitmap origin, float alpha) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(alpha);
        // 围绕原地进行旋转
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        //   origin.recycle();
        return newBM;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        StatusBarUtil.setTranslucentForImageView(this, 0, null);

        setContentView(R.layout.activity_face);
        initView();
        LoadingProgressDialog.showBlueProgress(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            initRxPermissions(index, permissions);
        } else {
            loginData();
        }
    }

    private void initView() {

        rel_top = findViewById(R.id.rel_top);
        int statusBarHeight = CommonUtil.getStatusBarHeight(this);
        RelativeLayout.LayoutParams rel_top_lp = (RelativeLayout.LayoutParams) rel_top.getLayoutParams();
        rel_top_lp.setMargins(0, statusBarHeight, 0, 0);
        rel_top.setLayoutParams(rel_top_lp);

        iv_temp = (ImageView) findViewById(R.id.iv_temp);
        cameraView = (CameraView) findViewById(R.id.camera_view);
        rectanglesView = (MyRectanglesView) findViewById(R.id.rectanglesView);
        findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fotoapparatSwitcher.stop();
                finish();
            }
        });
        btn_start_face = findViewById(R.id.btn_start_face);
        btn_start_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if (faces.size() > 0) {

                        PhotoResult photoResult = backFotoapparat.takePicture();
//                    Bitmap resultBitmap = photoResult.toBitmap().await().bitmap;
                        photoResult.toBitmap().whenDone(new PendingResult.Callback<BitmapPhoto>() {
                            @Override
                            public void onResult(BitmapPhoto bitmapPhoto) {
                                btn_start_face.setEnabled(false);
                                fotoapparatSwitcher.stop();
                                resultBitmap = bitmapPhoto.bitmap;

                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                resultBitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos);
                                byte[] datas = baos.toByteArray();

                                Log.e("Test", "resultBitmap.length====" + datas.length);
                           /* Bitmap bitmap = BitmapFactory.decodeByteArray(datas, 0, datas.length);
                            Bitmap tempBitmap = rotateBitmap(bitmap,90);
                            iv_temp.setImageBitmap(tempBitmap);*/


                                LoadingProgressDialog.showBlueProgress(FaceActivity.this);
                                //调用搜索接口
                                postData(datas);
                            }
                        });
//                    iv_temp.setImageBitmap(resultBitmap);

                    } else {
                        Toast.makeText(FaceActivity.this, "请对准人脸", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private Fotoapparat createFotoapparat(LensPosition position) {
        return Fotoapparat
                .with(this)
                .into(cameraView)
                .lensPosition(lensPosition(position))
                .frameProcessor(
                        MyFaceDetectorProcessor.with(this)
                                .listener(new MyFaceDetectorProcessor.OnFacesDetectedListener() {
                                    @Override
                                    public void onFacesDetected(List<Rectangle> faces, Frame frame) {

                                        Log.e("Test", "正在识别.....");

                                        Log.e("Test", "Detected faces: " + faces.size());

                                        FaceActivity.this.faces = faces;

                                        rectanglesView.setRectangles(faces);
                                       /* if (faces.size() > 0) {
                                            if (frame.image != null) {

                                                try {
                                                    YuvImage image = new YuvImage(frame.image, ImageFormat.NV21, frame.size.width,
                                                            frame.size.height, null);
                                                    if (image != null) {
                                                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                                        image.compressToJpeg(new Rect(0, 0, frame.size.width,
                                                                        frame.size.height),
                                                                80, stream);
                                                        Bitmap bitmap = BitmapFactory.decodeByteArray(
                                                                stream.toByteArray(), 0, stream.size());
                                                        stream.close();
                                                        iv_temp.setImageBitmap(bitmap);
                                                        backFotoapparat.stop();
                                                        Log.e("Test", "stream.toByteArray()====" + stream.toByteArray().length);
                                                        postData(stream.toByteArray());
                                                        showPanel();
                                                    }


                                                } catch (Exception ex) {
                                                    ex.printStackTrace();
                                                }

                                            }
                                        }*/
                                    }
                                })
                                .build()
                )
                .logger(loggers(
                        logcat(),
                        fileLogger(this)
                ))
                .build();
    }

    /**
     * 验证登录
     */
    private void loginData() {

        HttpManager.getHasHeaderNoParam(HttpManager.GET_FACE_LOGIN_SESSION, new ResultStringObserver() {
            @Override
            public void onSuccess(String result) {
                LoadingProgressDialog.hideLoading(FaceActivity.this);
                if (!TextUtils.isEmpty(result)) {
                    face_session = result;
                    cameraView.setVisibility(View.VISIBLE);
                    btn_start_face.setVisibility(View.VISIBLE);
                    backFotoapparat = createFotoapparat(LensPosition.BACK);
                    fotoapparatSwitcher = FotoapparatSwitcher.withDefault(backFotoapparat);
                    fotoapparatSwitcher.start();
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
            jsonObject.put("name", "FaceSearch");
            jsonObject.put("session", "815c56d9afac4a7b8e04a44c016eaadc");

            JSONObject bodyJsonObj = new JSONObject();
            bodyJsonObj.put("max_result", "10");
            bodyJsonObj.put("min_score", "0");
            bodyJsonObj.put("image", "msg://0");
            bodyJsonObj.put("face_set", "10");

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
                        JSONObject bodyJOb = resultJOb.getJSONObject("body");
                        List<FaceBean> faceBeans = com.alibaba.fastjson.JSONArray.parseArray(bodyJOb.getJSONArray("result").toString(), FaceBean.class);
                        StringBuffer sb = new StringBuffer();
                        int count = 0;
                        for (FaceBean faceBean : faceBeans) {
                            if (faceBean.getScore() > 0.5) {
                                sb.append(faceBean.getPeople_id() + ",");
                                count++;
                            }
                        }
                        if (count > 0) {
                            getMemberDatas(sb.toString().substring(0, sb.toString().indexOf(",")));
                            return;
                        }
                        Message msg = mHandler.obtainMessage();
                        msg.what = USER_TEST_FACE_NO;
                        mHandler.sendMessage(msg);
                    } else {
                        Message msg = mHandler.obtainMessage();
                        msg.what = USER_TEST_FACE_FAIL;
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
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("memberIds", "09b871dc38ba4e57b97782fb30d70517");
        Request request = new Request.Builder()
                .url("http://bweb.dev.ejoyst.com/member/menberShowInfo")
                .post(formBody.build())
                .build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Test", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //响应消息体
                String content = response.body().string();
                Log.e("Test", content);
                try {
                    JSONObject jsonObject = new JSONObject(content);
                    int code = jsonObject.getInt("code");
                    if (code == 0) {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        if (jsonArray.length() > 0) {
                            List<FaceDetail> faceDetails = com.alibaba.fastjson.JSONArray.parseArray(jsonArray.toString(), FaceDetail.class);
                            Message msg = mHandler.obtainMessage();
                            msg.what = USER_GET_VIP_INFO_SUCCESS;
                            msg.obj = faceDetails;
                            mHandler.sendMessage(msg);
                            return;
                        }
                        Message msg = mHandler.obtainMessage();
                        msg.what = USER_GET_VIP_INFO_NO;
                        mHandler.sendMessage(msg);
                    } else {
                        Message msg = mHandler.obtainMessage();
                        msg.what = USER_GET_VIP_INFO_FAIL;
                        mHandler.sendMessage(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Message msg = mHandler.obtainMessage();
                    msg.what = USER_GET_VIP_INFO_EXCEPTION;
                    mHandler.sendMessage(msg);
                }


            }
        });

    }

    private final int USER_VERIFICATION_USER_FAIL = 0;//验证失败
    private final int USER_TEST_FACE_NO = 1;//没有检测到人脸
    private final int USER_TEST_FACE_FAIL = 2;//识别失败
    private final int USER_TEST_FACE_EXCEPTION = 3;//识别异常
    private final int USER_GET_VIP_INFO_NO = 4;//没有获取到对应会员数据
    private final int USER_GET_VIP_INFO_FAIL = 5;//获取到对应会员数据失败
    private final int USER_GET_VIP_INFO_EXCEPTION = 6;//获取对应会员数据异常
    private final int USER_GET_VIP_INFO_SUCCESS = 7;//获取对应会员数据成功

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case USER_VERIFICATION_USER_FAIL:
                    Toast.makeText(FaceActivity.this, "用户验证失败", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case USER_TEST_FACE_NO:
                    Toast.makeText(FaceActivity.this, "没有检测到人脸", Toast.LENGTH_SHORT).show();
                    btn_start_face.setEnabled(true);
                    fotoapparatSwitcher.start();
                    break;
                case USER_TEST_FACE_FAIL:
                    Toast.makeText(FaceActivity.this, "识别失败", Toast.LENGTH_SHORT).show();
                    btn_start_face.setEnabled(true);
                    fotoapparatSwitcher.start();
                    break;
                case USER_TEST_FACE_EXCEPTION:
                    Toast.makeText(FaceActivity.this, "识别异常", Toast.LENGTH_SHORT).show();
                    btn_start_face.setEnabled(true);
                    fotoapparatSwitcher.start();
                    break;
                case USER_GET_VIP_INFO_NO:
                    Toast.makeText(FaceActivity.this, "没有获取到对应会员数据", Toast.LENGTH_SHORT).show();
                    btn_start_face.setEnabled(true);
                    fotoapparatSwitcher.start();
                    break;
                case USER_GET_VIP_INFO_FAIL:
                    Toast.makeText(FaceActivity.this, "获取到对应会员数据失败", Toast.LENGTH_SHORT).show();
                    btn_start_face.setEnabled(true);
                    fotoapparatSwitcher.start();
                    break;
                case USER_GET_VIP_INFO_EXCEPTION:
                    Toast.makeText(FaceActivity.this, "获取对应会员数据异常", Toast.LENGTH_SHORT).show();
                    btn_start_face.setEnabled(true);
                    fotoapparatSwitcher.start();
                    break;
                case USER_GET_VIP_INFO_SUCCESS:
                    showPanel(rotateBitmap(resultBitmap, 90), (List<FaceDetail>) msg.obj);
                    btn_start_face.setEnabled(true);
                    break;

            }
            LoadingProgressDialog.hideLoading(FaceActivity.this);

        }
    };

    @Override
    protected void onRestart() {
        Log.e("Test", "onRestart......");
        super.onRestart();
        if (fotoapparatSwitcher != null) {
            fotoapparatSwitcher.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Test", "onPause......");
        if (fotoapparatSwitcher != null) {
            fotoapparatSwitcher.stop();
        }
    }
}
