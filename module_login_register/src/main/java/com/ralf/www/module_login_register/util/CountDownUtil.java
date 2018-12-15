package com.ralf.www.module_login_register.util;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * @author Ralf(wanglixin)
 * 倒计时
 * @name CountDownUtil
 * @email -
 * @date 2018/12/11 上午10:54
 **/
public class CountDownUtil {


    public static Disposable startCountDown(long count, final CountCallBack callBack) {
        return startCountDown(0, count, 0, 1, TimeUnit.SECONDS, callBack);
    }

    public static Disposable startCountDown(long start, long count, long initialDelay,
                                            long period, TimeUnit unit, final CountCallBack callBack) {

        return Flowable.intervalRange(start, count, initialDelay, period, unit)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(aLong -> callBack.onNext(count - aLong))
                .doOnComplete(callBack::onFinish)
                .subscribe();
    }


    /**
     * 倒计时接口
     */
    public interface CountCallBack {

        void onNext(long leftTime);

        void onFinish();
    }
}
