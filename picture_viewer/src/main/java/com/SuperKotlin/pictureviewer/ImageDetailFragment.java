package com.SuperKotlin.pictureviewer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ImageDetailFragment extends Fragment {
    public static int mImageLoading;//占位符图片
    public static boolean mNeedDownload = false;//默认不支持下载
    private String mImageUrl;
    private ImageView mImageView;
    private PhotoViewAttacher mAttacher;
    private Bitmap mBitmap;

    public static ImageDetailFragment newInstance(String imageUrl) {
        final ImageDetailFragment imageDetailFragment = new ImageDetailFragment();

        final Bundle args = new Bundle();
        args.putString("url", imageUrl);
        imageDetailFragment.setArguments(args);

        return imageDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrl = getArguments() != null ? getArguments().getString("url") : null;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_image_detail, container, false);
        mImageView = (ImageView) v.findViewById(R.id.image);
        mAttacher = new PhotoViewAttacher(mImageView);
        mAttacher.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mNeedDownload) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("保存图片");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ImageUtil.saveImage(getActivity(), mImageUrl, mBitmap);
                        }
                    });
                    builder.create().show();
                }
                return false;
            }
        });
        mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

            @Override
            public void onPhotoTap(View arg0, float arg1, float arg2) {
                getActivity().finish();
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!TextUtils.isEmpty(mImageUrl)) {
            RequestOptions options = new RequestOptions()
                    .placeholder(mImageLoading)
                    .error(mImageLoading)
                    .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            Glide.with(getActivity()).asBitmap().load(mImageUrl).apply(options)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                            mBitmap = bitmap;
                            mImageView.setImageBitmap(mBitmap);
                            mAttacher.update();
                        }
                    });


        } else {
            mImageView.setImageResource(mImageLoading);
        }
    }
}
