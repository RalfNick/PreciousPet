package com.ralf.module_community.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.dg.component.DaggerAllChannelComponent;
import com.ralf.module_community.dg.module.AllChannelModule;
import com.ralf.module_community.entity.AllChannelSectionEntity;
import com.ralf.module_community.entity.ChannelTypeEntity;
import com.ralf.module_community.entity.eventbus.RefreshAttentionChannelEntity;
import com.ralf.module_community.entity.eventbus.RefreshCommentEntity;
import com.ralf.module_community.mvp.contract.AllChannelContract;
import com.ralf.module_community.mvp.presenter.AllChannelPresenter;
import com.ralf.module_community.mvp.ui.adapter.AllChannelAdapter;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelActivity
 * @email -
 * @date 2019/04/24 上午11:09
 **/
@Route(path = RouterConfig.CommunityModule.COMMUNITY_ALL_CHANNEL_PATH)
public class AllChannelActivity extends BaseSwipeBackActivity<AllChannelPresenter> implements AllChannelContract.View {

    @BindView(R2.id.all_channel_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.latest_info_smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private AllChannelAdapter mAdapter;
    private List<AllChannelSectionEntity> mEntityList = new ArrayList<>();

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerAllChannelComponent.builder()
                .appComponent(appComponent)
                .allChannelModule(new AllChannelModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_all_channel;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mAdapter = new AllChannelAdapter(R.layout.item_all_channel_layout,
                R.layout.head_all_channel_title_layout, mEntityList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRefreshLayout.setEnableLoadMore(false);
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(refreshLayout -> mPresenter.getAllChannelData());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            AllChannelSectionEntity sectionEntity = mEntityList.get(position);
            if (!sectionEntity.isHeader) {
                ChannelTypeEntity entity = sectionEntity.t;
                if (entity.getType() == Constant.CHANNEL_DETAIL) {
                    ARouter.getInstance()
                            .build(RouterConfig.CommunityModule.COMMUNITY_CHANNEL_DETAIL_PATH)
                            .withInt(RouterConfig.CommunityModule.KEY_CHANNEL_DETAIL_ID, entity.getChannelId())
                            .navigation();
                } else if (entity.getType() == Constant.CHANNEL_ADOPTION) {
                    ToastUtils.showShort("领养");
                } else if (entity.getType() == Constant.CHANNEL_PAIR) {
                    ToastUtils.showShort("配对");
                }
            }
        });
        mPresenter.getAllChannelData();
    }


    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);

    }

    @Override
    public void finishRefresh(List<AllChannelSectionEntity> sectionEntityList) {
        mRefreshLayout.finishRefresh();
        if (sectionEntityList != null && sectionEntityList.size() > 0) {
            mEntityList.clear();
            mEntityList.addAll(sectionEntityList);
            mAdapter.setNewData(mEntityList);
        }
    }

    /**
     * 刷新我的关注频道
     *
     * @param entity 数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefreshAttentionChannelEntity entity) {
        mPresenter.getAllChannelData();
    }
}
