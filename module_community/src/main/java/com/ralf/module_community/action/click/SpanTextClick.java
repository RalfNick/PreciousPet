package com.ralf.module_community.action.click;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ralf.pet_provider.router.RouterConfig;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name SpanTextClick
 * @email -
 * @date 2019/01/11 上午10:11
 **/
public class SpanTextClick extends ClickableSpan {

    private int mUserId;
    private TextClickType mType;
    private int mFromId;
    private String mNickName;


    private SpanTextClick(int userId, String nickName, int fromId, TextClickType type) {
        this.mUserId = userId;
        this.mType = type;
        this.mNickName = nickName;
        this.mFromId = fromId;
    }

    public static SpanTextClick getClicker(int userId, String nickName, int fromId, TextClickType type) {
        return new SpanTextClick(userId, nickName, fromId, type);
    }

    @Override
    public void onClick(View widget) {
        switch (mType) {

            case TYPE_PERSON_NAME:
                ARouter.getInstance()
                        .build(RouterConfig.UserModule.MASTER_INFO_PATH)
                        .withInt(RouterConfig.UserModule.KEY_USER_ID, mUserId)
                        .navigation();
                break;
            case TYPE_COMMENT_TEXT:
                ARouter.getInstance()
                        .build(RouterConfig.CommunityModule.COMMUNITY_COMMENT_PATH)
                        .withInt(RouterConfig.CommunityModule.KEY_USER_ID, mUserId)
                        .withString(RouterConfig.CommunityModule.KEY_NICK_NAME, mNickName)
                        .withInt(RouterConfig.CommunityModule.KEY_FROM_USER_ID, mFromId)
                        .navigation();
                break;
            default:
                break;
        }
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
    }
}
