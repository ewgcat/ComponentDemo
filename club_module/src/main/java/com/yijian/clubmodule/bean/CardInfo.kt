package com.yijian.clubmodule.bean

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/17 19:29:17
 */
data class CardInfo(val cardprodbaseId: String, val cardType: Integer,
                    val amount: Integer, val validDay: Integer,
                    val rechargeGivePercent: Integer, val cardName: String, val venusNames: String,
                    val rechargeGiveStr: String, val validTime: Integer, val salePrice: Integer, val venueNameList: List<String>) : Comparable<CardInfo> {


    override fun compareTo(other: CardInfo): Int {
        return salePrice.toInt() - other.salePrice.toInt()
    }
}
