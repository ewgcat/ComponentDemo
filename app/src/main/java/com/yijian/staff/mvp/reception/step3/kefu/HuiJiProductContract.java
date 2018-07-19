package com.yijian.staff.mvp.reception.step3.kefu;

import com.yijian.staff.bean.ReceptionStastuBean;
import com.yijian.staff.mvp.reception.step3.bean.CardInfo;
import com.yijian.staff.mvp.reception.step3.bean.ConditionBody;
import com.yijian.staff.mvp.reception.step3.coach.bean.ProductDetail;

import java.util.List;

/**
 * Created by The_P on 2018/4/11.
 */

public interface HuiJiProductContract {
    interface View {
        void showCards(List<CardInfo> goodsInfos, Boolean isRefresh);

        void showToCoachSucceed();

        void showNoCards(boolean isRefresh, boolean isSucceed);

        void showStatus(ReceptionStastuBean receptionStastuBean);

        void showCardToOrder();

        void shouldCardToOrder();

        void showProductDetail(ProductDetail productDetail);
//        void showRefresh(List<GoodsInfo> goodsInfos);


    }

    interface Presenter {
        void getRecptionCards(ConditionBody bodyCondition, boolean isRefresh);

        void toCoach(String memberId, String cardId);

        void getStatus(boolean isFirst, String memberId);

        void cardToOrder(String memberId, String cardprodbaseId);

        void getProductDetail(String memberId);
    }
}
