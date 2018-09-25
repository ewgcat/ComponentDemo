package com.yijian.clubmodule.ui.huifang.vip.task

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View

import com.alibaba.android.arouter.facade.annotation.Route
import com.yijian.clubmodule.R
import com.yijian.clubmodule.db.bean.HuiFangTypeBean
import com.yijian.commonlib.base.mvc.MvcBaseActivity
import com.yijian.clubmodule.ui.huifang.vip.history.HuiFangHistoryActivity
import com.yijian.clubmodule.ui.huifang.vip.task.fragment.BaseHuiFangTaskFragment
import com.yijian.clubmodule.ui.huifang.vip.task.pageadapter.HuiFangPagerAdapter
import com.yijian.clubmodule.net.httpmanager.HttpManager
import com.yijian.clubmodule.net.requestbody.HuiFangTypeRequestBody
import com.yijian.commonlib.net.response.ResultJSONArrayObserver
import com.yijian.commonlib.widget.NavigationBar
import com.yijian.clubmodule.db.ClubDBManager
import com.yijian.commonlib.util.JsonUtil

import org.json.JSONArray
import org.json.JSONException

import java.util.ArrayList

import kotlinx.android.synthetic.main.activity_hui_fang_task.*

@Route(path = "/test/33")
class HuiFangTaskActivity : MvcBaseActivity() {



    private val huiFangTypeBeanArrayList = ArrayList<HuiFangTypeBean>()
    private var totalNum: Int = 0


    override fun getLayoutID(): Int {
        return R.layout.activity_hui_fang_task
    }

    override fun initView(savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        initNavigation()
        initData()
        ll_hui_fang_ji_lu.setOnClickListener { _ -> startActivity(Intent(this@HuiFangTaskActivity, HuiFangHistoryActivity::class.java)) }
    }


    private fun initNavigation() {
        val navigationBar = findViewById<View>(R.id.hui_fang_task_navigation_bar) as NavigationBar
        navigationBar.setTitle("会员回访")
        navigationBar.hideLeftSecondIv()
        navigationBar.setBackClickListener(this)
    }


    private fun initData() {
        showLoading()
        val body = HuiFangTypeRequestBody()
        body.isChief = true
        body.type = 0
        HttpManager.postHuiFangType(body, object : ResultJSONArrayObserver(lifecycle) {
            override fun onSuccess(result: JSONArray?) {
                hideLoading()
                try {
                    if (result != null && result.length() > 0) {
                        for (i in 0 until result.length()) {
                            val jsonObject = result.getJSONObject(i)
                            val huiFangTypeBean = HuiFangTypeBean(jsonObject)
                            huiFangTypeBeanArrayList.add(huiFangTypeBean)
                        }
                        val jsonObject = result.getJSONObject(0)
                        if (jsonObject.has("totalNum")) {
                            totalNum = JsonUtil.getInt(jsonObject, "totalNum")
                        }
                        ClubDBManager.getInstance().insertOrReplaceHuiFangTypeBeans(huiFangTypeBeanArrayList)
                        initIndicatorAndViewPager()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }

            override fun onFail(msg: String) {
                hideLoading()
                showToast(msg)
            }
        })
    }

    private fun initIndicatorAndViewPager() {
        val mTitleList = ArrayList<String>()
        val fragmentList = ArrayList<Fragment>()
        for (i in huiFangTypeBeanArrayList.indices) {
            val huiFangTypeBean = huiFangTypeBeanArrayList[i]
            mTitleList.add(huiFangTypeBean.name)
            fragmentList.add(BaseHuiFangTaskFragment( huiFangTypeBean.menu))
        }
        val huiFangPagerAdapter = HuiFangPagerAdapter(supportFragmentManager, fragmentList, mTitleList)
        viewPager.adapter = huiFangPagerAdapter
        tabs.setViewPager(viewPager)
        tabs.updateBubbleNum(0, totalNum)
        //初始化显示第一页
        viewPager.currentItem = 0
    }



}


