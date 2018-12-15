package com.ralf.www.pet_provider.user;

import com.jess.arms.utils.SpUtil;
import com.jess.arms.utils.StringUtils;
import com.ralf.www.pet_provider.user.constant.UserConstant;

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

    public static String getUserId() {
        return SpUtil.getInstance().getString(UserConstant.USER_ID);
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
}
