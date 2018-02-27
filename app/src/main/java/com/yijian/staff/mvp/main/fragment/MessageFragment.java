package com.yijian.staff.mvp.main.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lishuaihua.compress.CompressListener;
import com.yijian.staff.R;

import java.io.File;
import java.util.ArrayList;

import me.iwf.photopicker.PhotoPicker;

import static android.app.Activity.RESULT_OK;
import static com.lishuaihua.compress.CompressPictureUtil.compressImageByHuffman;
import static com.lishuaihua.compress.CompressPictureUtil.getFileSize;


@SuppressLint("ValidFragment")
public class MessageFragment extends Fragment {
    private static final String TAG = "MessageFragment";
    //测试图片的存位置
    private String desPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "hfresult.jpg";
    private ImageView iv2;
    private ImageView iv1;
    private String path;
    int[] q = {1};
    int[] o= {100};

    public static MessageFragment mViperFragment = null;
    public static MessageFragment getInstance() {
        if (mViperFragment == null) {
            mViperFragment = new MessageFragment();
        }
        return mViperFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_message, container, false);
        iv1 = view.findViewById(R.id.iv1);
        iv2 = view.findViewById(R.id.iv2);


        view. findViewById(R.id.btn_compress)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        compressImageByHuffman(path, q, 50, desPath, new CompressListener() {
                            @Override
                            public void startCompress() {

                            }

                            @Override
                            public void completedCompress() {
                                File file1 = new File(desPath);
                                Log.i(TAG, "压缩后图片大小：" + getFileSize(file1)/1024);

                                Glide.with(getActivity()).load(file1).into(iv2);
                            }
                        });

                    }
                });


        view.findViewById(R.id.btn_select_ptoto)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        PhotoPicker.builder()
                                .setPhotoCount(9)
                                .setShowCamera(true)
                                .setShowGif(true)
                                .setPreviewEnabled(false)
                                .start(getActivity(),MessageFragment.this, PhotoPicker.REQUEST_CODE);
                    }
                });





        return view;

    }






    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                path = photos.get(0);
                File file = new File(path);
                Log.i(TAG, "原图片大小：" + getFileSize(file)/1024);
                Glide.with(MessageFragment.this).load(file).into(iv1);
            }
        }
    }


}
