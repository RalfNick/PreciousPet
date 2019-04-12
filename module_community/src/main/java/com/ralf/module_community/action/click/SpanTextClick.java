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
    private int dynamicId;
    private int position;

    private SpanTextClick(int dynamicId, int userId, String nickName,
                          int fromId, TextClickType type, int position) {
        this.mUserId = userId;
        this.mType = type;
        this.mNickName = nickName;
        this.mFromId = fromId;
        this.dynamicId = dynamicId;
        this.position = position;
    }

    public static SpanTextClick getClicker(int dynamicId, int userId, String nickName,
                                           int fromId, TextClickType type, int position) {
        return new SpanTextClick(dynamicId, userId, nickName, fromId, type, position);
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
                        .withInt(RouterConfig.CommunityModule.KEY_DYNAMIC_ID, dynamicId)
                        .withInt(RouterConfig.CommunityModule.KEY_NAVIGATE_TYPE, RouterConfig.CommunityModule.TYPE_SELECTED)
                        .withInt(RouterConfig.CommunityModule.KEY_FROM_USER_ID, mFromId)
                        .withInt(RouterConfig.CommunityModule.KEY_FROM_ITEM_POSITION, position)
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
