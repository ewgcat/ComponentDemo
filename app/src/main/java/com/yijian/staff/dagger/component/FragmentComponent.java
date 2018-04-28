package com.yijian.staff.dagger.component;

import android.app.Activity;


import com.yijian.staff.dagger.module.FragmentModule;
import com.yijian.staff.dagger.scope.FragmentScope;
import com.yijian.staff.mvp.reception.step3.kefu.HuiJiProductQuotationFragment;
import com.yijian.staff.mvp.main.work.WorkFragment;

import dagger.Component;



@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(WorkFragment workFragment);
    void inject(HuiJiProductQuotationFragment huiJiProductQuotationFragment);


}
