package com.ralf.www.pet_provider.http;

import com.orhanobut.logger.Logger;
import com.umeng.socialize.UMAuthListener;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * @author Ralf(wanglixin)
 * <p>
 * 网络请求要求返回的格式：必须使用 BaseEntity，否则不要使用
 * @name WebObserver
 * @email -
 * @date 2018/12/12 上午11:03
 **/
public abstract class WebObserver<E> extends ErrorHandleSubscriber<BaseEntity<E>> {

    public WebObserver(RxErrorHandler rxErrorHandler) {
        super(rxErrorHandler);
    }

    protected abstract void onSuccess(E data);

    protected abstract void onFailed(String failMsg);

    @Override
    public void onSubscribe(Disposable d) {
        super.onSubscribe(d);
    }

    @Override
    public void onNext(BaseEntity<E> t) {
        if (t == null) {
            Logger.e("the result is null!");
            onFailed("the result is null!");
        } else {
            E data = t.getData();
            if (data != null && t.getCode() == 0) {
                onSuccess(data);
            } else {
                String message = t.getMessage();
                onFailed(message);
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
    }

    @Override
    public void onComplete() {
        super.onComplete();
    }
}
