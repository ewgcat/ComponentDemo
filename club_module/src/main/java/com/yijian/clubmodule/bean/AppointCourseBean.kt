package com.yijian.clubmodule.bean


/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/9/14 10:48:42
 */

data class AppointCourseBean(
    val p2mParentId: String,
    val p2mToBCappVOS: List<P2mToBCappVOSBean>,
    val sysTime: Long
)


data class P2mToBCappVOSBean(
        val consumingMinute: Int,
        val endTime: String,
        val headPath: String,
        val memberCourseId: String,
        val memberCourseName: String,
        val memberId: String,
        val memberName: String,
        val privateapply2memberId: String,
        val sign: Int,
        val startDate: String,
        val startTime: String,
        val status: Int
)