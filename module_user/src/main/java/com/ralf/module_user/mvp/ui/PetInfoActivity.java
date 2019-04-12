package com.ralf.module_user.mvp.ui;

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
import com.jess.arms.utils.StringUtils;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_user.R;
import com.ralf.module_user.R2;
import com.ralf.module_user.dg.component.DaggerPetComponent;
import com.ralf.module_user.dg.module.PetModule;
import com.ralf.module_user.entity.PetDetailEntity;
import com.ralf.module_user.entity.PetRecordEntity;
import com.ralf.module_user.mvp.contact.PetContract;
import com.ralf.module_user.mvp.presenter.PetPresenter;
import com.ralf.module_user.mvp.ui.adapter.PetAdapter;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.common.PicturePreviewActivity;
import com.ralf.pet_provider.router.RouterConfig;
import com.ralf.pet_provider.util.ImageLoaderHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetInfoActivity
 * @email -
 * @date 2019/04/11 上午8:44
 **/
@Route(path = RouterConfig.UserModule.PET_INFO_PATH)
public class PetInfoActivity extends BaseSwipeBackActivity<PetPresenter>
        implements PetContract.View, View.OnClickListener {

    private static final String TITLE = "宠物详情";
    private static final String PRAISE_NUM_TXT = "%s 个赞";
    private static final String PET_AGE_TXT = "%s岁";

    @Autowired(name = RouterConfig.UserModule.KEY_PET_ID)
    int mPetId;

    @BindView(R2.id.back_iv)
    ImageView mBackIv;
    @BindView(R2.id.title_name_tv)
    TextView mTitleTv;
    @BindView(R2.id.right_iv)
    ImageView mRightIv;
    @BindView(R2.id.pet_info_list_rv)
    RecyclerView mRecyclerView;
    @BindView(R2.id.pet_smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private View mHeaderView;

    private PetAdapter mAdapter;
    private List<PetRecordEntity> mPetList = new ArrayList<>();

    /**
     * 当前用户 id，宠物头像 url
     */
    private int mUserId;
    private String mPetHeadUrl;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPetComponent.builder()
                .appComponent(appComponent)
                .petModule(new PetModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        mHeaderView = LayoutInflater.from(this).inflate(R.layout.layout_head_pet_info, null);
        return R.layout.acrivity_pet_info;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setUpView();
        getPetData(true);
    }

    private void getPetData(boolean isRefresh) {
        mPresenter.getPetData(mPetId, isRefresh);
    }

    private void setUpView() {
        mTitleTv.setText(TITLE);
        mRightIv.setImageResource(R.mipmap.pet_share);
        mRightIv.setVisibility(View.VISIBLE);
        mAdapter = new PetAdapter(R.layout.item_pet_record_layout, mPetList);
        mAdapter.addHeaderView(mHeaderView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getPetData(false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getPetData(true);
            }
        });
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            PetRecordEntity entity = mPetList.get(position);
            ARouter.getInstance()
                    .build(RouterConfig.CommunityModule.COMMUNITY_COMMENT_PATH)
                    .withInt(RouterConfig.CommunityModule.KEY_USER_ID, mUserId)
                    .withString(RouterConfig.CommunityModule.KEY_NICK_NAME, "")
                    .withInt(RouterConfig.CommunityModule.KEY_DYNAMIC_ID, entity.getDynamicId())
                    .withInt(RouterConfig.CommunityModule.KEY_NAVIGATE_TYPE, RouterConfig.CommunityModule.TYPE_SELECTED)
                    .withInt(RouterConfig.CommunityModule.KEY_FROM_ITEM_POSITION, position)
                    .navigation();
        });
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    @OnClick({R2.id.back_iv, R2.id.right_iv})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.back_iv) {
            finish();
        } else if (id == R.id.right_iv) {
            ToastUtils.showShort("分享宠物");
        } else if (id == R.id.item_pet_head_portrait_iv) {
            if (!StringUtils.isEmpty(mPetHeadUrl)) {
                PicturePreviewActivity.startPreViewPicActivity(this, new String[]{mPetHeadUrl}, 0);
            }
        } else if (id == R.id.head_pet_info_layout) {
            ToastUtils.showShort("宠物详细信息");
        }
    }

    @Override
    public void finishRefreshData(PetDetailEntity<PetRecordEntity> data) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.setEnableLoadMore(true);
        if (data != null) {
            mPetList.clear();
            setHeadViewData(data);
            mPresenter.handleData(mPetList, data);
            mAdapter.setNewData(mPetList);
        }
    }

    /**
     * 设置宠物详情，作为 header 部分
     *
     * @param data 数据
     */
    private void setHeadViewData(PetDetailEntity<PetRecordEntity> data) {
        TextView nameTv = mHeaderView.findViewById(R.id.item_pet_head_name_tv);
        nameTv.setText(data.getPetName());
        TextView typeTv = mHeaderView.findViewById(R.id.item_pet_type_tv);
        typeTv.setText(data.getBreed());
        TextView ageTv = mHeaderView.findViewById(R.id.item_pet_age_tv);
        ageTv.setVisibility(View.GONE);
        ImageView sexImage = mHeaderView.findViewById(R.id.item_pet_sex_iv);
        sexImage.setImageResource(data.getPetSex() == 0 ? R.mipmap.pet_boy : R.mipmap.pet_girl);
        TextView praiseNumTv = mHeaderView.findViewById(R.id.item_pet_num_praise_tv);
        ImageView headImage = mHeaderView.findViewById(R.id.item_pet_head_portrait_iv);
        praiseNumTv.setText(String.format(PRAISE_NUM_TXT, data.getBePraisedTimes()));
        ImageLoaderHelper.loadImage(this, headImage, data.getPetHeadPortrait(), false);
        headImage.setOnClickListener(this::onViewClicked);
        mHeaderView.setOnClickListener(this::onViewClicked);
    }

    @Override
    public void finishLoadMoreWithNoData() {
        mRefreshLayout.finishLoadMoreWithNoMoreData();
        mRefreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void finishLoadMore(PetDetailEntity<PetRecordEntity> data) {
        mRefreshLayout.finishLoadMore();
        List<PetRecordEntity> recordList = data.getRecordList();
        if (recordList != null && recordList.size() > 0) {
            mUserId = data.getUserId();
            mPetHeadUrl = data.getPetHeadPortrait();
            mPresenter.handleData(mPetList, data);
            mAdapter.addData(mPetList.size(), recordList);
        }
    }
}
