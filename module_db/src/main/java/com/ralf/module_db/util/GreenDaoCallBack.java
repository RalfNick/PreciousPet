package com.ralf.module_db.util;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * <p>
 * greenDao 异步操作回调
 * @name GreenDaoCallBack
 * @email wanglixin@icourt.cc
 * @date 2018/08/08 上午11:17
 **/
public interface GreenDaoCallBack<T> {

    void onSuccess(List<T> result);

    void onFailed();

    void onNotification(boolean result);
}
