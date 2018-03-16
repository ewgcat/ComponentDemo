package com.yijian.staff.widget.selectphoto;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.staff.R;
import com.yijian.staff.constant.BundleKeyConstant;
import com.yijian.staff.mvp.seepic.SeePicActivity;
import com.yijian.staff.util.FileUtil;


import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 *
 * @author:nickming date:2016/2/2
 * time: 00:08
 * e-mail：962570483@qq.com
 */

public class PostPhotoAdapter extends BaseAdapter {

    public static final int MAX_PHOTO_NUM = 6;

    protected boolean mIsAllowAdd = true;

    private boolean isPatrol = false;

    OnUpdatePhotoAdapterListener listener;

    Context mContext;
    List<String> mPhotoUrlList = new ArrayList<>();

    public void setListener(OnUpdatePhotoAdapterListener listener) {
        this.listener = listener;
    }


    public void clearPhotoUrlList() {
        mPhotoUrlList.clear();
        notifyDataSetChanged();
    }


    public void changeModeState() {
        notifyDataSetChanged();
    }


    public PostPhotoAdapter(Context mContext) {
        this.mContext = mContext;

    }

    public void addPhoto(String url) {
        mPhotoUrlList.add(url);
    }

    public void addPhotos(List<String> urls) {
        mPhotoUrlList.addAll(urls);
    }

    public void setmIsAllowAdd(boolean mIsAllowAdd, boolean isPatrol) {
        this.mIsAllowAdd = mIsAllowAdd;
        this.isPatrol = isPatrol;
        notifyDataSetChanged();
    }

    public void setmPhotoUrlList(List<String> datas) {
        this.mPhotoUrlList = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (isPatrol) {
            return mPhotoUrlList.size();
        } else {
            if (mIsAllowAdd) {
                if (mPhotoUrlList.size() < MAX_PHOTO_NUM) {
                    return mPhotoUrlList.size() + 1;
                }
                return MAX_PHOTO_NUM;
            } else {
                if (mPhotoUrlList.size() <= MAX_PHOTO_NUM)
                    return mPhotoUrlList.size();
                return MAX_PHOTO_NUM;
            }
        }


    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.view_add_pic_item, null);
        //点击弹出拍照对话框
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_back_vs_item);
        ImageView delete = (ImageView) view.findViewById(R.id.iv_btn_delete);
        int size = mPhotoUrlList.size();

        if (mIsAllowAdd) {

            if (size == position) {
                delete.setVisibility(View.GONE);
                imageView.setOnClickListener(view14 -> {
                    //跳转到拍照
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (mContext.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {

                            Toast.makeText(mContext, "应用需要相机权限，才可以使用拍照功能", Toast.LENGTH_LONG).show();
                            // Create app settings intent
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", mContext.getPackageName(), null);
                            intent.setData(uri);
                            mContext.startActivity(intent);
                        } else {
                            listener.addPhoto();
                        }
                    } else {
                        listener.addPhoto();
                    }
                });
            } else if (size > position) {
                delete.setVisibility(View.VISIBLE);

                RequestOptions options = new RequestOptions().centerCrop()
                        .placeholder(R.mipmap.placeholder)
                        .error(R.mipmap.placeholder)
                        .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(mContext).load(mPhotoUrlList.get(position)).apply(options).into(imageView);
                imageView.setOnClickListener(view12 -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (mContext.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {

                            Toast.makeText(mContext, "应用需要相机权限，才可以使用拍照功能", Toast.LENGTH_LONG).show();
                            // Create app settings intent
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", mContext.getPackageName(), null);
                            intent.setData(uri);
                            mContext.startActivity(intent);
                        } else {
                            ArrayList<String> urls = new ArrayList<String>();
                            urls.add(mPhotoUrlList.get(position));
                            mContext.startActivity(new Intent(mContext, SeePicActivity.class).putStringArrayListExtra(BundleKeyConstant.KEY_SEE_PIC_PATH, urls));
                        }
                    } else {
                        ArrayList<String> urls = new ArrayList<String>();
                        urls.add(mPhotoUrlList.get(position));
                        mContext.startActivity(new Intent(mContext, SeePicActivity.class).putStringArrayListExtra(BundleKeyConstant.KEY_SEE_PIC_PATH, urls));
                    }
                });
                delete.setOnClickListener(view13 -> {
                    String key = mPhotoUrlList.get(position);
                    FileUtil.deleteFile(key);
                    mPhotoUrlList.remove(key);
                    listener.finishDelete(key);
                    notifyDataSetChanged();
                });
            }

        } else {


            RequestOptions options = new RequestOptions().centerCrop()
                    .placeholder(R.mipmap.placeholder)
                    .error(R.mipmap.placeholder)
                    .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            Glide.with(mContext).load(mPhotoUrlList.get(position)).apply(options).into(imageView);
            imageView.setOnClickListener(view1 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (mContext.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {

                        Toast.makeText(mContext, "应用需要相机权限，才可以使用拍照功能", Toast.LENGTH_LONG).show();
                        // Create app settings intent
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", mContext.getPackageName(), null);
                        intent.setData(uri);
                        mContext.startActivity(intent);
                    } else {
                        ArrayList<String> urls = new ArrayList<String>();
                        urls.add(mPhotoUrlList.get(position));
                        mContext.startActivity(new Intent(mContext, SeePicActivity.class).putStringArrayListExtra(BundleKeyConstant.KEY_SEE_PIC_PATH, urls));
                    }
                } else {
                    ArrayList<String> urls = new ArrayList<String>();
                    urls.add(mPhotoUrlList.get(position));
                    mContext.startActivity(new Intent(mContext, SeePicActivity.class).putStringArrayListExtra(BundleKeyConstant.KEY_SEE_PIC_PATH, urls));
                }

            });
            delete.setVisibility(View.GONE);

        }


        return view;
    }

}
