package com.ralf.pet_provider.chat;

/**
 * @author Ralf(wanglixin)
 * 登录环信的回调接口
 * @name ChatCallBack
 * @email -
 * @date 2018/12/15 下午12:42
 **/
public interface ChatCallBack {

    void onSuccess();

    void onFailed(String failMsg);
}
