package com.yijian.staff.mvp.huiji.goodsbaojia;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import com.yijian.staff.mvp.huiji.goodsbaojia.bean.CardInfo;
import com.yijian.staff.mvp.huiji.goodsbaojia.bean.RecptionCards;
import com.yijian.staff.mvp.huiji.goodsbaojia.bean.CardRequestBody;
import com.yijian.staff.net.httpmanager.HttpManager;
import com.yijian.staff.net.response.ResultJSONObjectObserver;
import com.yijian.staff.widget.EmptyView;

import org.json.JSONObject;

import java.util.List;


public class HuiJiProductPresenter implements HuiJiProductContract.Presenter {
    private Context context;
    private HuiJiProductContract.View view;


    public HuiJiProductPresenter(Context context) {
        this.context = context;
    }

    public void setView(HuiJiProductContract.View view) {
        this.view = view;
    }

    @Override
    public void getRecptionCards(CardRequestBody bodyCondition, boolean isRefresh) {

        HttpManager.getHuiJiCardGoodsList(bodyCondition, new ResultJSONObjectObserver() {
            @Override
            public void onSuccess(JSONObject result) {
                RecptionCards recptionCards = new Gson().fromJson(result.toString(), RecptionCards.class);

                List<CardInfo> records = recptionCards.getRecords();
                if (records == null || records.size() == 0) {
                    view.showNoCards(isRefresh, true);
                } else {
                    view.showCards(records, isRefresh);
                }

            }

            @Override
            public void onFail(String msg) {
                view.showNoCards(isRefresh, false);
                Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();

            }
        });

    }


    public CardRequestBody resetBody(CardRequestBody body) {

        body.setPageNum(1);
        body.setPageSize(10);
        body.setCardName(null);
        body.setCardType(null);
        body.setStartPrice(null);
        body.setEndPrice(null);
        body.setIsSortByPrice(null);
        body.setVenueId(null);

        return body;
    }

    public CardRequestBody resetBodyPage(CardRequestBody body) {

        body.setPageNum(1);
        body.setPageSize(10);

        return body;
    }
}
