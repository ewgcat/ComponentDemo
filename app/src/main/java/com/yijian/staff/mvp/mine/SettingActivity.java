package com.yijian.staff.mvp.mine;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import com.yijian.staff.rx.RxUtil;
import com.yijian.staff.util.GlideCircleTransform;
import com.yijian.staff.util.Logger;
import com.yijian.staff.widget.NavigationBar;
import com.yijian.staff.widget.NavigationBarItemFactory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
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

    String[] permissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.setting_activity_navigation_bar);
        navigationBar.setTitle("设置", "#ffffff");
        navigationBar.setLeftButtonView(NavigationBarItemFactory.createNavigationItemImageView(this, NavigationBarItemFactory.NavigationItemType.BACK_WHITE));
        navigationBar.setLeftButtonClickListener(NavigationBarItemFactory.createBackClickListener(this));

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
                break;
        }
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
                .setPhotoCount(3)
                .isCamera(false)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(this, PhotoPicker.REQUEST_CODE);
    }

    /**
     * 运行时请求权限
     */
    private void initRxPermissions(int i, String[] permissions) {

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
            }
        }

    }
}
