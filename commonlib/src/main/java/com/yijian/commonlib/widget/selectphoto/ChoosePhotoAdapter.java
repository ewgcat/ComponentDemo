package com.yijian.commonlib.widget.selectphoto;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yijian.commonlib.R;
import com.yijian.commonlib.base.seepic.SeePicActivity;
import com.yijian.commonlib.constant.Constant;
import com.yijian.commonlib.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 17:08:17
 */
public class ChoosePhotoAdapter extends RecyclerView.Adapter<ChoosePhotoAdapter.ViewHolder> {

    public int MAX_PHOTO_NUM = 6;

    protected boolean mIsAllowAdd = true;

    private boolean isOnlyShow = false;

    OnUpdatePhotoAdapterListener listener;

    Context mContext;
    List<ImageBean> mPhotoUrlList = new ArrayList<>();

    public void setListener(OnUpdatePhotoAdapterListener listener) {
        this.listener = listener;
    }


    public void clearPhotoUrlList() {
        mPhotoUrlList.clear();
        notifyDataSetChanged();
    }

    public List<ImageBean> getmPhotoUrlList() {
        return this.mPhotoUrlList;
    }

    public ChoosePhotoAdapter(Context mContext) {
        this.mContext = mContext;

    }

    public ChoosePhotoAdapter(Context mContext, int maxPhotoNum) {
        this.mContext = mContext;
        this.MAX_PHOTO_NUM = maxPhotoNum;

    }

    public void addPhoto(ImageBean imageBean) {
        mPhotoUrlList.add(imageBean);
    }

    public void addPhotos(List<ImageBean> imageBeans) {
        mPhotoUrlList.addAll(imageBeans);
    }

    public void setmIsAllowAdd(boolean mIsAllowAdd, boolean isOnlyShow) {
        this.mIsAllowAdd = mIsAllowAdd;
        this.isOnlyShow = isOnlyShow;
        notifyDataSetChanged();
    }

    public void setmPhotoUrlList(List<ImageBean> datas) {
        this.mPhotoUrlList = datas;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_add_pic_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        int size = mPhotoUrlList.size();

        if (mIsAllowAdd) {

            if (size == position) {
                holder.delete.setVisibility(View.GONE);

                RequestOptions options = new RequestOptions().centerCrop()
                        .placeholder(R.mipmap.placeholder)
                        .error(R.mipmap.placeholder)    //.transform(new GlideRoundTransform())
                        .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(mContext).load(R.mipmap.ic_add_pic).apply(options).into(holder.imageView);
                holder.imageView.setOnClickListener(view14 -> {
                    //跳转到拍照
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (mContext.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                            Toast.makeText(mContext, "应用需要相机权限，才可以使用拍照功能", Toast.LENGTH_LONG).show();
                        } else {
                            listener.addPhoto();
                        }
                    } else {
                        listener.addPhoto();
                    }
                });
            } else if (size > position) {
                holder.delete.setVisibility(View.VISIBLE);

                RequestOptions options = new RequestOptions().centerCrop()
                        .placeholder(R.mipmap.placeholder)
                        .error(R.mipmap.placeholder)
                        .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(mContext).load(mPhotoUrlList.get(position).getUrl()).apply(options).into(holder.imageView);
                holder.imageView.setOnClickListener(view12 -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (mContext.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                            Toast.makeText(mContext, "应用需要相机权限，才可以使用拍照功能", Toast.LENGTH_LONG).show();
                        } else {
                            mContext.startActivity(new Intent(mContext, SeePicActivity.class).putExtra(Constant.KEY_SEE_PIC_PATH, mPhotoUrlList.get(position).getUrl()));
                        }
                    } else {
                        mContext.startActivity(new Intent(mContext, SeePicActivity.class).putExtra(Constant.KEY_SEE_PIC_PATH, mPhotoUrlList.get(position).getUrl()));
                    }
                });
                holder.delete.setOnClickListener(view13 -> {
                    ImageBean imageBean = mPhotoUrlList.get(position);
                    String key = imageBean.getUrl();
                    FileUtil.deleteFile(key);
                    mPhotoUrlList.remove(imageBean);
                    listener.finishDelete(key);
                    notifyDataSetChanged();
                });
            }

        } else {


            RequestOptions options = new RequestOptions().centerCrop()
                    .placeholder(R.mipmap.placeholder)
                    .error(R.mipmap.placeholder)
                    .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            Glide.with(mContext).load(mPhotoUrlList.get(position).getUrl()).apply(options).into(holder.imageView);
            holder.imageView.setOnClickListener(view1 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (mContext.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                        Toast.makeText(mContext, "应用需要相机权限，才可以使用拍照功能", Toast.LENGTH_LONG).show();
                    } else {
                        mContext.startActivity(new Intent(mContext, SeePicActivity.class).putExtra(Constant.KEY_SEE_PIC_PATH, mPhotoUrlList.get(position).getUrl()));
                    }
                } else {
                    mContext.startActivity(new Intent(mContext, SeePicActivity.class).putExtra(Constant.KEY_SEE_PIC_PATH, mPhotoUrlList.get(position).getUrl()));
                }

            });
            holder.delete.setVisibility(View.GONE);

        }


    }

    @Override
    public int getItemCount() {
        if (isOnlyShow) {
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

    static class ViewHolder extends RecyclerView.ViewHolder {


        private final ImageView imageView;
        private final ImageView delete;

        public ViewHolder(View view) {
            super(view);


            //点击弹出拍照对话框
            imageView = (ImageView) view.findViewById(R.id.iv_back_vs_item);
            delete = (ImageView) view.findViewById(R.id.iv_btn_delete);

        }
    }
}
