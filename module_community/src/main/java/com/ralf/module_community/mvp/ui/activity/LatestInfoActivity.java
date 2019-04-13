package com.ralf.module_community.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.dg.component.DaggerLatestInfoComponent;
import com.ralf.module_community.dg.module.LatestInfoModule;
import com.ralf.module_community.entity.LatestInfoEntity;
import com.ralf.module_community.mvp.contact.LatestInfoContract;
import com.ralf.module_community.mvp.presenter.LatestInfoPresenter;
import com.ralf.module_community.mvp.ui.adapter.LatestInfoAdapter;
import com.ralf.module_community.mvp.ui.decoration.GridSectionAverageGapItemDecoration;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
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
 * @name LatestInfoActivity
 * @email -
 * @date 2019/04/13 下午4:33
 **/
@Route(path = RouterConfig.CommunityModule.COMMUNITY_LATEST_PATH)
public class LatestInfoActivity extends BaseSwipeBackActivity<LatestInfoPresenter> implements LatestInfoContract.View {

    @BindView(R2.id.latest_info_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.latest_info_smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;

    private List<LatestInfoEntity> mInfoEntityList = new ArrayList<>();
    private LatestInfoAdapter mLatestInfoAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLatestInfoComponent.builder()
                .appComponent(appComponent)
                .latestInfoModule(new LatestInfoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_latest_info;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mLatestInfoAdapter = new LatestInfoAdapter(R.layout.item_latest_info_layout, mInfoEntityList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mLatestInfoAdapter);
        mRecyclerView.addItemDecoration(new GridSectionAverageGapItemDecoration(10, 2, 10, 2));
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getLatestData(false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getLatestData(true);
            }
        });
        mLatestInfoAdapter.setOnItemClickListener((adapter, view, position) -> {
            LatestInfoEntity infoEntity = mInfoEntityList.get(position);
            // 跳转到讨论区
            ARouter.getInstance()
                    .build(RouterConfig.CommunityModule.COMMUNITY_COMMENT_PATH)
                    .withInt(RouterConfig.CommunityModule.KEY_DYNAMIC_ID, infoEntity.getDynamicId())
                    .withString(RouterConfig.CommunityModule.KEY_NICK_NAME, "")
                    .withInt(RouterConfig.CommunityModule.KEY_NAVIGATE_TYPE, RouterConfig.CommunityModule.TYPE_SELECTED)
                    .navigation();
        });
        getLatestData(true);
    }

    private void getLatestData(boolean isRefresh) {
        mPresenter.getLatestData(isRefresh);
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void finishLoadMoreWithNoData() {
        mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
    }

    @Override
    public void finishRefresh(List<LatestInfoEntity> data) {
        mSmartRefreshLayout.finishRefresh();
        if (data != null && data.size() > 0){
            mInfoEntityList.clear();
            mInfoEntityList.addAll(data);
            mLatestInfoAdapter.setNewData(mInfoEntityList);
        }
    }

    @Override
    public void finishLoadMore(List<LatestInfoEntity> data) {
        mSmartRefreshLayout.finishLoadMore();
        int size = mInfoEntityList.size();
        if (data != null && data.size() > 0){
            mLatestInfoAdapter.addData(size,data);
        }
    }
}
