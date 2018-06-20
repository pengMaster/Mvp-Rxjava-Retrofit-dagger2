package vip.retail.kotlin.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import butterknife.internal.Utils.listOf
import vip.retail.heartchart.R
import kotlinx.android.synthetic.main.kotlin_activity_main.*
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import vip.retail.kotlin.adapter.ForecastListAdapter
import vip.retail.kotlin.model.api.ForecastRequest
import java.util.*

@SuppressLint("Registered")
/**
 *  主页
 */
class MainActivity : Activity(){

    private val items = listOf(

            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )

    private val map = HashMap<String,String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_activity_main)
        initView()
        netWork()
    }

    private fun initView(){

        //扩展属性 - 相当于findViewById
        mForecastList.layoutManager = LinearLayoutManager(this)
        mForecastList.adapter = ForecastListAdapter(items)

        //支持键值对遍历
        map["1"] = "value1"
        map["2"] = "value2"
        map["3"] = "value4"
        for ((key,value) in map) {
            Log.d("map", "key:$key, value:$value")
        }

    }



    private fun netWork() {

        async {

            var result = ForecastRequest("94043").execute()

            uiThread{
                toast("下载完成")
            }
        }
    }

}