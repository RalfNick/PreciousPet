package com.ralf.module_community.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import com.ralf.module_community.dg.component.DaggerRecommendComponent;
import com.ralf.module_community.dg.module.RecommendModule;
import com.ralf.module_community.entity.RecommendSectionEntity;
import com.ralf.module_community.mvp.contract.RecommendContract;
import com.ralf.module_community.mvp.presenter.RecommendPresenter;
import com.ralf.module_community.mvp.ui.adapter.RecommendSectionAdapter;
import com.ralf.module_community.mvp.ui.decoration.GridSectionAverageGapItemDecoration;
import com.ralf.pet_provider.router.RouterConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.ralf.module_community.constant.Constant.HEAD_TITLE_ARR;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendFragment
 * @email -
 * @date 2019/04/13 上午10:30
 **/
public class RecommendFragment extends BaseLazyFragment<RecommendPresenter> implements RecommendContract.View {

    private static final String TAG = RecommendFragment.class.getSimpleName();

    @BindView(R2.id.recommend_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.recommend_smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    /**
     * 筛选参数
     */
    private String mLat = "", mLng = "";

    private RecommendSectionAdapter mSectionAdapter;
    private List<RecommendSectionEntity> mSectionEntityList = new ArrayList<>();

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerRecommendComponent.builder()
                .appComponent(appComponent)
                .recommendModule(new RecommendModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater,
                         @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommend_layout, container, false);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    protected void loadLargeData() {
        mPresenter.getRecommendData(mLat, mLng);
        mIsLoaded = true;
    }

    @Override
    protected void initEvent(@Nullable Bundle savedInstanceState) {
        mSectionAdapter = new RecommendSectionAdapter(R.layout.item_recommend_content_layout,
                R.layout.head_recommend_title_layout, mSectionEntityList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mRecyclerView.setAdapter(mSectionAdapter);
        mRecyclerView.addItemDecoration(new GridSectionAverageGapItemDecoration(10, 5, 10, 10));
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setOnRefreshListener(refreshLayout -> mPresenter.getRecommendData(mLat, mLng));
        mSectionAdapter.setOnItemClickListener((adapter, view, position) -> {
            RecommendSectionEntity sectionEntity = mSectionEntityList.get(position);
            if (!sectionEntity.isHeader) {
                int id = sectionEntity.t.getId();
                int type = sectionEntity.t.getType();
                if (type == 0) {
                    // 跳转到主人详情
                    ARouter.getInstance()
                            .build(RouterConfig.UserModule.MASTER_INFO_PATH)
                            .withInt(RouterConfig.UserModule.KEY_USER_ID, id)
                            .navigation();
                } else {
                    // 跳转到讨论区
                    ARouter.getInstance()
                            .build(RouterConfig.CommunityModule.COMMUNITY_COMMENT_PATH)
                            .withInt(RouterConfig.CommunityModule.KEY_DYNAMIC_ID, id)
                            .withString(RouterConfig.CommunityModule.KEY_NICK_NAME, "")
                            .withInt(RouterConfig.CommunityModule.KEY_NAVIGATE_TYPE, RouterConfig.CommunityModule.TYPE_SELECTED)
                            .navigation();
                }
            }
        });
        // 更多查看
        mSectionAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            RecommendSectionEntity sectionEntity = mSectionEntityList.get(position);
            if (sectionEntity.isHeader) {
                if (HEAD_TITLE_ARR[0].equals(sectionEntity.header)) {
                    // 跳转到最新动态列表
                    ARouter.getInstance()
                            .build(RouterConfig.CommunityModule.COMMUNITY_LATEST_PATH)
                            .navigation();
                } else if (HEAD_TITLE_ARR[1].equals(sectionEntity.header)) {
                    ToastUtils.showShort("魅力榜");
                } else if (HEAD_TITLE_ARR[2].equals(sectionEntity.header)) {
                    // 跳转到好友点赞
                    ARouter.getInstance()
                            .build(RouterConfig.CommunityModule.COMMUNITY_FRIEND_PRAISE_PATH)
                            .withInt(RouterConfig.CommunityModule.KEY_COMMUNITY_FRIEND_PRISE_TYPE,
                                    RouterConfig.CommunityModule.TYPE_FRIEND_PRISE)
                            .navigation();
                } else if (HEAD_TITLE_ARR[3].equals(sectionEntity.header)) {
                    // 跳转到附近萌宠
                    ARouter.getInstance()
                            .build(RouterConfig.CommunityModule.COMMUNITY_NEAR_PET_PATH)
                            .navigation();
                } else if (HEAD_TITLE_ARR[4].equals(sectionEntity.header)) {
                    // 跳转到新萌露脸
                    ARouter.getInstance()
                            .build(RouterConfig.CommunityModule.COMMUNITY_FRIEND_PRAISE_PATH)
                            .withInt(RouterConfig.CommunityModule.KEY_COMMUNITY_FRIEND_PRISE_TYPE,
                                    RouterConfig.CommunityModule.TYPE_NEW_CUTE_PET)
                            .navigation();
                } else if (HEAD_TITLE_ARR[5].equals(sectionEntity.header)) {
                    // 跳转到热赞榜
                    ARouter.getInstance()
                            .build(RouterConfig.CommunityModule.COMMUNITY_HEAT_PRAISE_PATH)
                            .navigation();
                }
            }
        });
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void setDataToView(List<RecommendSectionEntity> resultList) {
        mRefreshLayout.finishRefresh();
        mSectionEntityList.clear();
        mSectionEntityList.addAll(resultList);
        mSectionAdapter.setNewData(resultList);
    }
}
