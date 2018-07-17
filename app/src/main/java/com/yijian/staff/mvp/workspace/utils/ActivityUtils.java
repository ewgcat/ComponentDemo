package com.yijian.staff.mvp.workspace.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by yangk on 2018/1/25.
 */

public class ActivityUtils {

    public static String moduleType; // 测试模块名称
    public static String name; // 测试者名字

    public static void addFragment(FragmentManager fragmentManager, int idRes, Fragment fragment,String tag){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(idRes,fragment,tag);
        fragmentTransaction.commit();
    }

    public static void hideFragment(FragmentManager fm, Fragment fragment){
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.hide(fragment);
        fragmentTransaction.commit();
    }

    public static void showFragment(FragmentManager fm, Fragment fragment){
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

}
