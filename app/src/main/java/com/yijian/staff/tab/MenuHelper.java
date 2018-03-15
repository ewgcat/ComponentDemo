package com.yijian.staff.tab;

import android.content.Context;
import android.content.SharedPreferences;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yijian.staff.tab.entity.MenuItem;
import com.yijian.staff.tab.tools.ContextUtil;
import com.yijian.staff.tab.tools.IOKit;


import java.util.ArrayList;
import java.util.List;


/**
 * 描述:菜单数据控制助手，模拟本地数据库
 * <p>
 * 作者:cjs
 * 创建时间:2017年11月03日 15:21
 * 邮箱:chenjunsen@outlook.com
 *
 * @version 1.0
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

    private int itemCounter = 0;//用于统计共有多少个子item,依次给每个item设置独立的id

    /*分组数据的缓存列表，初始化分组的时候用*/
    private List<MenuItem> frequentlyList;
    private List<MenuItem> vipmanagerList;
    private List<MenuItem> huijikefuList;
    private List<MenuItem> coachList;
    private List<MenuItem> caokeList;
    private List<MenuItem> admList;
    private List<MenuItem> audittaskList;
    private List<MenuItem> otherList;

    /**
     * 解析原始数据，用于模拟从服务器上获取到的JSON报文
     */
    private void parseJSONData() {
        String jsonStr = IOKit.getStringFromAssets(ContextUtil.getContext(), "dummy.json");//获取到assets目录下的报文
        JSONArray dataJsonArray = JSON.parseArray(jsonStr);
//        JSONObject dataJson = JSON.parseObject(jsonStr);//将报文string转换为JSON
        for (int i = 0; i < dataJsonArray.size(); i++) {
            JSONObject o = (JSONObject) dataJsonArray.get(i);
            String title = o.getString("title");
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
                case GROUP_CAO_KE:
                    caokeList = parseJSONList(items);

                    break;
                case GROUP_ADM:
                    admList = parseJSONList(items);

                    break;
                case GROUP_AUDIT_TASK:
                    audittaskList = parseJSONList(items);

                    break;
                case GROUP_OTHER:
                    otherList = parseJSONList(items);
                    break;
            }
        }


        savePreferFrequentlyList(frequentlyList);
        savePreferVipManageList(vipmanagerList);
        savePreferHuiJiKeFuList(huijikefuList);
        savePreferCoachList(coachList);
        savePreferCaoKeList(caokeList);
        savePreferAdmList(admList);
        savePreferAuditTaskList(audittaskList);
        savePreferOtherList(otherList);
    }


    private List<MenuItem> parseJSONList(JSONObject dataJSON, String group) {
        List<MenuItem> list = new ArrayList<>();
        JSONArray array = dataJSON.getJSONArray(group);
        int size = array.size();
        for (int i = 0; i < size; i++, itemCounter++) {
            JSONObject object = array.getJSONObject(i);
            //之所以没有在array层就进行JSON到java对象的转换，是为了进入内部遍历，产生id,并将id赋值给menuItem
            MenuItem item = JSON.toJavaObject(object, MenuItem.class);
            item.setItemId(itemCounter);
            list.add(item);
        }
        return list;
    }

    private List<MenuItem> parseJSONList(JSONArray array) {
        List<MenuItem> list = new ArrayList<>();
        int size = array.size();
        for (int i = 0; i < size; i++, itemCounter++) {
            JSONObject object = array.getJSONObject(i);
            //之所以没有在array层就进行JSON到java对象的转换，是为了进入内部遍历，产生id,并将id赋值给menuItem
            MenuItem item = JSON.toJavaObject(object, MenuItem.class);
            item.setItemId(itemCounter);
            list.add(item);
        }
        return list;
    }


    /**
     * 初始化数据
     */
    public static void init() {
        MenuHelper helper = new MenuHelper();
        helper.parseJSONData();
        setInit(true);
    }

    /**
     * 用于保存本地数据的文件名字
     */
    private static final String PREFERENCE_MENU_DATA_NAME = "menu_data";
    /**
     * 是否已经进行过初始化的字段名
     */
    private static final String PREFERENCE_HAS_EVER_INIT = "has_ever_init";

    /**
     * 获取本地数据的文件
     *
     * @return
     */
    public static SharedPreferences getMenuDataConfig() {
        return ContextUtil.getContext().getSharedPreferences(PREFERENCE_MENU_DATA_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 清空本地数据文件里面的内容
     */
    public static void clearMenuDataConfig() {
        getMenuDataConfig().edit().clear().commit();
    }

    public static boolean hasEverInit() {
        return getMenuDataConfig().getBoolean(PREFERENCE_HAS_EVER_INIT, false);
    }

    public static void setInit(boolean isInit) {
        getMenuDataConfig().edit().putBoolean(PREFERENCE_HAS_EVER_INIT, isInit);
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
        JSONArray array = JSONArray.parseArray(jsonStr);
        return array.toJavaList(MenuItem.class);
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

    /**
     * 从本地数据元素里面添加一个item
     *
     * @param group
     * @param item
     */
    public static void addItem(String group, MenuItem item) {
        List<MenuItem> list = getPreferMenuListData(group);
        if (!contains(list, item)) {
            list.add(item);
            savePreferMenuListData(group, list);
        }
    }

    private static boolean contains(List<MenuItem> list, MenuItem item) {
        if (list != null && list.size() > 0) {
            for (MenuItem i : list) {
                if (i.getItemId() == item.getItemId()) {
                    return true;
                }
            }
        }
        return false;
    }
    /*----------------------------原始方法-----------------------------------*/

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

    public static void savePreferCaoKeList(List<MenuItem> list) {
        savePreferMenuListData(GROUP_CAO_KE, list);
    }

    public static void savePreferAdmList(List<MenuItem> list) {
        savePreferMenuListData(GROUP_ADM, list);
    }


    public static void savePreferAuditTaskList(List<MenuItem> list) {
        savePreferMenuListData(GROUP_AUDIT_TASK, list);
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

    public static List<MenuItem> getPreferCaoKeList() {
        return getPreferMenuListData(GROUP_CAO_KE);
    }

    public static List<MenuItem> getPreferAdmList() {
        return getPreferMenuListData(GROUP_ADM);
    }


    public static List<MenuItem> getPreferAuditTaskList() {
        return getPreferMenuListData(GROUP_AUDIT_TASK);
    }

    public static List<MenuItem> getPreferOtherList() {
        return getPreferMenuListData(GROUP_OTHER);
    }


}
