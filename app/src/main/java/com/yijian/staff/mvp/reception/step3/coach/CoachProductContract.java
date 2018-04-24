package com.yijian.staff.mvp.reception.step3.coach;

import com.yijian.staff.mvp.reception.step3.coach.bean.ProductDetail;
import com.yijian.staff.mvp.reception.step3.coach.bean.ReceptionUserInfo;

/**
 * Created by The_P on 2018/4/12.
 */

public interface CoachProductContract {
    interface View{
        void showUserInfo(ReceptionUserInfo receptionUserInfo);
        void showProductDetail(ProductDetail productDetail);
        void showToLeaderSucceed();

        void coachToSaleSecceed();
    }

    interface Presenter{
        void getUserInfo(String memberId);//获取用户资料

        void getProductDetail(String memberId);//获取产品详情

        void postToLeader(String memberId,String reason,Integer postId);//TO给领导

        void coachToSale(String memberId,String cardId);//发送给会籍——客户愿意购买
    }
}
