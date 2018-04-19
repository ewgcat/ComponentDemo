package com.yijian.staff.mvp.reception.step3.kefu;

import com.yijian.staff.mvp.reception.step3.bean.CardInfo;
import com.yijian.staff.mvp.reception.step3.bean.ConditionBody;

import java.util.List;

/**
 * Created by The_P on 2018/4/11.
 */

public interface HuiJiProductContract {
    interface View{
        void showCards(List<CardInfo> goodsInfos, Boolean isRefresh);

        void showToCoachSucceed();

        void showNoCards(boolean isRefresh,boolean isSucceed);
    }

    interface Presenter{
        void getRecptionCards(ConditionBody bodyCondition, boolean isRefresh);

        void toCoach(String memberId,String cardId);
    }
}
