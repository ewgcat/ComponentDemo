package com.yijian.staff.mvp.reception.step3.kefu;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yijian.staff.mvp.huiji.goodsbaojia.bean.GoodsInfo;
import com.yijian.staff.mvp.reception.step3.bean.CardInfo;
import com.yijian.staff.mvp.reception.step3.bean.ConditionBody;
import com.yijian.staff.mvp.reception.step3.bean.RecptionCards;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.requestbody.huijigoods.HuiJiGoodsRequestBody;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The_P on 2018/4/11.
 */

public class HuiJiProductPresenter implements HuiJiProductContract.Presenter {
    private Context context;
    private HuiJiProductContract.View view;
    private int pageNum;
    private int pageSize;
    private int pages;


    public HuiJiProductPresenter(Context context) {
        this.context = context;
    }

    public void setView(HuiJiProductContract.View view) {
        this.view = view;
    }

    @Override
    public void getRecptionCards(SmartRefreshLayout refreshLayout, ConditionBody bodyCondition, Boolean isRefresh) {
        HttpManager.getHuiJiCardGoodsList_ycm(bodyCondition, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                pageNum=bodyCondition.getPageNum()+1;
                RecptionCards recptionCards = new Gson().fromJson(result.toString(), RecptionCards.class);
                pages=recptionCards.getPages();
                boolean hasMore = pages > pageNum ? true : false;

                List<CardInfo> records = recptionCards.getRecords();
                if (records == null || records.size() == 0) {
                    if (isRefresh) {
                        refreshLayout.finishRefresh(2000, true);//传入false表示刷新失败
                        Toast.makeText(context, "未查询到相关数据", Toast.LENGTH_SHORT).show();
                    } else {
                        refreshLayout.finishLoadMore(2000, true, hasMore);//传入false表示刷新失败
                        Toast.makeText(context, "已经是最后一页", Toast.LENGTH_SHORT).show();
                    }

                    return;
                }else {

                    if (isRefresh) {
                        refreshLayout.finishRefresh(2000, true);//传入false表示刷新失败
                    } else {
                        refreshLayout.finishLoadMore(2000, true, hasMore);//传入false表示刷新失败
                    }

                }
                view.showCards(records, isRefresh);

            }

            @Override
            public void onFail(String msg) {
                if (isRefresh) {
                    refreshLayout.finishRefresh(2000, false);//传入false表示刷新失败
                } else {
                    boolean hasMore = pages > pageNum ? true : false;
                    refreshLayout.finishLoadMore(2000, true, hasMore);//传入false表示刷新失败
                }

                Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
            }
        });

    }


    public ConditionBody resetBody(ConditionBody body) {

        body.setPageNum(1);
        body.setPageSize(10);
        body.setCardName(null);
        body.setCardType(null);
        body.setStartPrice(null);
        body.setEndPrice(null);
        body.setIsSortByPrice(null);
        body.setVenueName(null);

        return body;
    }

    public ConditionBody resetBodyPage(ConditionBody body) {

        body.setPageNum(1);
        body.setPageSize(10);

        return body;
    }
}
