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
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.dg.component.DaggerHeatPraiseComponent;
import com.ralf.module_community.dg.module.HeatPraiseModule;
import com.ralf.module_community.entity.HeatPraiseEntity;
import com.ralf.module_community.mvp.contract.HeatPraiseContract;
import com.ralf.module_community.mvp.presenter.DayHeatPraisePresenter;
import com.ralf.module_community.mvp.ui.adapter.DayHeatPraiseAdapter;
import com.ralf.module_community.widget.HeatPraiseView;
import com.ralf.pet_provider.router.RouterConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name DayHeatPraiseFragment
 * @email -
 * @date 2019/04/19 上午10:58
 **/
public class DayHeatPraiseFragment extends BaseLazyFragment<DayHeatPraisePresenter>
        implements HeatPraiseContract.DayView, View.OnClickListener {

    @BindView(R2.id.day_praise_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.day_praise_smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;

    private DayHeatPraiseAdapter mAdapter;
    private List<HeatPraiseEntity> mPraiseEntityList = new ArrayList<>();
    private View mHeadView;
    private HeatPraiseView mFirstPraiseView;
    private HeatPraiseView mSecondPraiseView;
    private HeatPraiseView mThirdPraiseView;

    @Override
    protected void loadLargeData() {
        getHeatPraiseData(true);
        mIsLoaded = true;
    }

    private void getHeatPraiseData(boolean isRefresh) {
        mPresenter.getHeatPraiseData(isRefresh);
    }

    @Override
    protected void initEvent(@Nullable Bundle savedInstanceState) {
        mFirstPraiseView = mHeadView.findViewById(R.id.day_heat_praise_first_hv);
        mSecondPraiseView = mHeadView.findViewById(R.id.day_heat_praise_second_hv);
        mThirdPraiseView = mHeadView.findViewById(R.id.day_heat_praise_third_hv);
        mSecondPraiseView.setOnClickListener(this);
        mFirstPraiseView.setOnClickListener(this);
        mThirdPraiseView.setOnClickListener(this);
        mAdapter = new DayHeatPraiseAdapter(R.layout.item_heat_praise_layout, mPraiseEntityList);
        mAdapter.addHeaderView(mHeadView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> getHeatPraiseData(true));
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            // 跳转到社区讨论页面
            HeatPraiseEntity heatPraiseEntity = mPraiseEntityList.get(position);
            int dynamicId = heatPraiseEntity.getDynamicId();
            switchToCommunityPage(dynamicId);
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
        View rootView = inflater.inflate(R.layout.fragment_day_heat_praise, container, false);
        mHeadView = inflater.inflate(R.layout.head_day_heat_praise_layout, null);
        return rootView;
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void refreshData(List<HeatPraiseEntity> dataList) {
        mSmartRefreshLayout.finishRefresh();
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
        mSmartRefreshLayout.finishRefresh();
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
        }
    }
}
