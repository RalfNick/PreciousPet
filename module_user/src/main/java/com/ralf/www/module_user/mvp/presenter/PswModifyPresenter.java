package com.ralf.www.module_user.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RegexUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.ralf.www.module_user.entity.VertifyCodeEntity;
import com.ralf.www.module_user.mvp.contact.PswModifyContact;
import com.ralf.www.module_user.util.CountDownUtil;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PswModifyPresenter
 * @email -
 * @date 2018/12/10 下午2:00
 **/
@ActivityScope
public class PswModifyPresenter extends BasePresenter<PswModifyContact.Model, PswModifyContact.View> {

    private static final String MSG_TO_USER = "验证码已发送到手机";
    private static final long TOTAL_TIME_SECOND = 60;

    @Inject
    RxErrorHandler mErrorHandler;

    /**
     * 手机，密码，验证码
     */
    private String mPhone;
    private String mPassWord;
    private String mVertifyCode;

    @Inject
    public PswModifyPresenter(PswModifyContact.Model model, PswModifyContact.View rootView) {
        super(model, rootView);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }

    /**
     * 检查三个文本框
     *
     * @param phoneObserver    手机号码
     * @param passwordObserver 密码
     * @param codeObservable   验证码
     */
    @SuppressWarnings("unchecked")
    public void setThreeEditTextObserver(Observable phoneObserver,
                                         Observable passwordObserver,
                                         Observable codeObservable) {


        Observable.combineLatest(phoneObserver, passwordObserver, codeObservable, (phone, password, validCode) -> {

            mPhone = phone.toString();
            mPassWord = password.toString();
            mVertifyCode = validCode.toString();

            return RegexUtils.isMobileExact(phone.toString())
                    && password.toString().length() >= 6
                    && validCode.toString().length() >= 3;
        })
                .skip(2)
                .distinctUntilChanged().subscribe(new DefaultObserver<Boolean>() {
            @Override
            public void onNext(Boolean result) {
                mRootView.enableSaveBtn(result);
            }

            @Override
            public void onError(Throwable e) {
                mRootView.enableSaveBtn(false);
            }

            @Override
            public void onComplete() {
            }
        });
    }

    /**
     * 检查三个文本框
     *
     * @param phoneObserver    手机号码
     * @param passwordObserver 密码
     */
    @SuppressWarnings("unchecked")
    public void setTwoEditTextObserver(Observable phoneObserver,
                                       Observable passwordObserver) {

        Observable.combineLatest(phoneObserver, passwordObserver, (phone, password) -> {

            mPhone = phone.toString();
            mPassWord = password.toString();
            return RegexUtils.isMobileExact(phone.toString())
                    && password.toString().length() >= 6;
        })
                .skip(1)
                .distinctUntilChanged()
                .subscribe(new DefaultObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean result) {
                        mRootView.enableSendCodeBtn(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRootView.enableSendCodeBtn(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 发送验证码
     */
    public void sendVertifyCode() {

        mModel.getVertifyCode(mPhone, "-1", 1)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(1, 2))
                .doOnSubscribe(disposable -> mRootView.showLoading())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.hideLoading())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<VertifyCodeEntity>(mErrorHandler) {
                    @Override
                    public void onNext(VertifyCodeEntity vertifyCodeEntity) {
                        if (vertifyCodeEntity != null && vertifyCodeEntity.getCode() == 0) {
                            mRootView.showMessage(MSG_TO_USER);
                            mRootView.enableSendCodeBtn(false);
                            startCount();
                        } else {
                            mRootView.showMessage("发送验证码失败！");
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        mRootView.showMessage("发送验证码失败！");
                    }
                });
    }

    /**
     * 倒计时
     */
    private void startCount() {

        Disposable disposable = CountDownUtil.startCountDown(TOTAL_TIME_SECOND, new CountDownUtil.CountCallBack() {
            @Override
            public void onNext(long leftTime) {
                mRootView.updateSendCodeBtn(String.valueOf(leftTime));
            }

            @Override
            public void onFinish() {
                mRootView.checkEditTexts();
            }
        });
        addDispose(disposable);
    }

    /**
     * 修改密码成功
     */
    public void saveUserPassWord() {

        mModel.requestModifyPsw(mPhone, mPassWord, mVertifyCode)
                .doOnSubscribe(disposable -> mRootView.showLoading())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> mRootView.hideLoading())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<VertifyCodeEntity>(mErrorHandler) {
                    @Override
                    public void onNext(VertifyCodeEntity vertifyCodeEntity) {
                        mRootView.showMessage("" + vertifyCodeEntity.getCode());
                    }
                });
    }
}