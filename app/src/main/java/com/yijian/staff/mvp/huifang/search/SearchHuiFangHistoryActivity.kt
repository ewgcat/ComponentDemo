package com.yijian.staff.mvp.huifang.search

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView

import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.scwang.smartrefresh.layout.header.BezierRadarHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.yijian.staff.R
import com.yijian.staff.bean.HuiFangInfo
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity
import com.yijian.staff.net.httpmanager.HttpManager
import com.yijian.staff.net.requestbody.HuifangRecordRequestBody
import com.yijan.commonlib.net.response.ResultJSONObjectObserver
import com.yijian.staff.util.JsonUtil
import com.yijian.staff.util.SystemUtil

import org.json.JSONObject

import java.util.ArrayList

import kotlinx.android.synthetic.main.activity_search_hui_fang_history.*
import kotlinx.android.synthetic.main.layout_base_smart_refresh_layout_recyclerview.*

class SearchHuiFangHistoryActivity : MvcBaseActivity() {


    private val huiFangInfoList = ArrayList<HuiFangInfo>()
    private var pageNum = 1//页码
    private var pageSize = 10//每页数量
    private var type: Int = 0

    lateinit var searchHuiFangHistoryAdapter: SearchHuiFangHistoryAdapter


    override fun getLayoutID(): Int {
        return R.layout.activity_search_hui_fang_history
    }

    override fun initView(savedInstanceState: Bundle?) {


        showKeyBoard(et_search)

        type = intent.getIntExtra("type", 0)

        empty_view.setButton { refresh() }


        et_search.setHintTextColor(Color.parseColor("#666666"))

        et_search.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    SystemUtil.hideKeyBoard(et_search, this@SearchHuiFangHistoryActivity)
                    refresh()
                }
            }
            true
        }
        initComponent()
        tv_cancel.setOnClickListener { _ ->
            SystemUtil.hideKeyBoard(et_search, this)
            finish()
        }

    }

    fun initComponent() {
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


        val layoutmanager = LinearLayoutManager(this)
        //设置RecyclerView 布局
        rv.layoutManager = layoutmanager
        searchHuiFangHistoryAdapter = SearchHuiFangHistoryAdapter(this, huiFangInfoList)
        rv.adapter = searchHuiFangHistoryAdapter
    }

    fun refresh() {
        val keyWord = et_search.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(keyWord)) {
            showToast("请输入关键字！")
            return
        }
        pageNum = 1
        pageSize = 10
        huiFangInfoList.clear()
        val huifangRecordRequestBody = HuifangRecordRequestBody()
        huifangRecordRequestBody.isChief = true
        huifangRecordRequestBody.pageNum = pageNum
        huifangRecordRequestBody.pageSize = pageSize
        huifangRecordRequestBody.type = type
        huifangRecordRequestBody.keyWord = keyWord
        showLoading()
        HttpManager.postHuiFangRecord(huifangRecordRequestBody, object : ResultJSONObjectObserver(lifecycle) {
            override fun onSuccess(result: JSONObject) {
                hideLoading()
                refreshLayout.finishRefresh(2000, true)


                pageNum = JsonUtil.getInt(result, "pageNum") + 1
                val records = JsonUtil.getJsonArray(result, "records")

                val list = com.alibaba.fastjson.JSONArray.parseArray(records.toString(), HuiFangInfo::class.java)
                huiFangInfoList.addAll(list)
                searchHuiFangHistoryAdapter.update(huiFangInfoList)
            }

            override fun onFail(msg: String) {
                hideLoading()

                refreshLayout.finishRefresh(2000, false)
                showToast(msg)
            }
        })
    }

    fun loadMore() {
        val keyWord = et_search.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(keyWord)) {
            showToast("请输入关键字！")
            return
        }
        val huifangRecordRequestBody = HuifangRecordRequestBody()
        huifangRecordRequestBody.isChief = true
        huifangRecordRequestBody.pageNum = pageNum
        huifangRecordRequestBody.pageSize = pageSize
        huifangRecordRequestBody.type = type
        huifangRecordRequestBody.keyWord = keyWord
        showLoading()
        showLoading()
        HttpManager.postHuiFangRecord(huifangRecordRequestBody, object : ResultJSONObjectObserver(lifecycle) {
            override fun onSuccess(result: JSONObject) {
                hideLoading()

                pageNum = JsonUtil.getInt(result, "pageNum") + 1

                refreshLayout.finishLoadMore(2000, true, false)//传入false表示刷新失败
                val records = JsonUtil.getJsonArray(result, "records")

                val list = com.alibaba.fastjson.JSONArray.parseArray(records.toString(), HuiFangInfo::class.java)
                huiFangInfoList.addAll(list)
                searchHuiFangHistoryAdapter.update(huiFangInfoList)
            }

            override fun onFail(msg: String) {
                hideLoading()

                refreshLayout.finishLoadMore(2000, false, false)//传入false表示刷新失败
                showToast(msg)
            }
        })
    }


}
