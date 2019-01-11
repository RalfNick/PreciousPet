package com.ralf.module_community.mvp.ui.provider;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.action.click.SpanTextClick;
import com.ralf.module_community.action.click.TextClickType;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.constant.MultiItemType;
import com.ralf.module_community.entity.AdapterMultiItemEntity;
import com.ralf.module_community.entity.CommentEntity;
import com.ralf.module_community.entity.DynamicEntity;
import com.ralf.module_community.entity.PraiseEntity;
import com.ralf.module_community.mvp.ui.view.FeaturedPersonView;
import com.rockerhieu.emojicon.EmojiconTextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ItemContentProvider
 * @email -
 * @date 2018/12/27 下午1:18
 **/
public class ItemFooterProvider extends BaseItemProvider<AdapterMultiItemEntity, BaseViewHolder> {

    /**
     * 总共的评论数3，最多显示3条，超过3条显示更多
     */
    private static final int TOTAL_COMMENT_NUM = 3;
    private String mMoreCommentsDes = "查看更多%s条评论";

    private FeaturedPersonView mPersonView;

    @Override
    public int viewType() {
        return MultiItemType.TYPE_FOOTER;
    }

    @Override
    public int layout() {
        return R.layout.item_featured_footer_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, AdapterMultiItemEntity data, int position) {
        DynamicEntity entity = data.getDynamicBean();
        Integer ownPraise = entity.getOwnPraise();
        Integer bePraiseTimes = data.getDynamicBean().getBePraiseTimes();

        mPersonView = helper.getView(R.id.item_footer_person_view);
        // 点赞总人数
        TextView personNumTv = helper.getView(R.id.item_footer_person_num);
        if (bePraiseTimes < 1) {
            personNumTv.setVisibility(View.GONE);
        } else {
            personNumTv.setVisibility(View.VISIBLE);
            personNumTv.setText(String.valueOf(bePraiseTimes));
        }
        setHeadPortraitData(entity);
        // 点赞，送礼，评论，分享
        RadioButton supportButton = helper.getView(R.id.item_footer_support_rb);
        RadioButton giftButton = helper.getView(R.id.item_footer_gift_rb);
        RadioButton commentButton = helper.getView(R.id.item_footer_comment_rb);
        RadioButton shareButton = helper.getView(R.id.item_footer_share_rb);
        helper.addOnClickListener(R.id.item_footer_support_rb);
        helper.addOnClickListener(R.id.item_footer_gift_rb);
        helper.addOnClickListener(R.id.item_footer_comment_rb);
        helper.addOnClickListener(R.id.item_footer_share_rb);
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
        LinearLayout commentLayout = helper.getView(R.id.item_footer_comment_ll);
        EmojiconTextView firstMsgTv = helper.getView(R.id.item_footer_comment_first);
        EmojiconTextView secondMsgTv = helper.getView(R.id.item_footer_comment_second);
        EmojiconTextView thirdMsgTv = helper.getView(R.id.item_footer_comment_third);
        helper.addOnClickListener(R.id.item_footer_comment_ll).
                addOnClickListener(R.id.item_footer_comment_first)
                .addOnClickListener(R.id.item_footer_comment_second)
                .addOnClickListener(R.id.item_footer_comment_third);

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
            commentLayout.setVisibility(View.GONE);
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
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor(Constant.COLOR_TEXT_COMMENT));
        if (commentEntity.getToUserId() == 0) {
            builder = new SpannableStringBuilder(fromNickName);
            builder.setSpan(colorSpan, 0, fromNickName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append(" : ");
        } else {
            // 回复的人的文本设置
            builder = new SpannableStringBuilder(fromNickName).append(" 回复 ");
            SpannableStringBuilder toBuilder = new SpannableStringBuilder(toNickname);
            toBuilder.setSpan(colorSpan, 0, toBuilder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            toBuilder.setSpan(SpanTextClick.getClicker(toUserId, TextClickType.TYPE_PERSON_NAME),
                    0, toBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append(toBuilder);
        }
        // click event
        builder.setSpan(SpanTextClick.getClicker(fromId, TextClickType.TYPE_PERSON_NAME),
                0, fromNickName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // content click
        int beforeLen = builder.length();
        builder.append(content);
        builder.setSpan(SpanTextClick.getClicker(fromId, TextClickType.TYPE_COMMENT_TEXT),
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
        TextView msgMoreTv = helper.getView(R.id.item_footer_comment_more);
        helper.addOnClickListener(R.id.item_footer_comment_more);
        int commentNum = entity.getCommentTotal();
        if (commentNum > TOTAL_COMMENT_NUM) {
            msgMoreTv.setText(String.format(mMoreCommentsDes, commentNum));
            msgMoreTv.setVisibility(View.VISIBLE);
        } else {
            msgMoreTv.setVisibility(View.GONE);
        }
    }

    /**
     * 判断点赞还是取消赞
     *
     * @param ownPraise
     * @return
     */
    private boolean makeSureZanOrCancel(int ownPraise) {
        switch (ownPraise) {
            case 0:
                // 取消点赞
                return false;
            case 1:
                // 点赞
                return true;
            default:
        }
        return false;
    }

    /**
     * 加载头像数据
     *
     * @param entity
     */
    private void setHeadPortraitData(DynamicEntity entity) {
        List<PraiseEntity> praiseList = entity.getPraiseList();
        Map<Integer, String> headDataMap = new HashMap<>();
        if (praiseList != null && praiseList.size() > 0) {
            for (PraiseEntity bean : praiseList) {
                headDataMap.put(bean.getUserId(), bean.getHeadPortrait());
            }
        }
        mPersonView.setClickListener(new FeaturedPersonView.OnHeadPortraitClickListener() {
            @Override
            public void onClick(int userId) {
                ToastUtils.showShort("跳转到用户界面 " + userId);
            }
        });
        mPersonView.setHeadPortaitData(headDataMap);
    }
}
