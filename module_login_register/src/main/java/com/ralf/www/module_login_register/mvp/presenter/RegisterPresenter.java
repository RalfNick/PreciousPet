package com.ralf.www.module_login_register.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RegexUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.jess.arms.utils.StringUtils;
import com.ralf.www.module_login_register.entity.VertifyCodeEntity;
import com.ralf.www.module_login_register.mvp.contact.RegisterContact;
import com.ralf.www.module_login_register.util.CountDownUtil;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RegisterPresenter
 * @email -
 * @date 2018/12/11 上午11:50
 **/
@ActivityScope
public class RegisterPresenter extends BasePresenter<RegisterContact.Model, RegisterContact.View> {

    private static final long TOTAL_TIME_SECOND = 60;

    @Inject
    RxErrorHandler mErrorHandler;

    private String mPhone;
    private String mPassWord;
    private String mVertifyCode;

    @Inject
    public RegisterPresenter(RegisterContact.Model model, RegisterContact.View rootView) {
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
     * 监听注册按钮的状态
     *
     * @param phoneObservable
     * @param pswObservable
     * @param codeObservable
     * @param checkBoxObservable
     */
    public void setRegisterBtnEvent(Observable<CharSequence> phoneObservable,
                                    Observable<CharSequence> pswObservable,
                                    Observable<CharSequence> codeObservable,
                                    Observable<Boolean> checkBoxObservable) {

        Observable.combineLatest(phoneObservable, pswObservable, codeObservable, checkBoxObservable,
                (phone, password, vertifyCode, checkBod) -> {

                    mPhone = phone.toString();
                    mPassWord = password.toString();
                    mVertifyCode = vertifyCode.toString();

                    return RegexUtils.isMobileExact(phone.toString())
                            && password.toString().length() > 5
                            && vertifyCode.toString().length() > 3 && checkBod;
                })
                .skip(3)
                .distinctUntilChanged()
                .subscribe(new DefaultObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean result) {
                        mRootView.enableRegisterbBtn(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRootView.enableRegisterbBtn(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 监听发送验证码按钮的状态
     *
     * @param phoneObservable
     * @param pswObservable
     */
    public void setSendCodeBtnEvent(Observable<CharSequence> phoneObservable,
                                    Observable<CharSequence> pswObservable) {

        Observable.combineLatest(phoneObservable, pswObservable,
                (phone, passWord) -> {
                    mPhone = phone.toString();
                    mPassWord = passWord.toString();
                    return RegexUtils.isMobileExact(phone) && passWord.toString().length() > 5;
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

    public void requestVertifyCode() {

        if (StringUtils.isEmpty(mPhone) || StringUtils.isEmpty(mPassWord)) {
            mRootView.showMessage("手机和密码不能为空！");
        } else {
            mModel.vertifyPhone(mPhone, mPassWord)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                    .subscribe(new ErrorHandleSubscriber<VertifyCodeEntity>(mErrorHandler) {
                        @Override
                        public void onNext(VertifyCodeEntity vertifyCodeEntity) {
                            if (vertifyCodeEntity != null && vertifyCodeEntity.getCode() == 0) {
                                getVertifyCode();
                            } else if (vertifyCodeEntity.getCode() == 10000) {
                                mRootView.showMessage("该手机号码已经注册！");
                            }
                        }

                        @Override
                        public void onError(Throwable t) {
                            super.onError(t);
                            mRootView.showMessage("获取验证码失败！");
                        }
                    });
        }
    }

    private void getVertifyCode() {
        if (StringUtils.isEmpty(mPhone)) {
            mRootView.showMessage("手机号码不能为空！");
        } else {
            mModel.getVertifyCode(mPhone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                    .subscribe(new DefaultObserver<VertifyCodeEntity>() {
                        @Override
                        public void onNext(VertifyCodeEntity vertifyCodeEntity) {
                            if (vertifyCodeEntity != null && vertifyCodeEntity.getCode() == 0) {
                                mRootView.showMessage("验证码已发送，请查收！");
                                startCount();
                            } else {
                                mRootView.showMessage("获取验证码失败！");
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            mRootView.showMessage("获取验证码失败！");
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    public void vertifyCode() {
        if (StringUtils.isEmpty(mPhone) || StringUtils.isEmpty(mPassWord) || StringUtils.isEmpty(mVertifyCode)) {
            mRootView.showMessage("手机号码，密码，验证码不能为空！");
        } else {
            mModel.vertifyCode(mPhone, mVertifyCode)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                    .subscribe(new DefaultObserver<VertifyCodeEntity>() {
                        @Override
                        public void onNext(VertifyCodeEntity vertifyCodeEntity) {
                            if (vertifyCodeEntity != null && vertifyCodeEntity.getCode() == 0) {
                                mRootView.jumpToUserSettingPage(mPhone, mPassWord, "注册成功！");
                            } else {
                                mRootView.showMessage("注册失败，请重试！");
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            mRootView.showMessage("注册失败，稍后请重试！");
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
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
                mRootView.checkBtnState();
            }
        });
        addDispose(disposable);
    }
}
