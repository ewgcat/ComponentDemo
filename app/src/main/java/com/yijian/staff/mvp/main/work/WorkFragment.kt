package com.yijian.staff.mvp.main.work

import android.annotation.SuppressLint
import android.arch.lifecycle.Lifecycle
import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout

import com.yijian.staff.R
import com.yijian.staff.bean.IndexDataInfo
import com.yijian.staff.db.DBManager
import com.yijian.staff.db.bean.OthermodelVo
import com.yijian.staff.jpush.ClearRedPointUtil
import com.yijian.staff.jpush.bean.PushInfoBean
import com.yijian.staff.mvp.base.mvc.MvcBaseFragment
import com.yijian.staff.mvp.face.FaceDetectorActivity
import com.yijian.staff.mvp.reception.ReceptionActivity
import com.yijian.staff.mvp.vipermanage.search.HuiJiSearchActivity
import com.yijian.staff.net.httpmanager.HttpManager
import com.yijian.staff.net.response.ResponseObserver
import com.yijian.staff.prefs.SharePreferenceUtil
import com.yijian.staff.util.Logger

import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList

import butterknife.BindView
import butterknife.OnClick


@SuppressLint("ValidFragment")
class WorkFragment : MvcBaseFragment() {
    @BindView(R.id.top_view)
    lateinit var topView: LinearLayout
    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView
    var hasNewJiedaiPush: Boolean = false
    var hasNewYueKePush: Boolean = false
    @BindView(R.id.iv_face)
    lateinit var ivFace: ImageView
    @BindView(R.id.ll_jiedai_container)
    lateinit var llJiedaiContainer: LinearLayout
    @BindView(R.id.ll_jiedai)
    lateinit var llJiedai: LinearLayout
    @BindView(R.id.ll_jie_dai_container)
    lateinit var llJieDaiContainer: LinearLayout
    @BindView(R.id.swipe_refresh_layout)
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    lateinit var indexMenuAdapter: IndexMenuAdapter
    private val menuList = ArrayList<IndexDataInfo.MenuModelListBean.SubMeneModelListBean>()
    private var faceRecognition: Boolean = false
    private var reception: Boolean = false


    override val layoutId: Int
        get() = R.layout.fragment_work


    override fun initView() {


        indexMenuAdapter = IndexMenuAdapter(this.getLifecycle(), context, menuList)
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.adapter = indexMenuAdapter

        val othermodelVo = DBManager.getInstance().queryOthermodelVo()
        Logger.i(TAG, othermodelVo.toString())
        faceRecognition = othermodelVo.faceRecognition
        if (faceRecognition) {
            ivFace.visibility = View.VISIBLE
        } else {
            ivFace.visibility = View.GONE
        }
        reception = othermodelVo.reception

        hasNewJiedaiPush = SharePreferenceUtil.hasNewJiedaiPush()
        hasNewYueKePush = SharePreferenceUtil.hasNewYueKePush()
        initData()
        swipeRefreshLayout.setOnRefreshListener { initData() }

    }

    fun showJieDaiView(i: Int) {
        if (isAdded) {
            if (i == 0) {//没有接待权限
                llJieDaiContainer.background = resources.getDrawable(R.mipmap.home_no_jd)
                llJiedaiContainer.visibility = View.GONE
                llJiedai.visibility = View.GONE
            } else if (i == 1) {//有接待权限，没有新消息
                llJieDaiContainer.background = resources.getDrawable(R.mipmap.home_no_new_jd)
                llJiedaiContainer.visibility = View.VISIBLE
                llJiedai.visibility = View.VISIBLE
            } else if (i == 2) {//有接待权限，有新消息
                llJieDaiContainer.background = resources.getDrawable(R.mipmap.home_new_jd)
                llJiedaiContainer.visibility = View.VISIBLE
                llJiedai.visibility = View.VISIBLE
            }
        }
    }


