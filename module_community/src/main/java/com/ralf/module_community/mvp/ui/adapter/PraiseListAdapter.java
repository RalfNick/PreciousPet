package com.ralf.module_community.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.ImageConfig;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.StringUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.entity.PraiseEntity;
import com.ralf.module_community.mvp.ui.view.HeadPortraitListView;
import com.ralf.pet_provider.router.RouterConfig;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PraiseListAdapter
 * @email -
 * @date 2019/04/08 上午8:32
 **/
public class PraiseListAdapter extends BaseQuickAdapter<PraiseEntity, BaseViewHolder> {

    /**
     * 超过一定数量显示更多
     */
    public static final int NUM_NEED_SHOW_MORE = 3;

    private ImageLoader mImageLoader;

    public PraiseListAdapter(int layoutResId, @Nullable List<PraiseEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PraiseEntity item) {
        setLocationView(helper, item);

        mImageLoader = ArmsUtils.obtainAppComponentFromContext(mContext).imageLoader();
        ImageView headPortrait = helper.getView(R.id.item_praise_head_portrait_iv);
        ImageConfig imageConfig = ImageConfigImpl.builder()
                .imageView(headPortrait)
                .url(item.getHeadPortrait())
                .isCircle(false)
                .build();
        mImageLoader.loadImage(mContext, imageConfig);

        helper.setText(R.id.item_praise_head_name_tv, item.getNickName());
        int sex = item.getSex();
        helper.setImageResource(R.id.item_praise_sex_iv,
                sex == 0 ? R.mipmap.icon_sex_man : R.mipmap.icon_sex_woman);

        setItemListData(helper, item);
    }

    /**
     * 设置头像列表数据
     *
     * @param helper BaseViewHolder
     * @param item   数据
     */
    private void setItemListData(BaseViewHolder helper, PraiseEntity item) {
        HeadPortraitListView headPortraitListView = helper.getView(R.id.item_praise_sublist_tv);
        List<PraiseEntity.PetBean> petList = item.getPetList();
        int totalPets = item.getPetTotal();
        if (petList != null) {
            LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
            if (totalPets <= NUM_NEED_SHOW_MORE) {
                for (PraiseEntity.PetBean bean : petList) {
                    map.put(bean.getPetId(), bean.getPetHeadPortrait());
                }
                headPortraitListView.setHeadPortraitData(map);
            } else {
                for (int i = 0; i < NUM_NEED_SHOW_MORE; i++) {
                    map.put(petList.get(i).getPetId(), petList.get(i).getPetHeadPortrait());
                }
                headPortraitListView.setHeadPortraitData(map, R.mipmap.icon_more);
            }
        }

        headPortraitListView.setClickListener(new HeadPortraitListView.OnHeadPortraitClickListener() {
            @Override
            public void onClick(int id) {
                // 跳转到宠物页面
                ARouter.getInstance()
                        .build(RouterConfig.UserModule.PET_INFO_PATH)
                        .withInt(RouterConfig.UserModule.KEY_PET_ID, id)
                        .navigation();
            }

            @Override
            public void onMoreClick() {
                // 跳转到宠物列表页面
                ARouter.getInstance()
                        .build(RouterConfig.CommunityModule.COMMUNITY_PET_LIST_PATH)
                        .withInt(RouterConfig.CommunityModule.KEY_USER_ID, item.getUserId())
                        .navigation();
            }
        });
    }

    /**
     * 设置位置信息
     *
     * @param helper BaseViewHolder
     * @param item   数据
     */
    private void setLocationView(BaseViewHolder helper, PraiseEntity item) {
        String city = item.getCity();
        String province = item.getProvince();
        if (StringUtils.isEmpty(city) && StringUtils.isEmpty(item.getProvince())) {
            helper.getView(R.id.item_praise_location_tv).setVisibility(View.GONE);
            helper.getView(R.id.item_praise_location_iv).setVisibility(View.GONE);
        } else {
            String location = StringUtils.isEmpty(city) ? province : city;
            helper.getView(R.id.item_praise_location_tv).setVisibility(View.VISIBLE);
            helper.setText(R.id.item_praise_location_tv, location);
            helper.getView(R.id.item_praise_location_iv).setVisibility(View.VISIBLE);
        }
    }
}
