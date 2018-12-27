package com.ralf.module_community.mvp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.jess.arms.base.BaseLazyFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageConfig;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.ToastUtils;
import com.orhanobut.logger.Logger;
import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration;
import com.oushangfeng.pinnedsectionitemdecoration.callback.OnHeaderClickListener;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.constant.MultiItemType;
import com.ralf.module_community.dg.component.DaggerFeaturedComponent;
import com.ralf.module_community.dg.module.FeaturedModule;
import com.ralf.module_community.entity.AdapterMultiItemEntity;
import com.ralf.module_community.entity.BannerEntity;
import com.ralf.module_community.entity.FeaturedEntity;
import com.ralf.module_community.mvp.contact.FeaturedContact;
import com.ralf.module_community.mvp.presenter.FeaturedPresenter;
import com.ralf.module_community.mvp.ui.adapter.FeaturedAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
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
    private PinnedHeaderItemDecoration mItemDecoration;

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        // decoration
        initDecoration();
        // click
        setClickEvent();
    }

    /**
     * 初始化吸顶悬浮布局
     */
    private void initDecoration() {

        OnHeaderClickListener clickListener = new OnHeaderClickListener() {
            @Override
            public void onHeaderClick(View view, int id, int position) {
                int viewId = view.getId();
                if (viewId == R.id.header_attention_btn) {
                    ToastUtils.showShort("Decoration 关注");
                    // 刷新ItemDecorations，导致重绘刷新头部
                    mRecyclerView.invalidateItemDecorations();
                    // TODO 需要更新关注按钮状态

                    // 跳转到主人详情
                } else if (viewId == R.id.header_master_avatar_iv
                        || viewId == R.id.header_no_pet_master_name_tv
                        || viewId == R.id.header_pet_master_name_tv) {
                    ToastUtils.showShort("Decoration 主人详情");

                    // 跳转宠物从详情
                } else if (viewId == R.id.header_pet_avatar_iv
                        || viewId == R.id.header_pet_name_tv) {
                    ToastUtils.showShort("Decoration 宠物详情");

                    // 宠物类型详情
                } else if (viewId == R.id.header_pet_type_tv) {
                    ToastUtils.showShort("Decoration 宠物类型");
                }
            }

            @Override
            public void onHeaderLongClick(View view, int id, int position) {
                Logger.e("decoration 长按事件--");
            }
        };
        mItemDecoration = new PinnedHeaderItemDecoration.Builder(MultiItemType.TYPE_HEADER)
                .setClickIds(R.id.header_attention_btn, R.id.header_master_avatar_iv,
                        R.id.header_no_pet_master_name_tv, R.id.header_pet_master_name_tv,
                        R.id.header_pet_avatar_iv, R.id.header_pet_name_tv, R.id.header_pet_type_tv)
                .setHeaderClickListener(clickListener)
                .enableDivider(false)
                .disableHeaderClick(false)
                .create();
        mRecyclerView.addItemDecoration(mItemDecoration);
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
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int id = view.getId();
                handleClick(id);
            }
        });
    }

    /**
     * 处理点击事件
     * @param viewId 控件 id
     */
    private void handleClick(int viewId) {
        if (viewId == R.id.header_attention_btn) {
            ToastUtils.showShort("关注");

            // 跳转到主人详情
        } else if (viewId == R.id.header_master_avatar_iv
                || viewId == R.id.header_no_pet_master_name_tv
                || viewId == R.id.header_pet_master_name_tv) {
            ToastUtils.showShort("主人详情");

            // 跳转宠物从详情
        } else if (viewId == R.id.header_pet_avatar_iv || viewId == R.id.header_pet_name_tv) {
            ToastUtils.showShort("宠物详情");

            // 宠物类型详情
        } else if (viewId == R.id.header_pet_type_tv) {
            ToastUtils.showShort("宠物类型");
        }
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
        List<FeaturedEntity.DynamicListBean> dynamicList = data.getDynamicList();
        for (FeaturedEntity.DynamicListBean bean : dynamicList) {

            // 头部动态布局
            AdapterMultiItemEntity headEntity = new AdapterMultiItemEntity(MultiItemType.TYPE_HEADER);
            headEntity.setDynamicBean(bean);
            mList.add(headEntity);

            // 内容部分
            AdapterMultiItemEntity contentEntity = new AdapterMultiItemEntity(MultiItemType.TYPE_CONTENT);
            contentEntity.setDynamicBean(bean);
            mList.add(contentEntity);

            // 底部部分
            AdapterMultiItemEntity footerEntity = new AdapterMultiItemEntity(MultiItemType.TYPE_FOOTER);
            contentEntity.setDynamicBean(bean);
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
