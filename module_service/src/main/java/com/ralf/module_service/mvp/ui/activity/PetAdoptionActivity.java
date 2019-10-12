package com.ralf.module_service.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_service.R;
import com.ralf.module_service.R2;
import com.ralf.module_service.dg.component.DaggerPetAdoptionComponent;
import com.ralf.module_service.dg.module.PetAdoptionModule;
import com.ralf.module_service.entity.AdoptionEntity;
import com.ralf.module_service.mvp.contract.PetAdoptionContract;
import com.ralf.module_service.mvp.presenter.PetAdoptionPresenter;
import com.ralf.module_service.mvp.ui.adapter.AdoptionAdapter;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ExpertOnlineActivity
 * @email -
 * @date 2019/06/19 18:13
 **/
@Route(path = RouterConfig.ServiceModule.PATH_PET_ADOPTION)
public class PetAdoptionActivity extends BaseSwipeBackActivity<PetAdoptionPresenter>
        implements PetAdoptionContract.View {

    @BindView(R2.id.back_iv)
    ImageView mBackIv;
    @BindView(R2.id.title_name_tv)
    TextView mTitleNameTv;
    @BindView(R2.id.pet_adoption_drop_menu)
    DropDownMenu mDropMenu;
    @BindView(R2.id.pet_adoption_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.pet_adoption_refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R2.id.pet_adoption_title_tv)
    TextView mAdoptionTitleTv;
    @BindView(R2.id.pet_adoption_container_fl)
    FrameLayout mContainerFl;

    private AdoptionAdapter mAdapter;
    private List<AdoptionEntity> mEntityList = new ArrayList<>();
    /**
     * 请求参数
     */
    private String mAdCode = "", mCityCode = "", mLat = "", mLng = "";
    private int petTypeId = 0, mProvinceId, mSex = 0, mSort = 1;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPetAdoptionComponent.builder()
                .appComponent(appComponent)
                .petAdoptionModule(new PetAdoptionModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_pet_adoption;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mTitleNameTv.setText("领养");
        mAdapter = new AdoptionAdapter(R.layout.item_adoption_layout, mEntityList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getAdoptionData(false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.setEnableLoadMore(true);
                getAdoptionData(true);
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AdoptionEntity entity = mEntityList.get(position);
                ToastUtils.showShort("领养详情");
            }
        });
    }

    /**
     * 获取领养数据
     *
     * @param isRefresh 是否是刷新
     */
    private void getAdoptionData(boolean isRefresh) {
        mPresenter.getAdoptionData(isRefresh, mAdCode, mCityCode, mLat, mLng, petTypeId, mProvinceId, mSex, mSort);
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    @OnClick({R2.id.back_iv, R2.id.pet_adoption_title_tv})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.back_iv) {
            finish();
        } else if (i == R.id.pet_adoption_title_tv) {
            ToastUtils.showShort("发布领养");
        }
    }

    @Override
    public void hideLoading() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void finishLoadMoreWithNoMoreData(List<AdoptionEntity> list) {
        mRefreshLayout.finishLoadMoreWithNoMoreData();
        mRefreshLayout.finishRefresh();
//        mRefreshLayout.setEnableLoadMore(false);
        if (list != null && list.size() > 0) {
            mEntityList.clear();
            mAdapter.addData(list);
        }
    }

    @Override
    public void finishRefresh(List<AdoptionEntity> list, boolean hasMore) {
        mRefreshLayout.finishRefresh();
        if (!hasMore) {
            mRefreshLayout.finishLoadMoreWithNoMoreData();
        }
        if (list != null && list.size() > 0) {
            mEntityList.clear();
            mAdapter.addData(list);
        }
    }

    @Override
    public void finishLoadMore(List<AdoptionEntity> list) {
        mRefreshLayout.finishLoadMore();
        if (list != null && list.size() > 0) {
            int position = list.size();
            mAdapter.addData(position, list);
        }
    }
}
