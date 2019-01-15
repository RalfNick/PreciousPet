package com.ralf.module_community.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.ralf.module_community.R;
import com.ralf.module_community.dg.component.DaggerCommentComponent;
import com.ralf.module_community.dg.module.CommentModule;
import com.ralf.module_community.mvp.contact.CommentContact;
import com.ralf.module_community.mvp.presenter.CommentPresenter;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommentActivity
 * @email -
 * @date 2019/01/15 上午10:00
 **/
@Route(path = RouterConfig.CommunityModule.COMMUNITY_COMMENT_PATH)
public class CommentActivity extends BaseSwipeBackActivity<CommentPresenter> implements CommentContact.View {

    @Autowired(name = RouterConfig.CommunityModule.KEY_USER_ID)
    int mUserId;

    @Autowired(name = RouterConfig.CommunityModule.KEY_FROM_USER_ID)
    int mFromId;

    @Autowired(name = RouterConfig.CommunityModule.KEY_NICK_NAME)
    String mNickName;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommentComponent.builder()
                .appComponent(appComponent)
                .commentModule(new CommentModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_comment;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        TextView view = (TextView) findViewById(R.id.text_view);
        view.setText("" + mUserId + " " + mFromId + " " + mNickName);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
