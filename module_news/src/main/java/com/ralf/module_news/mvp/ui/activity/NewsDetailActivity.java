package com.ralf.module_news.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_news.R;
import com.ralf.module_news.R2;
import com.ralf.module_news.dg.component.DaggerNewsDetailComponent;
import com.ralf.module_news.dg.module.NewsDetailModule;
import com.ralf.module_news.entity.NewsEntity;
import com.ralf.module_news.mvp.contract.NewsDetailContract;
import com.ralf.module_news.mvp.presenter.NewsDetailPresenter;
import com.ralf.module_news.mvp.ui.adapter.NewsDetailAdapter;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;
import com.ralf.pet_provider.util.WebViewUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsDetailActivity
 * @email -
 * @date 2019/05/27 19:30
 **/
@Route(path = RouterConfig.NewsModule.NEWS_PATH_DETAIL)
public class NewsDetailActivity extends BaseSwipeBackActivity<NewsDetailPresenter>
        implements NewsDetailContract.View {

    private static final String TITLE = "资讯详情";

    @Autowired(name = RouterConfig.NewsModule.KEY_NEWS_ID)
    String mNewsId;

    @BindView(R2.id.back_iv)
    ImageView mBackIv;
    @BindView(R2.id.title_name_tv)
    TextView mNameTv;
    @BindView(R2.id.news_detail_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.news_detail_refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private NewsDetailAdapter mAdapter;
    private List<NewsEntity> mEntityList = new ArrayList<>();

    private WebView mWebView;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerNewsDetailComponent.builder()
                .appComponent(appComponent)
                .newsDetailModule(new NewsDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mNameTv.setText(TITLE);
        mAdapter = new NewsDetailAdapter(R.layout.item_news_recommend_layout, mEntityList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            NewsEntity entity = mEntityList.get(position);
            ARouter.getInstance()
                    .build(RouterConfig.NewsModule.NEWS_PATH_DETAIL)
                    .withString(RouterConfig.NewsModule.KEY_NEWS_ID, String.valueOf(entity.getArticleId()))
                    .navigation();
        });
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getNewsDetailData(mNewsId, false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getNewsDetailData(mNewsId, true);
            }
        });
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableRefresh(false);
        mPresenter.getNewsDetailData(mNewsId, true);
    }

    @JavascriptInterface
    @Override
    public void setHeaderView(String url) {
        mAdapter.removeAllHeaderView();
        mWebView = WebViewUtil.initWebView(url, true);
        mWebView.addJavascriptInterface(this, "native");
        mAdapter.addHeaderView(mWebView);
    }

    @Override
    public void onBackPressed() {
        if (mWebView != null && mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void hideLoading() {
        mRefreshLayout.finishRefresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.destroy();
        }
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    @OnClick({R2.id.back_iv})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.back_iv) {
            onBackPressed();
        }
    }
}
