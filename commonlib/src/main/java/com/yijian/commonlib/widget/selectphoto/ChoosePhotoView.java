package com.yijian.commonlib.widget.selectphoto;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yijian.commonlib.R;
import com.yijian.commonlib.util.Logger;
import com.yijian.commonlib.util.PictureUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;

import static android.app.Activity.RESULT_OK;


public class ChoosePhotoView extends RelativeLayout {


    private static final String TAG = ChoosePhotoView.class.getSimpleName();

    public static final int MODE_ONLY_CAMERA = 10;
    public static final int MODE_NORMAL = 11;
    public static final int MODE_ONLY_SHOW = 12;

    protected Context context;

    protected Dialog dialog;


    protected ChoosePhotoAdapter choosePhotoAdapter;

    protected int mMode = MODE_NORMAL;


    public interface OnChoosePhotoViewListener {
        void hadChangedGridView();

        void hadAddToPath(String path);

        void hadDeletePath(String path);
    }


    private OnChoosePhotoViewListener listener;

    public void setChoosePhotoViewListener(OnChoosePhotoViewListener listener) {
        this.listener = listener;
    }

    protected List<String> mPhotoPathList = new ArrayList<>();

    public ChoosePhotoView(Activity context) {
        this(context, null);
    }

    public ChoosePhotoView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public ChoosePhotoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

    }

    public void setMode(int mMode) {
        this.mMode = mMode;
        if (mMode == MODE_ONLY_SHOW) {
            choosePhotoAdapter.setmIsAllowAdd(false, true);
        } else {
            choosePhotoAdapter.setmIsAllowAdd(true, false);
        }
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        choosePhotoAdapter = new ChoosePhotoAdapter(context, 9);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(choosePhotoAdapter);
        choosePhotoAdapter.setListener(new OnUpdatePhotoAdapterListener() {
            @Override
            public void addPhoto() {
                initDialog();
                if (mMode == MODE_NORMAL) {
                    dialog.show();
                } else if (mMode == MODE_ONLY_CAMERA) {
                    //判断有无拍照权限
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (getContext().checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                            //没有权限，提示设置权限
                            Toast.makeText(getContext(), "请到手机设置里给应用分配相机权限,否则无法使用手机拍照功能", Toast.LENGTH_SHORT).show();
                        } else {
                            //有权限，调用相机拍照
                            capturePhoto();
                        }
                    } else {
                        capturePhoto();
                    }
                }

                if (listener != null)
                    listener.hadChangedGridView();
            }

            @Override
            public void finishDelete(String path) {
                for (int i = 0; i < mPhotoPathList.size(); i++) {
                    if (mPhotoPathList.get(i).equals(path))
                        mPhotoPathList.remove(i);
                }
                if (listener != null) {
                    listener.hadDeletePath(path);
                }
            }
        });

    }


    private void initDialog() {
        final View view = LayoutInflater.from(context).inflate(R.layout.view_add_pic_dialog, null);
        dialog = new Dialog(context, R.style.custom_dialog);
        dialog.setOwnerActivity((Activity) context);
        dialog.setContentView(view);
        Button cameraBtn = (Button) view.findViewById(R.id.item_popupwindows_camera);
        Button albumBtn = (Button) view.findViewById(R.id.item_popupwindows_photo);
        Button cancelBtn = (Button) view.findViewById(R.id.item_popupwindows_cancel);

        //拍照
        cameraBtn.setOnClickListener(view1 -> {
            dialog.dismiss();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (this.getContext().checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    //没有权限，提示设置权限
                    Toast.makeText(this.getContext(), "请到手机设置里给应用分配相机权限,否则无法使用手机拍照功能", Toast.LENGTH_SHORT).show();
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
                if (getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    //没有权限
                    Toast.makeText(getContext(), "请到手机设置里给应用分配读写权限,否则应用无法正常使用", Toast.LENGTH_SHORT).show();
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


    public void showDialog() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }


    //拍照
    public void capturePhoto() {
        PhotoPicker.builder()
                .setPhotoCount(1)
                .isCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(true)
                .start(((Activity) context), PhotoPicker.REQUEST_CODE);
    }


    //相册
    protected void selectNewAlbum() {
        PhotoPicker.builder()
                .setPhotoCount(1)
                .isCamera(false)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(((Activity) context), PhotoPicker.REQUEST_CODE);
    }


    //处理选择照片
    public void judgeResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE && data != null) {
            ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            for (int i = 0; i < photos.size(); i++) {
                compress(photos.get(i));
            }
        }
    }


    //根据路径压缩图片
    private void compress(String path) {
        File soureFile = new File(path);
        Logger.i(TAG, "原文件," + "路径：" + path + " 大小：" + (float) soureFile.length() / 1024 + "K");
        Bitmap bm = PictureUtil.getSmallBitmap(path);
        File desFile = new File(PictureUtil.getAlbumDir(), "small_" + soureFile.getName());
        Logger.i(TAG, "压缩文件" + desFile.getAbsolutePath() + " 初始大小：" + (float) desFile.length() / 1024 + "K");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(desFile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if (bm == null) {
                return;
            }
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            int options = 100;
            while (baos.toByteArray().length / 1024 > 50 && (options >= 0 && options <= 100)) {    //循环判断如果压缩后图片是否大于50kb,大于继续压缩
                baos.reset();//重置baos即清空baos
                bm.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
                options -= 5;//每次都减少5
            }
            fos.write(baos.toByteArray());//把压缩后的数据baos存放到FileOutputStream中
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!bm.isRecycled()) {
            bm.recycle();
        }

        Logger.i(TAG, "压缩文件大小：" + (float) desFile.length() / 1024 + "K");


        mPhotoPathList.add(desFile.getPath());
        choosePhotoAdapter.addPhoto(new ImageBean(desFile.getPath(), 0));
        choosePhotoAdapter.notifyDataSetChanged();
        if (listener != null) {
            listener.hadAddToPath(soureFile.getPath());
        }


    }


    //获取图片路径
    public List<ImageBean> getmPhotoPathList() {
        return choosePhotoAdapter.getmPhotoUrlList();
    }


    //设置图片路径
    public void setmPhotoPathList(List<ImageBean> datas) {
        choosePhotoAdapter.setmPhotoUrlList(datas);
    }

}
