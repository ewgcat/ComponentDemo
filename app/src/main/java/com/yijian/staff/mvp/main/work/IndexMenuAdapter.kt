package com.yijian.staff.mvp.main.work

import android.arch.lifecycle.Lifecycle
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import com.alibaba.android.arouter.launcher.ARouter
import com.yijian.staff.R
import com.yijian.staff.bean.IndexDataInfo
import com.yijian.staff.jpush.ClearRedPointUtil
import com.yijian.staff.mvp.permission.PermissionUtils
import com.yijian.staff.prefs.SharePreferenceUtil
import com.yijian.staff.util.ImageLoader

import java.util.ArrayList

/**
 * author：李帅华
 * email：850716183@qq.com
 * time: 2018/6/25 17:31:31
 */
class IndexMenuAdapter(private val lifecycle: Lifecycle, private val context: Context?, menuModelList: List<IndexDataInfo.MenuModelListBean.SubMeneModelListBean>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var menuModelList = ArrayList<IndexDataInfo.MenuModelListBean.SubMeneModelListBean>()


    init {
        this.menuModelList = menuModelList as ArrayList<IndexDataInfo.MenuModelListBean.SubMeneModelListBean>
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.index_menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val menuModel = menuModelList[position]
        (holder as IndexMenuAdapter.ViewHolder).bind(context, menuModel)
    }

    override fun getItemCount(): Int {
        return menuModelList.size
    }

    private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val menu_icon: ImageView
        private val menu_title: TextView
        private val view_notice: View

        private val item_grid: LinearLayout

        init {
            menu_title = itemView.findViewById(R.id.menu_title)
            menu_icon = itemView.findViewById(R.id.menu_icon)
            view_notice = itemView.findViewById(R.id.view_notice)
            item_grid = itemView.findViewById(R.id.item_grid)

        }

        fun bind(context: Context?, menuModel: IndexDataInfo.MenuModelListBean.SubMeneModelListBean) {
            ImageLoader.setImageResource(SharePreferenceUtil.getImageUrl() + menuModel.icon, context, menu_icon)
            menu_title.text = menuModel.title
            item_grid.setOnClickListener {
                if (menuModel.count > 0) {
                    menuModel.count = 0
                    view_notice.visibility = View.INVISIBLE
                    ClearRedPointUtil.clearYueKeNotice(lifecycle)
                }

                val path = menuModel.path
                if (TextUtils.isEmpty(path)) {
                    Toast.makeText(context, "此版本不能提供该服务,请更新最新版本！", Toast.LENGTH_SHORT).show()
                } else {
                    PermissionUtils.getInstance().setMenuKey(menuModel.menuKey)
                    ARouter.getInstance().build(path).navigation()
                }
            }

            if (menuModel.count > 0) {
                view_notice.visibility = View.VISIBLE
            } else {
                view_notice.visibility = View.INVISIBLE
            }
        }

    }
}
