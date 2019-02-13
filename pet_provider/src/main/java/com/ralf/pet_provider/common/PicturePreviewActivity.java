package com.ralf.pet_provider.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.jess.arms.base.BaseApplication;
import com.jess.arms.http.imageloader.ImageConfig;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.PermissionUtils;
import com.jess.arms.utils.ToastUtils;
import com.jess.arms.utils.constant.PermissionConstants;
import com.jess.arms.utils.file_util.FileIOUtils;
import com.ralf.pet_provider.R;
import com.ralf.pet_provider.constant.PetConstant;
import com.ralf.pet_provider.entity.DialogEntity;
import com.ralf.pet_provider.http.DownloadPicService;
import com.ralf.pet_provider.router.RouterConfig;
import com.ralf.pet_provider.widget.dialog.CustomAlertDialog;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Ralf(wanglixin)
 * 图片预览 Activity
 * @name PicturePreviewActivity
 * @email -
 * @date 2019/01/17 下午7:35
 **/
public class PicturePreviewActivity extends AppCompatActivity implements View.OnClickListener {

    private String mIndexStr = "%s/%s";

    private ImageLoader mImageLoader;
    private List<String> mUrlsList = new ArrayList<>();
    private CustomAlertDialog mCustomAlertDialog;
    private TextView mPicIndexTextView;

    /**
     * 图片默认位置
     */
    private int mPicIndex = -1;

    public static void startPreViewPicActivity(Context context, String[] urls, int index) {
        Intent intent = new Intent(context, PicturePreviewActivity.class);
        intent.putExtra(RouterConfig.Provider.KEY_PIC_INDEX, index);
        intent.putExtra(RouterConfig.Provider.KEY_PIC_URLS, urls);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_preview);
        initData();
        initView();
        mImageLoader = ArmsUtils.obtainAppComponentFromContext(this).imageLoader();
        PermissionUtils.permission(PermissionConstants.STORAGE).request(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        Intent intent = getIntent();
        mPicIndex = intent.getIntExtra(RouterConfig.Provider.KEY_PIC_INDEX, 0);
        String[] urls = intent.getStringArrayExtra(RouterConfig.Provider.KEY_PIC_URLS);
        mUrlsList.clear();
        if (urls != null && urls.length > 0) {
            mUrlsList.addAll(Arrays.asList(urls));
        }
    }

    /**
     * 初始化视图
     */
    private void initView() {
        ImageView backIv = findViewById(R.id.back_iv);
        backIv.setOnClickListener(this);
        mPicIndexTextView = findViewById(R.id.pic_index_total_tv);
        if (mPicIndex > -1) {
            mPicIndexTextView.setVisibility(View.VISIBLE);
            mPicIndexTextView.setText(String.format(mIndexStr, mPicIndex + 1, mUrlsList.size()));
        } else {
            mPicIndexTextView.setVisibility(View.GONE);
        }
        TextView titleNameTv = findViewById(R.id.title_name_tv);
        titleNameTv.setText("查看图片");
        ViewPager viewPager = findViewById(R.id.view_pager_image);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mPicIndex = i;
                mPicIndexTextView.setText(String.format(mIndexStr, mPicIndex + 1, mUrlsList.size()));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        SimplePagerAdapter pagerAdapter = new SimplePagerAdapter();
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(mPicIndex);

        List<DialogEntity> data = new ArrayList<>();
        data.add(new DialogEntity("保存图片"));
        mCustomAlertDialog = new CustomAlertDialog(this, data);
        mCustomAlertDialog.setClickListener(position -> {
            savePicture(mPicIndex);
            mCustomAlertDialog.dismiss();
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.back_iv) {
            finish();
        }
    }

    class SimplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mUrlsList.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            photoView.setOnLongClickListener(v -> {
                if (mCustomAlertDialog.isShowing()) {
                    mCustomAlertDialog.dismiss();
                } else {
                    if (mPicIndex > -1) {
                        mCustomAlertDialog.show();
                    }
                }
                return false;
            });
            ImageConfig imageConfig = ImageConfigImpl.builder()
                    .url(mUrlsList.get(position))
                    .isCircle(false)
                    .imageView(photoView)
                    .build();
            mImageLoader.loadImage(container.getContext(), imageConfig);
            return photoView;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * 保存图片到本地
     *
     * @param position 位置
     */
    private void savePicture(int position) {
        String url = mUrlsList.get(position);
        BaseApplication baseApplication = (BaseApplication) getApplication();
        baseApplication.getAppComponent()
                .repositoryManager()
                .obtainRetrofitService(DownloadPicService.class)
                .downLoadPicture(url)
                .subscribeOn(Schedulers.io())
                .map(responseBody -> {
                    // 保存到本地
                    InputStream inputStream = responseBody.byteStream();
                    if (inputStream != null) {
                        String imageName = PetConstant.IMAGE_PATH_PREFIX + "_" + System.currentTimeMillis() + ".jpg";
                        String picturePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                                .getAbsolutePath() + "/Pet/";
                        File file = new File(picturePath, imageName);
                        FileIOUtils.inputStreamToFile(inputStream, file);
                        return file;
                    }
                    return null;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<File>() {
                    @Override
                    public void onNext(File file) {
                        if (file != null && file.exists()) {
                            ToastUtils.showShort("图片保存成功");
                        } else {
                            ToastUtils.showShort("图片保存失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("图片保存失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
