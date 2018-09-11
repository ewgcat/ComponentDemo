package com.yijian.staff.dagger.component;

import android.app.Activity;


import com.yijian.staff.dagger.module.FragmentModule;
import com.yijian.staff.dagger.scope.FragmentScope;

import dagger.Component;


@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();





}
