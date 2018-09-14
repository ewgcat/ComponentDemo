package com.yijian.staff.bean

import java.io.Serializable

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/5 16:22:37
 */
 class HuiFangInfo : Serializable {


    /**
     * fitnessHobby : string
     * gender : 0
     * headUrl : string
     * healthStatus : string
     * hobby : string
     * id : string
     * interviewName : string
     * invite : 0
     * inviteContent : string
     * inviteVisitTime : 2018-08-13T01:54:42.665Z
     * memberBirthdayInterview : {"birthday":"2018-08-13T01:54:42.665Z","birthdayTypeName":"string"}
     * memberId : string
     * memberPastDueInterview : {"expireDate":"2018-08-13T01:54:42.665Z"}
     * memberPotentialInterview : {}
     * memberQuietInterview : {"intervalDay":0,"lastTime":"2018-08-13T01:54:42.665Z"}
     * memberWillExpireInterview : {"amount":0,"cardType":0,"cardTypeName":"string","cardprodName":"string","endTime":"2018-08-13T01:54:42.665Z","surplusValidTime":0}
     * memberYesterdayBuyCardInterview : {"cardTypeName":"string","cardprodName":"string"}
     * memberYesterdayVisitInterview : {"yesterdayVisitTime":"2018-08-13T01:54:42.665Z"}
     * mobile : string
     * name : string
     * result : string
     * reviewReason : string
     * status : 0
     * studentBirthdayInterview : {"birthday":"2018-08-13T01:54:42.665Z","birthdayType":0,"birthdayTypeName":"string"}
     * studentPrivateCoursePastDueInterview : {"endTime":"2018-08-13T01:54:42.665Z"}
     * studentPrivateCourseWillExpireInterview : {"endTime":"2018-08-13T01:54:42.665Z"}
     * studentYesterdayBuyCourseInterview : {"buyCourseTime":"2018-08-13T01:54:42.665Z"}
     * studentYesterdayInCourseInterview : {"inviteTime":"2018-08-13T01:54:42.665Z"}
     */

    var fitnessHobby: String? = null
    var gender: Int = 0
    var headUrl: String? = null
    var healthStatus: String? = null
    var lastInterviewTime: String? = null
    var hobby: String? = null
    var id: String? = null
    var interviewName: String? = null
    var invite: Int = 0// 是否邀约, 0未邀约, 1已邀约 ,
    var memberMedalType: Int = 0
    var inviteContent: String? = null
    var inviteVisitTime: String? = null
    var memberBirthdayInterview: MemberBirthdayInterviewBean? = null
    var memberId: String? = null
    var memberPastDueInterview: MemberPastDueInterviewBean? = null
    var memberPotentialInterview: MemberPotentialInterviewBean? = null
    var memberQuietInterview: MemberQuietInterviewBean? = null
    var memberWillExpireInterview: MemberWillExpireInterviewBean? = null
    var memberYesterdayBuyCardInterview: MemberYesterdayBuyCardInterviewBean? = null
    var memberYesterdayVisitInterview: MemberYesterdayVisitInterviewBean? = null
    var mobile: String? = null
    var name: String? = null
    var memberTypeName: String? = null
    var result: String? = null
    var reviewReason: String? = null
    var status: Int = 0
    var studentBirthdayInterview: StudentBirthdayInterviewBean? = null
    var studentPrivateCoursePastDueInterview: StudentPrivateCoursePastDueInterviewBean? = null
    var studentPrivateCourseWillExpireInterview: StudentPrivateCourseWillExpireInterviewBean? = null
    var studentYesterdayBuyCourseInterview: StudentYesterdayBuyCourseInterviewBean? = null
    var studentYesterdayInCourseInterview: StudentYesterdayInCourseInterviewBean? = null

    class MemberBirthdayInterviewBean : Serializable {
        /**
         * birthday : 2018-08-13T01:54:42.665Z
         * birthdayTypeName : string
         */

        var birthday: String? = null
        var birthdayTypeName: String? = null
    }

    class MemberPastDueInterviewBean : Serializable {
        var expireDate: String? = null
        var cardprodName: String? = null
        var cardType: String? = null
        var cardTypeName: String? = null
    }

    class MemberPotentialInterviewBean : Serializable

    class MemberQuietInterviewBean : Serializable {
        /**
         * intervalDay : 0
         * lastTime : 2018-08-13T01:54:42.665Z
         */

        var intervalDay: Int = 0
        var lastTime: String? = null
    }

    class MemberWillExpireInterviewBean : Serializable {

        /**
         * amount : 0
         * cardType : 0
         * cardTypeName : string
         * cardprodName : string
         * endTime : 2018-08-13T01:54:42.665Z
         * surplusValidTime : 0
         */


        var amount: Int = 0
        var cardType: Int = 0//卡类型 (0:期限卡 1:次卡 2:储值卡 3:会员制卡 4:员工卡)
        var cardTypeName: String? = null
        var cardprodName: String? = null
        var endTime: String? = null//合同即将到期时间
        var surplusValidTime: Int = 0//如果是次卡
        var surplusDay: Int = 0//合同到期天数
    }

    class MemberYesterdayBuyCardInterviewBean : Serializable {
        /**
         * cardTypeName : string
         * cardprodName : string
         */

        var cardTypeName: String? = null
        var cardprodName: String? = null
    }

    class MemberYesterdayVisitInterviewBean : Serializable {
        /**
         * yesterdayVisitTime : 2018-08-13T01:54:42.665Z
         */

        var yesterdayVisitTime: String? = null
    }

    class StudentBirthdayInterviewBean : Serializable {
        /**
         * birthday : 2018-08-13T01:54:42.665Z
         * birthdayType : 0
         * birthdayTypeName : string
         */

        var birthday: String? = null
        var birthdayType: Int = 0
        var birthdayTypeName: String? = null
    }

    class StudentPrivateCoursePastDueInterviewBean : Serializable {
        /**
         * endTime : 2018-08-13T01:54:42.665Z
         */

        var endTime: String? = null
        var courseName: String? = null
    }

    class StudentPrivateCourseWillExpireInterviewBean : Serializable {
        /**
         * endTime : 2018-08-13T01:54:42.665Z
         */

        var endTime: String? = null
        var courseName: String? = null
    }

    class StudentYesterdayBuyCourseInterviewBean : Serializable {
        /**
         * buyCourseTime : 2018-08-13T01:54:42.665Z
         */

        var buyCourseTime: String? = null
        var courseName: String? = null


    }

    class StudentYesterdayInCourseInterviewBean : Serializable {
        /**
         * inviteTime : 2018-08-13T01:54:42.665Z
         */

        var inviteTime: String? = null
    }
}
