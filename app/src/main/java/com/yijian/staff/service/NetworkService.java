package com.yijian.staff.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;


import com.yijian.staff.util.NetworkUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * desc:网络监测状态 service
 *
 * @author:nickming date:2016/2/16
 * time: 15:06
 * e-mail：962570483@qq.com
 */

public class NetworkService extends Service {

    protected final static String TAG = NetworkService.class.getSimpleName();

    protected Context mContext;

    //判断当前网络连接是否正常
    protected boolean mIsConnect = true;

    protected Timer timer = new Timer();

    //网络binder
    private NetBind mBinder = new NetBind();

    public class NetBind extends Binder {
        public NetworkService getNetwrokService() {
            return NetworkService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {

        //动态注册广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
        //Logger.i(TAG, "onCreate :" + Thread.currentThread().getId());
        super.onCreate();
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            //当前接收器接收的是这个服务的时候，每秒监听网络服务
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                //            Logger.i(TAG, "onReceive :" + Thread.currentThread().getId());
                timer.schedule(new NetTask(context), 0, 1 * 1000);
            }
        }
    };

    /**
     * 网络任务，用来监听当前是否有网络
     */
    class NetTask extends TimerTask {

        public NetTask(Context context) {
            mContext = context;
        }

        @Override
        public void run() {

            //一直监听并设置网路的状态
            if (NetworkUtil.hasNetwork(mContext)) {
                mIsConnect = true;
            } else {
                mIsConnect = false;
            }

//            Logger.i(TAG, "run " + Thread.currentThread().getId());

            if (onGetConnectState != null) {
                onGetConnectState.GetState(mIsConnect);
            }

        }
    }

    /**
     * 获取网络连接的接口
     */
    public interface GetConnectState {
        void GetState(boolean isConnected);
    }

    protected GetConnectState onGetConnectState;

    public void setOnGetConnectState(GetConnectState onGetConnectState) {
        this.onGetConnectState = onGetConnectState;
    }

    /**
     * 服务被杀死或者退出程序后，结束任务，注销广播接收器
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        unregisterReceiver(receiver);
    }
}
