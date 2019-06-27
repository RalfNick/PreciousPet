package com.ralf.module_service;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.SizeUtils;
import com.ralf.module_service.constant.ServiceConstant;
import com.ralf.pet_provider.http.HttpUrl;
import com.ralf.pet_provider.router.RouterConfig;
import com.ralf.pet_provider.user.constant.UserConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ServiceFragment
 * @email -
 * @date 2019/06/16 11:44
 **/
@Route(path = RouterConfig.ServiceModule.SERVICE_PATH)
public class ServiceFragment extends BaseFragment {

    @BindView(R2.id.back_iv)
    ImageView mBackIv;
    @BindView(R2.id.title_name_tv)
    TextView mTitleNameTv;
    @BindView(R2.id.service_recycler_view)
    RecyclerView mRecyclerView;

    private List<ViewEntity> mEntityList = new ArrayList<>();

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_service_main, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mBackIv.setVisibility(View.GONE);
        mTitleNameTv.setText(R.string.service_name);
        for (int i = 0; i < ServiceConstant.TITLE.length; i++) {
            ViewEntity viewEntity = new ViewEntity(ServiceConstant.TITLE[i], ServiceConstant.DES[i], ServiceConstant.ICON[i]);
            if (i == 4) {
                viewEntity.isWeb = true;
                viewEntity.url = HttpUrl.SHOP_RU_ZHU;
            } else if (i == 5) {
                viewEntity.isWeb = true;
                viewEntity.url = HttpUrl.APP_URL_DOMAIN
                        + "web/service/zjrzPage?userId="
                        + UserConstant.APP_USERID;
            }
            viewEntity.path = ServiceConstant.PATH[i];
            mEntityList.add(viewEntity);
        }
        ServiceAdapter adapter = new ServiceAdapter(R.layout.item_service_layout, mEntityList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0, SizeUtils.dp2px(2), 0, 0);
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ViewEntity viewEntity = mEntityList.get(position);
                if (viewEntity.isWeb) {
                    ARouter.getInstance()
                            .build(viewEntity.path)
                            .withString(RouterConfig.Provider.KEY_WEB_URL, viewEntity.url)
                            .withString(RouterConfig.Provider.KEY_WEB_TITLE, "")
                            .withString(RouterConfig.Provider.KEY_WEB_MENU, "menu")
                            .navigation();
                } else {
                    ARouter.getInstance()
                            .build(viewEntity.path)
                            .navigation();
                }
            }
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    class ServiceAdapter extends BaseQuickAdapter<ViewEntity, BaseViewHolder> {

        ServiceAdapter(int layoutResId, @Nullable List<ViewEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ViewEntity item) {
            helper.setText(R.id.item_service_title_tv, item.title)
                    .setText(R.id.item_service_des_tv, item.des)
                    .setImageResource(R.id.item_service_iv, item.icon);
        }
    }

    class ViewEntity {
        private String title;
        private String des;
        private int icon;
        private boolean isWeb;
        private String url;
        private String path;

        ViewEntity(String title, String des, int icon) {
            this.title = title;
            this.des = des;
            this.icon = icon;
        }
    }
}
