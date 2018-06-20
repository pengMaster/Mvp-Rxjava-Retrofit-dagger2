package vip.retail.kotlin.domain

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/06/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface Command<T> {
    fun execute(): T
}