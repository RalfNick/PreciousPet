package com.ralf.module_service.mvp.ui.provider;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_service.R;
import com.ralf.module_service.entity.ExpertOnlineEntity;
import com.ralf.module_service.entity.ExpertOnlineMultiEntity;
import com.ralf.module_service.entity.ExpertOnlineMultiType;
import com.ralf.pet_provider.util.DateUtil;
import com.ralf.pet_provider.util.ImageLoaderHelper;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ItemOptionProvider
 * @email -
 * @date 2019/06/25 14:55
 **/
public class ItemCommentProvider extends BaseItemProvider<ExpertOnlineMultiEntity, BaseViewHolder> {

    @Override
    public int viewType() {
        return ExpertOnlineMultiType.TYPE_COMMENT;
    }

    @Override
    public int layout() {
        return R.layout.item_expert_comment_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, ExpertOnlineMultiEntity data, int position) {
        ExpertOnlineEntity.SeekHelpsListBean bean = data.getSeekHelpsListBean();
        setTopLabel(helper, bean);
        helper.setVisible(R.id.expert_more_iv, bean.getIsImg() != 0);
        helper.setText(R.id.seek_help_title_tv, bean.getTitle())
                .setText(R.id.seek_help_user_name_tv, bean.getNickName())
                .setText(R.id.seek_help_discuss_number_tv, String.valueOf(bean.getAnswerTotal()))
                .setText(R.id.seek_help_discuss_time_tv, DateUtil.timeAutoFormat(bean.getCreateTime()));
        ImageLoaderHelper.loadImage(mContext, helper.getView(R.id.seek_help_user_iv), bean.getHeadPortrait(), true);
        helper.getView(R.id.item_comment_layout).setOnClickListener(view -> ToastUtils.showShort("求助详情"));
    }

    private void setTopLabel(BaseViewHolder helper, ExpertOnlineEntity.SeekHelpsListBean bean) {
        ImageView iconTop = helper.getView(R.id.icon_top_iv);
        if (bean.getIsPlacedTop() == 0) {
            if (bean.getIsNew() == 0) {
                iconTop.setVisibility(View.GONE);
            } else {
                iconTop.setImageResource(R.mipmap.icon_new);
                iconTop.setVisibility(View.VISIBLE);
            }
        } else {
            iconTop.setImageResource(R.mipmap.icon_top);
            iconTop.setVisibility(View.VISIBLE);
        }
    }
}
