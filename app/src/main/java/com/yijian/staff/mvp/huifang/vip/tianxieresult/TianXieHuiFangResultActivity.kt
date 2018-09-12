package com.yijian.staff.mvp.huifang.vip.tianxieresult

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView

import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.yijian.staff.BuildConfig
import com.yijian.staff.R
import com.yijian.staff.net.requestbody.AbortFuFangBody
import com.yijian.staff.bean.HuiFangInfo
import com.yijian.staff.bean.HuiFangReasonBean
import com.yijian.staff.mvp.base.mvc.MvcBaseActivity
import com.yijian.staff.net.httpmanager.HttpManager
import com.yijian.staff.net.requestbody.huifang.AddHuiFangResultBody
import com.yijian.staff.net.response.ResultBooleanObserver
import com.yijian.staff.net.response.ResultJSONArrayObserver
import com.yijian.staff.util.DateUtil
import com.yijian.staff.util.ImageLoader
import com.yijian.staff.util.JsonUtil
import com.yijian.staff.widget.NavigationBar

import org.json.JSONArray
import org.json.JSONObject

import java.util.ArrayList
import java.util.Calendar

import butterknife.BindView
import butterknife.OnClick

class TianXieHuiFangResultActivity : MvcBaseActivity() {


    @BindView(R.id.rg)
    lateinit var rg: RadioGroup
    @BindView(R.id.rel_nav)
    lateinit var rel_nav: RelativeLayout
    @BindView(R.id.rel_sure)
    lateinit var rel_sure: RelativeLayout

    @BindView(R.id.iv_sure_header)
    lateinit var iv_sure_header: ImageView
    @BindView(R.id.tv_sure_name)
    lateinit var tv_sure_name: TextView
    @BindView(R.id.iv_sure_gender)
    lateinit var iv_sure_gender: ImageView
    @BindView(R.id.tv_fufan_time)
    lateinit var tv_fufan_time: TextView
    @BindView(R.id.tv_fufan_reason)
    lateinit var tv_fufan_reason: TextView


    @BindView(R.id.iv_nav_header)
    lateinit var iv_nav_header: ImageView
    @BindView(R.id.tv_nav_name)
    lateinit var tv_nav_name: TextView
    @BindView(R.id.iv_nav_gender)
    lateinit var iv_nav_gender: ImageView
    @BindView(R.id.tv_huifan_type)
    lateinit var tv_huifan_type: TextView
    @BindView(R.id.et_huifan_record)
    lateinit var et_huifan_record: EditText
    @BindView(R.id.tv_laifan_time)
    lateinit var tvLaifanTime: TextView
    @BindView(R.id.tv_vip_type)
    lateinit var tvVipType: TextView
    @BindView(R.id.tv_can_input_number)
    lateinit var tvCanInputNumber: TextView

    private var huiFangReasonBeanList: MutableList<HuiFangReasonBean> = ArrayList()
    private var needReview = false
    lateinit var huiFangInfo: HuiFangInfo
    lateinit var fufangTime: String
    lateinit var laifangTime: String
    lateinit var dictItemId: String


    override fun getLayoutID(): Int {
        return R.layout.activity_tian_xie_hui_ji_hui_fang_result
    }

    override fun initView(savedInstanceState: Bundle?) {
        initView()

    }

