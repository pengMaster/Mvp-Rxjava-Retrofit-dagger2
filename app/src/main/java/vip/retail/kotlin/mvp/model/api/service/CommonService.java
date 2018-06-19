package vip.retail.kotlin.mvp.model.api.service;

import com.squareup.okhttp.ResponseBody;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * 存放通用的一些API
 * Created by jess on 8/5/16 12:05
 * contact with jess.yan.effort@gmail.com
 */
public interface CommonService {
    //下载视频文件
    @GET
    Observable<ResponseBody> downloadVideoNet(@Url String fileUrl);
}
