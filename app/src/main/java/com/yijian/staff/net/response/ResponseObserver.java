package com.yijian.staff.net.response;


import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class ResponseObserver<T> implements Observer<JSONObject>, ResultCallBack<T>, LifecycleObserver {
    protected Type dataClassType;
    private ProgressDialog progressDialog;
    protected boolean isShowDialog = false;
    private Disposable disposable;
    private Lifecycle lifecycle;
    private final Gson gson = new Gson();//不要改成static，多线程会出现问题的

    public ResponseObserver() {
        initResultType();
        initLife();
    }

    public ResponseObserver(Activity activity, String msg) {
        this(activity, msg, true);
    }

    public ResponseObserver(Activity activity, String msg, boolean isShowDialog) {
        this();
        this.isShowDialog = isShowDialog;
        if (isShowDialog) {
            progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage(msg);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner owner) {
        destroy();
    }

    private void destroy() {
        if (lifecycle != null) {
            lifecycle.removeObserver(this);
        }
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }


    protected void initResultType() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        ParameterizedType type = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = type.getActualTypeArguments();
        dataClassType = actualTypeArguments[0];
    }

    private void initLife() {
        Object obj = getExternalClass();
        if (obj != null && obj instanceof LifecycleOwner) {
            lifecycle = ((LifecycleOwner) obj).getLifecycle();
            lifecycle.addObserver(this);
        } else {
            Log.d("ResponseObserver", "这个ResponseObserver一般用在activity 或者 fragment里面的，即实现了LifecycleOwner的类，" +
                    "不然就起不到在ondestroy中销毁的作用，如果不是在这些类中，使用弱引用也可起到销毁时进行内存释放的效果");
        }
    }


    private static final int SYNTHETIC = 0x00001000;
    private static final int FINAL = 0x00000010;
    private static final int SYNTHETIC_AND_FINAL = SYNTHETIC | FINAL;

    public Object getExternalClass() {
        try {
            return getField(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean checkModifier(int mod) {
        return (mod & SYNTHETIC_AND_FINAL) == SYNTHETIC_AND_FINAL;
    }

    private Object getField(String name) throws NoSuchFieldException {
        if (name == null || name.isEmpty()) {
            name = "this$0";
        }
        Field field = getClass().getDeclaredField(name);
        field.setAccessible(true);
        if (checkModifier(field.getModifiers())) {
            try {
                return field.get(this);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return getField(name + "$");
    }


    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
        if (isShowDialog) {
            progressDialog.show();
        }
    }

    @Override
    public void onComplete() {
        if (isShowDialog && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        destroy();
    }

    @Override
    public void onNext(JSONObject jsonObject) {
        try {
            ResponseBean responseBean = gson.fromJson(jsonObject.toString(), ResponseBean.class);
            switch (responseBean.getCode()) {
                case ResponseCode.SUCCESS:
                    responData(jsonObject);
                    break;
                case ResponseCode.TOKEN_TIME_OUT:
                    onFail(responseBean.getMsg());
                    ARouter.getInstance().build("/test/login").navigation();
                    break;
                default:
                    onFail(responseBean.getMsg());
                    break;
            }
        } catch (Exception e) {
            onFail(getErrorMsg(e));
        }

    }

    protected void responData(JSONObject jsonObject) throws Exception {
        String dataJson = jsonObject.get("data").toString();
        if (TextUtils.isEmpty(dataJson) || (dataJson.startsWith("{") && dataJson.length() <= 2)) {
            onSuccess(null);
        } else {
            T data = new Gson().fromJson(dataJson, dataClassType);
            onSuccess(data);
        }

    }

    @Override
    public void onError(Throwable e) {
        onFail(getErrorMsg(e));
        onComplete();
    }

    private String getErrorMsg(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            return "连接超时";
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            return "网络错误";
        } else if (e instanceof JsonSyntaxException) {
            return "解析数据出错了";
        } else {
            return "出错了";
        }
    }


}
