package com.ralf.module_community.mvp.ui.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ralf.module_community.R;
import com.ralf.module_community.entity.ChannelInfoEntity;
import com.ralf.pet_provider.util.ImageLoaderHelper;
import com.rockerhieu.emojicon.EmojiconTextView;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelAdapter
 * @email -
 * @date 2019/04/23 上午11:47
 **/
public class ChannelAdapter extends BaseQuickAdapter<ChannelInfoEntity, BaseViewHolder> {

    public ChannelAdapter(int layoutResId, @Nullable List<ChannelInfoEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChannelInfoEntity item) {
        TextView channelTypeTv = helper.getView(R.id.item_channel_type_tv);
        ImageView firstImage = helper.getView(R.id.item_channel_first_iv);
        ImageView secondImage = helper.getView(R.id.item_channel_second_iv);
        ImageView thirdImage = helper.getView(R.id.item_channel_third_iv);
        ImageView videoIcon = helper.getView(R.id.item_channel_video_iv);

        ImageView userImage = helper.getView(R.id.item_channel_user_iv);
        TextView userNameTv = helper.getView(R.id.item_channel_user_name_tv);
        ImageLoaderHelper.loadImage(mContext, userImage, item.getHeadPortrait(), true);
        userNameTv.setText(item.getNickName());
        channelTypeTv.setText(item.getChannelName());
        // 频道图片信息
        setChannelImage(helper, item, firstImage, secondImage, thirdImage, videoIcon);
        // 设置帖子状态信息
        setEmojiText(item, helper);
        // 设置点击事件
        helper.addOnClickListener(R.id.item_channel_type_tv)
                .addOnClickListener(R.id.item_channel_first_iv)
                .addOnClickListener(R.id.item_channel_second_iv)
                .addOnClickListener(R.id.item_channel_third_iv)
                .addOnClickListener(R.id.item_channel_user_iv)
                .addOnClickListener(R.id.item_channel_user_name_tv);
    }

    private void setChannelImage(BaseViewHolder helper, ChannelInfoEntity item, ImageView firstImage,
                                 ImageView secondImage, ImageView thirdImage, ImageView videoIcon) {
        int type = item.getType();
        // 0 - 文字  1 - 图片  2 - 视频
        if (type == 0) {
            helper.getView(R.id.item_channel_image_layout).setVisibility(View.GONE);
            videoIcon.setVisibility(View.GONE);
        } else if (type == 1) {
            videoIcon.setVisibility(View.GONE);
            List<String> imgUrlList = item.getImgUrlList();
            int size = imgUrlList.size();
            if (size > 0) {
                helper.getView(R.id.item_channel_image_layout).setVisibility(View.VISIBLE);
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
                helper.getView(R.id.item_channel_image_layout).setVisibility(View.GONE);
            }
        } else if (type == 2) {
            videoIcon.setVisibility(View.VISIBLE);
            helper.getView(R.id.item_channel_image_layout).setVisibility(View.VISIBLE);
            firstImage.setVisibility(View.VISIBLE);
            secondImage.setVisibility(View.GONE);
            thirdImage.setVisibility(View.GONE);
            List<String> imgUrlList = item.getImgUrlList();
            if (imgUrlList != null && imgUrlList.size() > 0) {
                ImageLoaderHelper.loadImage(mContext, firstImage, imgUrlList.get(0), 4);
            }
        }
    }

    /**
     * 设置帖子状态信息
     *
     * @param item   数据
     * @param helper holder
     */
    private void setEmojiText(ChannelInfoEntity item, BaseViewHolder helper) {
        EmojiconTextView emojiconTextView = helper.getView(R.id.item_channel_emoji_tv);
        SpannableStringBuilder oneSpannable = stringBuilder("", -1);
        SpannableStringBuilder twoSpannable = stringBuilder("", -1);
        SpannableStringBuilder threeSpannable = stringBuilder("", -1);
        for (int i = 0; i < item.getTopicDetailList().size(); i++) {
            switch (i) {
                case 0:
                    ChannelInfoEntity.TopicBean oneListBean = item.getTopicDetailList().get(0);
                    String oneStr = oneListBean.getTopicDetailName();
                    int oneId = oneListBean.getTopicDetailId();
                    oneSpannable = stringBuilder(oneStr, oneId);
                    break;
                case 1:
                    ChannelInfoEntity.TopicBean twoListBean = item.getTopicDetailList().get(1);
                    String twoStr = twoListBean.getTopicDetailName();
                    int twoId = twoListBean.getTopicDetailId();
                    twoSpannable = stringBuilder(twoStr, twoId);
                    break;
                case 2:
                    ChannelInfoEntity.TopicBean threeListBean = item.getTopicDetailList().get(2);
                    String threeStr = threeListBean.getTopicDetailName();
                    int threeId = threeListBean.getTopicDetailId();
                    threeSpannable = stringBuilder(threeStr, threeId);
                    break;
                default:
                    break;
            }
        }
        emojiconTextView.setText(oneSpannable
                .append(twoSpannable)
                .append(threeSpannable)
                .append(item.getContent()));
        emojiconTextView.setUseSystemDefault(false);
        emojiconTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private SpannableStringBuilder stringBuilder(String content, int id) {
        int color = Color.parseColor("#FF449DDE");
        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        builder.setSpan(new ForegroundColorSpan(color), 0, content.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }
}
