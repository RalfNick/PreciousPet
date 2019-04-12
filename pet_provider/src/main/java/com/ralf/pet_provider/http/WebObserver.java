package com.ralf.pet_provider.http;

import com.jess.arms.event.transmit.EventPublicApi;
import com.jess.arms.event.transmit.EventPublicApiHelper;
import com.orhanobut.logger.Logger;
import com.ralf.pet_provider.router.RouterConfig;

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
            if (data == null) {
                Logger.e("the data is null!");
            } else {
                if (t.getCode() == HttpCode.CODE_SUCCESS) {
                    onSuccess(data);
                } else if (t.getCode() == HttpCode.CODE_SIGN_OUT) {
                    // 账号在其他地方登录，需要登出，重新登录
                    EventPublicApi.LogoutApi logoutApi = EventPublicApiHelper.getEventModuleApi(EventPublicApi.LogoutApi.class);
                    logoutApi.jumpToLoginPage(RouterConfig.LoginRegisterModule.ENTRANCE_PATH,
                            RouterConfig.LoginRegisterModule.KEY_LOGOUT, RouterConfig.LoginRegisterModule.VALUE_LOGOUT);
                } else {
                    String message = t.getMessage();
                    onFailed(message);
                }
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
