package com.yijian.staff.mvp.huiji.goodsbaojia;


import com.yijian.staff.mvp.huiji.goodsbaojia.bean.CardInfo;
import com.yijian.staff.mvp.huiji.goodsbaojia.bean.CardRequestBody;

import java.util.List;

/**
 * Created by The_P on 2018/4/11.
 */

public interface HuiJiProductContract {
    interface View{
        void showCards(List<CardInfo> goodsInfos, Boolean isRefresh);


        void showNoCards(boolean isRefresh, boolean isSucceed);




    }

    interface Presenter{
        void getRecptionCards(CardRequestBody bodyCondition, boolean isRefresh);


    }
}
