package com.yijian.clubmodule.ui.price.cardprice;

import androidx.lifecycle.Lifecycle;
import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import com.yijian.clubmodule.bean.CardInfo;
import com.yijian.clubmodule.bean.RecptionCards;
import com.yijian.clubmodule.net.requestbody.CardRequestBody;
import com.yijian.clubmodule.net.httpmanager.HttpManager;
import com.yijian.commonlib.net.response.ResultJSONObjectObserver;

import org.json.JSONObject;

import java.util.List;


public class HuiJiProductPresenter implements HuiJiProductContract.Presenter {
    private Context context;
    private HuiJiProductContract.View view;


    private Lifecycle lifecycle;

    public HuiJiProductPresenter( Lifecycle lifecycle,Context context) {
        this.context = context;
        this.lifecycle = lifecycle;

    }

    public void setView(HuiJiProductContract.View view) {
        this.view = view;
    }

    @Override
    public void getRecptionCards(CardRequestBody bodyCondition, boolean isRefresh) {

        HttpManager.getHuiJiCardGoodsList(bodyCondition, new ResultJSONObjectObserver(lifecycle) {
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
