package com.ralf.module_service.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.StringUtils;
import com.jess.arms.utils.ToastUtils;
import com.jess.arms.utils.device_util.ScreenUtils;
import com.ralf.module_service.R;
import com.ralf.module_service.R2;
import com.ralf.module_service.dg.component.DaggerExpertOnlineComponent;
import com.ralf.module_service.dg.module.ExpertOnlineModule;
import com.ralf.module_service.entity.ExpertOnlineEntity;
import com.ralf.module_service.entity.ExpertOnlineMultiEntity;
import com.ralf.module_service.entity.ExpertOnlineMultiType;
import com.ralf.module_service.mvp.contract.ExpertOnlineContract;
import com.ralf.module_service.mvp.presenter.ExpertOnlinePresenter;
import com.ralf.module_service.mvp.ui.adapter.ExpertOnlineAdapter;
import com.ralf.module_service.mvp.ui.view.ServicePopupWindow;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;
import com.ralf.pet_provider.widget.stickyitemdecoration.OnStickyChangeListener;
import com.ralf.pet_provider.widget.stickyitemdecoration.StickyHeadContainer;
import com.ralf.pet_provider.widget.stickyitemdecoration.StickyItemDecoration;
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
 * @name ExpertOnlineActivity
 * @email -
 * @date 2019/06/19 18:13
 **/
@Route(path = RouterConfig.ServiceModule.PATH_EXPERT_ONLINE)
public class ExpertOnlineActivity extends BaseSwipeBackActivity<ExpertOnlinePresenter> implements ExpertOnlineContract.View {

    private ServicePopupWindow mPopupWindow;
    /**
     * 类型 - popupwindow 点击类型，医疗 - 1  宠物 - 2
     */
    private static final int TYPE_MEDICAL = 1;
    private static final int TYPE_PET = 2;
    private int mSelectType;
    /**
     * 专家擅长类别id, 宠物类型id
     */
    private int mMedicalType = 1;
    private int mPetType = 0;
    /**
     * popupwindow 医疗和宠物的数据集合
     */
    private List<ServicePopupWindow.PopupItem> mMedicalItems = new ArrayList<>();
    private List<ServicePopupWindow.PopupItem> mPetItems = new ArrayList<>();

    @BindView(R2.id.service_back_iv)
    ImageView mBackIv;
    @BindView(R2.id.service_help_tv)
    TextView mHelpTv;
    @BindView(R2.id.service_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.service_refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R2.id.service_search_tv)
    TextView mSearchTv;
    @BindView(R2.id.service_container_rl)
    RelativeLayout mContainerLayout;
    @BindView(R2.id.service_sticky_decoration)
    StickyHeadContainer mStickyHeadContainer;

    private RadioButton mMedicalRb;
    private RadioButton mPetRb;

