package com.yijian.staff.mvp.reception.step3.coach;

import com.yijian.staff.mvp.reception.step3.coach.bean.ReceptionUserInfo;

/**
 * Created by The_P on 2018/4/12.
 */

public interface CoachProductContract {
    interface View{
        void showUserInfo(ReceptionUserInfo receptionUserInfo);
    }

    interface Presenter{
        void getUserInfo(String memberId);//获取用户资料

    }
}
