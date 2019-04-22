package com.ralf.module_community.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.SizeUtils;
import com.jess.arms.utils.ToastUtils;
import com.jess.arms.widget.decoration.GridOffsetsItemDecoration;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.dg.component.DaggerFriendPraiseComponent;
import com.ralf.module_community.dg.module.FriendPraiseModule;
import com.ralf.module_community.entity.FeedbackEntity;
import com.ralf.module_community.entity.FriendPraiseEntity;
import com.ralf.module_community.mvp.contract.FriendPraiseContract;
import com.ralf.module_community.mvp.presenter.FriendPraisePresenter;
import com.ralf.module_community.mvp.ui.adapter.FriendPraiseAdapter;
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
 * @name FriendPraiseActivity
 * @email -
 * @date 2019/04/15 上午11:31
 **/
@Route(path = RouterConfig.CommunityModule.COMMUNITY_FRIEND_PRAISE_PATH)
public class FriendPraiseActivity extends BaseSwipeBackActivity<FriendPraisePresenter>
        implements FriendPraiseContract.View, BaseQuickAdapter.OnItemChildClickListener {

    private static final String TOAST_CONTENT_PRAISE = "点赞奖励%s";

    @BindView(R2.id.friend_praise_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.friend_praise_smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;

    private FriendPraiseAdapter mAdapter;
    private List<FriendPraiseEntity> mEntityList = new ArrayList<>();

    @Autowired(name = RouterConfig.CommunityModule.KEY_COMMUNITY_FRIEND_PRISE_TYPE)
    int mPageType;

    /**
     * 当前点赞的位置
     */
    private int mCurrentPosition;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerFriendPraiseComponent.builder()
                .appComponent(appComponent)
                .friendPraiseModule(new FriendPraiseModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_friend_praise_list;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mAdapter = new FriendPraiseAdapter(R.layout.item_friend_praise_layout, mEntityList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        GridOffsetsItemDecoration offsetsItemDecoration = new GridOffsetsItemDecoration(
                GridOffsetsItemDecoration.GRID_OFFSETS_VERTICAL);
        offsetsItemDecoration.setVerticalItemOffsets(SizeUtils.dp2px( 8));
        offsetsItemDecoration.setHorizontalItemOffsets(SizeUtils.dp2px( 8));
        mRecyclerView.addItemDecoration(offsetsItemDecoration);
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getData(false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData(true);
            }
        });
        mAdapter.setOnItemChildClickListener(this);
        getData(true);
    }

    public void getData(boolean isRefresh) {
        // 好友点赞和新萌露脸
        if (mPageType == RouterConfig.CommunityModule.TYPE_FRIEND_PRISE) {
            mPresenter.getFriendPraiseData(isRefresh);
        } else if (mPageType == RouterConfig.CommunityModule.TYPE_NEW_CUTE_PET) {
            mPresenter.getNewCutePetData(isRefresh);
        }
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
    public void finishNewPetRefresh(List<FriendPraiseEntity> newPetList) {
        mSmartRefreshLayout.finishRefresh();
        if (newPetList != null && newPetList.size() > 0) {
            mEntityList.clear();
            mEntityList.addAll(newPetList);
            mAdapter.setNewData(mEntityList);
        }
    }

    @Override
    public void finisNewPetLoadMore(List<FriendPraiseEntity> newPetList) {
        mSmartRefreshLayout.finishLoadMore();
        if (newPetList != null && newPetList.size() > 0) {
            int size = mEntityList.size();
            mEntityList.addAll(newPetList);
            mAdapter.addData(size, mEntityList);
        }
    }

    @Override
    public void finishFriendPraiseRefresh(List<FriendPraiseEntity> friendPraiseList) {
        mSmartRefreshLayout.finishRefresh();
        if (friendPraiseList != null && friendPraiseList.size() > 0) {
            mEntityList.clear();
            mEntityList.addAll(friendPraiseList);
            mAdapter.setNewData(mEntityList);
        }
    }

    @Override
    public void finishFriendPraiseLoadMore(List<FriendPraiseEntity> friendPraiseList) {
        mSmartRefreshLayout.finishLoadMore();
        if (friendPraiseList != null && friendPraiseList.size() > 0) {
            int size = mEntityList.size();
            mEntityList.addAll(friendPraiseList);
            mAdapter.addData(size, mEntityList);
        }
    }

    @Override
    public void showToastOfPrize(int type, int valueStr) {
        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
        TextView textView = ToastUtils.showCustomShort(R.layout.layout_toast_custom_view)
                .findViewById(R.id.toast_message_tv);
        textView.setText(String.format(TOAST_CONTENT_PRAISE, valueStr));
    }

    @Override
    public void updatePraiseState(FeedbackEntity data) {
        mPresenter.processPraiseResult(data, mEntityList.get(mCurrentPosition));
        mAdapter.notifyItemChanged(mCurrentPosition);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        int viewId = view.getId();
        int dynamicId = mEntityList.get(position).getDynamicId();
        int petId = mEntityList.get(position).getPetId();
        int userId = mEntityList.get(position).getUserId();
        if (viewId == R.id.item_friend_pet_image_iv) {
            // 跳转到讨论区
            ARouter.getInstance()
                    .build(RouterConfig.CommunityModule.COMMUNITY_COMMENT_PATH)
                    .withInt(RouterConfig.CommunityModule.KEY_DYNAMIC_ID, dynamicId)
                    .withString(RouterConfig.CommunityModule.KEY_NICK_NAME, "")
                    .withInt(RouterConfig.CommunityModule.KEY_NAVIGATE_TYPE, RouterConfig.CommunityModule.TYPE_SELECTED)
                    .navigation();
        } else if (viewId == R.id.item_friend_pet_head_iv
                || viewId == R.id.item_friend_pet_name_tv
                || viewId == R.id.item_friend_pet_sex_iv) {
            // 跳转到宠物页面
            ARouter.getInstance()
                    .build(RouterConfig.UserModule.PET_INFO_PATH)
                    .withInt(RouterConfig.UserModule.KEY_PET_ID, petId)
                    .navigation();
        } else if (viewId == R.id.item_friend_praise_name_tv
                || viewId == R.id.item_friend_praise_image_iv) {
            // 跳转到主人详情
            ARouter.getInstance()
                    .build(RouterConfig.UserModule.MASTER_INFO_PATH)
                    .withInt(RouterConfig.UserModule.KEY_USER_ID, userId)
                    .navigation();
        } else if (viewId == R.id.item_send_praise_rb) {
            int ownPraise = mEntityList.get(position).getOwnPraise();
            mCurrentPosition = position;
            mPresenter.sendPraiseRequest(dynamicId, userId, ownPraise);
        }
    }
}
