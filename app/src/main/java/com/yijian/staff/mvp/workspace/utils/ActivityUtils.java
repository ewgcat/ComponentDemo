package com.yijian.staff.mvp.workspace.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.yijian.staff.mvp.course.preparelessons.createlession.EditActionObservable;
import com.yijian.staff.mvp.workspace.base.BaseSpaceFragment;
import com.yijian.staff.mvp.workspace.bean.PerfectRequestBody;
import com.yijian.staff.mvp.workspace.bean.WorkSpaceVipBean;

/**
 *  1. 全局数据池
 *  2.小部分Fragment工具
 *  3.Activity 跳转
 */

public class ActivityUtils {

    public static String moduleType = ""; // 测试模块名称
    public static String name = ""; // 测试者名字
    public static String MODULE_PERFECT = "完美围度";
    public static String MODULE_SPORT = "运动表现";
    public static String MODULE_STATIC_EVALUATE = "静态评估";
    public static String MODULE_DYNAMIC_EVALUATE = "动态评估";
    public static String searchMemberName = ""; //搜索过滤的字段
    public static boolean isShareJump = false; //是否是从分享页作为入口的标志位 false : 不是，true 是
    public static String tempModule = ""; // 临时记录之前入口跳转模块
    public static WorkSpaceVipBean workSpaceVipBean = null;


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

    public static void showFragment(FragmentManager fm, int idRes, String showTag, String... hideTags){
        FragmentTransaction hideTransaction = fm.beginTransaction();
        try{
            for(int i = 0; i < hideTags.length; i++){
                Fragment hideFragment = fm.findFragmentByTag(hideTags[i]);
                if(hideFragment != null){
                    hideTransaction.hide(hideFragment);
                }
            }
            hideTransaction.commit();
            FragmentTransaction showTransaction = fm.beginTransaction();
            Fragment showFragment = fm.findFragmentByTag(showTag);
            if(showFragment != null){
                showTransaction.show(showFragment);
            }else{
                Class clazz = Class.forName(showTag);
                showFragment = (Fragment) clazz.newInstance();
                showTransaction.add(idRes, showFragment, showTag);
            }
            showTransaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void showFragment(FragmentManager fm, int idRes, String showTag, EditActionObservable editActionObservable, String... hideTags){
        FragmentTransaction hideTransaction = fm.beginTransaction();
        try{
            for(int i = 0; i < hideTags.length; i++){
                BaseSpaceFragment hideFragment = (BaseSpaceFragment) fm.findFragmentByTag(hideTags[i]);
                if(hideFragment != null){
                    hideTransaction.hide(hideFragment);
                }
            }
            hideTransaction.commit();
            FragmentTransaction showTransaction = fm.beginTransaction();
            BaseSpaceFragment showFragment = (BaseSpaceFragment) fm.findFragmentByTag(showTag);
            if(showFragment != null){
                showTransaction.show(showFragment);
            }else{
                Class clazz = Class.forName(showTag);
                showFragment = (BaseSpaceFragment) clazz.newInstance();
                editActionObservable.addObserver(showFragment);
                showTransaction.add(idRes, showFragment, showTag);
            }
            showTransaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void startActivity(Context context, Class clazz){
        context.startActivity(new Intent(context,clazz));
    }

    public static void startActivity(Context context, Class clazz, Bundle bundle){
        Intent intent = new Intent(context,clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}
