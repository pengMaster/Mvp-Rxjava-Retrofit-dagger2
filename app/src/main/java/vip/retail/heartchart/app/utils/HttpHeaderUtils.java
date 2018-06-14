package vip.retail.heartchart.app.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;



import java.util.HashMap;
import java.util.Map;

import vip.retail.heartchart.app.AppLifecyclesImpl;


public class HttpHeaderUtils {
    // 接口入口中的分支方法
    public final static String DOWNLOAD_PRO_RANDOM_EXDOC = "DOWNLOAD_PRO_RANDOM_EXDOC";

    private static HttpHeaderUtils mInstance;

    // 传header参数
    private static final String IMEI = "imei";
    private static final String VERSION_CODE = "versionCode";
    private static final String VERSION_NAME = "versionName";
    private static final String SYSTEM_MODEL = "systemModel";
    private static final String SYSTEM_VERSION = "systemVersion";
    private static final String METHOD = "method";

    private static final int IMEI_INT = 0;
    private static final int VERSION_CODE_INT = 1;
    private static final int VERSION_NAME_INT = 2;
    private static final int SYSTEM_MODEL_INT = 3;
    private static final int SYSTEM_VERSION_INT = 4;
    private static final int METHOD_INT = 5;

    /**
     * header 传参方法
     *
     * @return map
     */
    public Map<String, String> addHeaders() {
//		Map<String, String> map = new HashMap<>();
//		map.put(IMEI, getMD5Str(getDeviceId(AppLifecyclesImpl.mAppContext)));
//		// BaseApplication.getApplication() 自己实体类中获取上下文的方法
//		map.put(VERSION_CODE, getVersionCode(AppLifecyclesImpl.mAppContext));// 软件内部版本号
//		map.put(VERSION_NAME, getVersionName(AppLifecyclesImpl.mAppContext));// 软件外部版本号
//		map.put(SYSTEM_MODEL, getSystemModel());// 手机型号
//		map.put(SYSTEM_VERSION, HttpHeaderUtils.getSystemVersion());// 系统版本
//		map.put(METHOD, "");// 要访问的方法
        return getHeaderMap("");
    }

    /**
     * header 传参方法
     *
     * @return map
     */
    public Map<String, String> addHeaders(String method) {
//		Map<String, String> map = new HashMap<>();
//		map.put(IMEI, getMD5Str(getDeviceId(AppLifecyclesImpl.mAppContext)));
//		// BaseApplication.getApplication() 自己实体类中获取上下文的方法
//		map.put(VERSION_CODE, getVersionCode(AppLifecyclesImpl.mAppContext));// 软件内部版本号
//		map.put(VERSION_NAME, getVersionName(AppLifecyclesImpl.mAppContext));// 软件外部版本号
//		map.put(SYSTEM_MODEL, getSystemModel());// 手机型号
//		map.put(SYSTEM_VERSION, HttpHeaderUtils.getSystemVersion());// 系统版本
//		map.put(METHOD, method);// 要访问的方法
        return getHeaderMap(method);
    }

//	/**
//	 * header 传参方法
//	 *
//	 * @return HttpPost
//	 */
//	public HttpPost addHeaders(HttpPost request, String method) {
//
//		request.addHeader(IMEI, getMD5Str(getDeviceId(BaseApplication.getApplication())));
//		request.addHeader(VERSION_CODE,
//				getVersionCode(BaseApplication.getApplication()));// 软件内部版本号
//		request.addHeader(VERSION_NAME,
//				getVersionName(BaseApplication.getApplication()));// 软件外部版本号
//		request.addHeader(SYSTEM_MODEL, getSystemModel());// 手机型号
//		request.addHeader(SYSTEM_VERSION, getSystemVersion());// 系统版本
//		request.addHeader(METHOD, method);// 要访问的方法
//		return request;
//	}

    public static HttpHeaderUtils i() {
        if (mInstance == null) {
            mInstance = new HttpHeaderUtils();
            initHeaderMap();
        }
        return mInstance;
    }

    private static Map<String, String> mHeaderMap = new HashMap<>();
    private static String[] mHeaderStrArray = {};

    private static void initHeaderMap() {
        mHeaderMap = new HashMap<>();
        mHeaderMap.put(IMEI, Md5Util.getMD5Str(getDeviceId(AppLifecyclesImpl.mAppContext)));
        // BaseApplication.getApplication() 自己实体类中获取上下文的方法
        mHeaderMap.put(VERSION_CODE, getVersionCode(AppLifecyclesImpl.mAppContext));// 软件内部版本号
        mHeaderMap.put(VERSION_NAME, getVersionName(AppLifecyclesImpl.mAppContext));// 软件外部版本号
        mHeaderMap.put(SYSTEM_MODEL, getSystemModel());// 手机型号
        mHeaderMap.put(SYSTEM_VERSION, HttpHeaderUtils.getSystemVersion());// 系统版本
        mHeaderStrArray = new String[]{
                IMEI + ":" + mHeaderMap.get(IMEI)
                , VERSION_CODE + ":" + mHeaderMap.get(VERSION_CODE)
                , VERSION_NAME + ":" + mHeaderMap.get(VERSION_NAME)
                , SYSTEM_MODEL + ":" + mHeaderMap.get(SYSTEM_MODEL)
                , SYSTEM_VERSION + ":" + mHeaderMap.get(SYSTEM_VERSION)
                , ""
        };
    }

    public Map<String, String> getHeaderMap(String method) {
        if (mHeaderMap.get(METHOD) == null) {
            mHeaderMap.put(METHOD, method);// 要访问的方法
        } else {
            mHeaderMap.remove(METHOD);
            mHeaderMap.put(METHOD, method);// 要访问的方法
        }
        return mHeaderMap;
    }

    public static String[] getHeaderStrArray(String method) {
        mHeaderStrArray[METHOD_INT] = METHOD + ":" + method;
        return mHeaderStrArray;
    }

    public static String getRetorfitHead(int key) {
        return mHeaderStrArray[key];
    }

    public static String getHeadMethod(String method) {
        return METHOD + ":" + method;
    }

    public static String getDeviceId(Context ctx) {
        final TelephonyManager tm = (TelephonyManager) ctx
                .getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        System.out.println("deviceId=" + deviceId);
        if (deviceId == null || deviceId.length() == 0)
            deviceId = Secure.getString(ctx.getContentResolver(), Secure.ANDROID_ID);
        return deviceId;
    }

    public static String getVersionCode(Context context) {
        String versionCode = "";
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            versionCode = String.valueOf(info.versionCode);
        } catch (NameNotFoundException e) {
            Log.e("---versionCode---", e.getMessage());
        }
        Log.d("---versionCode---", versionCode);
        return versionCode;
    }

    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (NameNotFoundException e) {
            Log.e("---versionName---", e.getMessage());
        }
        return versionName;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

}
