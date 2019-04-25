package com.ralf.module_community.mvp.ui.fragment;

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
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.dg.component.DaggerChannelComponent;
import com.ralf.module_community.dg.module.ChannelModule;
import com.ralf.module_community.entity.ChannelInfoEntity;
import com.ralf.module_community.entity.result.ChannelResultEntity;
import com.ralf.module_community.mvp.contract.ChannelContract;
import com.ralf.module_community.mvp.presenter.ChannelPresenter;
import com.ralf.module_community.mvp.ui.adapter.ChannelAdapter;
import com.ralf.module_community.mvp.ui.block.ChannelHeaderView;
import com.ralf.pet_provider.router.RouterConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendFragment
 * @email -
 * @date 2019/04/13 上午10:30
 **/
public class ChannelFragment extends BaseLazyFragment<ChannelPresenter> implements ChannelContract.View {

    @BindView(R2.id.channel_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.history_praise_smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private ChannelAdapter mChannelAdapter;
    private List<ChannelInfoEntity> mInfoEntityList = new ArrayList<>();
    private View mHeaderView;

    @Inject
    ChannelHeaderView mChannelHeaderView;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerChannelComponent.builder()
                .appComponent(appComponent)
                .channelModule(new ChannelModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mHeaderView = inflater.inflate(R.layout.header_channel_layout, null);
        return inflater.inflate(R.layout.fragment_channel_layout, container, false);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    protected void loadLargeData() {
        getChannelData(true);
        mIsLoaded = true;
    }

    /**
     * 请求频道数据
     *
     * @param isRefresh 是否刷新
     */
    private void getChannelData(boolean isRefresh) {
        mPresenter.getChannelData(isRefresh);
    }

    @Override
    protected void initEvent(@Nullable Bundle savedInstanceState) {
        mChannelAdapter = new ChannelAdapter(R.layout.item_channel_content_layout, mInfoEntityList);
        mRecyclerView.setAdapter(mChannelAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getChannelData(false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getChannelData(true);
            }
        });
        mChannelAdapter.addHeaderView(mHeaderView);
        mChannelAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            int viewId = view.getId();
            ChannelInfoEntity channelInfoEntity = mInfoEntityList.get(position);
            int postId = channelInfoEntity.getPostId();
            int userId = channelInfoEntity.getPublisherId();
            int channelId = channelInfoEntity.getChannelId();
            if (viewId == R.id.item_channel_type_tv) {
                ARouter.getInstance()
                        .build(RouterConfig.CommunityModule.COMMUNITY_CHANNEL_DETAIL_PATH)
                        .withInt(RouterConfig.CommunityModule.KEY_CHANNEL_DETAIL_ID, channelId)
                        .navigation();
            } else if (viewId == R.id.item_channel_first_iv
                    || viewId == R.id.item_channel_second_iv
                    || viewId == R.id.item_channel_third_iv) {
                ARouter.getInstance()
                        .build(RouterConfig.CommunityModule.COMMUNITY_POST_DETAIL_PATH)
                        .withInt(RouterConfig.CommunityModule.COMMUNITY_KEY_POST_DETAIL_ID, postId)
                        .navigation();
            } else if (viewId == R.id.item_channel_user_iv
                    || viewId == R.id.item_channel_user_name_tv) {
                // 跳转到用户
                ARouter.getInstance()
                        .build(RouterConfig.UserModule.MASTER_INFO_PATH)
                        .withInt(RouterConfig.UserModule.KEY_USER_ID, userId)
                        .navigation();
            }
        });
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
    public void finishRefresh(ChannelResultEntity data) {
        mRefreshLayout.finishRefresh();
        mChannelHeaderView.init(mContext, mHeaderView);
        // Head 部分频道列表
        List<ChannelInfoEntity> cnlInfoList = data.getCnlInfoList();
        mChannelHeaderView.setData(data.getMyList());
        if (cnlInfoList != null && cnlInfoList.size() > 0) {
            mInfoEntityList.clear();
            mInfoEntityList.addAll(cnlInfoList);
            mChannelAdapter.setNewData(mInfoEntityList);
        }
    }

    @Override
    public void finishLoadMore(ChannelResultEntity data) {
        mRefreshLayout.finishLoadMore();
        List<ChannelInfoEntity> cnlInfoList = data.getCnlInfoList();
        if (cnlInfoList != null && cnlInfoList.size() > 0) {
            int position = mInfoEntityList.size();
            mChannelAdapter.addData(position, cnlInfoList);
        }
    }
}
