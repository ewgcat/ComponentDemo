package com.yijian.staff.bean

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/8/17 19:21:46
 */
class CourseTimeBean(val startTime: String,val endTime: String) : Comparable<CourseTimeBean>{


    override fun compareTo(other: CourseTimeBean): Int {
        return Integer.parseInt(this.startTime.replace(":".toRegex(), "")) - Integer.parseInt(other.startTime.replace(":".toRegex(), ""))
    }
}