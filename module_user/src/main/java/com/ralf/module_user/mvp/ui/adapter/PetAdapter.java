package com.ralf.module_user.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ralf.module_user.R;
import com.ralf.module_user.entity.PetRecordEntity;
import com.ralf.pet_provider.util.ImageLoaderHelper;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetAdapter
 * @email -
 * @date 2019/04/11 上午10:48
 **/
public class PetAdapter extends BaseQuickAdapter<PetRecordEntity, BaseViewHolder> {

    public PetAdapter(int layoutResId, @Nullable List<PetRecordEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PetRecordEntity item) {
        // 分割线
        RelativeLayout decoration = helper.getView(R.id.item_pet_decoration_rl);
        decoration.setVisibility(item.isFirstItem() ? View.VISIBLE : View.GONE);

        ImageView imageView = helper.getView(R.id.item_pet_content_iv);
        ImageView playImageView = helper.getView(R.id.item_pet_content_play_iv);
        TextView contentTv = helper.getView(R.id.item_pet_content_emoji_tv);
        TextView shareTv = helper.getView(R.id.item_pet_share_tv);
        TextView dateTv = helper.getView(R.id.item_pet_content_date);
        playImageView.setVisibility(item.getType() == 1 ? View.VISIBLE : View.GONE);
        if (item.isFirstItem()) {
            if (item.isMyTag()) {
                imageView.setImageResource(R.mipmap.icon_pet_add_danymic);
                SpannableStringBuilder builder = new SpannableStringBuilder("今天");
                builder.setSpan(new RelativeSizeSpan(1.6f), 0, builder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                dateTv.setText(builder);
                contentTv.setText("");
                shareTv.setText("");
            } else {
                ImageLoaderHelper.loadImage(mContext, imageView, item.getDynamicsPath(), false);
                String day = item.getDay();
                String month = item.getMonth();
                SpannableStringBuilder builder = new SpannableStringBuilder(day + "  ");
                builder.setSpan(new RelativeSizeSpan(2.0f), 0, builder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                builder.append(month);
                dateTv.setText(builder);
                shareTv.setText("分享");
                contentTv.setText(item.getTalk());
            }
        } else {
            ImageLoaderHelper.loadImage(mContext, imageView, item.getDynamicsPath(), false);
            String day = item.getDay();
            String month = item.getMonth();
            SpannableStringBuilder builder = new SpannableStringBuilder(day + "  ");
            builder.setSpan(new RelativeSizeSpan(2.0f), 0, builder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            builder.append(month);
            dateTv.setText(builder);
            shareTv.setText("分享");
            contentTv.setText(item.getTalk());
        }
    }
}
