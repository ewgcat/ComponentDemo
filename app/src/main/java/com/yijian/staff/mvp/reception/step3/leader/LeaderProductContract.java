package com.yijian.staff.mvp.reception.step3.leader;

import com.yijian.staff.mvp.reception.step3.coach.bean.ProductDetail;
import com.yijian.staff.mvp.reception.step3.coach.bean.ReceptionUserInfo;

/**
 * Created by The_P on 2018/4/19.
 */

public interface LeaderProductContract {
    interface View {
        void showUserInfo(ReceptionUserInfo receptionUserInfo);

        void showProductDetail(ProductDetail productDetail);

        void leaderToSaleSecceed();
    }

    interface Presenter {
        void getUserInfo(String memberId);//获取用户资料

        void getProductDetail(String memberId);//获取产品详情

        void leaderToSale(String memberId);//领导通知会籍——用户愿意购买
    }
}
