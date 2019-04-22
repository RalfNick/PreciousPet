package com.ralf.module_community.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.utils.StringUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.entity.FriendPraiseEntity;
import com.ralf.pet_provider.util.ImageLoaderHelper;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FriendPraiseAdapter
 * @email -
 * @date 2019/04/15 上午11:47
 **/
public class FriendPraiseAdapter extends BaseQuickAdapter<FriendPraiseEntity, BaseViewHolder> {

    public FriendPraiseAdapter(int layoutResId, @Nullable List<FriendPraiseEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendPraiseEntity item) {
        // 主人头像和名字
        ImageView friendImage = helper.getView(R.id.item_friend_praise_image_iv);
        ImageLoaderHelper.loadImage(mContext, friendImage, item.getUserHeadPortrait(), true);
        helper.setText(R.id.item_friend_praise_name_tv, item.getNickName());
        helper.addOnClickListener(R.id.item_friend_praise_name_tv);
        helper.addOnClickListener(R.id.item_friend_praise_image_iv);
        // 宠物头像，名字和性别
        TextView petNameTv = helper.getView(R.id.item_friend_pet_name_tv);
        ImageView petSexImage = helper.getView(R.id.item_friend_pet_sex_iv);
        ImageView petHeadImage = helper.getView(R.id.item_friend_pet_head_iv);
        String petName = item.getPetName();
        if (StringUtils.isEmpty(petName)) {
            petNameTv.setVisibility(View.GONE);
            petSexImage.setVisibility(View.GONE);
            petHeadImage.setVisibility(View.GONE);
        } else {
            petNameTv.setVisibility(View.VISIBLE);
            petSexImage.setVisibility(View.VISIBLE);
            petHeadImage.setVisibility(View.VISIBLE);
            petNameTv.setText(petName);
            petSexImage.setImageResource(item.getPetSex() == 0 ? R.mipmap.pet_boy : R.mipmap.pet_girl);
            ImageLoaderHelper.loadImage(mContext, petHeadImage, item.getPetHeadPortrait(), true);
        }
        helper.addOnClickListener(R.id.item_friend_pet_name_tv);
        helper.addOnClickListener(R.id.item_friend_pet_sex_iv);
        helper.addOnClickListener(R.id.item_friend_pet_head_iv);
        // play icon
        boolean isDynamic = item.getType() == 0;
        helper.getView(R.id.item_friend_video_play_iv)
                .setVisibility(isDynamic ? View.GONE : View.VISIBLE);
        ImageView petImage = helper.getView(R.id.item_friend_pet_image_iv);
        if (isDynamic) {
            ImageLoaderHelper.loadImage(mContext, petImage, item.getDynamicsPath(), 0);
        } else {
            ImageLoaderHelper.loadImage(mContext, petImage, item.getVideoPrintscreen(), 0);
        }
        helper.addOnClickListener(R.id.item_friend_pet_image_iv);
        // radio btn of praise
        RadioButton radioButton = helper.getView(R.id.item_send_praise_rb);
        if (item.getOwnPraise() == 0) {
            radioButton.setChecked(false);
            if (item.getBePraiseTimes() != 0) {
                radioButton.setText(String.valueOf(item.getBePraiseTimes()));
            }
        } else {
            radioButton.setChecked(true);
            radioButton.setText(String.valueOf(item.getBePraiseTimes()));
        }
        helper.addOnClickListener(R.id.item_send_praise_rb);
    }
}
