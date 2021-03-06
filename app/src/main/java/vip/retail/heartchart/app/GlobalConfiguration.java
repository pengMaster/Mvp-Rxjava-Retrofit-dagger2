package vip.retail.heartchart.app;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.di.module.GlobalConfigModule;
import com.jess.arms.integration.ConfigModule;

import java.util.List;

import vip.retail.heartchart.mvp.model.api.Api;

import static com.jess.arms.utils.PermissionUtil.TAG;


/**
 * application，BaseActivity,BaseFragment 总体配置
 *
 * @author Wp
 */
public class GlobalConfiguration implements ConfigModule {

    /**
     * 使用builder可以为框架配置一些配置信息
     */
    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {
        builder.baseurl(Api.baseUrl).responseErrorListener(new ErrorListenerImpl());
    }

    /**
     * 向Application的生命周期中注入一些自定义逻辑
     */
    @Override
    public void injectAppLifecycle(Context context, List<AppLifecycles> lifecycles) {
        lifecycles.add(new AppLifecyclesImpl());

    }

    /**
     * 向Activity的生命周期中注入一些自定义逻辑
     */
    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {
        lifecycles.add(new ActLifecycleCallbacksImpl());
    }

    /**
     * 向Fragment的生命周期中注入一些自定义逻辑
     */
    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {
        lifecycles.add(new FragLifecycleCallbacksImpl());
    }


}
