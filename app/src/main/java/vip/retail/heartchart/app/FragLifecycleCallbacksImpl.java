package vip.retail.heartchart.app;

import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * BaseFragment所做的事情可在此实现
 *
 * @author Wp
 */
public class FragLifecycleCallbacksImpl extends FragmentManager.FragmentLifecycleCallbacks {

    @Override
    public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        super.onFragmentCreated(fm, f, savedInstanceState);
    }
}
