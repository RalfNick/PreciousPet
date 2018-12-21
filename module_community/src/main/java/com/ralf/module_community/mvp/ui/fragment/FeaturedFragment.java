package com.ralf.module_community.mvp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.base.BaseLazyFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.dg.component.DaggerFeaturedComponent;
import com.ralf.module_community.dg.module.FeaturedModule;
import com.ralf.module_community.entity.FeaturedEntity;
import com.ralf.module_community.mvp.contact.FeaturedContact;
import com.ralf.module_community.mvp.presenter.FeaturedPresenter;
import com.ralf.module_community.mvp.ui.adapter.FeaturedAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Ralf(wanglixin)
 * 精选页面
 * @name FeaturedFragment
 * @email -
 * @date 2018/12/15 下午8:48
 **/
public class FeaturedFragment extends BaseLazyFragment<FeaturedPresenter> implements FeaturedContact.View {

    @BindView(R2.id.featured_header)
    MaterialHeader mHeaderView;
    @BindView(R2.id.featured_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.featured_refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    /**
     * 轮播图，list，adapter,页号
     */
    private Banner mBanner;
    private List<FeaturedEntity> mList = new ArrayList<>();
    private FeaturedAdapter mAdapter;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerFeaturedComponent.builder()
                .appComponent(appComponent)
                .featuredModule(new FeaturedModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_featured, container, false);
    }

    /**
     * 设置点击事件
     */
    private void setClickEvent() {
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtils.showShort("你点击了图片-- " + position);
            }
        });

        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });

        // 子控件点击事件
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        // 子控件内部点击事件
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    protected void loadLargeData() {
        mList.add(new FeaturedEntity("dog - 1"));
        mList.add(new FeaturedEntity("dog - 2"));
        mList.add(new FeaturedEntity("dog - 3"));
        mAdapter.setNewData(mList);
        requestData(true, Constant.TYPE_REFRESH);
    }

    @Override
    protected void initEvent(@Nullable Bundle savedInstanceState) {
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.featured_banner_layout, mRefreshLayout, false);

        mBanner = (Banner) headView;
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(BANNER_ITEMS);
        mAdapter = new FeaturedAdapter(R.layout.item_featured_layout);
        mAdapter.addHeaderView(mBanner);
        mAdapter.openLoadAnimation();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        setClickEvent();
        mBanner.start();
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    /**
     * 请求数据
     *
     * @param isRefresh 是否刷新
     * @param type      类型
     */
    private void requestData(boolean isRefresh, int type) {
        mPresenter.requestData(isRefresh, type);
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(((BannerItem) path).pic).into(imageView);
        }
    }

    public static class BannerItem {

        public int pic;
        public String title;

        public BannerItem() {
        }

        public BannerItem(String title, int pic) {
            this.pic = pic;
            this.title = title;
        }
    }

    public static List<BannerItem> BANNER_ITEMS = new ArrayList<BannerItem>() {{
        add(new BannerItem("最后的骑士", R.mipmap.ic_launcher));
        add(new BannerItem("三生三世十里桃花", R.mipmap.icon_take_pic));
        add(new BannerItem("豆福传", R.mipmap.right_setting));
    }};
}
