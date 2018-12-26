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

import com.jess.arms.http.imageloader.ImageConfig;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.entity.FeaturedEntity;
import com.ralf.module_community.util.AttentionStatusUtil;
import com.ralf.pet_provider.user.constant.UserConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R2.id.header_master_level_iv)
    ImageView mLevelIv;
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
    private String mLocationText = "%s%s";

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

        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.item_decoration_featured_layout, this);

        ButterKnife.bind(this, view);
        mImageLoader = ArmsUtils.obtainAppComponentFromContext(getContext()).imageLoader();
    }

    public void setData(FeaturedEntity.DynamicListBean bean) {

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
        Integer userId = bean.getUserId();

        // 没有宠物情况下
        mNoPetNameTv.setText(nickName);
        mNoPetLocationTv.setText(String.format(mLocationText, province, city));

        // 有宠物情况下
        mMasterNameTv.setText(nickName);
        mLocationTv.setText(String.format(mLocationText, province, city));

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
        mPetSexIv.setImageResource(petSexResId);
        mMasterLayout.setVisibility(hasPet ? View.VISIBLE : View.GONE);
        mNoPetLayout.setVisibility(hasPet ? View.GONE : View.VISIBLE);
        mPetLayout.setVisibility(hasPet ? View.VISIBLE : View.GONE);

        // 关注按钮
        if (userId.equals(UserConstant.APP_USERID)) {
            mAttentionBtn.setVisibility(View.GONE);
        } else {
            mAttentionBtn.setBackground(AttentionStatusUtil.setAttentionStatus(getContext(), bean.getAttentionStatus()));
            mAttentionBtn.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R2.id.header_master_avatar_iv, R2.id.header_attention_btn,
            R2.id.header_no_pet_master_name_tv, R2.id.header_pet_master_name_tv,
            R2.id.header_pet_avatar_iv, R2.id.header_pet_name_tv,
            R2.id.header_pet_type_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 关注
            case R2.id.header_attention_btn:
                ToastUtils.showShort("关注");
                break;
            // 跳转到主人详情
            case R2.id.header_master_avatar_iv:
            case R2.id.header_no_pet_master_name_tv:
            case R2.id.header_pet_master_name_tv:
                ToastUtils.showShort("主人详情");
                break;
            // 跳转宠物从详情
            case R2.id.header_pet_avatar_iv:
            case R2.id.header_pet_name_tv:
                ToastUtils.showShort("宠物详情");
                break;
            // 宠物类型详情
            case R2.id.header_pet_type_tv:
                ToastUtils.showShort("宠物类型");
                break;
            default:
                break;
        }
    }
}
