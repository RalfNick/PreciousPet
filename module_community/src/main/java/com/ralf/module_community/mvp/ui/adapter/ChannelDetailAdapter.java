package com.ralf.module_community.mvp.ui.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ralf.module_community.R;
import com.ralf.module_community.action.click.SpanTextClick;
import com.ralf.module_community.entity.ChannelPostEntity;
import com.ralf.module_community.widget.player.SimpleCoverVideo;
import com.ralf.pet_provider.util.ImageLoaderHelper;
import com.rockerhieu.emojicon.EmojiconTextView;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelDetailAdapter
 * @email -
 * @date 2019/04/30 11:10
 **/
public class ChannelDetailAdapter extends BaseQuickAdapter<ChannelPostEntity, BaseViewHolder> {

    private static final String TAG = ChannelDetailAdapter.class.getSimpleName();

    private GSYVideoOptionBuilder mVideoOptionBuilder;

    public ChannelDetailAdapter(int layoutResId, @Nullable List<ChannelPostEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChannelPostEntity item) {
        helper.setText(R.id.item_channel_detail_user_name_tv, item.getNickName())
                .setText(R.id.item_comment_num_tv, String.valueOf(item.getReplyTimes()))
                .setText(R.id.item_comment_time_tv, item.getCreatTime());
        ImageView userImage = helper.getView(R.id.item_channel_detail_user_iv);
        ImageLoaderHelper.loadImage(mContext, userImage, item.getHeadPortrait(), true);
        // 根据item类型 加载不同布局 0全文, 1多图, 2视频
        setChannelImage(helper, item);
        // 设置帖子状态信息
        setEmojiText(item, helper);
        // 设置点击事件
        helper.addOnClickListener(R.id.item_channel_detail_user_iv)
                .addOnClickListener(R.id.item_channel_detail_user_name_tv);
    }

    private void setChannelImage(BaseViewHolder helper, ChannelPostEntity item) {
        ImageView firstImage = helper.getView(R.id.item_channel_detail_first_iv);
        ImageView secondImage = helper.getView(R.id.item_channel_detail_second_iv);
        ImageView thirdImage = helper.getView(R.id.item_channel_detail_third_iv);
        ImageView videoIcon = helper.getView(R.id.item_channel_detail_video_iv);
        int type = item.getType();
        // 0 - 文字  1 - 图片  2 - 视频
        if (type == 0) {
            helper.getView(R.id.item_channel_detail_image_layout).setVisibility(View.GONE);
            videoIcon.setVisibility(View.GONE);
            helper.setVisible(R.id.item_channel_detail_player, false);
        } else if (type == 1) {
            videoIcon.setVisibility(View.GONE);
            helper.setVisible(R.id.item_channel_detail_player, false);
            List<String> imgUrlList = item.getImgUrl();
            int size = imgUrlList.size();
            if (size > 0) {
                helper.getView(R.id.item_channel_detail_image_layout).setVisibility(View.VISIBLE);
                if (size == 1) {
                    ImageLoaderHelper.loadImage(mContext, firstImage, imgUrlList.get(0), 4);
                    firstImage.setVisibility(View.VISIBLE);
                    secondImage.setVisibility(View.GONE);
                    thirdImage.setVisibility(View.GONE);
                } else if (size == 2) {
                    ImageLoaderHelper.loadImage(mContext, firstImage, imgUrlList.get(0), 4);
                    ImageLoaderHelper.loadImage(mContext, secondImage, imgUrlList.get(1), 4);
                    firstImage.setVisibility(View.VISIBLE);
                    secondImage.setVisibility(View.VISIBLE);
                    thirdImage.setVisibility(View.GONE);
                } else {
                    ImageLoaderHelper.loadImage(mContext, firstImage, imgUrlList.get(0), 4);
                    ImageLoaderHelper.loadImage(mContext, secondImage, imgUrlList.get(1), 4);
                    ImageLoaderHelper.loadImage(mContext, thirdImage, imgUrlList.get(2), 4);
                    firstImage.setVisibility(View.VISIBLE);
                    secondImage.setVisibility(View.VISIBLE);
                    thirdImage.setVisibility(View.VISIBLE);
                }
            } else {
                helper.getView(R.id.item_channel_detail_image_layout).setVisibility(View.GONE);
            }
        } else if (type == 2) {
            videoIcon.setVisibility(View.VISIBLE);
            helper.getView(R.id.item_channel_detail_image_layout).setVisibility(View.VISIBLE);
            firstImage.setVisibility(View.GONE);
            secondImage.setVisibility(View.GONE);
            thirdImage.setVisibility(View.GONE);
            helper.getView(R.id.item_channel_detail_player).setVisibility(View.VISIBLE);
            setVideoPlayer(helper, item);
        }
    }

    private void setVideoPlayer(BaseViewHolder helper, ChannelPostEntity item) {
        SimpleCoverVideo videoPlayer = helper.getView(R.id.item_channel_detail_player);
        if (mVideoOptionBuilder == null) {
            mVideoOptionBuilder = new GSYVideoOptionBuilder();
        }
        getParentPosition(item);
        mVideoOptionBuilder.setIsTouchWiget(false)
                .setUrl(item.getVideoUrl())
                .setCacheWithPlay(false)
                .setLockLand(true)
                .setPlayTag(TAG)
                .setPlayPosition(helper.getAdapterPosition())
                .build(videoPlayer);
        // 隐藏 title
        videoPlayer.getTitleTextView().setVisibility(View.GONE);
        // 设置返回键
        videoPlayer.getBackButton().setVisibility(View.GONE);
        // 设置全屏按键功能
        videoPlayer.getFullscreenButton().setVisibility(View.GONE);
    }

    /**
     * 设置帖子状态信息
     *
     * @param item   数据
     * @param helper holder
     */
    private void setEmojiText(ChannelPostEntity item, BaseViewHolder helper) {
        EmojiconTextView emojiconTextView = helper.getView(R.id.item_channel_detail_emoji_tv);
        SpannableStringBuilder strings = null;
        SpannableStringBuilder contentSpannable = new SpannableStringBuilder("");
        String contentStr = item.getContent();
        int color = Color.parseColor("#FF449DDE");
        for (int i = 0; i < item.getThemeList().size(); i++) {
            ChannelPostEntity.ThemeListBean bean = item.getThemeList().get(i);
            String topicName = bean.getThemeName();
            strings = new SpannableStringBuilder(topicName);
            strings.setSpan(new ForegroundColorSpan(color), 0, topicName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            strings.setSpan(SpanTextClick.getTopicClicker(bean.getThemeId()),
                    0, topicName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            contentSpannable.append(strings);
        }
        contentSpannable.append(contentStr);
        emojiconTextView.setText(contentSpannable);
        emojiconTextView.setUseSystemDefault(false);
        emojiconTextView.setMovementMethod(LinkMovementMethod.getInstance());

        emojiconTextView.setUseSystemDefault(false);
        emojiconTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}