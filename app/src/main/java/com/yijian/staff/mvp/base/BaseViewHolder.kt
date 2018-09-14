package com.yijian.staff.mvp.base

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View

/**
 * ViewHolder基类
 */
class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val views: SparseArray<View>

    init {
        this.views = SparseArray()
    }
    @Suppress("UNCHECKED_CAST")
    fun <T : View> getView(viewId: Int): T? {
        var view: View? = views.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            views.put(viewId, view)
        }
        return view as T?
    }
}
