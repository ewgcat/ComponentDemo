package com.yijian.staff.mvp.mine.qualification;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yijian.staff.BuildConfig;
import com.yijian.staff.R;
import com.yijian.staff.application.CustomApplication;
import com.yijian.staff.constant.BundleKeyConstant;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.db.bean.User;
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity;
import com.yijian.staff.mvp.seepic.SeePicActivity;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.httpmanager.UploadManager;
import com.yijian.staff.net.requestbody.authcertificate.AuthBean;
import com.yijian.staff.net.requestbody.authcertificate.AuthCertificateRequestBody;
import com.yijian.staff.net.requestbody.authcertificate.CertBean;
import com.yijian.staff.net.response.ResultJSONArrayObserver;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.net.response.ResultStringObserver;
import com.yijian.staff.rx.RxBus;
import com.yijian.staff.util.JsonUtil;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.selectphoto.ChoosePhotoView;
import com.yijian.staff.widget.selectphoto.ImageBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import me.iwf.photopicker.PhotoPicker;

public class EditQualificationActivity extends MvcBaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.choose_photo_view)
    ChoosePhotoView choosePhotoView;
    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.et3)
    EditText et3;
    @BindView(R.id.et4)
    EditText et4;
    @BindView(R.id.et5)
    EditText et5;
    @BindView(R.id.et6)
    EditText et6;
    private Dialog dialog;

    private List<AuthBean> authList = new ArrayList<>();
    private List<CertBean> certList = new ArrayList<>();


    @Override
    protected int getLayoutID() {
        return R.layout.activity_edit_qualification;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.edit_activity_navigation_bar);
        navigationBar2.setTitle("资格证书");
        navigationBar2.setmRightTvText("完成");
        navigationBar2.hideLeftSecondIv();
        navigationBar2.setBackClickListener(this);
        navigationBar2.setmRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post();

            }
        });


        choosePhotoView.setMode(ChoosePhotoView.MODE_NORMAL);
        choosePhotoView.setChoosePhotoViewListener(new ChoosePhotoView.OnChoosePhotoViewListener() {
            @Override
            public void hadChangedGridView() {
            }

            @Override
            public void hadAddToPath(String path) {
            }

            @Override
            public void hadDeletePath(String path) {
            }
        });
        initDialog();
        initData();

    }

    private void initData() {
        User user = DBManager.getInstance().queryUser();
        HashMap<String, String> param = new HashMap<>();
        param.put("coachId", user.getUserId());
        HttpManager.postHasHeaderHasParam(HttpManager.GET_CERTIFICATE_URL, param, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                CertificateBean certificateBean = new CertificateBean(result);
                updateView(certificateBean);
            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });
    }

    private void updateView(CertificateBean certificateBean) {

        if (certificateBean != null) {
            List<AuthBean> authList = certificateBean.getAuthList();
            int size = authList.size();
            if (size >= 1) {
                et1.setText(authList.get(0).getAuthInfo());
            }
            if (size >= 2) {
                et2.setText(authList.get(1).getAuthInfo());
            }
            if (size >= 3) {
                et3.setText(authList.get(2).getAuthInfo());
            }
            if (size >= 4) {
                et4.setText(authList.get(3).getAuthInfo());
            }
            if (size >= 5) {
                et5.setText(authList.get(4).getAuthInfo());
            }
            if (size >= 6) {
                et5.setText(authList.get(5).getAuthInfo());
            }

            //证书照片
            List<CertBean> certList = certificateBean.getCertList();
            List<ImageBean> photoPathList = new ArrayList<>();
            for (int i = 0; i < certList.size(); i++) {
                photoPathList.add(new ImageBean(certList.get(i).getCertificate(), 1));
            }
            choosePhotoView.setmPhotoPathList(photoPathList);
        }
    }

    private void post() {
        List<ImageBean> photoPathList = choosePhotoView.getmPhotoPathList();
        List<String> list = new ArrayList<>();
        List<String> hasAddList = new ArrayList<>();
        for (int i = 0; i < photoPathList.size(); i++) {
            int type = photoPathList.get(i).getType();
            if (type == 0) {
                list.add(photoPathList.get(i).getUrl());
            } else {
                hasAddList.add(photoPathList.get(i).getUrl());
            }
        }
        if (list.size() > 0) {

            UploadManager.upLoadFiles(list, "0", new ResultJSONObjectObserver() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {
                        JSONArray dataList = JsonUtil.getJsonArray(result, "dataList");
                        for (int i = 0; i < dataList.length(); i++) {
                            String o = (String) dataList.get(i);
                            String url = BuildConfig.FILE_HOST + o;
                            certList.add(new CertBean(url));
                        }
                        for (int i = 0; i < hasAddList.size(); i++) {
                            String url = hasAddList.get(i);
                            certList.add(new CertBean(url));
                        }
                        if (photoPathList.size() == certList.size()) {
                            postAdd();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String msg) {
                    showToast(msg);
                }
            });
        } else {
            for (int i = 0; i < hasAddList.size(); i++) {
                String url = hasAddList.get(i);
                certList.add(new CertBean(url));
            }
            postAdd();
        }

    }

    private void postAdd() {
        authList.clear();
        String s1 = et1.getText().toString().trim();
        String s2 = et2.getText().toString().trim();
        String s3 = et3.getText().toString().trim();
        String s4 = et4.getText().toString().trim();
        String s5 = et5.getText().toString().trim();
        String s6 = et6.getText().toString().trim();
        if (!TextUtils.isEmpty(s1)) {
            authList.add(new AuthBean(s1));
        }
        if (!TextUtils.isEmpty(s2)) {
            authList.add(new AuthBean(s2));
        }
        if (!TextUtils.isEmpty(s3)) {
            authList.add(new AuthBean(s3));
        }
        if (!TextUtils.isEmpty(s4)) {
            authList.add(new AuthBean(s4));
        }
        if (!TextUtils.isEmpty(s5)) {
            authList.add(new AuthBean(s5));
        }
        if (!TextUtils.isEmpty(s6)) {
            authList.add(new AuthBean(s6));
        }

        User user = DBManager.getInstance().queryUser();
        AuthCertificateRequestBody authCertificateRequestBody = new AuthCertificateRequestBody(user.getUserId(), authList, certList);
        HttpManager.addCertificate(authCertificateRequestBody, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == certList.size()) {//点击“+”号位置添加图片
            dialog.show();
        } else {
            Intent intent = new Intent(this, SeePicActivity.class);
            String path = "";
            intent.putExtra(BundleKeyConstant.KEY_SEE_PIC_PATH, path);
            startActivity(intent);
        }
    }


    /*****************************  选择相册 ********************************/

    private void initDialog() {
        final View view = LayoutInflater.from(this).inflate(R.layout.view_add_pic_dialog, null);
        dialog = new Dialog(this, R.style.custom_dialog);

        dialog.setOwnerActivity(this);
        dialog.setContentView(view);
        Button cameraBtn = (Button) view.findViewById(R.id.item_popupwindows_camera);
        Button albumBtn = (Button) view.findViewById(R.id.item_popupwindows_photo);
        Button cancelBtn = (Button) view.findViewById(R.id.item_popupwindows_cancel);

        //拍照
        cameraBtn.setOnClickListener(view1 -> {
            dialog.dismiss();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (this.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    //没有权限，提示设置权限
                    RxPermissions rxPermissions = new RxPermissions(this);

                    rxPermissions
                            .request(Manifest.permission.CAMERA)
                            .subscribe(granted -> {
                                if (granted) {
                                    capturePhoto();
                                } else {
                                    Toast.makeText(this, "请到手机设置里给应用分配相机权限,否则无法使用手机拍照功能", Toast.LENGTH_SHORT).show();
                                }
                            });

                } else {
                    //有权限，调用相机拍照
                    capturePhoto();

                }
            } else {
                capturePhoto();
            }
        });

        //相册
        albumBtn.setOnClickListener(view2 -> {
            dialog.dismiss();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    //没有权限
                    RxPermissions rxPermissions = new RxPermissions(this);

                    rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE)
                            .subscribe(granted -> {
                                if (granted) {
                                    selectNewAlbum();
                                } else {
                                    Toast.makeText(this, "请到手机设置里给应用分配读写权限,否则应用无法正常使用", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    //有权限
                    selectNewAlbum();
                }
            } else {
                selectNewAlbum();
            }
        });

        //取消
        cancelBtn.setOnClickListener(view3 -> dialog.dismiss());

    }


    //拍照
    public void capturePhoto() {
        PhotoPicker.builder()
                .setPhotoCount(1)
                .isCamera(true)
                .setShowGif(false)
                .setPreviewEnabled(false)
                .start(this, PhotoPicker.REQUEST_CODE);
    }


    //相册
    public void selectNewAlbum() {
        PhotoPicker.builder()
                .setPhotoCount(1)
                .isCamera(false)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(this, PhotoPicker.REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE && data != null) {
            choosePhotoView.judgeResult(requestCode, resultCode, data);
        }

    }

}
