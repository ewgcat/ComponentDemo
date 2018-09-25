package com.yijian.commonlib.mvp.base

import android.util.SparseArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView

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
