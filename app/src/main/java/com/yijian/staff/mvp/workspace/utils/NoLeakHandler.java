package com.yijian.staff.mvp.workspace.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;


import java.lang.ref.WeakReference;

public class NoLeakHandler<T extends Context> extends Handler {

    private WeakReference<T> weakReferenceBean;

    public NoLeakHandler(T t){
        weakReferenceBean = new WeakReference<>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
//        Toast.makeText(mActivity.get(),"消息来了...",Toast.LENGTH_SHORT).show();
    }



}
