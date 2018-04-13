package com.yijian.staff.mvp.reception.step3.kefu;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yijian.staff.mvp.huiji.goodsbaojia.bean.GoodsInfo;
import com.yijian.staff.mvp.reception.step3.bean.CardInfo;
import com.yijian.staff.mvp.reception.step3.bean.ConditionBody;
import com.yijian.staff.net.requestbody.huijigoods.HuiJiGoodsRequestBody;

import java.util.List;

/**
 * Created by The_P on 2018/4/11.
 */

public interface HuiJiProductContract {
    interface View{
        void showCards(List<CardInfo> goodsInfos, Boolean isRefresh);
//        void showRefresh(List<GoodsInfo> goodsInfos);
    }

    interface Presenter{
        void getRecptionCards(SmartRefreshLayout refreshLayout,ConditionBody bodyCondition, Boolean isRefresh);

//        void toCoach();
    }
}
