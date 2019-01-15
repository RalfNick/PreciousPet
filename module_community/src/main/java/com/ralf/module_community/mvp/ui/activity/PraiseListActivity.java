package com.ralf.module_community.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.ralf.module_community.R;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;

/**
 * @author Ralf(wanglixin)
 * 点赞列表页面
 * @name PraiseListActivity
 * @email -
 * @date 2019/01/15 下午1:35
 **/
@Route(path = RouterConfig.CommunityModule.COMMUNITY_PRAISE_LIST_PATH)
public class PraiseListActivity extends BaseSwipeBackActivity {

    @Autowired(name = RouterConfig.CommunityModule.KEY_USER_ID)
    int mUserId;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_praise_list;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
