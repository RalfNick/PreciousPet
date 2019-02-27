package com.ralf.module_community.mvp.ui.provider;

import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.ralf.module_community.R;
import com.ralf.module_community.action.click.SpanTextClick;
import com.ralf.module_community.action.click.TextClickType;
import com.ralf.module_community.constant.MultiItemType;
import com.ralf.module_community.entity.AdapterMultiItemEntity;
import com.ralf.module_community.entity.CommentEntity;
import com.ralf.module_community.entity.DynamicEntity;
import com.rockerhieu.emojicon.EmojiconTextView;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ItemCommentProvider
 * @email -
 * @date 2018/12/27 下午1:18
 **/
public class ItemCommentProvider extends BaseItemProvider<AdapterMultiItemEntity, BaseViewHolder> {

    /**
     * 总共的评论数3，最多显示3条，超过3条显示更多
     */
    private static final int TOTAL_COMMENT_NUM = 3;
    private String mMoreCommentsDes = "查看更多%s条评论";

    @Override
    public int viewType() {
        return MultiItemType.TYPE_COMMENT;
    }

    @Override
    public int layout() {
        return R.layout.item_featured_comment_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, AdapterMultiItemEntity data, int position) {
        DynamicEntity entity = data.getDynamicBean();
        // 评论消息显示
        convertCommentsDetail(helper, entity);
        // 查看更多评论
        convertMoreComment(helper, entity);
    }

    /**
     * 设置评论部分视图
     *
     * @param helper holder
     */
    private void convertCommentsDetail(BaseViewHolder helper, DynamicEntity entity) {
        EmojiconTextView firstMsgTv = helper.getView(R.id.item_content_comment_first);
        EmojiconTextView secondMsgTv = helper.getView(R.id.item_content_comment_second);
        EmojiconTextView thirdMsgTv = helper.getView(R.id.item_content_comment_third);

        List<CommentEntity> commentList = entity.getCommentList();
        if (commentList != null && commentList.size() > 0) {
            int size = commentList.size();
            if (size == 1) {
                secondMsgTv.setVisibility(View.GONE);
                thirdMsgTv.setVisibility(View.GONE);
            } else if (size == 2) {
                thirdMsgTv.setVisibility(View.GONE);
            }
        } else {
            firstMsgTv.setVisibility(View.GONE);
            secondMsgTv.setVisibility(View.GONE);
            thirdMsgTv.setVisibility(View.GONE);
        }
        // 设置评论的数据，一共三条
        EmojiconTextView[] textViews = new EmojiconTextView[]{firstMsgTv, secondMsgTv, thirdMsgTv};
        int i = 0;
        if (commentList != null && commentList.size() > 0) {
            for (CommentEntity commentEntity : commentList) {
                if (i == 3) {
                    break;
                }
                // 设置 Span 文本
                SpannableStringBuilder builder = getSpannableStringBuilder(commentEntity);
                textViews[i].setText(builder);
                textViews[i].setUseSystemDefault(false);
                textViews[i].setVisibility(View.VISIBLE);
                textViews[i].setMovementMethod(LinkMovementMethod.getInstance());
                i++;
            }
        }
    }

    /**
     * 获取 Span 文本
     *
     * @param commentEntity
     * @return
     */
    @NonNull
    private SpannableStringBuilder getSpannableStringBuilder(CommentEntity commentEntity) {
        String fromNickName = commentEntity.getNickName();
        String toNickname = commentEntity.getToNickName();
        String content = commentEntity.getContent();
        int fromId = commentEntity.getUserId();
        int toUserId = commentEntity.getToUserId();
        SpannableStringBuilder builder;
        int color = mContext.getResources().getColor(R.color.comment_nickname);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
        if (commentEntity.getToUserId() == 0) {
            builder = new SpannableStringBuilder(fromNickName);
            builder.setSpan(colorSpan, 0, fromNickName.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            builder.append(" : ");
        } else {
            // 回复的人的文本设置
            builder = new SpannableStringBuilder(fromNickName).append(" 回复 ");
            SpannableStringBuilder toBuilder = new SpannableStringBuilder(toNickname);
            toBuilder.setSpan(colorSpan, 0, toBuilder.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            toBuilder.setSpan(SpanTextClick.getClicker(toUserId, "", 0, TextClickType.TYPE_PERSON_NAME),
                    0, toBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append(toBuilder);
        }
        // click event
        builder.setSpan(SpanTextClick.getClicker(fromId, "", 0, TextClickType.TYPE_PERSON_NAME),
                0, fromNickName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // content click
        int beforeLen = builder.length();
        builder.append(content);
        builder.setSpan(SpanTextClick.getClicker(fromId, "", 0, TextClickType.TYPE_COMMENT_TEXT),
                beforeLen, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    /**
     * 查看更多评论
     *
     * @param helper holder
     * @param entity 数据
     */
    private void convertMoreComment(BaseViewHolder helper, DynamicEntity entity) {
        // 查看更多评论
        TextView msgMoreTv = helper.getView(R.id.item_content_comment_more);
        helper.addOnClickListener(R.id.item_content_comment_more);
        int commentNum = entity.getCommentTotal();
        if (commentNum > TOTAL_COMMENT_NUM) {
            msgMoreTv.setText(String.format(mMoreCommentsDes, commentNum));
            msgMoreTv.setVisibility(View.VISIBLE);
        } else {
            msgMoreTv.setVisibility(View.GONE);
        }
    }
}
