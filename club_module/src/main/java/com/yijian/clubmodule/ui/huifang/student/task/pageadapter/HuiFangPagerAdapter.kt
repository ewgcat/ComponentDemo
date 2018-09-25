package com.yijian.clubmodule.ui.huifang.student.task.pageadapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentTransaction
import android.view.ViewGroup

import java.util.ArrayList

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/3/5 19:14:06
 */
class HuiFangPagerAdapter(private val mFragmentManager: FragmentManager, private val fragments: List<Fragment>, mTitleDataList: List<String>) : FragmentPagerAdapter(mFragmentManager) {
    private var mTitleDataList = ArrayList<String>()
    private val fragmentTransaction: FragmentTransaction? = null


    init {
        this.mTitleDataList = mTitleDataList as ArrayList<String>
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return mTitleDataList[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
}
