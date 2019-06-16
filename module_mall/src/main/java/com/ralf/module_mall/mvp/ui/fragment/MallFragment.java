package com.ralf.module_mall.mvp.ui.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_mall.R;
import com.ralf.module_mall.R2;
import com.ralf.module_mall.dg.component.DaggerMallComponent;
import com.ralf.module_mall.dg.module.MallModule;
import com.ralf.module_mall.mvp.contract.MallContract;
import com.ralf.module_mall.mvp.presenter.MallPresenter;
import com.ralf.module_mall.mvp.ui.view.MallClickSupport;
import com.ralf.module_mall.mvp.ui.view.SimpleContentView;
import com.ralf.module_mall.mvp.ui.view.SimpleGongnengView;
import com.ralf.module_mall.mvp.ui.view.SimpleGudingImage;
import com.ralf.module_mall.mvp.ui.view.SimpleImageView;
import com.ralf.module_mall.mvp.ui.view.SimpleTitleView;
import com.ralf.pet_provider.router.RouterConfig;
import com.ralf.pet_provider.util.ImageLoaderHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.TangramEngine;
import com.tmall.wireless.tangram.util.IInnerImageSetter;

import org.json.JSONArray;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MallFragment
 * @email -
 * @date 2019/06/13 16:49
 **/
@Route(path = RouterConfig.MallModule.MALL_PATH)
public class MallFragment extends BaseFragment<MallPresenter> implements MallContract.View {

    @BindView(R2.id.mall_back_iv)
    ImageView mBackIv;
    @BindView(R2.id.mall_class_iv)
    ImageView mClassIv;
    @BindView(R2.id.mall_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.mall_refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private TangramEngine mEngine;
    private TangramBuilder.InnerBuilder mBuilder;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMallComponent.builder()
                .appComponent(appComponent)
                .mallModule(new MallModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mall_main, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mBackIv.setVisibility(View.GONE);
        TangramBuilder.init(mContext, new IInnerImageSetter() {
            @Override
            public <IMAGE extends ImageView> void doLoadImageUrl(@NonNull IMAGE view, @Nullable String url) {
                ImageLoaderHelper.loadImage(mContext, view, url, false);
            }
        }, ImageView.class);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getMallData(false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getMallData(true);
            }
        });
        mRefreshLayout.setEnableLoadMore(false);
        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(2, 2, 2, 2);
            }
        };
        mRecyclerView.addItemDecoration(itemDecoration);
        initTangram();
        mPresenter.getMallData(true);
    }

    private void initTangram() {
        /**
         * step2
         * 注册内置Card，初始化设置
         */
        mBuilder = TangramBuilder.newInnerBuilder(mContext);
        /**
         * step3
         * 注册Cell、自定义Card
         */
        mBuilder.registerCell(1, SimpleImageView.class); //简单图片
        mBuilder.registerCell(2, SimpleTitleView.class); //标题栏
        mBuilder.registerCell(3, SimpleGongnengView.class); //菜单、功能键
        mBuilder.registerCell(4, SimpleContentView.class); //猜你喜欢
        mBuilder.registerCell(5, SimpleGudingImage.class); //购物车
        //        mBuilder.registerCard(6, OnePlusNExCard.class); //1拖n扩展(未用)
        /**
         * step4
         * 提交初始化
         */
        mEngine = mBuilder.build();
        /**
         * step5
         * 上拉加载策略，需在最后一条数据加字段
         * "loadType": 1(必加)
         * "load": "com.tmall.request.load.more",
         */
        //        mEngine.addCardLoadSupport(new CardLoadSupport(
        //                new AsyncPageLoader() {
        //                    @Override
        //                    public void loadData(int page, @NonNull Card card, @NonNull LoadedCallback callback) {
        //
        //                        JSONArray jsonArray = mPresenter.getMoreJSONArray();
        //                        List<BaseCell> baseCells = mEngine.parseComponent(jsonArray);
        //                        Log.i("shopping", "***" + page);
        //                        if (card.page == 1) {
        //                            card.setCells(baseCells);
        //                        } else {
        //                            card.addCells(baseCells);
        //                        }
        //                        callback.finish(card.page != 6);
        //                        card.notifyDataChange();
        //
        //                    }
        //                }
        //        ));

        /**
         * step6
         * 注册Cell的点击事件
         */
        mEngine.addSimpleClickSupport(new MallClickSupport());
        /**
         * step7
         * 是否自动加载
         */
        mEngine.enableAutoLoadMore(true);
        /**
         * step8
         * 绑定到RecyclerView上
         */
        mEngine.bindView(mRecyclerView);
        /**
         * step9
         * 上拉加载预加载,
         * 当滑动到特定的条目时，上拉加载数据
         */
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mEngine.onScrolled();
            }
        });
        /**
         * step10
         * 设置浮动值，
         * 不设置上拉加载无效
         */
        mEngine.getLayoutManager().setFixOffset(0, 40, 0, 0);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    @OnClick({R2.id.mall_back_iv, R2.id.mall_class_iv})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.mall_back_iv) {

        } else if (i == R.id.mall_class_iv) {

        }
    }

    @Override
    public void showDataOnView(boolean isRefresh, JSONArray jsonArray) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
        if (isRefresh) {
            mEngine.setData(jsonArray);
        } else {
            mEngine.appendData(jsonArray);
        }
    }
}
