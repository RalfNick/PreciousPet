package com.ralf.module_news.mvp.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.chrisbanes.photoview.PhotoView;
import com.jaeger.library.StatusBarUtil;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_news.R;
import com.ralf.module_news.R2;
import com.ralf.module_news.dg.component.DaggerPicturePreviewComponent;
import com.ralf.module_news.dg.module.PicturePreviewModule;
import com.ralf.module_news.entity.CollectionEntity;
import com.ralf.module_news.entity.ImageUrlEntity;
import com.ralf.module_news.entity.NewsDetailEntity;
import com.ralf.module_news.mvp.contract.PicturePreviewContract;
import com.ralf.module_news.mvp.presenter.PicturePreviewPresenter;
import com.ralf.module_news.mvp.ui.view.PhotoViewPager;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;
import com.ralf.pet_provider.util.ImageLoaderHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PicturePreviewActivity
 * @email -
 * @date 2019/06/01 14:00
 **/
@Route(path = RouterConfig.NewsModule.NEWS_PATH_PICTURE_PREVIEW)
public class PicturePreviewActivity extends BaseSwipeBackActivity<PicturePreviewPresenter>
        implements PicturePreviewContract.View {

    private String mIndexStr = "%s/%s";

    @Autowired(name = RouterConfig.NewsModule.KEY_IMAGE_ID)
    int mId;

    @BindView(R2.id.back_iv)
    ImageView mBackIv;
    @BindView(R2.id.title_name_tv)
    TextView mTitleNameTv;
    @BindView(R2.id.right_iv)
    ImageView mRightIv;
    @BindView(R2.id.activity_photo_view_pager)
    PhotoViewPager mPhotoViewPager;
    @BindView(R2.id.photo_preview_download_iv)
    ImageView mDownloadIv;
    @BindView(R2.id.photo_preview_collection_tv)
    RadioButton mCollectionTv;
    @BindView(R2.id.photo_preview_page_num_tv)
    TextView mPageNumTv;
    @BindView(R2.id.photo_preview_title_tv)
    TextView mPhotoTitleTv;
    @BindView(R2.id.photo_preview_content_tv)
    TextView mPhotoContentTv;
    @BindView(R2.id.activity_photo_view_toolbar)
    RelativeLayout mTitleLayout;


    private int mCurrentPosition;
    private int mWish;
    private List<String> mUrlList = new ArrayList<>();
    private SamplePagerAdapter mAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPicturePreviewComponent.builder()
                .appComponent(appComponent)
                .picturePreviewModule(new PicturePreviewModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_picture_view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.setTranslucent(this);
        mRightIv.setVisibility(View.VISIBLE);
        mRightIv.setImageResource(R.mipmap.icon_share);
        mTitleNameTv.setVisibility(View.GONE);
        mTitleLayout.setBackgroundColor(Color.BLACK);
        mAdapter = new SamplePagerAdapter();
        mPhotoViewPager.setAdapter(mAdapter);
        mPhotoViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mCurrentPosition = i;
                mPageNumTv.setText(String.format(mIndexStr, mCurrentPosition + 1, mUrlList.size()));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        if (mCurrentPosition > -1) {
            mPageNumTv.setVisibility(View.VISIBLE);
            mPageNumTv.setText(String.format(mIndexStr, mCurrentPosition + 1, mUrlList.size()));
        } else {
            mPageNumTv.setVisibility(View.GONE);
        }
        mPresenter.getPhotoData(mId);
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void updateView(NewsDetailEntity data) {
        mPhotoContentTv.setText(data.getContent());
        mPhotoTitleTv.setText(data.getTitle());
        List<ImageUrlEntity> picList = data.getPicList();
        mUrlList.clear();
        for (ImageUrlEntity entity : picList) {
            mUrlList.add(entity.getPicUrl());
        }
        mAdapter.notifyDataSetChanged();
        mPhotoViewPager.setCurrentItem(0);
        mWish = data.getWish();
        mCollectionTv.setChecked(data.getWish() == 1);
    }

    @Override
    public void updateCollectionView(CollectionEntity data) {
        mWish = data.getStatus();
        boolean checked = data.getStatus() == 1;
        mCollectionTv.setChecked(checked);
        ToastUtils.showShort(checked ? "收藏成功" : "取消收藏");
    }

    @OnClick({R2.id.back_iv, R2.id.right_iv, R2.id.photo_preview_download_iv, R2.id.photo_preview_collection_tv})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.back_iv) {
            finish();
        } else if (i == R.id.photo_preview_download_iv) {
            mPresenter.requestDownLoadImg(mUrlList.get(mCurrentPosition));
        } else if (i == R.id.photo_preview_collection_tv) {
            mPresenter.requestCollection(mId, mWish);
        } else if (i == R.id.right_iv) {
            ToastUtils.showShort("微信分享");
        }
    }

    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mUrlList.size();
        }

        @NonNull
        @Override
        public View instantiateItem(@NonNull ViewGroup container, int position) {
            mCurrentPosition = position;
            PhotoView photoView = new PhotoView(container.getContext());
            String url = mUrlList.get(position);
            ImageLoaderHelper.loadImage(container.getContext(), photoView, url, false);
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

    }
}
