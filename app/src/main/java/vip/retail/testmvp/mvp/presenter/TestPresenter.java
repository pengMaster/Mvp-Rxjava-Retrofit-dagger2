package vip.retail.testmvp.mvp.presenter;

import vip.retail.testmvp.mvp.base.BasePresenter;
import vip.retail.testmvp.mvp.base.IBaseView;

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/07/03
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class TestPresenter extends BasePresenter<IBaseView> {

    private void getData() {

        getView().hideLoading();
    }
}
