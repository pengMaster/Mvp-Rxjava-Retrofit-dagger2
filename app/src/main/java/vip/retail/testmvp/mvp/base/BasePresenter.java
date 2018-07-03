package vip.retail.testmvp.mvp.base;

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/07/03
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class BasePresenter<V extends IBaseView> {
    /**
     * 绑定的view
     */
    private V mvpView;

    /**
     * 绑定view，一般在初始化中调用该方法
     */

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    /**
     * 断开view，一般在onDestroy中调用
     */
    public void detachView() {
        this.mvpView = null;
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached() {
        return mvpView != null;
    }

    /**
     * 获取连接的view
     */
    public V getView() {
        return mvpView;
    }
}
