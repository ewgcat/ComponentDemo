package com.yijian.clubmodule.ui.huifang.search

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.bumptech.glide.Glide
import com.yijian.clubmodule.BuildConfig
import com.yijian.clubmodule.R
import com.yijian.clubmodule.bean.HuiFangInfo
import com.yijian.commonlib.prefs.SharePreferenceUtil
import com.yijian.commonlib.util.ImageLoader
import com.yijian.commonlib.util.Logger

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/2/28 17:08:17
 */
class SearchHuiFangHistoryAdapter(private val context: Context, private var mHuiFangInfoList: List<HuiFangInfo>?) : RecyclerView.Adapter<SearchHuiFangHistoryAdapter.ViewHolder>() {


    fun update(mHuiFangInfoList: List<HuiFangInfo>) {
        this.mHuiFangInfoList = mHuiFangInfoList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHuiFangHistoryAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hui_fang_history, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val huiFangInfo = mHuiFangInfoList!![position]
        resetView(holder)

        holder.bindView(context, huiFangInfo)
    }

    override fun getItemCount(): Int {
        return if (mHuiFangInfoList == null) 0 else mHuiFangInfoList!!.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivHead: ImageView
        var iv_rank: ImageView
        var ivSex: ImageView
        var tvViperName: TextView
        var tvShentiZhuangtai: TextView
        var tvJianshenAihao: TextView
        var tvXingquAihao: TextView

        var llOutdateTime: LinearLayout
        var llHetongDaoQiRi: LinearLayout
        var llPreVisitDate: LinearLayout
        var llFuFangReason: LinearLayout
        var llCardName: LinearLayout
        var llCardType: LinearLayout
        var llCardYuEr: LinearLayout
        var llZuijinJianshen: LinearLayout
        var llChenMoTianShu: LinearLayout
        var llBirthdayType: LinearLayout
        var llBirthday: LinearLayout
        var llDaoFangDate: LinearLayout
        var llShangKeTime: LinearLayout
        var llCourseName: LinearLayout
        var llHuifangJilu: LinearLayout
        var llYaoyueJilu: LinearLayout
        var llNextVisitTime: LinearLayout

        var llQuanYiYuEr: LinearLayout
        var llChuzhiYuEr: LinearLayout
        var tvChuzhiYuEr: TextView
        var tvQuanYiYuEr: TextView

        var tvCourseName: TextView
        var tvOutdateTime: TextView
        var tvHetongDaoQiRi: TextView
        var tvCardName: TextView
        var tvCardType: TextView
        var tvZuijinJianshen: TextView
        var tvChenMoTianShu: TextView
        var tvPreVisitDate: TextView
        var tvFuFangReason: TextView
        var tvBirthdayType: TextView
        var tvBirthday: TextView
        var tvDaoFangDate: TextView
        var tvShangKeTime: TextView
        var tvHuifangJilu: TextView
        var tvYaoyueJilu: TextView
        var tvNextVisitTime: TextView


        var tvHuifangType: TextView


        init {
            ivHead = view.findViewById(R.id.iv_head)
            iv_rank = view.findViewById(R.id.iv_rank)
            tvViperName = view.findViewById(R.id.tv_viper_name)
            ivSex = view.findViewById(R.id.iv_sex)
            tvShentiZhuangtai = view.findViewById(R.id.tv_shenti_zhuangtai)
            tvJianshenAihao = view.findViewById(R.id.tv_jianshen_aihao)
            tvXingquAihao = view.findViewById(R.id.tv_xingqu_aihao)
            llCardName = view.findViewById(R.id.ll_card_name)


            llBirthday = view.findViewById(R.id.ll_birthday)
            tvBirthday = view.findViewById(R.id.tv_birthday)

            llCourseName = view.findViewById(R.id.ll_course_name)
            tvCourseName = view.findViewById(R.id.tv_course_name)


            llBirthdayType = view.findViewById(R.id.ll_birthday_type)
            tvBirthdayType = view.findViewById(R.id.tv_birthday_type)

            llOutdateTime = view.findViewById(R.id.ll_outdate_time)
            tvOutdateTime = view.findViewById(R.id.tv_outdate_time)


            llHetongDaoQiRi = view.findViewById(R.id.ll_hetong_dao_qi_ri)
            tvHetongDaoQiRi = view.findViewById(R.id.tv_hetong_dao_qi_ri)


            tvQuanYiYuEr = view.findViewById(R.id.tv_quanyi_yu_er)
            tvChuzhiYuEr = view.findViewById(R.id.tv_chuzhi_yu_er)
            llChuzhiYuEr = view.findViewById(R.id.ll_chuzhi_yu_er)
            llQuanYiYuEr = view.findViewById(R.id.ll_quanyi_yu_er)

            tvCardName = view.findViewById(R.id.tv_card_name)
            llCardName = view.findViewById(R.id.ll_card_name)
            llCardYuEr = view.findViewById(R.id.ll_card_yu_er)
            tvCardType = view.findViewById(R.id.tv_card_type)
            llCardType = view.findViewById(R.id.ll_card_type)
            tvZuijinJianshen = view.findViewById(R.id.tv_zuijin_jianshen)
            llZuijinJianshen = view.findViewById(R.id.ll_zuijin_jianshen)
            tvChenMoTianShu = view.findViewById(R.id.tv_chen_mo_tian_shu)
            llChenMoTianShu = view.findViewById(R.id.ll_chen_mo_tian_shu)
            tvPreVisitDate = view.findViewById(R.id.tv_pre_visit_date)
            llPreVisitDate = view.findViewById(R.id.ll_pre_visit_date)
            tvFuFangReason = view.findViewById(R.id.tv_fu_fang_reason)
            llFuFangReason = view.findViewById(R.id.ll_fu_fang_reason)


            tvDaoFangDate = view.findViewById(R.id.tv_dao_fang_date)
            llDaoFangDate = view.findViewById(R.id.ll_dao_fang_date)


            tvShangKeTime = view.findViewById(R.id.tv_shang_ke_time)
            llShangKeTime = view.findViewById(R.id.ll_shang_ke_time)

            tvHuifangJilu = view.findViewById(R.id.tv_huifang_jilu)
            llHuifangJilu = view.findViewById(R.id.ll_huifang_jilu)
            tvYaoyueJilu = view.findViewById(R.id.tv_yaoyue_jilu)
            llYaoyueJilu = view.findViewById(R.id.ll_yaoyue_jilu)
            tvNextVisitTime = view.findViewById(R.id.tv_next_visit_time)
            llNextVisitTime = view.findViewById(R.id.ll_next_visit_time)

            tvHuifangType = view.findViewById(R.id.tv_huifang_type)

        }

        fun bindView(context: Context, huiFangInfo: HuiFangInfo) {
            //公共部分


            ImageLoader.setHeadImageResource(SharePreferenceUtil.getHostUrl() + huiFangInfo.headUrl!!, context, ivHead)
            tvViperName.text = huiFangInfo.name
            val resId = if (huiFangInfo.gender == 1) R.mipmap.lg_man else R.mipmap.lg_women
            Glide.with(context).load(resId).into(ivSex)

            val medalType = huiFangInfo.memberMedalType
            if (medalType == 0) {

            } else if (medalType == 1) {
                ImageLoader.setImageResource(R.mipmap.member_gray, context, iv_rank)
            } else if (medalType == 2) {
                ImageLoader.setImageResource(R.mipmap.member_gold, context, iv_rank)
            }


            tvShentiZhuangtai.text = nullCovertToPlaceHolder(huiFangInfo.healthStatus)
            tvJianshenAihao.text = nullCovertToPlaceHolder(huiFangInfo.fitnessHobby)
            tvXingquAihao.text = nullCovertToPlaceHolder(huiFangInfo.hobby)
            tvHuifangType.text = nullCovertToPlaceHolder(huiFangInfo.interviewName)
            val reviewReason = huiFangInfo.reviewReason
            val status = huiFangInfo.status
            if (status == 3) {
                if (!TextUtils.isEmpty(reviewReason)) {
                    llPreVisitDate.visibility = View.VISIBLE
                    llFuFangReason.visibility = View.VISIBLE
                    tvPreVisitDate.text = nullCovertToPlaceHolder(huiFangInfo.lastInterviewTime)
                    tvFuFangReason.text = nullCovertToPlaceHolder(reviewReason)
                    tvHuifangType.text = nullCovertToPlaceHolder(huiFangInfo.interviewName) + " ( 复访 ）"
                }
            }
            val invite = huiFangInfo.invite// 是否邀约, 0未邀约, 1已邀约 ,

            if (invite == 0) {
                llHuifangJilu.visibility = View.VISIBLE
                val result = huiFangInfo.result
                Logger.i("SearchHuiFangHistoryAdapter", "result=" + result!!)
                tvHuifangJilu.text = nullCovertToPlaceHolder(result)
            } else if (invite == 1) {
                llYaoyueJilu.visibility = View.VISIBLE
                llNextVisitTime.visibility = View.VISIBLE
                val inviteContent = huiFangInfo.inviteContent
                tvYaoyueJilu.text = nullCovertToPlaceHolder(inviteContent)
                val inviteVisitTime = huiFangInfo.inviteVisitTime
                tvNextVisitTime.text = nullCovertToPlaceHolder(inviteVisitTime)
            }


            //会员生日回访
            val memberBirthdayInterview = huiFangInfo.memberBirthdayInterview
            if (memberBirthdayInterview != null) {
                llBirthday.visibility = View.VISIBLE
                llBirthdayType.visibility = View.VISIBLE
                tvBirthday.text = nullCovertToPlaceHolder(memberBirthdayInterview.birthday)
                tvBirthdayType.text = nullCovertToPlaceHolder(memberBirthdayInterview.birthdayTypeName)
            }

            //会员过期回访
            val memberPastDueInterview = huiFangInfo.memberPastDueInterview
            if (memberPastDueInterview != null) {
                llCardName.visibility = View.VISIBLE
                llOutdateTime.visibility = View.VISIBLE
                tvCardName.text = nullCovertToPlaceHolder(memberPastDueInterview.cardprodName)
                tvOutdateTime.text = nullCovertToPlaceHolder(memberPastDueInterview.expireDate)
            }

            //沉寂会员回访
            val memberQuietInterview = huiFangInfo.memberQuietInterview
            if (memberQuietInterview != null) {
                llChenMoTianShu.visibility = View.VISIBLE
                llZuijinJianshen.visibility = View.VISIBLE
                val lastTime = memberQuietInterview.lastTime
                tvZuijinJianshen.text = nullCovertToPlaceHolder(lastTime)
                val intervalDay = memberQuietInterview.intervalDay
                tvChenMoTianShu.text = nullCovertToPlaceHolder("" + intervalDay)
            }
            //快到期会员回访
            val memberWillExpireInterview = huiFangInfo.memberWillExpireInterview
            if (memberWillExpireInterview != null) {
                llHetongDaoQiRi.visibility = View.VISIBLE
                llQuanYiYuEr.visibility = View.VISIBLE
                llChuzhiYuEr.visibility = View.VISIBLE
                llCardName.visibility = View.VISIBLE
                tvCardName.text = nullCovertToPlaceHolder(memberWillExpireInterview.cardprodName)
                val endTime = memberWillExpireInterview.endTime
                tvHetongDaoQiRi.text = nullCovertToPlaceHolder(endTime)
                val amount = memberWillExpireInterview.amount
                tvChuzhiYuEr.text = amount.toString() + "元"
                if (memberWillExpireInterview.cardType == 1) {
                    tvQuanYiYuEr.text = memberWillExpireInterview.surplusValidTime.toString() + "次"
                } else {
                    tvQuanYiYuEr.text = memberWillExpireInterview.surplusDay.toString() + "天"
                }
            }

            //昨日开卡回访
            val memberYesterdayBuyCardInterview = huiFangInfo.memberYesterdayBuyCardInterview
            if (memberYesterdayBuyCardInterview != null) {
                llCardName.visibility = View.VISIBLE
                llCardType.visibility = View.VISIBLE
                tvCardName.text = nullCovertToPlaceHolder(memberYesterdayBuyCardInterview.cardprodName)
                tvCardType.text = nullCovertToPlaceHolder(memberYesterdayBuyCardInterview.cardTypeName)
            }

            //昨日到访回访
            val memberYesterdayVisitInterview = huiFangInfo.memberYesterdayVisitInterview
            if (memberYesterdayVisitInterview != null) {
                llDaoFangDate.visibility = View.VISIBLE
                tvDaoFangDate.text = nullCovertToPlaceHolder(memberYesterdayVisitInterview.yesterdayVisitTime)
            }


            //学员生日来访
            val studentBirthdayInterview = huiFangInfo.studentBirthdayInterview
            if (studentBirthdayInterview != null) {
                llBirthday.visibility = View.VISIBLE
                llBirthdayType.visibility = View.VISIBLE
                tvBirthday.text = nullCovertToPlaceHolder(studentBirthdayInterview.birthday)
                tvBirthdayType.text = nullCovertToPlaceHolder(studentBirthdayInterview.birthdayTypeName)
            }

            //昨日上课
            val studentYesterdayInCourseInterview = huiFangInfo.studentYesterdayInCourseInterview
            if (studentYesterdayInCourseInterview != null) {
                llShangKeTime.visibility = View.VISIBLE
                tvShangKeTime.text = nullCovertToPlaceHolder(studentYesterdayInCourseInterview.inviteTime)
            }

            //学员到期回访
            val studentPrivateCoursePastDueInterview = huiFangInfo.studentPrivateCoursePastDueInterview
            if (studentPrivateCoursePastDueInterview != null) {
                llCourseName.visibility = View.VISIBLE
                llOutdateTime.visibility = View.VISIBLE
                tvCourseName.text = nullCovertToPlaceHolder(studentPrivateCoursePastDueInterview.courseName)
                tvOutdateTime.text = nullCovertToPlaceHolder(nullCovertToPlaceHolder(studentPrivateCoursePastDueInterview.endTime))
            }

            //快到期学员回访
            val studentPrivateCourseWillExpireInterview = huiFangInfo.studentPrivateCourseWillExpireInterview
            if (studentPrivateCourseWillExpireInterview != null) {
                llCourseName.visibility = View.VISIBLE
                llHetongDaoQiRi.visibility = View.VISIBLE
                tvCourseName.text = nullCovertToPlaceHolder(studentPrivateCourseWillExpireInterview.courseName)
                tvHetongDaoQiRi.text = nullCovertToPlaceHolder(studentPrivateCourseWillExpireInterview.endTime)
            }

            //昨日买课回访
            val studentYesterdayBuyCourseInterview = huiFangInfo.studentYesterdayBuyCourseInterview
            if (studentYesterdayBuyCourseInterview != null) {
                llCourseName.visibility = View.VISIBLE
                tvCourseName.text = nullCovertToPlaceHolder(studentYesterdayBuyCourseInterview.courseName)
            }


        }

    }

