package com.ralf.module_community.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseLazyFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.StringUtils;
import com.jess.arms.utils.TimeUtils;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.dg.component.DaggerHeatPraiseComponent;
import com.ralf.module_community.dg.module.HeatPraiseModule;
import com.ralf.module_community.entity.HeatPraiseEntity;
import com.ralf.module_community.entity.HistoryHeatPraiseEntity;
import com.ralf.module_community.entity.HistoryPraiseSectionEntity;
import com.ralf.module_community.mvp.contract.HeatPraiseContract;
import com.ralf.module_community.mvp.presenter.HistoryHeatPraisePresenter;
import com.ralf.module_community.mvp.ui.adapter.HistoryPraiseSectionAdapter;
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
 * @name HistoryHeatPraiseFragment
 * @email -
 * @date 2019/04/19 上午11:02
 **/
public class HistoryHeatPraiseFragment extends BaseLazyFragment<HistoryHeatPraisePresenter> implements HeatPraiseContract.HistoryView {

    @BindView(R2.id.history_praise_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.history_praise_smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private HistoryPraiseSectionAdapter mAdapter;
    private List<HistoryPraiseSectionEntity> mPraiseEntityList = new ArrayList<>();

    @Override
    protected void loadLargeData() {
        getHeatPraiseData(true);
        mIsLoaded = true;
    }

    /**
     * 获取历史点赞榜数据
     *
     * @param isRefresh 是否是刷新
     */
    private void getHeatPraiseData(boolean isRefresh) {
        mPresenter.getHeatPraiseData(isRefresh);
    }

    @Override
    protected void initEvent(@Nullable Bundle savedInstanceState) {
        mAdapter = new HistoryPraiseSectionAdapter(R.layout.item_history_heat_praise_layout,
                R.layout.head_history_heat_praise_layout, mPraiseEntityList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mRefreshLayout.setOnRefreshListener(refreshLayout -> getHeatPraiseData(true));
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            // 跳转到社区讨论页面
            HistoryPraiseSectionEntity heatPraiseEntity = mPraiseEntityList.get(position);
            if (!heatPraiseEntity.isHeader) {
                int dynamicId = heatPraiseEntity.t.getDynamicId();
                switchToCommunityPage(dynamicId);
            }

        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            int id = view.getId();
            HistoryPraiseSectionEntity sectionEntity = mPraiseEntityList.get(position);
            if (id == R.id.history_praise_head_more_tv) {
                if (!StringUtils.isEmpty(sectionEntity.getDateTime())) {
                    ARouter.getInstance()
                            .build(RouterConfig.CommunityModule.COMMUNITY_HISTORY_PRAISE_MORE_PATH)
                            .withString(RouterConfig.CommunityModule.KEY_HISTORY_PRAISE_MORE_TIME, sectionEntity.getDateTime())
                            .navigation();
                }
            }
        });
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getHeatPraiseData(false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getHeatPraiseData(true);
            }
        });
    }

    /**
     * 跳转到讨论区
     *
     * @param dynamicId 动态 id
     */
    private void switchToCommunityPage(int dynamicId) {
        ARouter.getInstance()
                .build(RouterConfig.CommunityModule.COMMUNITY_COMMENT_PATH)
                .withInt(RouterConfig.CommunityModule.KEY_DYNAMIC_ID, dynamicId)
                .withString(RouterConfig.CommunityModule.KEY_NICK_NAME, "")
                .withInt(RouterConfig.CommunityModule.KEY_NAVIGATE_TYPE, RouterConfig.CommunityModule.TYPE_SELECTED)
                .navigation();
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHeatPraiseComponent.builder()
                .appComponent(appComponent)
                .heatPraiseModule(new HeatPraiseModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history_heat_praise, container, false);
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
    public void refreshWithNoData() {
        mRefreshLayout.finishRefresh();
        ToastUtils.showShort("暂无数据");
    }

    @Override
    public void finishRefresh(List<HistoryHeatPraiseEntity> dataList) {
        mRefreshLayout.finishRefresh();
        if (dataList != null && dataList.size() > 0) {
            mPraiseEntityList.clear();
            for (HistoryHeatPraiseEntity entity : dataList) {
                if (entity.getCurrentDayTotal() > 0) {
                    boolean isMore = entity.getCurrentDayTotal() > 3;
                    long time = Long.valueOf(entity.getDateTime());
                    String date = TimeUtils.date2String(TimeUtils.FORMAT_YYYYMMDD,
                            String.valueOf(time * 1000L));
                    HistoryPraiseSectionEntity headerEntity = new HistoryPraiseSectionEntity(true, date);
                    headerEntity.setMore(isMore);
                    headerEntity.setDateTime(entity.getDateTime());
                    mPraiseEntityList.add(headerEntity);
                    for (HeatPraiseEntity bean : entity.getCurrentDayList()) {
                        HistoryPraiseSectionEntity contentEntity = new HistoryPraiseSectionEntity(bean);
                        mPraiseEntityList.add(contentEntity);
                    }
                }
            }
            mAdapter.setNewData(mPraiseEntityList);
        }
    }

    @Override
    public void finishLoadMore(List<HistoryHeatPraiseEntity> dataList) {
        mRefreshLayout.finishLoadMore();
        if (dataList != null) {
            int position = mPraiseEntityList.size();
            List<HistoryPraiseSectionEntity> list = new ArrayList<>();
            for (HistoryHeatPraiseEntity entity : dataList) {
                if (entity.getCurrentDayList().size() > 0) {
                    boolean isMore = entity.getCurrentDayTotal() > 3;
                    long time = Long.valueOf(entity.getDateTime());
                    String date = TimeUtils.date2String(TimeUtils.FORMAT_YYYYMMDD,
                            String.valueOf(time * 1000L));
                    HistoryPraiseSectionEntity headerEntity = new HistoryPraiseSectionEntity(true, date);
                    headerEntity.setMore(isMore);
                    headerEntity.setDateTime(entity.getDateTime());
                    list.add(headerEntity);
                    for (HeatPraiseEntity bean : entity.getCurrentDayList()) {
                        HistoryPraiseSectionEntity contentEntity = new HistoryPraiseSectionEntity(bean);
                        list.add(contentEntity);
                    }
                }
            }
            mAdapter.addData(position, list);
        }
    }
}
