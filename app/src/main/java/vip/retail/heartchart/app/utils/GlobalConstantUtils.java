package vip.retail.heartchart.app.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class GlobalConstantUtils {

    public static String loginDate;//登录时间
    public static String URL; //登录URL
    public static String LOGINURL; //登录URL
    public static String WEBURL; //查看附件
    private static String token;

    //存放全局的 用户对象信息

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        GlobalConstantUtils.token = token;
    }

    public static void setLoginDate(String loginDate) {
        GlobalConstantUtils.loginDate = loginDate;
    }

    /**
     * 关闭输入法
     */
    public void hideSoftInput(EditText et, Context context) {
        InputMethodManager mInputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }
}
