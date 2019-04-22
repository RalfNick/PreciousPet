package com.ralf.module_community.mvp.presenter;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.ralf.module_community.mvp.contract.ChannelContract;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendPresenter
 * @email -
 * @date 2019/04/13 上午10:30
 **/
@FragmentScope
public class ChannelPresenter extends BasePresenter<ChannelContract.Model, ChannelContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    private int mTotalPage;
    private int mCurrentPage = 1;

    @Inject
    public ChannelPresenter(ChannelContract.Model model, ChannelContract.View rootView) {
        super(model, rootView);
    }
}
