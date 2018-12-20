package com.jess.arms.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.jess.arms.mvp.IPresenter;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name BaseLazyFragment
 * @email -
 * @date 2018/12/19 上午11:17
 **/
public abstract class BaseLazyFragment<P extends IPresenter> extends BaseFragment<P> {

    /**
     * 是否 UI 准备好 和 是否加载了数据
     */
    private boolean mIsPrepared = false;
    private boolean mIsLoaded = false;

    /**
     * 网络请求等延迟操作在此函数中进行,需要进行懒加载
     */
    protected abstract void loadLargeData();

    /**
     * 少量数据，初始化时就加载，如 View 的初始化，点击事件设置等，接口中 iniView 仅仅用来返回布局
     */
    protected abstract void loadSmallData();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsPrepared = true;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        loadSmallData();
        loadLargeData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoad();
        }
    }

    /**
     * 懒加载数据
     */
    protected void lazyLoad() {
        if (getUserVisibleHint() && mIsPrepared && !mIsLoaded) {
            //异步初始化，在初始化后显示正常UI
            loadLargeData();
        }
    }

    /**
     * 设置UI是否准备后
     *
     * @param prepared true or false
     */
    protected void setPrepared(boolean prepared) {
        mIsPrepared = prepared;
    }

    /**
     * 设置数据是否已经加载，loadLargeData() 异步加载之后需要设置为 true
     *
     * @param isLoaded true or false
     */
    protected void setDataLoaded(boolean isLoaded) {
        mIsLoaded = isLoaded;
    }
}
