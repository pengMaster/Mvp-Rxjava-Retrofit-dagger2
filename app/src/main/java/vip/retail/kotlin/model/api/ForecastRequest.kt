package vip.retail.kotlin.model.api

import com.google.gson.Gson
import com.zhy.http.okhttp.OkHttpUtils
import vip.retail.kotlin.model.bean.ForecastResult

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/06/20
 *     desc   : 天气请求类
 *     version: 1.0
 * </pre>
 */
public class ForecastRequest(val zipCode: String) {
    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/" +
        "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }
    fun execute(): ForecastResult {

        val forecastJsonStr = OkHttpUtils.post()
                .url(COMPLETE_URL + zipCode)
                .addParams("","")
                .build().execute().body().string()

        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}