    private ExpertOnlineAdapter mAdapter;
    private List<ExpertOnlineMultiEntity> mEntityList = new ArrayList<>();
    /**
     * 吸顶布局对应的位置
     */
    private int mCurrentStickyPos;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerExpertOnlineComponent.builder()
                .appComponent(appComponent)
                .expertOnlineModule(new ExpertOnlineModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_expert_online;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mAdapter = new ExpertOnlineAdapter(mEntityList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getExpertOnlineData(false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.setEnableLoadMore(true);
                getExpertOnlineData(true);
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ExpertOnlineMultiEntity entity = mEntityList.get(position);
                if (entity.getItemType() == ExpertOnlineMultiType.TYPE_COMMENT) {
                    ToastUtils.showShort("跳转到求助详情");
                }
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int viewId = view.getId();
                if (viewId == R.id.expert_choice_medical_rb) {
                    mSelectType = TYPE_MEDICAL;
                    mMedicalRb.setChecked(true);
                    showPopupWindow(position);
                } else if (viewId == R.id.expert_choice_pet_rb) {
                    mSelectType = TYPE_PET;
                    mPetRb.setChecked(true);
                    showPopupWindow(position);
                }
            }
        });
        initStickyView();
        getExpertOnlineData(true);
    }

    /**
     * 获取医疗相关数据
     *
     * @param isRefresh 是否是刷新
     */
    private void getExpertOnlineData(boolean isRefresh) {
        mPresenter.getExpertOnlineData(isRefresh, mMedicalType, mPetType);
    }

    private void initStickyView() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int viewId = view.getId();
                if (viewId == R.id.expert_choice_medical_rb) {
                    mSelectType = TYPE_MEDICAL;
                    mMedicalRb.setChecked(true);
                    showPopupWindow(mCurrentStickyPos);
                } else if (viewId == R.id.expert_choice_pet_rb) {
                    mSelectType = TYPE_PET;
                    mPetRb.setChecked(true);
                    showPopupWindow(mCurrentStickyPos);
                }

            }
        };
        mPetRb = mStickyHeadContainer.findViewById(R.id.expert_choice_pet_rb);
        mMedicalRb = mStickyHeadContainer.findViewById(R.id.expert_choice_medical_rb);
        mPetRb.setOnClickListener(listener);
        mMedicalRb.setOnClickListener(listener);
        StickyItemDecoration itemDecoration = new StickyItemDecoration(mStickyHeadContainer, ExpertOnlineMultiType.TYPE_OPTION);
        mStickyHeadContainer.setDataCallback(new StickyHeadContainer.DataCallback() {
            @Override
            public void onDataChange(int pos) {
                mCurrentStickyPos = pos;
                ExpertOnlineMultiEntity entity = mEntityList.get(pos);
                String medical = StringUtils.isEmpty(entity.getMedicalType()) ? getString(R.string.expert_medical) : entity.getMedicalType();
                String petType = StringUtils.isEmpty(entity.getPetType()) ? getString(R.string.all) : entity.getPetType();
                mMedicalRb.setText(medical);
                mPetRb.setText(petType);
            }
        });
        itemDecoration.setOnStickyChangeListener(new OnStickyChangeListener() {
            @Override
            public void onScrollable(int offset) {
                mStickyHeadContainer.scrollChild(offset);
                mStickyHeadContainer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onInVisible() {
                mStickyHeadContainer.reset();
                mStickyHeadContainer.setVisibility(View.INVISIBLE);
            }
        });
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    @OnClick({R2.id.service_back_iv, R2.id.service_help_tv, R2.id.service_search_tv})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.service_back_iv) {
            finish();
        } else if (i == R.id.service_help_tv) {
            ToastUtils.showShort("求助");
        } else if (i == R.id.service_search_tv) {
            ToastUtils.showShort("搜索");
        }
    }

    /**
     * 初始化宠物弹出框数据
     *
     * @param entity 数据
     */
    private void initPetTypeData(ExpertOnlineMultiEntity entity) {
        mPetItems.clear();
        List<ExpertOnlineEntity.PetTypeListBean> petTypeList = entity.getData().getPetTypeList();
        for (ExpertOnlineEntity.PetTypeListBean bean : petTypeList) {
            mPetItems.add(new ServicePopupWindow.PopupItem(bean.getTypeName(), bean.getPetId()));
        }
    }

    /**
     * 初始化医疗弹出框数据
     *
     * @param entity 数据
     */
    private void initMedicalTypeData(ExpertOnlineMultiEntity entity) {
        mMedicalItems.clear();
        List<ExpertOnlineEntity.ExpertExcelListBean> expertExcelList = entity.getData().getExpertExcelList();
        for (ExpertOnlineEntity.ExpertExcelListBean bean : expertExcelList) {
            mMedicalItems.add(new ServicePopupWindow.PopupItem(bean.getExcelName(), bean.getExcelId()));
        }
    }

    private void showPopupWindow(int index) {
        if (mPopupWindow == null) {
            mPopupWindow = new ServicePopupWindow.Builder(this)
                    .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    .focusable(true)
                    .layoutId(R.layout.popup_window_option_lalyout)
                    .animationStyle(R.style.popup_window_anim_style)
                    .outsideTouchable(true)
                    .dismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            setParentViewAlpha(1.0f);
                            mMedicalRb.setChecked(false);
                            mPetRb.setChecked(false);
                            mAdapter.notifyItemChanged(index);
                        }
                    })
                    .itemClickListener(new ServicePopupWindow.ItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            ServicePopupWindow.PopupItem popupItem =
                                    (mSelectType == TYPE_MEDICAL ? mMedicalItems : mPetItems).get(position);
                            String title = popupItem.getItem();
                            ExpertOnlineMultiEntity entity = mEntityList.get(index);
                            if (mSelectType == TYPE_MEDICAL) {
                                mMedicalRb.setText(title);
                                mMedicalType = popupItem.getTypeId();
                                entity.setMedicalType(title);
                            } else {
                                mPetRb.setText(title);
                                mPetType = popupItem.getTypeId();
                                entity.setPetType(title);
                            }
                            getExpertOnlineData(true);
                            mPopupWindow.dismiss();
                        }
                    })
                    .popupItems(mSelectType == TYPE_MEDICAL ? mMedicalItems : mPetItems)
                    .build();
        } else {
            mPopupWindow.setPopupWindowData(mSelectType == TYPE_MEDICAL ? mMedicalItems : mPetItems);
        }
        setParentViewAlpha(0.8f);
        int yOffSet = ScreenUtils.getBottomStatusHeight(this);
        mPopupWindow.showAtLocation(mContainerLayout, Gravity.BOTTOM, 0, yOffSet);
    }

    private void setParentViewAlpha(float alpha) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = alpha;
        getWindow().setAttributes(params);
    }

    @Override
    public void hideLoading() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void finishLoadMoreWithNoData(List<ExpertOnlineMultiEntity> resultList) {
        if (resultList != null && resultList.size() > 0) {
            int position = resultList.size();
            mAdapter.addData(position, resultList);
        }
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.finishLoadMoreWithNoMoreData();
    }

    @Override
    public void finishRefresh(List<ExpertOnlineMultiEntity> resultList, boolean hasMore) {
        mRefreshLayout.finishRefresh();
        if (!hasMore) {
            mRefreshLayout.finishLoadMoreWithNoMoreData();
        }
        if (resultList.size() > 0) {
            mEntityList.clear();
            mEntityList.addAll(resultList);
            mAdapter.setNewData(mEntityList);
            // 初始化 popupwindow 的数据
            ExpertOnlineMultiEntity entity = resultList.get(0);
            initMedicalTypeData(entity);
            initPetTypeData(entity);
        }
    }

    @Override
    public void finishLoadMore(List<ExpertOnlineMultiEntity> resultList) {
        mRefreshLayout.finishLoadMore();
        if (resultList.size() > 0) {
            int position = resultList.size();
            mAdapter.addData(position, resultList);
        }
    }
}
