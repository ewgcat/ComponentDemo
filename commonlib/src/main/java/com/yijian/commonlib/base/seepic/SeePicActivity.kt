package com.yijian.commonlib.base.seepic

import android.os.Bundle
import android.view.View
import com.SuperKotlin.pictureviewer.PhotoView

import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.yijian.commonlib.R
import com.yijian.commonlib.base.mvc.MvcBaseActivity
import com.yijian.commonlib.constant.Constant
import com.yijian.commonlib.widget.NavigationBar





class SeePicActivity : MvcBaseActivity() {


    override fun getLayoutID(): Int {
        return R.layout.activity_see_pic
    }

    override fun initView(savedInstanceState: Bundle?) {
        val path = intent.getStringExtra(Constant.KEY_SEE_PIC_PATH)

        val navigationBar = findViewById<View>(R.id.navigationBar) as NavigationBar
        navigationBar.setTitle("查看图片")
        navigationBar.hideLeftSecondIv()
        navigationBar.setBackClickListener(this)

        val photoView = findViewById<PhotoView>(R.id.photo_view)
        val options = RequestOptions()
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.placeholder)
                .priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        Glide.with(this@SeePicActivity).load(path).apply(options).into(photoView)

    }


}
