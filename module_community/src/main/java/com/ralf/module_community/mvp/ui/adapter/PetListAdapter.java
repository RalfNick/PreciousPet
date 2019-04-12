package com.ralf.module_community.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ralf.module_community.R;
import com.ralf.module_community.entity.PetInfoEntity;
import com.ralf.pet_provider.util.ImageLoaderHelper;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetListAdapter
 * @email -
 * @date 2019/04/09 上午8:04
 **/
public class PetListAdapter extends BaseQuickAdapter<PetInfoEntity, BaseViewHolder> {

    public PetListAdapter(int layoutResId, @Nullable List<PetInfoEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PetInfoEntity item) {
        ImageView headImageView = helper.getView(R.id.item_pet_head_portrait_iv);
        ImageLoaderHelper.loadImage(mContext, headImageView, item.getHeadPortrait(), true);
        helper.setText(R.id.item_pet_head_name_tv, item.getPetName())
                .setText(R.id.item_pet_type_tv, item.getBreed())
                .setText(R.id.item_pet_age_tv, item.getAge())
                .setImageResource(R.id.item_pet_sex_iv, item.getSex() == 0 ?
                        R.mipmap.pet_boy : R.mipmap.pet_girl);

        helper.addOnClickListener(R.id.item_pet_head_portrait_iv);
    }
}
