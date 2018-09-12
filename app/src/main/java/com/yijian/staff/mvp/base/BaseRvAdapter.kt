package com.yijian.staff.mvp.base


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

/**
 * 封装adapter（注意：仅供参考，根据需要选择使用demo中提供的封装adapter）
 * @param <T>
</T> */
abstract class BaseRvAdapter<T>(protected var mContext: Context) : RecyclerView.Adapter<BaseViewHolder>() {
    private val mInflater: LayoutInflater

    protected var mDataList: MutableList<T> = ArrayList()

    abstract val layoutId: Int

    val dataList: List<T>
        get() = mDataList

    init {
        mInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemView = mInflater.inflate(layoutId, parent, false)
        return BaseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        onBindItemHolder(holder, position)
    }



    abstract fun onBindItemHolder(holder: BaseViewHolder, position: Int)



    override fun getItemCount(): Int {
        return mDataList.size
    }

    fun setDataList(list: Collection<T>) {
        this.mDataList.clear()
        this.mDataList.addAll(list)
        notifyDataSetChanged()
    }

    fun addAll(list: Collection<T>) {
        val lastIndex = this.mDataList.size
        if (this.mDataList.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size)
        }
    }

    fun remove(position: Int) {
        this.mDataList.removeAt(position)
        notifyItemRemoved(position)

        if (position != dataList.size) { // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position, this.mDataList.size - position)
        }
    }

    fun clear() {
        mDataList.clear()
        notifyDataSetChanged()
    }
}


