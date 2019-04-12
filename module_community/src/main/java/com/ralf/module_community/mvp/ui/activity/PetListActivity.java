package com.ralf.module_community.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.dg.component.DaggerPetListComponent;
import com.ralf.module_community.dg.module.PetListModule;
import com.ralf.module_community.entity.PetInfoEntity;
import com.ralf.module_community.mvp.contact.PetListContract;
import com.ralf.module_community.mvp.presenter.PetListPresenter;
import com.ralf.module_community.mvp.ui.adapter.PetListAdapter;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.common.PicturePreviewActivity;
import com.ralf.pet_provider.router.RouterConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetListActivity
 * @email -
 * @date 2019/04/08 下午8:34
 **/
@Route(path = RouterConfig.CommunityModule.COMMUNITY_PET_LIST_PATH)
public class PetListActivity extends BaseSwipeBackActivity<PetListPresenter> implements PetListContract.View {

    private static final String TITLE = "宠物列表";

    @Autowired(name = RouterConfig.CommunityModule.KEY_USER_ID)
    int mUserId;

    @BindView(R2.id.back_iv)
    ImageView mBackIv;
    @BindView(R2.id.title_name_tv)
    TextView mTitleTv;
    @BindView(R2.id.pet_list_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R2.id.pet_list_smart_refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private PetListAdapter mPetListAdapter;
    private List<PetInfoEntity> mEntityList = new ArrayList<>();

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPetListComponent.builder()
                .appComponent(appComponent)
                .petListModule(new PetListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_pet_list;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mTitleTv.setText(TITLE);
        mPetListAdapter = new PetListAdapter(R.layout.item_pet_list_layout, mEntityList);
        mPetListAdapter.setOnItemClickListener((adapter, view, position) -> {
            // 跳转到宠物页面
            ARouter.getInstance()
                    .build(RouterConfig.UserModule.PET_INFO_PATH)
                    .withInt(RouterConfig.UserModule.KEY_PET_ID, mEntityList.get(position).getPetId())
                    .navigation();
        });
        mPetListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            int id = view.getId();
            if (id == R.id.item_pet_head_portrait_iv) {
                // 预览图片
                String headPortrait = mEntityList.get(position).getHeadPortrait();
                PicturePreviewActivity.startPreViewPicActivity(PetListActivity.this,
                        new String[]{headPortrait}, 0);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mPetListAdapter);
        mRefreshLayout.setOnRefreshListener(refreshLayout -> getPetData());
        mRefreshLayout.setEnableLoadMore(false);
        getPetData();
    }

    private void getPetData() {
        mPresenter.getDataList(mUserId);
    }

    @Override
    public void finishRefreshData(List<PetInfoEntity> data) {
        mRefreshLayout.finishRefresh();
        if (data != null) {
            mEntityList.clear();
            mEntityList.addAll(data);
            mPetListAdapter.setNewData(mEntityList);
        }
    }

    @OnClick({R2.id.back_iv, R2.id.title_name_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.back_iv:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }
}
