package vip.retail.kotlin.activity

import android.app.Activity
import android.os.Bundle
import vip.retail.heartchart.R

/**
 *  主页
 */
class MainActivity : Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.abc_action_bar_view_list_nav_layout)
        initView()
    }

    private fun initView() {

    }


}