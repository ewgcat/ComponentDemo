package com.yijian.clubmodule.ui.price.cardprice;


import com.yijian.clubmodule.bean.CardInfo;
import com.yijian.clubmodule.net.requestbody.CardRequestBody;

import java.util.List;

/**
 * Created by The_P on 2018/4/11.
 */

public interface HuiJiProductContract {
    interface View {
        void showCards(List<CardInfo> goodsInfos, Boolean isRefresh);


        void showNoCards(boolean isRefresh, boolean isSucceed);


    }

    interface Presenter {
        void getRecptionCards(CardRequestBody bodyCondition, boolean isRefresh);


    }
}
