package com.ralf.module_news.mvp.ui.fragment;

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
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseLazyFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_news.R;
import com.ralf.module_news.R2;
import com.ralf.module_news.constant.NewsConstant;
import com.ralf.module_news.dg.component.DaggerRecommendComponent;
import com.ralf.module_news.dg.module.RecommendModule;
import com.ralf.module_news.entity.BannerEntity;
import com.ralf.module_news.entity.NewsEntity;
import com.ralf.module_news.mvp.contract.RecommendContract;
import com.ralf.module_news.mvp.presenter.RecommendPresenter;
import com.ralf.module_news.mvp.ui.adapter.NewsRecommendAdapter;
import com.ralf.pet_provider.router.RouterConfig;
import com.ralf.pet_provider.util.ImageLoaderHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsRecommendFragment
 * @email -
 * @date 2019/05/15 17:58
 **/
public class NewsRecommendFragment extends BaseLazyFragment<RecommendPresenter> implements RecommendContract.View {

    /**
     * 信息无限流提前加载
     */
    private static final int POSITION_REFRESH = 5;

    /**
     * BANNER 轮播延迟时间
     */
    private static final int BANNER_DELAY_TIME = 3000;

    @BindView(R2.id.news_recommend_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.news_smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private View mHeaderView;
    private TextView mSearchView;
    private Banner mBanner;
    private List<BannerEntity> mBannerEntityList = new ArrayList<>();
    private List<String> mBannerTitleList = new ArrayList<>();

    private NewsRecommendAdapter mAdapter;
    private List<NewsEntity> mEntityList = new ArrayList<>();

    /**
     * 是否在加载数据
     */
    private boolean isLoadingData;

    @Override
    protected void loadLargeData() {
        isLoadingData = true;
        mPresenter.getRecommendData(NewsConstant.POSITION_ARR[0], true);
        mIsLoaded = true;
    }

    @Override
    protected void initEvent(@Nullable Bundle savedInstanceState) {
        setHeaderView();
        mAdapter = new NewsRecommendAdapter(R.layout.item_news_recommend_layout, mEntityList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            NewsEntity entity = mEntityList.get(position);
            ARouter.getInstance()
                    .build(RouterConfig.NewsModule.NEWS_PATH_DETAIL)
                    .withString(RouterConfig.NewsModule.KEY_NEWS_ID, String.valueOf(entity.getArticleId()))
                    .navigation();
        });
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addHeaderView(mHeaderView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isLoadingData = true;
                mPresenter.getRecommendData(NewsConstant.POSITION_ARR[0], false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isLoadingData = true;
                mPresenter.getRecommendData(NewsConstant.POSITION_ARR[0], true);
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int itemPosition = layoutManager.findLastVisibleItemPosition();
                if (itemPosition + POSITION_REFRESH <= mAdapter.getItemCount()) {
                    if (!isLoadingData) {
                        mPresenter.getRecommendData(NewsConstant.POSITION_ARR[0], false);
                    }
                }
            }
        });
        mIsPrepared = true;
    }

    private void setHeaderView() {
        mBanner = mHeaderView.findViewById(R.id.news_recommend_banner);
        mSearchView = mHeaderView.findViewById(R.id.item_search_tv);
        mBanner.setImages(mBannerEntityList)
                .setBannerTitles(mBannerTitleList)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setDelayTime(BANNER_DELAY_TIME)
                .setImageLoader(new GlideImageLoader());
        mBanner.setOnBannerListener(position -> {
            BannerEntity entity = mBannerEntityList.get(position);
            ARouter.getInstance()
                    .build(RouterConfig.NewsModule.NEWS_PATH_DETAIL)
                    .withString(RouterConfig.NewsModule.KEY_NEWS_ID, String.valueOf(entity.getId()))
                    .navigation();

        });
        mSearchView.setOnClickListener(view -> ToastUtils.showShort("搜索"));
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerRecommendComponent.builder()
                .appComponent(appComponent)
                .recommendModule(new RecommendModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mHeaderView = inflater.inflate(R.layout.header_layout_news_recommend, container, false);
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void finishLoadMoreWithNoData() {
        isLoadingData = false;
        mRefreshLayout.finishLoadMoreWithNoMoreData();
    }

    @Override
    public void finishRefresh(List<NewsEntity> recommendList) {
        mRefreshLayout.finishRefresh();
        isLoadingData = false;
        if (recommendList != null && recommendList.size() > 0) {
            mEntityList.clear();
            mAdapter.addData(recommendList);
        }
    }

    @Override
    public void finishLoadMore(List<NewsEntity> recommendList) {
        mRefreshLayout.finishLoadMore();
        isLoadingData = false;
        if (recommendList != null && recommendList.size() > 0) {
            int size = mEntityList.size();
            mAdapter.addData(size, recommendList);
        }
    }

    @Override
    public void hideLoading() {
        isLoadingData = false;
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void showBanner(List<BannerEntity> bannerList) {
        if (bannerList != null && bannerList.size() > 0) {
            List<String> titleList = new ArrayList<>();
            for (BannerEntity entity : bannerList) {
                titleList.add(entity.getTitle());
            }
            mBanner.update(bannerList, titleList);
        }
    }

    /**
     * banner 的图片加载器
     */
    private class GlideImageLoader extends com.youth.banner.loader.ImageLoader {
        @Override
        public void displayImage(Context context, Object object, ImageView imageView) {
            BannerEntity entity = (BannerEntity) object;
            ImageLoaderHelper.loadImage(mContext, imageView, entity.getImgUrl(), false);
        }
    }
}
