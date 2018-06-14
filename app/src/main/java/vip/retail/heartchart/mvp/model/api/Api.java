package vip.retail.heartchart.mvp.model.api;

/**
 * Created by jess on 8/5/16 11:25
 * contact with jess.yan.effort@gmail.com
 */
public interface Api {
    /**
     * 注意: 使用Retrofit baseUrl 必须以 / 结尾
     */
    public static String baseUrl = "http://200.200.202.136:8080/nhis/";
    String APP_DOMAIN = "http://gank.io/";
    String RequestSuccess = "0";

    /**
     * --------------------------------映射接口 2017/10/18------------------------------
     * 123.124.93.36:80
     **/
    //根据监督记录ID获取相关的媒体信息接口
    public String GET_MEDIA_RECORD = "http://123.124.93.36:80/lrms/mtm/api/getDataAcquisitions";
    //鉴权接口
    public String POST_KEY = "http://123.124.93.36:80/lrms/mtm/api/getToken";
    //获取关联视频
    public String GET_RELATION_MEDIA = "http://123.124.93.36:80/lrms/mtm/api/getNoCorrelationList";
    //关联视频
    public String RELATION_MEDIA = "http://123.124.93.36:80/lrms/mtm/api/manualCorrelation";
}
