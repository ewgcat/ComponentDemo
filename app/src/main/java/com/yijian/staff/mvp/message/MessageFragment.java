package com.yijian.staff.mvp.message;

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

import com.yijian.staff.R;

import java.io.File;


@SuppressLint("ValidFragment")
public class MessageFragment extends Fragment {
    private static final String TAG = "MessageFragment";
    //测试图片的存位置

    public static MessageFragment mMessageFragment = null;

    public static MessageFragment getInstance() {
        if (mMessageFragment == null) {
            mMessageFragment = new MessageFragment();
        }
        return mMessageFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_message, container, false);


        return view;
    }

}
