package com.yijian.staff

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        seek_bar2.setProgress(39f)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        bt.setOnClickListener(this)
        seek_bar2.setUserSeekAble(false)
    }



}
