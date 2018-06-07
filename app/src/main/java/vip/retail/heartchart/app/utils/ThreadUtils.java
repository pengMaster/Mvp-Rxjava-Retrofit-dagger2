package vip.retail.heartchart.app.utils;


import vip.retail.heartchart.app.AppLifecyclesImpl;


public class ThreadUtils {

    //判断当前的线程是不是在主线程
    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    public static void runInMainThread(Runnable runnable) {
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            postMain(runnable);
        }
    }
    public static void runThread(Runnable runnable) {
        if (isRunInMainThread()) {
            new Thread(runnable).start();
        } else {
            runnable.run();
        }
    }
    public static long getMainThreadId() {
        return AppLifecyclesImpl.mMainThreadId;
    }

    public static boolean postMain(Runnable runnable) {
        return AppLifecyclesImpl.mMainThreadHandler.post(runnable);
    }

    public static boolean postMain(Runnable runnable, long time) {
        return AppLifecyclesImpl.mMainThreadHandler.postDelayed(runnable, time);
    }

}