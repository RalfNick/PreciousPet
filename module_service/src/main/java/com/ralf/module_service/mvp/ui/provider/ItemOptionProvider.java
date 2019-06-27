package com.ralf.module_service.mvp.ui.provider;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.jess.arms.utils.StringUtils;
import com.ralf.module_service.R;
import com.ralf.module_service.entity.ExpertOnlineMultiEntity;
import com.ralf.module_service.entity.ExpertOnlineMultiType;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ItemOptionProvider
 * @email -
 * @date 2019/06/25 14:55
 **/
public class ItemOptionProvider extends BaseItemProvider<ExpertOnlineMultiEntity, BaseViewHolder> {

    @Override
    public int viewType() {
        return ExpertOnlineMultiType.TYPE_OPTION;
    }

    @Override
    public int layout() {
        return R.layout.layout_expert_online_option;
    }

    @Override
    public void convert(BaseViewHolder helper, ExpertOnlineMultiEntity data, int position) {
        String medical = StringUtils.isEmpty(data.getMedicalType()) ? mContext.getString(R.string.expert_medical) : data.getMedicalType();
        String petType = StringUtils.isEmpty(data.getPetType()) ? mContext.getString(R.string.all) : data.getPetType();
        helper.setChecked(R.id.expert_choice_medical_rb, false)
                .setChecked(R.id.expert_choice_pet_rb, false)
                .setText(R.id.expert_choice_medical_rb, medical)
                .setText(R.id.expert_choice_pet_rb, petType);
        helper.addOnClickListener(R.id.expert_choice_medical_rb)
                .addOnClickListener(R.id.expert_choice_pet_rb);
    }
}
