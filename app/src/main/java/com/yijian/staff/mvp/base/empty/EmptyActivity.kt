package com.yijian.staff.mvp.base.empty

import android.os.Bundle

import com.alibaba.android.arouter.facade.annotation.Route
import com.yijian.staff.R
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity
import com.yijian.staff.widget.NavigationBar

@Route(path = "/test/empty")
class EmptyActivity : MvcBaseActivity() {

    override fun getLayoutID(): Int {
        return R.layout.activity_empty
    }

    override fun initView(savedInstanceState: Bundle?) {
        val navigationBar = findViewById<NavigationBar>(R.id.empty_navigation_bar2)
        navigationBar.setBackClickListener(this)
        navigationBar.hideLeftSecondIv()
    }
}
