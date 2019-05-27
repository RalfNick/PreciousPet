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
import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.SizeUtils;
import com.jess.arms.utils.ToastUtils;
import com.jess.arms.utils.device_util.ScreenUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.dg.component.DaggerChannelDetailComponent;
import com.ralf.module_community.dg.module.ChannelDetailModule;
import com.ralf.module_community.entity.ChannelAttentionEntity;
import com.ralf.module_community.entity.ChannelDetailEntity;
import com.ralf.module_community.entity.ChannelPostEntity;
import com.ralf.module_community.entity.eventbus.RefreshAttentionChannelEntity;
import com.ralf.module_community.mvp.contract.ChannelDetailContract;
import com.ralf.module_community.mvp.presenter.ChannelDetailPresenter;
import com.ralf.module_community.mvp.ui.adapter.ChannelDetailAdapter;
import com.ralf.module_community.widget.player.ScrollCalculatorHelper;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;
import com.ralf.pet_provider.user.constant.UserConstant;
import com.ralf.pet_provider.util.ImageLoaderHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelActivity
 * @email -
 * @date 2019/04/24 上午11:09
 **/
@Route(path = RouterConfig.CommunityModule.COMMUNITY_CHANNEL_DETAIL_PATH)
public class ChannelActivity extends BaseSwipeBackActivity<ChannelDetailPresenter> implements ChannelDetailContract.View {

    /**
     * 视频自动播放设置位置，上下偏离距离
     */
    private static final int OFFSET_PLAYER_TOP = 100;
    private static final int OFFSET_PLAYER_BOTTOM = 100;

    @Autowired(name = RouterConfig.CommunityModule.KEY_CHANNEL_DETAIL_ID)
    int mChannelId;

    @BindView(R2.id.back_iv)
    ImageView mBackIv;
    @BindView(R2.id.title_name_tv)
    TextView mTitleNameTv;
    @BindView(R2.id.right_iv)
    ImageView mRightIv;
    @BindView(R2.id.right_option_iv)
    ImageView mOptionIv;
    @BindView(R2.id.channel_detail_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.channel_detail_smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R2.id.channel_detail_send_post_tv)
    TextView mSendPostTv;

    private View mHeaderView;
    private View mFooterView;

    private ChannelDetailAdapter mDetailAdapter;
    private List<ChannelPostEntity> mPostEntityList = new ArrayList<>();
    /**
     * 滑动时自动播放视频辅助类
     */
    private ScrollCalculatorHelper mScrollCalculatorHelper;

