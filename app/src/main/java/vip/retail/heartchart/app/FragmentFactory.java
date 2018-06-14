package vip.retail.heartchart.app;


import android.util.SparseArray;

import com.jess.arms.base.BaseFragment;

public class FragmentFactory {

    /**
     * 监督记录-基本信息
     */
    public static final int FRAG_RECORD_BASEINFO = 1;

    /**
     * 监督记录-文书列表
     */
    public static final int FRAG_RECORD_LAWFILE = 2;

    /**
     * 监督记录-媒体信息
     */
    public static final int FRAG_RECORD_MEDIA = 3;


    /**
     * 记录所有的fragment，防止重复创建
     */
    private static SparseArray<BaseFragment> mFragmentMap = new SparseArray<>();

    /**
     * 采用工厂类进行创建Fragment，便于扩展，已经创建的Fragment不再创建
     */
    public static BaseFragment createFragment(int index) {
        BaseFragment fragment = mFragmentMap.get(index);

        if (fragment == null) {
            switch (index) {

                default:
            }
            mFragmentMap.put(index, fragment);
        }
        return fragment;
    }

    public static void removeFragment(int index) {
        if (mFragmentMap.get(index) != null) {
            mFragmentMap.remove(index);
        }
    }
}
