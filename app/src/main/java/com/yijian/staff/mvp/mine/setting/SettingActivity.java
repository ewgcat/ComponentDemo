package com.yijian.staff.mvp.mine.setting;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yijian.staff.R;
import com.yijian.staff.constant.BundleKeyConstant;
import com.yijian.staff.db.DBManager;
import com.yijian.staff.mvp.login.LoginActivity;
import com.yijian.staff.mvp.mine.selectheadicon.ClipActivity;
import com.yijian.staff.mvp.seepic.SeePicActivity;
import com.yijian.staff.prefs.SharePreferenceUtil;
import com.yijian.staff.util.GlideCircleTransform;
import com.yijian.staff.widget.NavigationBar2;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;

public class SettingActivity extends AppCompatActivity {

    private static final java.lang.String TAG = SettingActivity.class.getSimpleName();
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        NavigationBar2 navigationBar2 = (NavigationBar2) findViewById(R.id.setting_activity_navigation_bar2);
        navigationBar2.setTitle("设置");
        navigationBar2.setBackClickListener(this);
        navigationBar2.hideLeftSecondIv();
        initDialog();
    }

    @OnClick({R.id.ll_head, R.id.ll_username, R.id.ll_sex, R.id.ll_age, R.id.ll_phone, R.id.tv_exit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_head:
                dialog.show();
                break;
            case R.id.ll_username:
                break;
            case R.id.ll_sex:
                break;
            case R.id.ll_age:
                break;
            case R.id.ll_phone:
                break;
            case R.id.tv_exit_login:
                exitLogin();
                break;
        }
    }

    private void exitLogin() {
        //发送退出登录请求


        DBManager.getInstance().clearUser();
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


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

                    rxPermissions.request(Manifest.permission.CAMERA)
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
                .setPhotoCount(3)
                .isCamera(false)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(this, PhotoPicker.REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE && data != null) {
            ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            if (photos != null && photos.size() > 0) {
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.placeholder)
                        .error(R.mipmap.placeholder)
                        .transform(new GlideCircleTransform())
                        .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(this).load(photos.get(0)).apply(options).into(ivHead);

                Intent intent = new Intent(this, ClipActivity.class);
                intent.putExtra("path", photos.get(0));
                startActivityForResult(intent, 1000);
            }
        } else if (resultCode == RESULT_OK && requestCode == 1000) {
            Intent intent = new Intent(this, SeePicActivity.class);

            intent.putExtra(BundleKeyConstant.KEY_SEE_PIC_PATH, getExternalCacheDir().toString() + "/head/head.png");
            startActivity(intent);
        }

    }
}
