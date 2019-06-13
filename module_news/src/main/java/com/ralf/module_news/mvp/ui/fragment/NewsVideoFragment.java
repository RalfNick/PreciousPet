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
import com.ralf.module_news.dg.component.DaggerNewsVideoComponent;
import com.ralf.module_news.dg.module.NewsVideoModule;
import com.ralf.module_news.entity.NewsEntity;
import com.ralf.module_news.mvp.contract.NewsVideoContract;
import com.ralf.module_news.mvp.presenter.NewsVideoPresenter;
import com.ralf.module_news.mvp.ui.adapter.NewsVideoAdapter;
import com.ralf.pet_provider.router.RouterConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

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
public class NewsVideoFragment extends BaseLazyFragment<NewsVideoPresenter> implements NewsVideoContract.View {

    @BindView(R2.id.news_video_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.news_video_refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private NewsVideoAdapter mAdapter;
    private List<NewsEntity> mEntityList = new ArrayList<>();

    @Override
    protected void loadLargeData() {
        mPresenter.getVideoData(NewsConstant.POSITION_ARR[2], true);
        mIsLoaded = true;
    }

    @Override
    protected void initEvent(@Nullable Bundle savedInstanceState) {
        mIsPrepared = true;
        mAdapter = new NewsVideoAdapter(R.layout.item_news_video_layout, mEntityList);
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
                mPresenter.getVideoData(NewsConstant.POSITION_ARR[2], false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getVideoData(NewsConstant.POSITION_ARR[2], true);
            }
        });
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerNewsVideoComponent.builder()
                .appComponent(appComponent)
                .newsVideoModule(new NewsVideoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_video, container, false);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser){
            GSYVideoManager.onPause();
        }
    }

    @Override
    public void hideLoading() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
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

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void finishLoadMoreWithNoData() {
        mRefreshLayout.finishLoadMoreWithNoMoreData();
    }

    @Override
    public void finishRefresh(List<NewsEntity> data) {
        mRefreshLayout.finishRefresh();
        if (data != null && data.size() > 0) {
            mEntityList.clear();
            mAdapter.addData(data);
        }
    }

    @Override
    public void finishLoadMore(List<NewsEntity> data) {
        mRefreshLayout.finishLoadMore();
        if (data != null && data.size() > 0) {
            int size = mEntityList.size();
            mAdapter.addData(size, data);
        }
    }
}
