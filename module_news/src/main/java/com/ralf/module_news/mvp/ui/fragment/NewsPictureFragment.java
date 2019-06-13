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
import com.ralf.module_news.dg.component.DaggerNewsPictureComponent;
import com.ralf.module_news.dg.module.NewsPictureModule;
import com.ralf.module_news.entity.NewsEntity;
import com.ralf.module_news.mvp.contract.NewsPictureContract;
import com.ralf.module_news.mvp.presenter.NewsPicturePresenter;
import com.ralf.module_news.mvp.ui.adapter.NewsPictureAdapter;
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
public class NewsPictureFragment extends BaseLazyFragment<NewsPicturePresenter> implements NewsPictureContract.View {

    @BindView(R2.id.news_picture_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.news_picture_refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private NewsPictureAdapter mAdapter;
    private List<NewsEntity> mEntityList = new ArrayList<>();

    @Override
    protected void loadLargeData() {
        mPresenter.getPictureData(NewsConstant.POSITION_ARR[3], true);
        mIsLoaded = true;
    }

    @Override
    protected void initEvent(@Nullable Bundle savedInstanceState) {
        mIsPrepared = true;
        mAdapter = new NewsPictureAdapter(R.layout.item_news_picture_layout, mEntityList);
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
        setClickEvent();
    }

    private void setClickEvent() {
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            NewsEntity entity = mEntityList.get(position);
            ARouter.getInstance().build(RouterConfig.NewsModule.NEWS_PATH_PICTURE_PREVIEW)
                    .withInt(RouterConfig.NewsModule.KEY_IMAGE_ID,entity.getArticleId())
                    .navigation();
        });
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getPictureData(NewsConstant.POSITION_ARR[3], false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getPictureData(NewsConstant.POSITION_ARR[3], true);
            }
        });
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerNewsPictureComponent.builder()
                .appComponent(appComponent)
                .newsPictureModule(new NewsPictureModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_picture, container, false);
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

    @Override
    public void hideLoading() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }
}
