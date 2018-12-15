package com.ralf.www.pet_provider.chat;

import android.content.Context;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.NetUtils;
import com.orhanobut.logger.Logger;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChatUtil
 * @email -
 * @date 2018/12/14 下午12:30
 **/
public class ChatUtil {

    public static void login(String userName, String passWord) {
        EMClient.getInstance().login(userName, passWord, new EMCallBack() {
            @Override
            public void onSuccess() {
                EMClient.getInstance().chatManager().loadAllConversations();
            }

            @Override
            public void onError(int i, String s) {
                Logger.e("登录聊天服务器失败！ %s" + +i + s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    public static void logout() {
        EMClient.getInstance().logout(true);
    }

    public void ConnectionListener(Context context) {
        EMClient.getInstance().addConnectionListener(new EMConnectionListener() {
            @Override
            public void onConnected() {

            }

            @Override
            public void onDisconnected(int error) {
                if (error == EMError.USER_REMOVED) {
                    // 显示帐号已经被移除
                } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                    // 显示帐号在其他设备登录
                } else {
                    if (NetUtils.hasNetwork(context)) {
                        //连接不到聊天服务器
                    } else {
                        //当前网络不可用，请检查网络设置
                    }
                }
            }
        });
    }


}
