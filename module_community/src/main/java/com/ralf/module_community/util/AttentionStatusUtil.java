package com.ralf.module_community.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.ralf.module_community.R;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name AttentionStatusUtil
 * @email -
 * @date 2018/12/25 上午8:31
 **/
public class AttentionStatusUtil {

    /**
     * 获取关注状态的 Drawable
     *
     * @param context context
     * @param status  状态
     * @return
     */
    public static Drawable setAttentionStatus(Context context, int status) {
        Drawable drawable = null;
        switch (status) {
            case 0:
                // 未关注
                drawable = context.getResources().getDrawable(R.mipmap.attention_add, null);
            case 3:
                // 拉黑
                drawable = context.getResources().getDrawable(R.mipmap.attention_add, null);
                break;
            case 1:
                // 已关注
                drawable = context.getResources().getDrawable(R.mipmap.attented_already, null);
                break;
            case 2:
                // 好友
                drawable = context.getResources().getDrawable(R.mipmap.attention_friend, null);
                break;
            default:
                break;
        }
        return drawable;
    }
}
