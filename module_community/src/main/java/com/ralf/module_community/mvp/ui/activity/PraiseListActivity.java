package com.ralf.module_community.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.event.transmit.EventPublicApi;
import com.jess.arms.event.transmit.EventPublicApiHelper;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.dg.component.DaggerPraiseListComponent;
import com.ralf.module_community.dg.module.PraiseListModule;
import com.ralf.module_community.entity.PraiseEntity;
import com.ralf.module_community.mvp.contact.PraiseListContract;
import com.ralf.module_community.mvp.presenter.PraiseLisPresenter;
import com.ralf.module_community.mvp.ui.adapter.PraiseListAdapter;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Ralf(wanglixin)
 * 点赞列表页面
 * @name PraiseListActivity
 * @email -
 * @date 2019/01/15 下午1:35
 **/
@Route(path = RouterConfig.CommunityModule.COMMUNITY_PRAISE_LIST_PATH)
public class PraiseListActivity extends BaseSwipeBackActivity<PraiseLisPresenter> implements PraiseListContract.View {

    @Autowired(name = RouterConfig.CommunityModule.KEY_USER_ID)
    int mUserId;

    @BindView(R2.id.back_iv)
    ImageView mBackIv;
    @BindView(R2.id.title_name_tv)
    TextView mTitleNameTv;
    @BindView(R2.id.praise_list_recyclerView)
    RecyclerView mListRecyclerView;
    @BindView(R2.id.praise_list_smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;

    private PraiseListAdapter mListAdapter;
    private List<PraiseEntity> mPraiseEntityList = new ArrayList<>();
    private View mEmptyView;

    /**
     * 类型，点赞，粉丝，朋友等
     */
    private int mType;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPraiseListComponent.builder()
                .appComponent(appComponent)
                .praiseListModule(new PraiseListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        mEmptyView = LayoutInflater.from(this).inflate(R.layout.empty_layout, null);
        return R.layout.activity_praise_list;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setUpView();
        refreshData(true);
    }

    private void refreshData(boolean isRefresh) {
        mPresenter.getDataList(mUserId, isRefresh, mType);
    }

    private void setUpView() {
        mTitleNameTv.setText("点赞列表");
        mListAdapter = new PraiseListAdapter(R.layout.item_praise_list_layout, mPraiseEntityList);
        mListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListRecyclerView.setAdapter(mListAdapter);
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshData(false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshData(true);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventPublicApiHelper.unregister(EventPublicApi.LogoutApi.class);
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    @OnClick(R2.id.back_iv)
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.back_iv) {
            finish();
        }
    }

    @Override
    public void finishLoadMoreWithNoData() {
        mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
    }

    @Override
    public void finishRefresh(List<PraiseEntity> dataBean) {
        mSmartRefreshLayout.finishRefresh();
        if (dataBean == null || dataBean.size() < 1) {
            mListAdapter.setEmptyView(mEmptyView);
        } else {
            mPraiseEntityList.clear();
            mListAdapter.setNewData(dataBean);
        }

    }

    @Override
    public void finishLoadMore(List<PraiseEntity> data) {
        mSmartRefreshLayout.finishLoadMore();
        if (data == null || data.size() < 1) {
            return;
        }
        int position = mPraiseEntityList.size();
        mListAdapter.addData(position,data);
    }
}
