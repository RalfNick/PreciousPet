package com.ralf.module_community.mvp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.base.BaseLazyFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageConfig;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.SizeUtils;
import com.jess.arms.utils.ToastUtils;
import com.jess.arms.utils.device_util.ScreenUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.constant.MultiItemType;
import com.ralf.module_community.dg.component.DaggerFeaturedComponent;
import com.ralf.module_community.dg.module.FeaturedModule;
import com.ralf.module_community.entity.AdapterMultiItemEntity;
import com.ralf.module_community.entity.BannerEntity;
import com.ralf.module_community.entity.DynamicEntity;
import com.ralf.module_community.entity.FeaturedEntity;
import com.ralf.module_community.entity.ListRefreshEntity;
import com.ralf.module_community.mvp.contact.FeaturedContact;
import com.ralf.module_community.mvp.presenter.FeaturedPresenter;
import com.ralf.module_community.mvp.ui.adapter.FeaturedAdapter;
import com.ralf.module_community.mvp.ui.view.FeaturedHeaderView;
import com.ralf.module_community.widget.player.ScrollCalculatorHelper;
import com.ralf.pet_provider.http.HttpUrl;
import com.ralf.pet_provider.router.RouterConfig;
import com.ralf.pet_provider.share.PetShare;
import com.ralf.pet_provider.widget.stickyitemdecoration.OnStickyChangeListener;
import com.ralf.pet_provider.widget.stickyitemdecoration.StickyHeadContainer;
import com.ralf.pet_provider.widget.stickyitemdecoration.StickyItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Ralf(wanglixin)
 * 精选页面
 * @name FeaturedFragment
 * @email -
 * @date 2018/12/15 下午8:48
 **/
public class FeaturedFragment extends BaseLazyFragment<FeaturedPresenter> implements FeaturedContact.View {

    /**
     * BANNER 轮播延迟时间
     */
    private static final int BANNER_DELAY_TIME = 2500;

    /**
     * 视频自动播放设置位置，上下偏离距离
     */
    private static final int OFFSET_PLAYER_TOP = 100;
    private static final int OFFSET_PLAYER_BOTTOM = 100;

    @BindView(R2.id.featured_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.featured_refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    /**
     * 顶部视图，轮播图，list，adapter,页号
     */
    private Banner mBanner;
    private View mHeadView;
    private com.jess.arms.http.imageloader.ImageLoader mImageLoader;
    private List<BannerEntity> mBannerList = new ArrayList<>();

    private List<AdapterMultiItemEntity> mList = new ArrayList<>();
    private FeaturedAdapter mAdapter;
    private StickyHeadContainer mStickyHeadContainer;
    private Handler mHandler = new Handler();

    /**
     * 当前吸顶的位置
     */
    private int mCurrentStickyPos;

    /**
     * 滑动时自动播放视频辅助类
     */
    private ScrollCalculatorHelper mScrollCalculatorHelper;

    /**
     * 计算滑动位置
     */
    private LinearLayoutManager mLayoutManager;
    private int mFirstVisibleItem;
    private int mLastVisibleItem;
    /**
     * 是否在发送点赞请求
     */
    private boolean isSendingPraise;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerFeaturedComponent.builder()
                .appComponent(appComponent)
                .featuredModule(new FeaturedModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_featured, container, false);
        mHeadView = inflater.inflate(R.layout.featured_header_layout, mRefreshLayout, false);
        mBanner = mHeadView.findViewById(R.id.featured_header_banner);
        mStickyHeadContainer = rootView.findViewById(R.id.feather_sticky_decoration);
        return rootView;
    }

    @Override
    protected void loadLargeData() {
        requestData(true, Constant.TYPE_REFRESH);
    }

