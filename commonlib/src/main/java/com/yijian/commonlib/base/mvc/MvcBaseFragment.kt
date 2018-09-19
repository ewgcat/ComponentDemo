package com.yijian.commonlib.base.mvc

import android.app.Activity
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.yijian.commonlib.widget.LoadingDialog


/**
 * 无MVP的Fragment基类
 */

abstract class MvcBaseFragment : Fragment() {

    protected var mContext: Activity? = null
    protected var rootView: View? = null



    protected var loadingDialog: LoadingDialog? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), null)
            mContext = activity

        }else{
            val parent = rootView?.parent as ViewGroup?
            parent?.removeView(rootView)
        }



        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /**
     * 加载布局
     */
    @LayoutRes
    abstract fun getLayoutId():Int

    /**
     * 初始化 ViewI
     */
    abstract fun initView()


    fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(mContext)
        }
        if (loadingDialog != null) {
            //防止弹出之前activity已经被销毁了
            if (!mContext!!.isFinishing) {
                loadingDialog!!.show()
                loadingDialog!!.setCancelable(false)
            }
        }

    }

    fun hideLoading() {
        if (loadingDialog != null && loadingDialog!!.isShowing) {
            //防止显示期间activity已经被销毁了
            if (!mContext!!.isFinishing) {
                loadingDialog!!.dismiss()
                loadingDialog = null
            }
        }
    }

    fun showToast(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : View> findView(viewId: Int): T {
        return rootView!!.findViewById<View>(viewId) as T
    }



}
