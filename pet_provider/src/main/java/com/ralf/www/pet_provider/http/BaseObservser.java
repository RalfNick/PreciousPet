package com.ralf.www.pet_provider.http;

import com.orhanobut.logger.Logger;

import io.reactivex.observers.DefaultObserver;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name BaseObservser
 * @email -
 * @date 2018/12/12 上午11:03
 **/
public abstract class BaseObservser<T> extends DefaultObserver<T> {

    protected abstract void onSuccess(T t);

    protected abstract void onFailed(T t);

    @Override
    public void onNext(T t) {
        if (t == null) {
            Logger.e("the result is null!");
            onFailed(t);
        } else {
            onSuccess(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        Logger.e(e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
