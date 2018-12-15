package com.ralf.pet_provider.base;

import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name SimpleObserver
 * @email -
 * @date 2018/12/13 上午11:23
 **/
public abstract class SimpleObserver<T> implements Observer<T> {

    protected abstract void onSuccess(T data);

    protected abstract void onFailed();

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        if (t == null) {
            Logger.e("data is null!");
            onFailed();
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
