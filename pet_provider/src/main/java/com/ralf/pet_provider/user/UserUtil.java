package com.ralf.pet_provider.user;

import com.jess.arms.utils.SpUtil;
import com.jess.arms.utils.StringUtils;
import com.orhanobut.logger.Logger;
import com.ralf.pet_provider.chat.ChatCallBack;
import com.ralf.pet_provider.chat.ChatUtil;
import com.ralf.pet_provider.user.constant.UserConstant;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name UserUtil
 * @email -
 * @date 2018/12/07 上午10:22
 **/
public class UserUtil {

    public static String getUserToken() {
        return SpUtil.getInstance().getString(UserConstant.USER_TOKEN);
    }

    public static int getUserId() {
        return SpUtil.getInstance().getInt(UserConstant.USER_ID, -1);
    }

    public static String getUserName() {
        return SpUtil.getInstance().getString(UserConstant.USER_NAME);
    }

    public static String getHxName() {
        return SpUtil.getInstance().getString(UserConstant.HX_USER_NAME);
    }

    public static boolean isUserLogin() {
        String token = SpUtil.getInstance().getString(UserConstant.USER_TOKEN);
        return !StringUtils.isEmpty(token);
    }

    public static void removeToken() {
        SpUtil.getInstance().remove(UserConstant.USER_TOKEN,true);
    }

    /**
     * 登录环信
     */
    public static void loginHx() {

        String hxUserName = getHxName();
        if (StringUtils.isEmpty(hxUserName)) {
            return;
        }
        ChatUtil.login(hxUserName, hxUserName, new ChatCallBack() {
            @Override
            public void onSuccess() {
                Logger.e("登录环信成功");
            }

            @Override
            public void onFailed(String failMsg) {
                Logger.e("登录环信失败");
            }
        });
    }
}
