package com.yijian.staff.mvp.coach.huifang.tianxieresult;

import com.bigkoo.pickerview.model.IPickerViewData;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONObject;

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/4/20 15:40:15
 */
public class CoachHuiFangReasonBean implements IPickerViewData {


    /**
     * dictItemId : 0
     * dictItemKey : string
     * dictItemName : string
     * sort : 0
     */

    private String dictItemId;
    private String dictItemKey;
    private String dictItemName;
    private String sort;

    public CoachHuiFangReasonBean(JSONObject jsonObject) {
        this.dictItemId = JsonUtil.getString(jsonObject, "dictItemId");
        this.dictItemKey = JsonUtil.getString(jsonObject, "dictItemKey");
        this.dictItemName = JsonUtil.getString(jsonObject, "dictItemName");
        this.sort = JsonUtil.getString(jsonObject, "sort");
    }

    public String getDictItemId() {
        return dictItemId;
    }

    public void setDictItemId(String dictItemId) {
        this.dictItemId = dictItemId;
    }

    public String getDictItemKey() {
        return dictItemKey;
    }

    public void setDictItemKey(String dictItemKey) {
        this.dictItemKey = dictItemKey;
    }

    public String getDictItemName() {
        return dictItemName;
    }

    public void setDictItemName(String dictItemName) {
        this.dictItemName = dictItemName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String getPickerViewText() {
        return dictItemName;
    }
}
