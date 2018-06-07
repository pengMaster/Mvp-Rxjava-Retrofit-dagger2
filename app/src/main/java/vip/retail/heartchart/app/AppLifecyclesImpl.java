/**
 * Copyright 2017 JessYan
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vip.retail.heartchart.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

//import com.hss01248.dialog.StyledDialog;
import com.jess.arms.base.BaseApplication;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.integration.AppManager;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DeviceUtils;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * ================================================
 * 展示 {@link AppLifecycles} 的用法
 * <p>
 * Created by JessYan on 04/09/2017 17:12
 * Contact with <mailto:jess.yan.effort@gmail.com>
 * Follow me on <https://github.com/JessYanCoding>
 * ================================================
 */
public class AppLifecyclesImpl implements AppLifecycles {

    public static Context mAppContext;
    public static float phoneWidth;
    public static float phoneHeight;
    /**
     * 主线程Handler
     */
    public static Handler mMainThreadHandler;
    /**
     * 主线程ID
     */
    public static int mMainThreadId = -1;

    public static IRepositoryManager iRepositoryManager;
    public static RxErrorHandler rxErrorHandler;


    @Override
    public void attachBaseContext(Context base) {
//          MultiDex.install(base);  //这里比 onCreate 先执行,常用于 MultiDex 初始化,插件化框架的初始化
    }

    @Override
    public void onCreate(Application application) {
//        StyledDialog.init(application);//初始化对话框
        iRepositoryManager = ((BaseApplication) application).getAppComponent().repositoryManager();
        rxErrorHandler = ((BaseApplication) application).getAppComponent().rxErrorHandler();
        mAppContext = application.getApplicationContext();
        phoneWidth = DeviceUtils.getScreenWidth(mAppContext);
        phoneHeight = DeviceUtils.getScreenHeight(mAppContext);
        mMainThreadHandler = new Handler();
        mMainThreadId = android.os.Process.myTid();
//        if (BuildConfig.LOG_DEBUG) {//Timber初始化
        //Timber 是一个日志框架容器,外部使用统一的Api,内部可以动态的切换成任何日志框架(打印策略)进行日志打印
        //并且支持添加多个日志框架(打印策略),做到外部调用一次 Api,内部却可以做到同时使用多个策略
        //比如添加三个策略,一个打印日志,一个将日志保存本地,一个将日志上传服务器
//        Timber.plant(new Timber.DebugTree());
        // 如果你想将框架切换为 Logger 来打印日志,请使用下面的代码,如想切换为其他日志框架请根据下面的方式扩展
//                    Logger.addLogAdapter(new AndroidLogAdapter());
//                    Timber.plant(new Timber.DebugTree() {
//                        @Override
//                        protected void log(int priority, String tag, String message, Throwable t) {
//                            Logger.log(priority, tag, message, t);
//                        }
//                    });
//        ButterKnife.setDebug(true);
//        }


        //leakCanary内存泄露检查
//        ArmsUtils.obtainAppComponentFromContext(application).extras().put(RefWatcher.class.getName(), BuildConfig.USE_CANARY ? LeakCanary.install(application) : RefWatcher.DISABLED);
        //扩展 AppManager 的远程遥控功能
        ArmsUtils.obtainAppComponentFromContext(application).appManager().setHandleListener(new AppManager.HandleListener() {
            @Override
            public void handleMessage(AppManager appManager, Message message) {

            }
        });
        //Usage:
        //Message msg = new Message();
        //msg.what = 0;
        //AppManager.post(msg); like EventBus
    }

    @Override
    public void onTerminate(Application application) {

    }
}
