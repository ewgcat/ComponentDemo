package com.yijian.staff.mvp.coach.experienceclass.step5.coach;

import com.yijian.staff.mvp.coach.experienceclass.step5.bean.ConsultationConclusionBean;

/**
 * Created by The_P on 2018/5/4.
 */

public interface ExperienceClassStep5CoachContract {
    interface View {

        void showConclusion(ConsultationConclusionBean bean);
    }

    interface Presenter {
        //        GET /experienceProcess/toConsultationConclusion
        void getConsultationConclusion(String processId);
    }
}
