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
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jess.arms.base.BaseLazyFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageConfig;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.dg.component.DaggerFeaturedComponent;
import com.ralf.module_community.dg.module.FeaturedModule;
import com.ralf.module_community.entity.BannerEntity;
import com.ralf.module_community.entity.FeaturedEntity;
import com.ralf.module_community.entity.TabEntity;
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

    private static final String[] TAB_TITLES = {"全部", "小狗", "宠物", "萌猫", "萌犬", "其他"};

    @BindView(R2.id.featured_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.featured_refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    /**
     * 轮播图，list，adapter,页号
     */
    private Banner mBanner;
    private com.jess.arms.http.imageloader.ImageLoader mImageLoader;
    private List<BannerEntity> mBannerList = new ArrayList<>();

    private List<FeaturedEntity> mList = new ArrayList<>();
    private FeaturedAdapter mAdapter;

    private CommonTabLayout mHeaderTab;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

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
        View headerView = inflater.inflate(R.layout.featured_header_layout, mRefreshLayout, false);
        mBanner = headerView.findViewById(R.id.featured_header_banner);
        mHeaderTab = headerView.findViewById(R.id.feather_header_tab);
        return rootView;
    }

    @Override
    protected void loadLargeData() {
        mList.add(new FeaturedEntity());
        mList.add(new FeaturedEntity());
        mList.add(new FeaturedEntity());
        mAdapter.setNewData(mList);
        requestData(true, Constant.TYPE_REFRESH);
    }

    @Override
    protected void initEvent(@Nullable Bundle savedInstanceState) {

        mImageLoader = ArmsUtils.obtainAppComponentFromContext(getContext()).imageLoader();
        mBanner.setImages(mBannerList)
                .setIndicatorGravity(BannerConfig.CENTER)
                .setDelayTime(BANNER_DELAY_TIME)
                .setImageLoader(new GlideImageLoader());

        for (String title : TAB_TITLES) {
            mTabEntities.add(new TabEntity(title));
        }
        mHeaderTab.setTabData(mTabEntities);
        mAdapter = new FeaturedAdapter(R.layout.item_featured_layout);
        mAdapter.addHeaderView(mBanner);
        mAdapter.openLoadAnimation();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        setClickEvent();
    }

    /**
     * 设置点击事件
     */
    private void setClickEvent() {
        mBanner.setOnBannerListener(position -> {
            BannerEntity bannerEntity = mBannerList.get(position);
            ToastUtils.showShort("你点击了图片-- " + position + " 连接是 " + bannerEntity.getLinkUrl());
        });

        mHeaderTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                CustomTabEntity customTabEntity = mTabEntities.get(position);
                String tabTitle = customTabEntity.getTabTitle();
                ToastUtils.showShort("你选择了 - " + tabTitle);
            }

            @Override
            public void onTabReselect(int position) {

            }
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

            }
        });

        // 子控件内部点击事件
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
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
    public void updateView(FeaturedEntity data) {

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
