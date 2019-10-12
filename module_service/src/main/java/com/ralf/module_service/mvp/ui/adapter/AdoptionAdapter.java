package com.ralf.module_service.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ralf.module_service.R;
import com.ralf.module_service.entity.AdoptionEntity;
import com.ralf.pet_provider.util.DateUtil;
import com.ralf.pet_provider.util.ImageLoaderHelper;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name AdoptionAdapter
 * @email -
 * @date 2019/06/29 17:12
 **/
public class AdoptionAdapter extends BaseQuickAdapter<AdoptionEntity, BaseViewHolder> {

    public AdoptionAdapter(int layoutResId, @Nullable List<AdoptionEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AdoptionEntity item) {
        ImageView petImage = helper.getView(R.id.adoption_pet_image_iv);
        ImageLoaderHelper.loadImage(mContext, petImage, item.getVideoPrintscreen(), 2);
        helper.setText(R.id.adoption_pet_title_et, item.getAdoptRequest())
                .setText(R.id.adoption_pet_type_tv, item.getBreedTypeName())
                .setText(R.id.adoption_pet_state_time_tv, DateUtil.timeAutoFormat(item.getCreateTime()))
                .setText(R.id.adoption_pet_age_tv, item.getAge());
        ImageView petSexImage = helper.getView(R.id.adoption_pet_sex_iv);
        petSexImage.setImageResource(item.getSex() == 1 ? R.mipmap.pet_boy : R.mipmap.pet_girl);
        helper.addOnClickListener(R.id.item_lingyang_layout);
    }
}