    private fun resetView(holder: ViewHolder) {
        holder.llBirthday.visibility = View.GONE
        holder.llBirthdayType.visibility = View.GONE
        holder.llHetongDaoQiRi.visibility = View.GONE
        holder.llCardName.visibility = View.GONE
        holder.llCardType.visibility = View.GONE
        holder.llCardYuEr.visibility = View.GONE

        holder.llQuanYiYuEr.visibility = View.GONE
        holder.llChuzhiYuEr.visibility = View.GONE
        holder.llZuijinJianshen.visibility = View.GONE
        holder.llChenMoTianShu.visibility = View.GONE


        holder.llFuFangReason.visibility = View.GONE
        holder.llCourseName.visibility = View.GONE
        holder.llOutdateTime.visibility = View.GONE
        holder.llPreVisitDate.visibility = View.GONE

        holder.llDaoFangDate.visibility = View.GONE
        holder.llHuifangJilu.visibility = View.GONE
        holder.llYaoyueJilu.visibility = View.GONE
        holder.llNextVisitTime.visibility = View.GONE
        holder.llShangKeTime.visibility = View.GONE
    }

    companion object {

        fun nullCovertToPlaceHolder(text: String?): String {

            if (text != null && text.isNotEmpty()) {
                return text
            } else {
                return "暂无录入"
            }

        }
    }
}
