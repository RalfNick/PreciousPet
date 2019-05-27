package com.ralf.module_news.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseLazyFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_news.R;
import com.ralf.module_news.R2;
import com.ralf.module_news.constant.NewsConstant;
import com.ralf.module_news.dg.component.DaggerNewsImportantComponent;
import com.ralf.module_news.dg.module.NewsImportantModule;
import com.ralf.module_news.entity.NewsEntity;
import com.ralf.module_news.mvp.contract.NewsImportantContract;
import com.ralf.module_news.mvp.presenter.NewsImportantPresenter;
import com.ralf.module_news.mvp.ui.adapter.NewsImportantAdapter;
import com.ralf.pet_provider.router.RouterConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

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
public class NewsImportantFragment extends BaseLazyFragment<NewsImportantPresenter> implements NewsImportantContract.View {

    /**
     * 信息无限流提前加载
     */
    private static final int POSITION_REFRESH = 5;

    @BindView(R2.id.news_important_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.news_important_refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private NewsImportantAdapter mAdapter;
    private List<NewsEntity> mEntityList = new ArrayList<>();

    /**
     * 是否在加载数据
     */
    private boolean isLoadingData;

    @Override
    protected void loadLargeData() {
        isLoadingData = true;
        mPresenter.getImportantNews(NewsConstant.POSITION_ARR[1], true);
        mIsLoaded = true;
    }

    @Override
    protected void initEvent(@Nullable Bundle savedInstanceState) {
        mAdapter = new NewsImportantAdapter(R.layout.item_news_important_layout, mEntityList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            NewsEntity entity = mEntityList.get(position);
            ARouter.getInstance()
                    .build(RouterConfig.NewsModule.NEWS_PATH_DETAIL)
                    .withString(RouterConfig.NewsModule.KEY_NEWS_ID, String.valueOf(entity.getArticleId()))
                    .navigation();
        });
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isLoadingData = true;
                mPresenter.getImportantNews(NewsConstant.POSITION_ARR[1], false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isLoadingData = true;
                mPresenter.getImportantNews(NewsConstant.POSITION_ARR[1], true);
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
                        mPresenter.getImportantNews(NewsConstant.POSITION_ARR[1], false);
                    }
                }
            }
        });
        mIsPrepared = true;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerNewsImportantComponent.builder()
                .appComponent(appComponent)
                .newsImportantModule(new NewsImportantModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_important, container, false);
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
}