    @Override
    protected void initEvent(@Nullable Bundle savedInstanceState) {

        mImageLoader = ArmsUtils.obtainAppComponentFromContext(getContext()).imageLoader();
        mBanner.setImages(mBannerList)
                .setIndicatorGravity(BannerConfig.CENTER)
                .setDelayTime(BANNER_DELAY_TIME)
                .setImageLoader(new GlideImageLoader());
        mAdapter = new FeaturedAdapter(mList);
        mAdapter.addHeaderView(mHeadView);
        mAdapter.openLoadAnimation();
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        // 限定范围为屏幕一半的上下偏移180
        int playTop = ScreenUtils.getScreenHeight() / 2 - SizeUtils.dp2px(OFFSET_PLAYER_TOP);
        int playBottom = ScreenUtils.getScreenHeight() / 2 + SizeUtils.dp2px(OFFSET_PLAYER_BOTTOM);
        // 自定播放帮助类
        mScrollCalculatorHelper = new ScrollCalculatorHelper(R.id.video_item_player, playTop, playBottom);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mScrollCalculatorHelper.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mFirstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
                mLastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                // 滑动自动播放的代码
                mScrollCalculatorHelper.onScroll(recyclerView, mFirstVisibleItem, mLastVisibleItem,
                        mLastVisibleItem - mFirstVisibleItem);
            }
        });
        // decoration
        initDecoration();
        // click
        setClickEvent();
    }

    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }

    /**
     * 初始化吸顶悬浮布局
     */
    private void initDecoration() {

        final FeaturedHeaderView featuredHeaderView = mStickyHeadContainer.findViewById(R.id.item_header_view);
        View.OnClickListener listener = v -> {
            int viewId = v.getId();
            int userId = mAdapter.getData().get(mCurrentStickyPos).getDynamicBean().getUserId();
            if (viewId == R.id.header_attention_btn) {
                ToastUtils.showShort("Decoration 关注");
                // TODO 需要更新关注按钮状态
                // 跳转到主人详情
            } else if (viewId == R.id.header_master_avatar_iv
                    || viewId == R.id.header_no_pet_master_name_tv
                    || viewId == R.id.header_pet_master_name_tv) {
                ARouter.getInstance()
                        .build(RouterConfig.UserModule.MASTER_INFO_PATH)
                        .withInt(RouterConfig.UserModule.KEY_USER_ID, userId)
                        .navigation();
                // 跳转宠物从详情
            } else if (viewId == R.id.header_pet_avatar_iv
                    || viewId == R.id.header_pet_name_tv) {
                ToastUtils.showShort("Decoration 宠物详情");

                // 宠物类型详情
            } else if (viewId == R.id.header_pet_type_tv) {
                ToastUtils.showShort("Decoration 宠物类型");
            }
        };
        mStickyHeadContainer.findViewById(R.id.header_attention_btn).setOnClickListener(listener);
        mStickyHeadContainer.findViewById(R.id.header_master_avatar_iv).setOnClickListener(listener);
        mStickyHeadContainer.findViewById(R.id.header_no_pet_master_name_tv).setOnClickListener(listener);
        mStickyHeadContainer.findViewById(R.id.header_pet_master_name_tv).setOnClickListener(listener);
        mStickyHeadContainer.findViewById(R.id.header_pet_avatar_iv).setOnClickListener(listener);
        mStickyHeadContainer.findViewById(R.id.header_pet_name_tv).setOnClickListener(listener);
        mStickyHeadContainer.findViewById(R.id.header_pet_type_tv).setOnClickListener(listener);

        mStickyHeadContainer.setDataCallback(pos -> {
            mCurrentStickyPos = pos;
            featuredHeaderView.setData(mAdapter.getData().get(pos).getDynamicBean());
        });

        StickyItemDecoration itemDecoration = new StickyItemDecoration(mStickyHeadContainer, MultiItemType.TYPE_HEADER);
        itemDecoration.setOnStickyChangeListener(new OnStickyChangeListener() {
            @Override
            public void onScrollable(int offset) {
                mStickyHeadContainer.scrollChild(offset);
                mStickyHeadContainer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onInVisible() {
                mStickyHeadContainer.reset();
                mStickyHeadContainer.setVisibility(View.INVISIBLE);
            }
        });
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    /**
     * 设置点击事件
     */
    private void setClickEvent() {
        mBanner.setOnBannerListener(position -> {
            BannerEntity bannerEntity = mBannerList.get(position);
            ToastUtils.showShort("你点击了图片-- " + position + " 连接是 " + bannerEntity.getLinkUrl());
        });

        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                requestData(false, Constant.TYPE_REFRESH);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestData(true, Constant.TYPE_REFRESH);
            }
        });

        // 子控件点击事件
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showShort("ItemClick - " + position);
            }
        });
        // 子控件内部点击事件
        mAdapter.setOnItemChildClickListener(
                (adapter, view, position) -> {
                    int id = view.getId();
                    handleClick(position, id);
                }
        );
    }

    /**
     * 处理点击事件
     *
     * @param position item 位置
     * @param viewId   控件 id
     */
    private void handleClick(int position, int viewId) {
        AdapterMultiItemEntity itemEntity = mList.get(position);
        DynamicEntity dynamicBean = itemEntity.getDynamicBean();
        Integer userId = dynamicBean.getUserId();
        if (viewId == R.id.header_attention_btn) {
            ToastUtils.showShort("关注");
            // 跳转到主人详情
        } else if (viewId == R.id.header_master_avatar_iv
                || viewId == R.id.header_no_pet_master_name_tv
                || viewId == R.id.header_pet_master_name_tv) {
            ARouter.getInstance()
                    .build(RouterConfig.UserModule.MASTER_INFO_PATH)
                    .withInt(RouterConfig.UserModule.KEY_USER_ID, userId)
                    .navigation();
            // 跳转宠物从详情
        } else if (viewId == R.id.header_pet_avatar_iv || viewId == R.id.header_pet_name_tv) {
            ToastUtils.showShort("宠物详情");
            // 宠物类型详情
        } else if (viewId == R.id.header_pet_type_tv) {
            ToastUtils.showShort("宠物类型");
        } else if (viewId == R.id.item_content_comment_more) {
            jumpToNewPage(userId, "", 0);
        } else if (viewId == R.id.item_content_support_rb) {
            if (!isSendingPraise) {
                isSendingPraise = true;
                mPresenter.sendPraise(position, dynamicBean);
            }
        } else if (viewId == R.id.item_content_gift_rb) {
            ToastUtils.showShort("送礼物");
        } else if (viewId == R.id.item_content_comment_rb) {
            jumpToNewPage(userId, "", 0);
        } else if (viewId == R.id.item_content_share_rb) {
            shareCommunity(dynamicBean);
        } else if (viewId == R.id.item_content_person_num) {
            ARouter.getInstance()
                    .build(RouterConfig.CommunityModule.COMMUNITY_PRAISE_LIST_PATH)
                    .withInt(RouterConfig.CommunityModule.KEY_USER_ID, userId)
                    .navigation();
        }
    }

    /**
     * 分享社区状态
     *
     * @param dynamicEntity 实体类
     */
    private void shareCommunity(DynamicEntity dynamicEntity) {
        String url = String.format(HttpUrl.COMMUNITY_SHARE_URL, dynamicEntity.getDynamicId());
        String nickName = dynamicEntity.getNickName();
        String title = TextUtils.isEmpty(nickName) ? "\r" : String.format(PetShare.shareTitleOfCommunity, nickName);
        String desc = !TextUtils.isEmpty(dynamicEntity.getTalk()) ? dynamicEntity.getTalk() : PetShare.SHARE_SELECTED_DESC;
        // 1 视频类型   0 图片类型
        String imgUrl = dynamicEntity.getType() == 1 ? dynamicEntity.getVideoPrintscreen() : dynamicEntity.getDynamicsPath();
        PetShare.ShareBuilder
                .with(mContext)
                .url(url)
                .imgUrl(imgUrl)
                .title(title)
                .desc(desc)
                .withText(desc)
                .build()
                .shareToOthers(PetShare.ShareTypeEnum.SHARE_COMMUNITY_FEATURED);
    }

    /**
     * 跳转到新界面，评论详情
     *
     * @param userId   用户 id
     * @param nickName 昵称
     * @param fromId   来自的 id
     */
    private void jumpToNewPage(int userId, String nickName, int fromId) {
        ARouter.getInstance()
                .build(RouterConfig.CommunityModule.COMMUNITY_COMMENT_PATH)
                .withInt(RouterConfig.CommunityModule.KEY_USER_ID, userId)
                .withString(RouterConfig.CommunityModule.KEY_NICK_NAME, nickName)
                .withInt(RouterConfig.CommunityModule.KEY_FROM_USER_ID, fromId)
                .navigation();
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        mRefreshLayout.finishRefresh();
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    /**
     * 请求数据
     *
     * @param isRefresh 是否刷新
     * @param type      类型
     */
    private void requestData(boolean isRefresh, int type) {
        mPresenter.requestData(isRefresh, type);
    }

    @Override
    public void updateView(boolean isRefresh, FeaturedEntity data) {
        List<DynamicEntity> dynamicList = data.getDynamicList();
        for (DynamicEntity bean : dynamicList) {
            bean.setRefresh(false);
            // 头部动态布局
            AdapterMultiItemEntity headEntity = new AdapterMultiItemEntity(MultiItemType.TYPE_HEADER);
            headEntity.setDynamicBean(bean);
            mList.add(headEntity);

            // 内容部分
//            AdapterMultiItemEntity contentEntity = new AdapterMultiItemEntity(MultiItemType.TYPE_CONTENT);
//            contentEntity.setDynamicBean(bean);
//            mList.add(contentEntity);

            // 底部部分
            AdapterMultiItemEntity footerEntity = new AdapterMultiItemEntity(MultiItemType.TYPE_FOOTER);
            footerEntity.setDynamicBean(bean);
            mList.add(footerEntity);
        }
        if (isRefresh) {
            mRefreshLayout.finishRefresh();
        } else {
            mRefreshLayout.finishLoadMore();
        }
        mAdapter.setNewData(mList);
    }

    @Override
    public void loadBannerView(List<BannerEntity> bannerEntityList) {
        if (bannerEntityList != null && bannerEntityList.size() > 0) {
            mBanner.update(bannerEntityList);
        }
    }

    @Override
    public void updatePraiseView(int position, DynamicEntity entity) {
        entity.setRefresh(true);
        mList.get(position).setDynamicBean(entity);
        // 刷新头像
        mAdapter.notifyItemChanged(position + mAdapter.getHeaderLayoutCount(),
                new ListRefreshEntity(ListRefreshEntity.RefreshType.REFRESH_PRAISE));
    }

    @Override
    public void resetPraiseState() {
        mHandler.postDelayed(() -> isSendingPraise = false, 200);
    }

    /**
     * banner 的图片加载器
     */
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object object, ImageView imageView) {
            BannerEntity entity = (BannerEntity) object;
            ImageConfig imageConfig = ImageConfigImpl.builder()
                    .imageView(imageView)
                    .url(entity.getImgUrl())
                    .build();
            mImageLoader.loadImage(getContext(), imageConfig);
        }
    }
}