    fun observe(pushInfoBean: PushInfoBean) {

        hasNewJiedaiPush = pushInfoBean.hasNewJiedaiPush
        hasNewYueKePush = pushInfoBean.hasNewYueKePush

        setRedPoint()

    }

    private fun setRedPoint() {
        if (reception) {
            if (hasNewJiedaiPush) {
                showJieDaiView(2)
            } else {
                showJieDaiView(1)
            }
        } else {
            showJieDaiView(0)
        }
        if (hasNewYueKePush) {
            Logger.i(TAG, "有排课信息推送")

            for (i in menuList.indices) {
                if (menuList[i].menuKey == "app_course_appoint_info") {
                    menuList[i].count = 1
                }
            }
        }
        indexMenuAdapter.notifyDataSetChanged()

    }


    private fun initData() {
        showLoading()

        HttpManager.getNewIndexMenuList(object : ResponseObserver<IndexDataInfo>(getLifecycle()) {
            override fun onSuccess(result: IndexDataInfo) {
                hideLoading()
                val othermodelVoBean = result.othermodelVo
                val jsonObject = JSONObject()
                try {
                    jsonObject.put("faceRecognition", othermodelVoBean.isFaceRecognition)
                    jsonObject.put("reception", othermodelVoBean.isReception)
                    jsonObject.put("coachSchedule", othermodelVoBean.isCoachSchedule)
                    jsonObject.put("sellerSchedule", othermodelVoBean.isSellerSchedule)
                    DBManager.getInstance().insertOrReplaceOthermodelVo(OthermodelVo(jsonObject))
                    reception = othermodelVoBean.isReception
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

                //TODO 首页 接待 人脸识别权限

                menuList.clear()
                val menuModelList = result.menuModelList
                SharePreferenceUtil.setAppSellerBuiness(false)
                SharePreferenceUtil.setAppCourseBuiness(false)
                for (i in menuModelList.indices) {
                    val menuModelListBean = menuModelList[i]
                    if (menuModelListBean != null && menuModelListBean.menuKey == "app_workbench") {
                        val subMeneModelList = menuModelListBean.subMeneModelList
                        for (j in subMeneModelList.indices) {
                            val subMeneModelListBean = subMeneModelList[j]
                            menuList.add(subMeneModelListBean)
                        }
                    } else if (menuModelListBean != null && menuModelListBean.menuKey == "app_business_message") {
                        val subMeneModelList = menuModelListBean.subMeneModelList
                        for (j in subMeneModelList.indices) {
                            val subMeneModelListBean = subMeneModelList[j]
                            if (subMeneModelListBean.menuKey == "app_seller_business") {
                                SharePreferenceUtil.setAppSellerBuiness(true)
                            } else if (subMeneModelListBean.menuKey == "app_course_business") {
                                SharePreferenceUtil.setAppCourseBuiness(true)
                            }
                        }
                    }
                }
                setRedPoint()
                swipeRefreshLayout.isRefreshing = false

            }

            override fun onFail(msg: String) {
                swipeRefreshLayout.isRefreshing = false
                hideLoading()
                showToast(msg)
            }
        })


    }


    @OnClick(R.id.et_search, R.id.ll_jiedai, R.id.iv_face)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.et_search ->
                // 此处为得到焦点时的处理内容
                startActivity(Intent(context, HuiJiSearchActivity::class.java))
            R.id.ll_jiedai -> {
                ClearRedPointUtil.clearJieDaiNotice(this.getLifecycle())
                showJieDaiView(1)
                SharePreferenceUtil.setHasNewJiedaiPush(false)
                startActivity(Intent(activity, ReceptionActivity::class.java))
            }
            R.id.iv_face -> startActivity(Intent(activity, FaceDetectorActivity::class.java))
        }
    }

    companion object {
        private val TAG = WorkFragment::class.java.simpleName

        lateinit var mWorkFragment: WorkFragment
    }

}


