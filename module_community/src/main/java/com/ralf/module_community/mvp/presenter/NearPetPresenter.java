package com.ralf.module_community.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.ralf.module_community.mvp.contract.NearPetContract;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FriendPraisePresenter
 * @email -
 * @date 2019/04/15 上午11:40
 **/
@ActivityScope
public class NearPetPresenter extends BasePresenter<NearPetContract.Model, NearPetContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public NearPetPresenter(NearPetContract.Model model, NearPetContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void addDispose(Disposable disposable) {
        super.addDispose(disposable);
    }
}
