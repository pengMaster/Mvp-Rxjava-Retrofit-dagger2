package vip.retail.heartchart.app.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import vip.retail.heartchart.app.AppLifecyclesImpl;

/**
 * 吐司类
 *
 * @author Wp
 */
public class ToastUtils {

    private static Toast toast;
    private static Toast toast2;

    private static void initToast(final CharSequence message, final int duration) {
        if (ThreadUtils.isRunInMainThread()) {
            showToastSafe(message, duration);
        } else {
            ThreadUtils.postMain(new Runnable() {
                @Override
                public void run() {
                    showToastSafe(message, duration);
                }
            });
        }
    }


    public static void showTop(final String title) {
        try {
            if (ThreadUtils.isRunInMainThread()) {
                show(title, "", 1000, Gravity.TOP);
            } else {
                ThreadUtils.postMain(new Runnable() {
                    @Override
                    public void run() {
                        show(title, "", 1000, Gravity.TOP);
                    }
                });
            }
        } catch (Throwable e) {
            e.printStackTrace();
            showShort(title);
        }
    }




    /**
     * 任意线程里都可以使用
     *
     * @param message  内容
     * @param duration 时间
     */
    @SuppressLint("SetTextI18n")
    private static void showToastSafe(CharSequence message, int duration) {
        if (toast == null) {
            toast = Toast.makeText(AppLifecyclesImpl.mAppContext, message, duration);
//            View view = ArmsUtils.inflate(AppLifecyclesImpl.mAppContext, R.layout.layout_toast_top);
//            TextView tv = view.findViewById(R.id.toast_title_tv);
//            tv.setText(message + "");
//            toast.setView(view);
        } else {
//            View view = toast.getView();
//            if (view != null) {
//                ((TextView) toast.getView().findViewById(R.id.toast_title_tv)).setText(message + "");
//            } else {
                toast.setText(message);
//            }
            toast.setDuration(duration);
        }

//        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    /**
     * 短时间显示Toast
     */
    public static void showShort(CharSequence message) {
        initToast(message, Toast.LENGTH_SHORT);
    }


    /**
     * 短时间显示Toast
     */
    public static void showShort(int strResId) {
//		Toast.makeText(context, strResId, Toast.LENGTH_SHORT).show();
        initToast(AppLifecyclesImpl.mAppContext.getResources().getText(strResId), Toast.LENGTH_SHORT);
    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(CharSequence message) {
        initToast(message, Toast.LENGTH_LONG);
    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(int strResId) {
        initToast(AppLifecyclesImpl.mAppContext.getResources().getText(strResId), Toast.LENGTH_LONG);
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(CharSequence message, int duration) {
        initToast(message, duration);
    }

    public static void show(String title, CharSequence message, int i, int top) {
        initToast(message, Toast.LENGTH_SHORT);
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(Context context, int strResId, int duration) {
        initToast(context.getResources().getText(strResId), duration);
    }

    /**
     * 显示有image的toast
     *
     * @param tvStr         内容
     * @param imageResource 图片id
     * @return Toast
     */
//    public static Toast showToastWithImg(final String tvStr, final int imageResource) {
//        if (toast2 == null) {
//            toast2 = new Toast(AppLifecyclesImpl.mAppContext);
//        }
//        View view = ArmsUtils.inflate(AppLifecyclesImpl.mAppContext,R.layout.layout_toast);
//        TextView tv = (TextView) view.findViewById(R.id.toast_custom_tv);
//        tv.setText(TextUtils.isEmpty(tvStr) ? "" : tvStr);
//        ImageView iv = (ImageView) view.findViewById(R.id.toast_custom_iv);
//        if (imageResource > 0) {
//            iv.setVisibility(View.VISIBLE);
//            iv.setImageResource(imageResource);
//        } else {
//            iv.setVisibility(View.GONE);
//        }
//        toast2.setView(view);
//        toast2.setGravity(Gravity.CENTER, 0, 0);
//        toast2.show();
//        return toast2;
//    }
}