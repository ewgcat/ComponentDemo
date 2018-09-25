package com.yijian.clubmodule.bean

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/17 19:29:17
 */
data class CardInfo(val cardprodbaseId: String, val cardType: Int,
                    val amount: Int, val validDay: Int,
                    val rechargeGivePercent: Int, val cardName: String, val venusNames: String,
                    val rechargeGiveStr: String, val validTime: Int, val salePrice: Int, val venueNameList: List<String>) : Comparable<CardInfo> {


    override fun compareTo(other: CardInfo): Int {
        return salePrice - other.salePrice
    }
}
