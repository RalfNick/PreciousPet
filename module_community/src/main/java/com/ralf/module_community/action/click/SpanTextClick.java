package com.ralf.module_community.action.click;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.jess.arms.utils.ToastUtils;

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

    private SpanTextClick(int userId, TextClickType type) {
        this.mUserId = userId;
        this.mType = type;
    }

    public static SpanTextClick getClicker(int userId, TextClickType type) {
        return new SpanTextClick(userId, type);
    }

    @Override
    public void onClick(View widget) {
        switch (mType) {

            case TYPE_PERSON_NAME:
                ToastUtils.showShort("跳转到个人主页");
                break;
            case TYPE_COMMENT_TEXT:
                ToastUtils.showShort("跳转到状态详情页");
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
