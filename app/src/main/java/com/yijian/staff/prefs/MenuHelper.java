package com.yijian.staff.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.yijian.staff.tab.entity.MenuItem;
import com.yijian.staff.tab.tools.ContextUtil;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/28 15:13:27
 */
public class MenuHelper {
    /*分组的标签*/
    public static final String GROUP_FREQUENTLY = "常用功能";
    public static final String GROUP_VIP_MANAGER = "会员管理";
    public static final String GROUP_HUI_JI_KE_FU = "会籍(客服)";
    public static final String GROUP_COCAH = "教练";
    public static final String GROUP_CAO_KE = "操课";
    public static final String GROUP_ADM = "行政";
    public static final String GROUP_AUDIT_TASK = "审核任务";
    public static final String GROUP_OTHER = "其他";


    /*分组数据的缓存列表，初始化分组的时候用*/
    private List<MenuItem> frequentlyList;
    private List<MenuItem> vipmanagerList;
    private List<MenuItem> huijikefuList;
    private List<MenuItem> coachList;
    private List<MenuItem> caokeList;
    private List<MenuItem> admList;
    private List<MenuItem> audittaskList;
    private List<MenuItem> otherList;

    public void parseJSONArrayToMenuList(JSONArray jsonArray) {

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject o = null;
            try {
                o = (JSONObject) jsonArray.get(i);
                String title = JsonUtil.getString(o, "title");
                JSONArray items = o.getJSONArray("item");

                switch (title) {
                    case GROUP_FREQUENTLY:
                        frequentlyList = parseJSONList(items);

                        break;
                    case GROUP_VIP_MANAGER:
                        vipmanagerList = parseJSONList(items);

                        break;
                    case GROUP_HUI_JI_KE_FU:
                        huijikefuList = parseJSONList(items);

                        break;
                    case GROUP_COCAH:
                        coachList = parseJSONList(items);

                        break;

                    case GROUP_OTHER:
                        otherList = parseJSONList(items);
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


        savePreferFrequentlyList(frequentlyList);
        savePreferVipManageList(vipmanagerList);
        savePreferHuiJiKeFuList(huijikefuList);
        savePreferCoachList(coachList);
        savePreferOtherList(otherList);
    }

    private List<MenuItem> parseJSONList(JSONArray items) {
        try {
            List<MenuItem> menuItemList = new ArrayList<MenuItem>();
            for (int i = 0; i < items.length(); i++) {
                JSONObject o = (JSONObject) items.get(i);
                MenuItem menuItem = new MenuItem(o);
                menuItemList.add(menuItem);
            }
            return menuItemList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用于保存本地数据的文件名字
     */
    private static final String PREFERENCE_MENU_DATA_NAME = "menu_data";

    /**
     * 获取本地数据的文件
     *
     * @return
     */
    public static SharedPreferences getMenuDataConfig() {
        return ContextUtil.getContext().getSharedPreferences(PREFERENCE_MENU_DATA_NAME, Context.MODE_PRIVATE);
    }

    /*----------------------------原始方法-----------------------------------*/

    /**
     * 将List转换为JsonString保存进SharedPreference
     *
     * @param group
     * @param list
     */
    private static void savePreferMenuListData(String group, List<MenuItem> list) {
        SharedPreferences.Editor editor = getMenuDataConfig().edit();
        editor.putString(group, JSON.toJSONString(list));
        editor.commit();
    }

    /**
     * 从SharedPreference里面取出JsonString,再转换为List
     *
     * @param group
     * @return
     */
    private static List<MenuItem> getPreferMenuListData(String group) {
        String jsonStr = getMenuDataConfig().getString(group, "");
        if (jsonStr.equals("")||jsonStr.equals("null")){
            return null;
        }else {
            com.alibaba.fastjson.JSONArray array = com.alibaba.fastjson.JSONArray.parseArray(jsonStr);
            return array.toJavaList(MenuItem.class);
        }

    }

    /**
     * 从本地数据缓存列表里面删除一个item
     *
     * @param group
     * @param item
     */
    public static void deleteItem(String group, MenuItem item) {
        List<MenuItem> list = getPreferMenuListData(group);
        for (MenuItem i : list) {
            if (i.getItemId() == item.getItemId()) {
                list.remove(i);
                break;
            }
        }
        savePreferMenuListData(group, list);
    }

    /*----------------------------衍生方法-----------------------------------*/
    public static void savePreferFrequentlyList(List<MenuItem> list) {
        savePreferMenuListData(GROUP_FREQUENTLY, list);
    }

    public static void savePreferVipManageList(List<MenuItem> list) {
        savePreferMenuListData(GROUP_VIP_MANAGER, list);
    }

    public static void savePreferHuiJiKeFuList(List<MenuItem> list) {
        savePreferMenuListData(GROUP_HUI_JI_KE_FU, list);
    }


    public static void savePreferCoachList(List<MenuItem> list) {
        savePreferMenuListData(GROUP_COCAH, list);
    }




    public static void savePreferOtherList(List<MenuItem> list) {
        savePreferMenuListData(GROUP_OTHER, list);
    }

    /*----------------------------衍生方法-----------------------------------*/
    public static List<MenuItem> getPreferFrequentlyList() {
        return getPreferMenuListData(GROUP_FREQUENTLY);
    }

    public static List<MenuItem> getPreferVipManageList() {
        return getPreferMenuListData(GROUP_VIP_MANAGER);
    }

    public static List<MenuItem> getPreferHuiJiKeFuList() {
        return getPreferMenuListData(GROUP_HUI_JI_KE_FU);
    }


    public static List<MenuItem> getPreferCoachList() {
        return getPreferMenuListData(GROUP_COCAH);
    }



    public static List<MenuItem> getPreferOtherList() {
        return getPreferMenuListData(GROUP_OTHER);
    }

}
