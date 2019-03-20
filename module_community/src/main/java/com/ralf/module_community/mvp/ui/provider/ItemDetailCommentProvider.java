package com.ralf.module_community.mvp.ui.provider;

import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.ralf.module_community.R;
import com.ralf.module_community.action.click.SpanTextClick;
import com.ralf.module_community.action.click.TextClickType;
import com.ralf.module_community.constant.MultiItemType;
import com.ralf.module_community.entity.AdapterMultiItemEntity;
import com.ralf.module_community.entity.CommentEntity;
import com.ralf.pet_provider.util.DateUtil;
import com.rockerhieu.emojicon.EmojiconTextView;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ItemDetailCommentProvider
 * @email -
 * @date 2019/03/01 下午7:47
 **/
public class ItemDetailCommentProvider extends BaseItemProvider<AdapterMultiItemEntity, BaseViewHolder> {

    @Override
    public int viewType() {
        return MultiItemType.TYPE_COMMENT;
    }

    @Override
    public int layout() {
        return R.layout.item_comment_detail_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, AdapterMultiItemEntity data, int position) {
        CommentEntity commentEntity = data.getCommentEntity();
        int commentTotal = commentEntity.getTotalComment();
        String commentNumStr = String.valueOf(commentTotal)
                + mContext.getResources().getString(R.string.num_comment_text);
        RelativeLayout container = helper.getView(R.id.item_content_discuss_layout);
        if (commentTotal == 0) {
            container.setVisibility(View.GONE);
            showTotalCommentNum(helper, commentNumStr, true);
        } else {
            int id = commentEntity.getId();
            if (id == 0) {
                showTotalCommentNum(helper, commentNumStr, true);
            } else {
                showTotalCommentNum(helper, commentNumStr, false);
            }
            container.setVisibility(View.VISIBLE);
            helper.addOnClickListener(R.id.item_content_discuss_layout);
            TextView personNameTv = helper.getView(R.id.discuss_name_tv);
            personNameTv.setText(commentEntity.getNickName());
            ImageView personPicIv = helper.getView(R.id.discuss_image_iv);
            Glide.with(mContext).load(commentEntity.getHeadPortrait()).into(personPicIv);
            helper.addOnClickListener(R.id.discuss_image_iv);
            EmojiconTextView emojiconTextView = helper.getView(R.id.discuss_content);
            TextView discussTimeTv = helper.getView(R.id.discuss_time_tv);
            discussTimeTv.setText(DateUtil.timeAutoFormat(commentEntity.getCreateTime()));
            // 回复楼主
            if (commentEntity.getToUserId() == 0 || commentEntity.getToUserId() == data.getUserId()) {
                emojiconTextView.setText(commentEntity.getContent());
            } else {
                // 回复的人的文本设置
                SpannableStringBuilder builder = getSpannableStringBuilder(commentEntity);
                emojiconTextView.setText(builder);
                emojiconTextView.setUseSystemDefault(false);
                emojiconTextView.setMovementMethod(LinkMovementMethod.getInstance());
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

        String toNickname = commentEntity.getToNickName();
        String content = commentEntity.getContent();
        int fromId = commentEntity.getUserId();
        int toUserId = commentEntity.getToUserId();
        int dynamicId = commentEntity.getDynamicId();
        SpannableStringBuilder builder;
        int color = mContext.getResources().getColor(R.color.comment_nickname);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
        // 回复的人的文本设置
        builder = new SpannableStringBuilder("回复 ");
        SpannableStringBuilder toBuilder = new SpannableStringBuilder(toNickname);
        toBuilder.setSpan(colorSpan, 0, toBuilder.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        toBuilder.setSpan(SpanTextClick.getClicker(dynamicId, toUserId, toNickname, fromId, TextClickType.TYPE_PERSON_NAME),
                0, toBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(toBuilder);
        // content click
        builder.append(content);
        return builder;
    }

    /**
     * 显示评论总数
     *
     * @param helper        holder
     * @param commentNumStr 评论总数
     */
    private void showTotalCommentNum(BaseViewHolder helper, String commentNumStr, boolean isShow) {
        TextView backgroundTv = helper.getView(R.id.total_comment_bkg_tv);
        backgroundTv.setVisibility(isShow ? View.VISIBLE : View.GONE);
        TextView commentNumTv = helper.getView(R.id.total_comment_tv);
        commentNumTv.setVisibility(isShow ? View.VISIBLE : View.GONE);
        commentNumTv.setText(commentNumStr);
    }
}
