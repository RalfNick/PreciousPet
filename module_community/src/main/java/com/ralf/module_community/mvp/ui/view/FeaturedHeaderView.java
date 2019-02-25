package com.ralf.module_community.mvp.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.ImageConfig;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.entity.DynamicEntity;
import com.ralf.module_community.util.AttentionStatusUtil;
import com.ralf.pet_provider.user.constant.UserConstant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FeaturedHeaderView
 * @email -
 * @date 2018/12/24 下午8:39
 **/
public class FeaturedHeaderView extends RelativeLayout {

    @BindView(R2.id.header_master_avatar_iv)
    ImageView mAvatarIv;
    @BindView(R2.id.header_attention_btn)
    Button mAttentionBtn;
    @BindView(R2.id.header_no_pet_master_name_tv)
    TextView mNoPetNameTv;
    @BindView(R2.id.header_no_pet_location_tv)
    TextView mNoPetLocationTv;
    @BindView(R2.id.header_no_pet_layout)
    LinearLayout mNoPetLayout;
    @BindView(R2.id.header_pet_master_name_tv)
    TextView mMasterNameTv;
    @BindView(R2.id.header_pet_location_tv)
    TextView mLocationTv;
    @BindView(R2.id.header_master_layout)
    LinearLayout mMasterLayout;
    @BindView(R2.id.header_pet_avatar_iv)
    ImageView mPetAvatarIv;
    @BindView(R2.id.header_pet_name_tv)
    TextView mPetNameTv;
    @BindView(R2.id.header_pet_type_tv)
    TextView mPetTypeTv;
    @BindView(R2.id.header_pet_sex_iv)
    ImageView mPetSexIv;
    @BindView(R2.id.header_pet_layout)
    LinearLayout mPetLayout;

    private ImageLoader mImageLoader;

    public FeaturedHeaderView(Context context) {
        this(context, null);

    }

    public FeaturedHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FeaturedHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_header_view_layout, this);
        ButterKnife.bind(this, view);
        mImageLoader = ArmsUtils.obtainAppComponentFromContext(getContext()).imageLoader();
    }

    public void setData(DynamicEntity bean) {
        setData(bean, null);
    }

    public void setData(DynamicEntity bean, BaseViewHolder helper) {
        loadData(bean);
        // 设置点击事件
        if (helper != null) {
            setClickEvent(helper);
        }
    }

    /**
     * 加载数据到 view 中
     *
     * @param bean DynamicEntity
     */
    private void loadData(DynamicEntity bean) {
        // 主人头像
        ImageConfig imageConfig = ImageConfigImpl.builder()
                .imageView(mAvatarIv)
                .url(bean.getUserHeadPortrait())
                .isCircle(true)
                .build();
        mImageLoader.loadImage(getContext(), imageConfig);
        // 主人信息
        String nickName = bean.getNickName();
        String province = bean.getProvince();
        String city = bean.getCity();
        Integer owner = bean.getOwner();
        boolean hasPet = false;
        int petSexResId = R.mipmap.pet_boy;
        // 有宠物
        if (owner == 1) {
            hasPet = true;
            mPetNameTv.setText(bean.getPetName());
            mPetTypeTv.setText(bean.getBreed());
            ImageConfig petConfig = ImageConfigImpl.builder()
                    .imageView(mPetAvatarIv)
                    .url(bean.getPetHeadPortrait())
                    .isCircle(true)
                    .build();
            mImageLoader.loadImage(getContext(), petConfig);
            if (bean.getPetSex() == 1) {
                petSexResId = R.mipmap.pet_girl;
            }
        }
        mMasterLayout.setVisibility(hasPet ? View.VISIBLE : View.GONE);
        mNoPetLayout.setVisibility(hasPet ? View.GONE : View.VISIBLE);
        mPetLayout.setVisibility(hasPet ? View.VISIBLE : View.GONE);
        mPetSexIv.setImageResource(petSexResId);
        // 没有宠物情况下
        mNoPetNameTv.setText(nickName);
        String locationText = "%s%s";
        mNoPetLocationTv.setText(String.format(locationText, province, city));
        // 有宠物情况下
        mMasterNameTv.setText(nickName);
        mLocationTv.setText(String.format(locationText, province, city));
        setAttentionState(bean);
    }

    /**
     * 设置关注按钮状态
     *
     * @param bean DynamicEntity
     */
    private void setAttentionState(DynamicEntity bean) {
        Integer userId = bean.getUserId();
        if (userId.equals(UserConstant.APP_USERID)) {
            mAttentionBtn.setVisibility(View.GONE);
        } else {
            mAttentionBtn.setBackground(AttentionStatusUtil.setAttentionStatus(getContext(), bean.getAttentionStatus()));
            mAttentionBtn.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置点击事件
     *
     * @param helper BaseViewHolder
     */
    private void setClickEvent(BaseViewHolder helper) {

        helper.addOnClickListener(R.id.header_attention_btn)
                .addOnClickListener(R.id.header_master_avatar_iv)
                .addOnClickListener(R.id.header_no_pet_master_name_tv)
                .addOnClickListener(R.id.header_pet_master_name_tv)
                .addOnClickListener(R.id.header_pet_avatar_iv)
                .addOnClickListener(R.id.header_pet_name_tv)
                .addOnClickListener(R.id.header_pet_type_tv);
    }
}
