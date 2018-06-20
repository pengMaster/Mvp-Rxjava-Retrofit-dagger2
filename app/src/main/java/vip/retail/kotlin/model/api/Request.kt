package vip.retail.kotlin.model.api

import android.util.Log
import com.zhy.http.okhttp.OkHttpUtils

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/06/20
 *     desc   : 网络请求
 *     version: 1.0
 * </pre>
 */
 class Request(val url: String) {

     fun run() {

        val forecastJsonStr = OkHttpUtils.post().url(url)
                .addParams("","")
                .build().execute().body().string()

        Log.d(javaClass.simpleName, forecastJsonStr)

    }
}