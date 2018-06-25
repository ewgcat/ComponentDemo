package com.yijian.staff.mvp.coach.experienceclass.step3;

import com.yijian.staff.mvp.coach.experienceclass.step3.bean.ConsultationProgrammeBean;

/**
 * Created by The_P on 2018/4/16.
 */

public interface ExperienceClassProcess3Contract {
    interface View {
        void showConsultationProgramme(ConsultationProgrammeBean consultationProgrammeBean);

        void showSaveSecceed();
    }

    interface Presenter {
        void getConsultationProgramme(String processId);

        void postConsultationProgramme(String processId, String programmeContext);
    }
}
