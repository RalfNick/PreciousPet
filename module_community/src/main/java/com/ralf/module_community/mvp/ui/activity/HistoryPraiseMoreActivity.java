package com.ralf.module_community.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.dg.component.DaggerHistoryPraiseMoreComponent;
import com.ralf.module_community.dg.module.HistoryPraiseMoreModule;
import com.ralf.module_community.entity.HeatPraiseEntity;
import com.ralf.module_community.mvp.contract.HistoryPraiseMoreContract;
import com.ralf.module_community.mvp.presenter.HistoryPraiseMorePresenter;
import com.ralf.module_community.mvp.ui.adapter.DayHeatPraiseAdapter;
import com.ralf.module_community.widget.HeatPraiseView;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name HistoryPraiseMoreActivity
 * @email -
 * @date 2019/04/22 下午8:15
 **/
@Route(path = RouterConfig.CommunityModule.COMMUNITY_HISTORY_PRAISE_MORE_PATH)
public class HistoryPraiseMoreActivity extends BaseSwipeBackActivity<HistoryPraiseMorePresenter>
        implements HistoryPraiseMoreContract.View, View.OnClickListener {

    @Autowired(name = RouterConfig.CommunityModule.KEY_HISTORY_PRAISE_MORE_TIME)
    String mDateTime;

    @BindView(R2.id.history_praise_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.history_praise_smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private DayHeatPraiseAdapter mAdapter;
    private List<HeatPraiseEntity> mPraiseEntityList = new ArrayList<>();
    private View mHeadView;
    private HeatPraiseView mFirstPraiseView;
    private HeatPraiseView mSecondPraiseView;
    private HeatPraiseView mThirdPraiseView;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerHistoryPraiseMoreComponent.builder()
                .appComponent(appComponent)
                .historyPraiseMoreModule(new HistoryPraiseMoreModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        mHeadView = LayoutInflater.from(this).inflate(R.layout.head_day_heat_praise_layout, null);
        return R.layout.activity_history_more_heat_praise;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mFirstPraiseView = mHeadView.findViewById(R.id.day_heat_praise_first_hv);
        mSecondPraiseView = mHeadView.findViewById(R.id.day_heat_praise_second_hv);
        mThirdPraiseView = mHeadView.findViewById(R.id.day_heat_praise_third_hv);
        ImageView backImageView = mHeadView.findViewById(R.id.heat_praise_back_iv);
        backImageView.setVisibility(View.VISIBLE);
        mSecondPraiseView.setOnClickListener(this);
        mFirstPraiseView.setOnClickListener(this);
        mThirdPraiseView.setOnClickListener(this);
        backImageView.setOnClickListener(this);
        mAdapter = new DayHeatPraiseAdapter(R.layout.item_heat_praise_layout, mPraiseEntityList);
        mAdapter.addHeaderView(mHeadView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mRefreshLayout.setOnRefreshListener(refreshLayout -> getHeatPraiseData(true));
        mRefreshLayout.setEnableLoadMore(false);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            // 跳转到社区讨论页面
            HeatPraiseEntity heatPraiseEntity = mPraiseEntityList.get(position);
            int dynamicId = heatPraiseEntity.getDynamicId();
            switchToCommunityPage(dynamicId);
        });
        getHeatPraiseData(true);
    }

    private void getHeatPraiseData(boolean isRefresh) {
        mPresenter.getHeatPraiseData(isRefresh, mDateTime);
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
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.day_heat_praise_first_hv) {
            if (mPraiseEntityList.size() > 0) {
                HeatPraiseEntity entity = mPraiseEntityList.get(0);
                switchToCommunityPage(entity.getDynamicId());
            }
        } else if (viewId == R.id.day_heat_praise_second_hv) {
            if (mPraiseEntityList.size() > 1) {
                HeatPraiseEntity entity = mPraiseEntityList.get(1);
                switchToCommunityPage(entity.getDynamicId());
            }
        } else if (viewId == R.id.day_heat_praise_third_hv) {
            if (mPraiseEntityList.size() > 2) {
                HeatPraiseEntity entity = mPraiseEntityList.get(2);
                switchToCommunityPage(entity.getDynamicId());
            }
        } else if (viewId == R.id.heat_praise_back_iv) {
            finish();
        }
    }

    @Override
    public void refreshData(List<HeatPraiseEntity> dataList) {
        mRefreshLayout.finishRefresh();
        if (dataList != null) {
            mPraiseEntityList.clear();
            HeatPraiseView[] heatPraiseViews = {mFirstPraiseView, mSecondPraiseView, mThirdPraiseView};
            for (int i = 0; i < dataList.size(); i++) {
                if (i > 2) {
                    mPraiseEntityList.add(dataList.get(i));
                } else {
                    heatPraiseViews[i].setData(dataList.get(i));
                }
            }
            mAdapter.setNewData(mPraiseEntityList);
        }
    }

    @Override
    public void refreshWithNoData() {
        mRefreshLayout.finishRefresh();
    }
}
