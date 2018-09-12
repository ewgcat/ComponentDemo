package com.yijian.staff.mvp.huifang.student.history

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.scwang.smartrefresh.layout.header.BezierRadarHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.yijian.staff.R
import com.yijian.staff.bean.HuiFangInfo
import com.yijian.staff.mvp.huifang.search.SearchHuiFangHistoryActivity
import com.yijian.staff.mvp.huifang.vip.history.HuiFangHistoryAdapter
import com.yijian.staff.net.requestbody.HuifangRecordRequestBody
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity
import com.yijian.staff.net.httpmanager.HttpManager
import com.yijian.staff.net.response.ResultJSONObjectObserver
import com.yijian.staff.util.ImageLoader
import com.yijian.staff.util.JsonUtil
import com.yijian.staff.widget.NavigationBar

import org.json.JSONArray
import org.json.JSONObject

import java.util.ArrayList

import butterknife.OnClick

class HuiFangHistoryActivity : MvcBaseActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var refreshLayout: RefreshLayout
    lateinit var huiFangHistoryAdapter:HuiFangHistoryAdapter

    private val huiFangInfoList = ArrayList<HuiFangInfo>()
    private var pageNum = 1//页码
    private var pageSize = 10//每页数量
    private val type = 1

    override fun getLayoutID(): Int {
        return R.layout.activity_hui_fang_history
    }

    override fun initView(savedInstanceState: Bundle?) {

        val navigationBar = findViewById<View>(R.id.hui_fang_history_navigation_bar) as NavigationBar
        navigationBar.setTitle("回访记录")
        navigationBar.hideLeftSecondIv()
        navigationBar.setBackClickListener(this)
        navigationBar.getmRightTv().text = "搜索"
        ImageLoader.setImageResource(R.mipmap.search, this, navigationBar.getmRightIv())
        navigationBar.setRightClickListener {
            val intent = Intent(this@HuiFangHistoryActivity, SearchHuiFangHistoryActivity::class.java)
            intent.putExtra("type", type)
            startActivity(intent)
        }
        initComponent()
    }


    fun initComponent() {
        refreshLayout = findViewById<View>(R.id.refreshLayout) as RefreshLayout
        //设置 Header 为 BezierRadar 样式
        val header = BezierRadarHeader(this).setEnableHorizontalDrag(true)
        header.setPrimaryColor(Color.parseColor("#1997f8"))
        refreshLayout.setRefreshHeader(header)
        //设置 Footer 为 球脉冲
        val footer = BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale)
        footer.setAnimatingColor(Color.parseColor("#1997f8"))
        refreshLayout.setRefreshFooter(footer)
        refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                refresh()
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                loadMore()
            }
        })


        recyclerView = findViewById(R.id.rlv)

        val layoutmanager = LinearLayoutManager(this)
        //设置RecyclerView 布局
        recyclerView.layoutManager = layoutmanager
        huiFangHistoryAdapter = HuiFangHistoryAdapter(this, huiFangInfoList)
        recyclerView.adapter = huiFangHistoryAdapter
        refresh()
    }

    fun refresh() {
        pageNum = 1
        pageSize = 10
        huiFangInfoList.clear()

        val huifangRecordRequestBody = HuifangRecordRequestBody()
        huifangRecordRequestBody.isChief = true
        huifangRecordRequestBody.pageNum = pageNum
        huifangRecordRequestBody.pageSize = pageSize
        huifangRecordRequestBody.type = type
        HttpManager.postHuiFangRecord(huifangRecordRequestBody, object : ResultJSONObjectObserver(lifecycle) {
            override fun onSuccess(result: JSONObject) {
                refreshLayout.finishRefresh(2000, true)


                pageNum = JsonUtil.getInt(result, "pageNum") + 1
                val records = JsonUtil.getJsonArray(result, "records")

                val list = com.alibaba.fastjson.JSONArray.parseArray(records.toString(), HuiFangInfo::class.java)
                huiFangInfoList.addAll(list)
                huiFangHistoryAdapter.update(huiFangInfoList)
            }

            override fun onFail(msg: String) {
                refreshLayout.finishRefresh(2000, false)
                showToast(msg)
            }
        })
    }

    fun loadMore() {

        val huifangRecordRequestBody = HuifangRecordRequestBody()
        huifangRecordRequestBody.isChief = true
        huifangRecordRequestBody.pageNum = pageNum
        huifangRecordRequestBody.pageSize = pageSize
        huifangRecordRequestBody.type = type

        HttpManager.postHuiFangRecord(huifangRecordRequestBody, object : ResultJSONObjectObserver(lifecycle) {
            override fun onSuccess(result: JSONObject) {
                pageNum = JsonUtil.getInt(result, "pageNum") + 1

                refreshLayout.finishLoadMore(2000, true, false)//传入false表示刷新失败
                val records = JsonUtil.getJsonArray(result, "records")

                val list = com.alibaba.fastjson.JSONArray.parseArray(records.toString(), HuiFangInfo::class.java)
                huiFangInfoList.addAll(list)
                huiFangHistoryAdapter.update(huiFangInfoList)
            }

            override fun onFail(msg: String) {

                refreshLayout.finishLoadMore(2000, false, false)//传入false表示刷新失败
                showToast(msg)
            }
        })
    }

    @OnClick(R.id.ll_hui_fang_ren_wu)
    fun onViewClicked() {
        finish()
    }
}