    private fun initView() {
        val navigationBar = findViewById<NavigationBar>(R.id.tian_xie_hui_fang_result_navigation_bar)
        navigationBar.hideLeftSecondIv()
        navigationBar.setBackClickListener(this)
        navigationBar.setTitle("填写记录")
        navigationBar.setmRightTvText("确定")
        navigationBar.setmRightTvColor(Color.parseColor("#1997f8"))
        navigationBar.setRightClickListener { sendResult() }
        updateUi()

        rg.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rb_nav) {
                rel_nav.visibility = View.VISIBLE
                rel_sure.visibility = View.GONE
                needReview = false
            } else {
                rel_nav.visibility = View.GONE
                rel_sure.visibility = View.VISIBLE
                needReview = true
            }
        }
        et_huifan_record.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                val s1 = s.toString()

                if (!TextUtils.isEmpty(s1)) {
                    val num = 140 - s1.length
                    tvCanInputNumber.text = num.toString() + "字"
                } else {
                    tvCanInputNumber.text = "140字"
                }
            }
        })
    }

    private fun updateUi() {

        HttpManager.getHasHeaderNoParam(HttpManager.GET_COACH_HUI_FANG_REASON_LIST_URL, object : ResultJSONArrayObserver(lifecycle) {
            override fun onSuccess(result: JSONArray) {
                for (i in 0 until result.length()) {
                    val jsonObject = JsonUtil.getJsonObject(result, i)
                    val huiFangReasonBean = HuiFangReasonBean(jsonObject)
                    huiFangReasonBeanList.add(huiFangReasonBean)
                }
            }

            override fun onFail(msg: String) {
                showToast(msg)
            }
        })

        huiFangInfo = intent.getSerializableExtra("huiFangInfo") as HuiFangInfo
        ImageLoader.setHeadImageResource(BuildConfig.FILE_HOST + huiFangInfo.headUrl, this, iv_nav_header)
        ImageLoader.setHeadImageResource(BuildConfig.FILE_HOST + huiFangInfo.headUrl, this, iv_sure_header)
        tv_nav_name.text = huiFangInfo.name
        tv_sure_name.text = huiFangInfo.name
        val resId = if (huiFangInfo.gender == 0) R.mipmap.lg_man else R.mipmap.lg_women
        iv_nav_gender.setImageResource(resId)
        iv_sure_gender.setImageResource(resId)


        tv_huifan_type.text = huiFangInfo.interviewName
        tvVipType.text = huiFangInfo.memberTypeName
    }

    @OnClick(R.id.rel_huifan_time, R.id.rel_huifan_reason, R.id.rel_laifan_time)
    fun onViewClicked(view: View) {
        when (view.id) {

            R.id.rel_huifan_time -> {

                val c = Calendar.getInstance()
                val dialog = DatePickerDialog(this,
                        DatePickerDialog.OnDateSetListener {_, year, month, dayOfMonth ->
                            var time = ""
                            if (month < 9 && dayOfMonth < 10) {
                                time += year.toString() + "-0" + (month + 1) + "-0" + dayOfMonth
                            } else if (month > 9 && dayOfMonth >= 10) {
                                time += year.toString() + "-" + (month + 1) + "-" + dayOfMonth
                            } else if (month < 9 && dayOfMonth >= 10) {
                                time += year.toString() + "-0" + (month + 1) + "-" + dayOfMonth
                            } else if (month > 9 && dayOfMonth < 10) {
                                time += year.toString() + "-" + (month + 1) + "-0" + dayOfMonth
                            }


                            val s = "" + DateUtil.getCurrentYear() + DateUtil.getCurrentMonth() + DateUtil.getCurrentDay()
                            val s1 = "" + year + month + dayOfMonth
                            if (Integer.parseInt(s1) >= Integer.parseInt(s)) {
                                fufangTime = time
                                tv_fufan_time.text = time
                            } else {
                                fufangTime = ""
                                tv_fufan_time.text = ""
                                showToast("复访时间不得小于当前时间")
                            }
                        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH))
                dialog.show()
            }
            R.id.rel_laifan_time -> {


                val c1 = Calendar.getInstance()
                DatePickerDialog(this,
                        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                            var time = ""
                            if (month < 9 && dayOfMonth < 10) {
                                time += year.toString() + "-0" + (month + 1) + "-0" + dayOfMonth
                            } else if (month > 9 && dayOfMonth >= 10) {
                                time += year.toString() + "-" + (month + 1) + "-" + dayOfMonth
                            } else if (month < 9 && dayOfMonth >= 10) {
                                time += year.toString() + "-0" + (month + 1) + "-" + dayOfMonth
                            } else if (month > 9 && dayOfMonth < 10) {
                                time += year.toString() + "-" + (month + 1) + "-0" + dayOfMonth
                            }


                            val s = "" + DateUtil.getCurrentYear() + DateUtil.getCurrentMonth() + DateUtil.getCurrentDay()
                            val s1 = "" + year + month + dayOfMonth
                            if (Integer.parseInt(s1) >= Integer.parseInt(s)) {
                                laifangTime = time
                                tvLaifanTime.text = time
                            } else {
                                laifangTime = ""
                                showToast("来访时间不得小于当前时间")
                            }
                        }, c1.get(Calendar.YEAR), c1.get(Calendar.MONTH),
                        c1.get(Calendar.DAY_OF_MONTH)).show()
            }
            R.id.rel_huifan_reason -> showPickerReasonView()
        }
    }

    private fun sendResult() {

        if (needReview) {
            val reason = tv_fufan_reason.text.toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(fufangTime)) {
                showToast("请选择复访时间")
                return
            }
            if (TextUtils.isEmpty(reason)) {
                showToast("请选择复访原因")
                return
            }
            val body = AbortFuFangBody()
            body.isChief = true
            body.id = huiFangInfo.id
            body.reviewReason = reason
            body.reviewTime = fufangTime
            HttpManager.postAbortFuFang(body, object : ResultBooleanObserver(lifecycle) {
                override fun onSuccess(result: Boolean?) {
                    if (result!!) {
                        hideKeyBoard(et_huifan_record)
                        finish()
                    } else {
                        showToast("保存失败")
                    }

                }

                override fun onFail(msg: String) {
                    showToast(msg)
                }
            })

        } else {

            val result = et_huifan_record.text.toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(result)) {
                showToast("请填写回访结果")
                return
            }
            val body = AddHuiFangResultBody()
            body.id = huiFangInfo.id
            body.isChief = true
            body.desc = result
            body.visitTime = laifangTime

            HttpManager.postHuiFangResult(body, object : ResultBooleanObserver(lifecycle) {
                override fun onSuccess(result: Boolean?) {
                    if (result!!) {
                        hideKeyBoard(et_huifan_record)
                        finish()
                    } else {
                        showToast("保存失败")
                    }
                }

                override fun onFail(msg: String) {
                    showToast(msg)
                }
            })
        }


    }

    private fun showPickerReasonView() {// 弹出选择器

        if (huiFangReasonBeanList.size > 0) {
            val pvOptions = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, _, _, _ ->
                //返回的分别是三个级别的选中位置
                val reason = huiFangReasonBeanList[options1].dictItemName
                tv_fufan_reason.text = reason
                dictItemId = huiFangReasonBeanList[options1].dictItemId
            }).setTitleText("回访原因").setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .build<Any>()

            pvOptions.setPicker(huiFangReasonBeanList as List<Any>?)//二级选择器
            pvOptions.show()
        }

    }


}