    /**
     * 计算滑动位置
     */
    private LinearLayoutManager mLayoutManager;
    private int mFirstVisibleItem;
    private int mLastVisibleItem;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerChannelDetailComponent.builder()
                .appComponent(appComponent)
                .channelDetailModule(new ChannelDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        mHeaderView = LayoutInflater.from(this).inflate(R.layout.header_channel_detail_title_layout, null);
        mFooterView = View.inflate(this, R.layout.layout_load_no_more_data, null);
        return R.layout.activity_channel_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mTitleNameTv.setText("全部");
        mRightIv.setVisibility(View.VISIBLE);
        mRightIv.setImageResource(R.mipmap.icon_share);
        mOptionIv.setVisibility(View.VISIBLE);
        mOptionIv.setImageResource(R.mipmap.icon_search);
        mDetailAdapter = new ChannelDetailAdapter(R.layout.item_channel_detail_content, mPostEntityList);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mDetailAdapter);
        mDetailAdapter.addHeaderView(mHeaderView);
        mDetailAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            ChannelPostEntity channelPostEntity = mPostEntityList.get(position);
            int userId = channelPostEntity.getPublisherId();
            int viewId = view.getId();
            if (viewId == R.id.item_channel_detail_user_iv
                    || viewId == R.id.item_channel_detail_user_name_tv) {
                ARouter.getInstance()
                        .build(RouterConfig.UserModule.MASTER_INFO_PATH)
                        .withInt(RouterConfig.UserModule.KEY_USER_ID, userId)
                        .navigation();
            }
        });
        mDetailAdapter.setOnItemClickListener((adapter, view, position) -> {
            ChannelPostEntity channelPostEntity = mPostEntityList.get(position);
            ARouter.getInstance()
                    .build(RouterConfig.CommunityModule.COMMUNITY_CHANNEL_TOPIC_PATH)
                    .withInt(RouterConfig.CommunityModule.
                            KEY_CHANNEL_TOPIC_ID, channelPostEntity.getTopicId())
                    .navigation();
        });
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getChannelDetail(false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mDetailAdapter.removeAllFooterView();
                getChannelDetail(true);
            }
        });
        // 限定范围为屏幕一半的上下偏移180
        int playTop = ScreenUtils.getScreenHeight() / 2 - SizeUtils.dp2px(OFFSET_PLAYER_TOP);
        int playBottom = ScreenUtils.getScreenHeight() / 2 + SizeUtils.dp2px(OFFSET_PLAYER_BOTTOM);
        // 自定播放帮助类
        mScrollCalculatorHelper = new ScrollCalculatorHelper(R.id.video_item_player, playTop, playBottom);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mScrollCalculatorHelper.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mFirstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
                mLastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                // 滑动自动播放的代码
                mScrollCalculatorHelper.onScroll(recyclerView, mFirstVisibleItem, mLastVisibleItem,
                        mLastVisibleItem - mFirstVisibleItem);
            }
        });
        getChannelDetail(true);
    }

    private void getChannelDetail(boolean isRefresh) {
        mPresenter.getChannelDetail(mChannelId, isRefresh, UserConstant.APP_USERID);
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void onFinishLoadMoreWithNoData() {
        mRefreshLayout.finishLoadMoreWithNoMoreData();
        mDetailAdapter.addFooterView(mFooterView);
    }

    @Override
    public void onFinishFresh(ChannelDetailEntity channelPostList) {
        mRefreshLayout.finishRefresh();
        setHeaderView(channelPostList.getChannelTitle());
        List<ChannelPostEntity> postEntities = channelPostList.getChannelPostList();
        if (postEntities != null && postEntities.size() > 0) {
            mPostEntityList.clear();
            mPostEntityList.addAll(postEntities);
            mDetailAdapter.setNewData(mPostEntityList);
        }
    }

    /**
     * 设置 header 部分频道介绍
     *
     * @param channelTitle 频道 title 数据
     */
    private void setHeaderView(ChannelDetailEntity.ChannelTitleBean channelTitle) {
        if (channelTitle != null) {
            ImageView channelImg = mHeaderView.findViewById(R.id.channel_detail_header_iv);
            TextView memberTv = mHeaderView.findViewById(R.id.channel_detail_header_member_num_tv);
            TextView postTv = mHeaderView.findViewById(R.id.channel_detail_header_post_num_tv);
            TextView titleTv = mHeaderView.findViewById(R.id.channel_detail_header_title_tv);
            ImageView attentionImg = mHeaderView.findViewById(R.id.channel_detail_header_attention_btn);
            ImageView seeChannelImg = mHeaderView.findViewById(R.id.channel_detail_header_see_detail_iv);
            ImageLoaderHelper.loadImage(this, channelImg, channelTitle.getChannelUrl(), 3);
            attentionImg.setVisibility(channelTitle.getAttention() == 0 ? View.VISIBLE : View.GONE);
            seeChannelImg.setVisibility(channelTitle.getAttention() == 1 ? View.VISIBLE : View.GONE);
            memberTv.setText(String.format(Constant.CHANNEL_DETAIL_MEMBER_NUM, channelTitle.getMembers()));
            postTv.setText(String.format(Constant.CHANNEL_DETAIL_POST_NUM, channelTitle.getTopicNum()));
            titleTv.setText(channelTitle.getChannelName());

            mHeaderView.setOnClickListener(view ->
                    ARouter.getInstance()
                            .build(RouterConfig.CommunityModule.COMMUNITY_CHANNEL_INFO_PATH)
                            .withInt(RouterConfig.CommunityModule.KEY_DYNAMIC_ID, mChannelId)
                            .navigation());
            attentionImg.setOnClickListener(view -> mPresenter.addAttentionOfChannel(1, mChannelId, 0, UserConstant.APP_USERID));
        } else {
            mHeaderView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFinishLoadData(ChannelDetailEntity channelPostList) {
        mRefreshLayout.finishLoadMore();
        int size = mPostEntityList.size();
        List<ChannelPostEntity> postEntities = channelPostList.getChannelPostList();
        if (postEntities != null && postEntities.size() > 0) {
            mDetailAdapter.addData(size, postEntities);
        }
    }

    @Override
    public void onFinishAttention(ChannelAttentionEntity data) {
        mHeaderView.findViewById(R.id.channel_detail_header_attention_btn).setVisibility(View.GONE);
        mHeaderView.findViewById(R.id.channel_detail_header_see_detail_iv).setVisibility(View.VISIBLE);
        EventBus.getDefault().post(new RefreshAttentionChannelEntity());
    }

    @OnClick({R2.id.back_iv, R2.id.right_iv, R2.id.right_option_iv,
            R2.id.channel_detail_send_post_tv})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.back_iv) {
            finish();
        } else if (i == R.id.right_iv) {
            ToastUtils.showShort("分享");
        } else if (i == R.id.right_option_iv) {
            ToastUtils.showShort("查找");
        } else if (i == R.id.channel_detail_send_post_tv) {
            ToastUtils.showShort("发帖");
        }
    }
}
