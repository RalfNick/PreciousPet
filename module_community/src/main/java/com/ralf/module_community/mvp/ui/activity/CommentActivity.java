package com.ralf.module_community.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.event.transmit.EventPublicApi;
import com.jess.arms.event.transmit.EventPublicApiHelper;
import com.jess.arms.utils.StringUtils;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.dg.component.DaggerCommentComponent;
import com.ralf.module_community.dg.module.CommentModule;
import com.ralf.module_community.entity.AdapterMultiItemEntity;
import com.ralf.module_community.entity.ChannelDetailEntity;
import com.ralf.module_community.entity.CommentEntity;
import com.ralf.module_community.entity.DynamicEntity;
import com.ralf.module_community.mvp.contact.CommentContact;
import com.ralf.module_community.mvp.presenter.CommentPresenter;
import com.ralf.module_community.mvp.ui.adapter.CommentDetailAdapter;
import com.ralf.module_community.mvp.ui.view.CommentInputView;
import com.ralf.module_community.util.CommentInputHelper;
import com.ralf.module_community.util.ShareClickHelper;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;
import com.ralf.pet_provider.user.constant.UserConstant;
import com.ralf.pet_provider.widget.dialog.DialogSure;
import com.rockerhieu.emojicon.EmojiconEditText;
import com.rockerhieu.emojicon.EmojiconGridFragment;
import com.rockerhieu.emojicon.EmojiconsFragment;
import com.rockerhieu.emojicon.emoji.Emojicon;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.ralf.module_community.constant.MultiItemType.TYPE_COMMENT;
import static com.ralf.module_community.constant.MultiItemType.TYPE_CONTENT;
import static com.ralf.module_community.constant.MultiItemType.TYPE_HEADER;
import static com.ralf.module_community.constant.MultiItemType.TYPE_PRAISE;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommentActivity
 * @email -
 * @date 2019/01/15 上午10:00
 **/
@Route(path = RouterConfig.CommunityModule.COMMUNITY_COMMENT_PATH)
public class CommentActivity extends BaseSwipeBackActivity<CommentPresenter>
        implements CommentContact.View, EventPublicApi.LogoutApi,
        EmojiconsFragment.OnBackClickListener, EmojiconGridFragment.OnEmojiconClickedListener {

    private static final String TAG = CommentActivity.class.getSimpleName();

    private static final String TOAST_CONTENT_PRAISE = "点赞奖励%s";

    @Autowired(name = RouterConfig.CommunityModule.KEY_USER_ID)
    int mUserId;

    @Autowired(name = RouterConfig.CommunityModule.KEY_FROM_USER_ID)
    int mFromId;

    @Autowired(name = RouterConfig.CommunityModule.KEY_NICK_NAME)
    String mNickName;

    @Autowired(name = RouterConfig.CommunityModule.KEY_DYNAMIC_ID)
    int mDynamicId;

    /**
     * 跳转的类型（精选 or 频道）
     */
    @Autowired(name = RouterConfig.CommunityModule.KEY_NAVIGATE_TYPE)
    int mNavigateType;

    /**
     * 上一个页面的位置（FeaturedFragment RecyclerView）
     */
    @Autowired(name = RouterConfig.CommunityModule.KEY_ITEM_POSITION)
    String itemPosition;

    @BindView(R2.id.back_iv)
    ImageView mBackIv;
    @BindView(R2.id.title_name_tv)
    TextView mTitleNameTv;
    @BindView(R2.id.right_tv)
    TextView mRightTv;
    @BindView(R2.id.right_iv)
    ImageView mRightIv;
    @BindView(R2.id.layout_title)
    RelativeLayout mLayoutTitle;
    @BindView(R2.id.comment_detail_header_view)
    MaterialHeader mHeaderView;
    @BindView(R2.id.comment_detail_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.comment_detail_refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R2.id.comment_input_view)
    CommentInputView mCommentInputView;

    private EmojiconEditText mEmojiconEditText;

    private DialogSure mDialogSure;
    private List<AdapterMultiItemEntity> mItemEntityList = new ArrayList<>();
    private CommentDetailAdapter mAdapter;
    private CommentInputHelper mInputHelper;

    /**
     * 评论的编号、评论的开始位置
     */
    private int commentIndex = 0;
    private static final int INDEX_START_COMMENT = 3;
    private int startCommentIndex = INDEX_START_COMMENT;

    /**
     * 加载数据，数据到底时显示
     */
    private View mFooterView;

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
        mFooterView = View.inflate(this, R.layout.layout_load_no_more_data, null);
        return R.layout.activity_comment;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        EventPublicApiHelper.register(EventPublicApi.LogoutApi.class, this);
        mTitleNameTv.setText("详情");
        mAdapter = new CommentDetailAdapter(mItemEntityList);
        mAdapter.openLoadAnimation();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getCommentData(false, 0);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mAdapter.removeFooterView(mFooterView);
                mRefreshLayout.setEnableLoadMore(true);
                getCommentData(true, 0);
            }
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> handleClick(position, view.getId()));
        initEmojiView();
        getCommentData(true, 0);
    }

    /**
     * 处理点击事件
     *
     * @param position item 位置
     * @param viewId   控件 id
     */
    private void handleClick(int position, int viewId) {
        AdapterMultiItemEntity itemEntity = mItemEntityList.get(position);
        DynamicEntity dynamicBean = itemEntity.getDynamicBean();
        Integer userId = 0;
        CommentEntity commentEntity = itemEntity.getCommentEntity();
        if (commentEntity != null) {
            userId = commentEntity.getUserId();
        }
        // 弹出消息回复
        if (viewId == R.id.item_content_discuss_layout || viewId == R.id.item_praise_comment_rb) {
            String nickName = commentEntity.getNickName();
            Integer toUserId = commentEntity.getUserId();
            if (toUserId == UserConstant.APP_USERID) {
                ToastUtils.showShort("不能回复自己");
            } else {
                mEmojiconEditText.setHint("回复 " + nickName);
                mInputHelper.showKeyBoard();
                mPresenter.setCurrentComment(commentEntity.getDynamicId(), toUserId);
                CommentInputHelper.isResponse = true;
            }
        } else if (viewId == R.id.discuss_image_iv) {
            ARouter.getInstance()
                    .build(RouterConfig.UserModule.MASTER_INFO_PATH)
                    .withInt(RouterConfig.UserModule.KEY_USER_ID, userId)
                    .navigation();
        } else if (viewId == R.id.header_attention_btn) {
            mPresenter.requestAttentionState(position, dynamicBean);
        } else if (viewId == R.id.header_master_avatar_iv
                || viewId == R.id.header_no_pet_master_name_tv
                || viewId == R.id.header_pet_master_name_tv) {
            // 跳转到主人详情
            ARouter.getInstance()
                    .build(RouterConfig.UserModule.MASTER_INFO_PATH)
                    .withInt(RouterConfig.UserModule.KEY_USER_ID, userId)
                    .navigation();
        } else if (viewId == R.id.header_pet_avatar_iv || viewId == R.id.header_pet_name_tv) {
            // 跳转宠物从详情
            ToastUtils.showShort("宠物详情");
        } else if (viewId == R.id.header_pet_type_tv) {
            // 宠物类型详情
            ToastUtils.showShort("宠物类型");
        } else if (viewId == R.id.item_praise_support_rb) {
            // 获取点击位置的坐标
            setClickViewPosition(position + mAdapter.getHeaderLayoutCount(), dynamicBean, R.id.item_praise_support_rb);
            mPresenter.sendPraise(position, dynamicBean);
        } else if (viewId == R.id.item_praise_gift_rb) {
            ToastUtils.showShort("送礼物");
        } else if (viewId == R.id.item_praise_share_rb) {
            ShareClickHelper.shareCommunity(this, dynamicBean);
        } else if (viewId == R.id.item_praise_person_num) {
            ARouter.getInstance()
                    .build(RouterConfig.CommunityModule.COMMUNITY_PRAISE_LIST_PATH)
                    .withInt(RouterConfig.CommunityModule.KEY_USER_ID, mUserId)
                    .navigation();
        }
    }

    /**
     * 设置点击控件的位置坐标
     *
     * @param position    位置
     * @param dynamicBean 数据
     */
    private void setClickViewPosition(int position, DynamicEntity dynamicBean, @IdRes int resId) {
        View view = mAdapter.getViewByPosition(mRecyclerView, position, resId);
        int[] viewLocations = new int[2];
        if (view != null) {
            view.getLocationOnScreen(viewLocations);
            dynamicBean.setViewLocations(viewLocations);
        }
    }

    private void initEmojiView() {
        EmojiconsFragment fragment = new EmojiconsFragment();
        fragment.setEmojiconView(this);
        EmojiconGridFragment mGridFragment = new EmojiconGridFragment();
        mGridFragment.setmOnEmojiconClickedListener(this);
        mInputHelper = CommentInputHelper.with(this, mCommentInputView)
                .bindContentView(mRefreshLayout)
                .build();

        mEmojiconEditText = mCommentInputView.getmEmojiconEditText();
        ImageView imageView = mCommentInputView.getmEmojiconImage();
        mInputHelper.setSendButtonClick((view, editTextContent) ->
                mPresenter.sendCommentContent(editTextContent));

        resetEmojiconEditText();
        mEmojiconEditText.setOnClickListener((v) -> {
            mInputHelper.showKeyBoard();
            CommentInputHelper.isResponse = true;
        });
    }

    /**
     * 评论框恢复到默认状态，评论楼主
     */
    private void resetEmojiconEditText() {
        mEmojiconEditText.setHint("回复 " + mNickName);
        CommentInputHelper.isResponse = false;
        mInputHelper.hideKeyBoard();
        mPresenter.setCurrentComment(mDynamicId, mUserId);
    }

    /**
     * 请求数据
     */
    private void getCommentData(boolean isRefresh, int type) {
        if (mNavigateType == RouterConfig.CommunityModule.TYPE_CHANNEL) {
            mPresenter.getChannelDetailRequest(isRefresh);
        } else if (mNavigateType == RouterConfig.CommunityModule.TYPE_SELECTED) {
            mPresenter.getSelectedDiscussDetail(isRefresh, mDynamicId);
        }
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventPublicApiHelper.unregister(EventPublicApi.LogoutApi.class);
    }

    @Override
    public void jumpToLoginPage() {
        if (mDialogSure == null) {
            mDialogSure = new DialogSure.Builder(this)
                    .content("您的账号已在别处登录，请重新登录")
                    .cancelable(false)
                    .title("下线通知")
                    .sureListener(dialog -> {
                        ARouter.getInstance()
                                .build(RouterConfig.LoginRegisterModule.ENTRANCE_PATH)
                                .withString(RouterConfig.LoginRegisterModule.KEY_LOGOUT, RouterConfig.LoginRegisterModule.VALUE_LOGOUT)
                                .navigation();
                        dialog.dismiss();
                    })
                    .build();
        }
        if (!mDialogSure.isShowing()) {
            mDialogSure.show();
        }
    }

    @Override
    public void onRefreshChannelView(ChannelDetailEntity data) {
        mItemEntityList.clear();
        DynamicEntity dynamicEntity = new DynamicEntity();
        dynamicEntity.setDynamicId(mUserId);
        dynamicEntity.setBreed(data.getPetBreed());
        dynamicEntity.setCity(data.getCity());
        dynamicEntity.setProvince(data.getProvince());
        dynamicEntity.setUserHeadPortrait(data.getHeadPortrait());
        dynamicEntity.setPetHeadPortrait(data.getPetHeadPortrait());
        dynamicEntity.setAttentionStatus(data.getIsAttention());
        dynamicEntity.setNickName(data.getNickName());
        dynamicEntity.setPetId(data.getUserPetId());
        dynamicEntity.setPetSex(data.getPetSex());
        dynamicEntity.setPetName(data.getPetName());
        dynamicEntity.setTalk(data.getContent());
        List<ChannelDetailEntity.TopicDetailListBean> topicDetailList = data.getTopicDetailList();
        dynamicEntity.setVideoPrintscreen(data.getVideoUrl());
        dynamicEntity.setType(StringUtils.isEmpty(data.getVideoUrl()) ? 0 : 1);
        dynamicEntity.setDynamicsPath(data.getImgUrlList().get(0));
        dynamicEntity.setImageList(data.getImgUrlList());
        dynamicEntity.setWidth(data.getVideoWidth());
        dynamicEntity.setHigh(data.getVideoHeight());

        AdapterMultiItemEntity headerEntity = new AdapterMultiItemEntity(TYPE_HEADER);
        headerEntity.setDynamicBean(dynamicEntity);
        headerEntity.setUserId(mUserId);
        mItemEntityList.add(0, headerEntity);

        AdapterMultiItemEntity contentEntity = new AdapterMultiItemEntity(TYPE_CONTENT);
        contentEntity.setUserId(mUserId);
        contentEntity.setDynamicBean(dynamicEntity);
        mItemEntityList.add(1, contentEntity);

        mRefreshLayout.finishRefresh();
        mAdapter.setNewData(mItemEntityList);
    }

    @Override
    public void onFinishChannelLoadMore(ChannelDetailEntity data) {
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void onFinishDiscussLoadMore(DynamicEntity data) {
        // 加载评论数据
        updateDiscussData(data);
        mRefreshLayout.finishLoadMore();
        int size = data.getCommentList().size();
        mAdapter.notifyItemRangeInserted(mItemEntityList.size() - size, mItemEntityList.size() - 1);
        mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount());
    }

    @Override
    public void onFinishLoadMoreWithNoData() {
        mRefreshLayout.finishLoadMoreWithNoMoreData();
        mRefreshLayout.setEnableLoadMore(false);
        mAdapter.addFooterView(mFooterView, mItemEntityList.size());
    }

    @Override
    public void onRefreshDiscussView(DynamicEntity dynamicEntity) {
        mItemEntityList.clear();
        AdapterMultiItemEntity headerEntity = new AdapterMultiItemEntity(TYPE_HEADER);
        headerEntity.setDynamicBean(dynamicEntity);
        headerEntity.setUserId(mUserId);
        mItemEntityList.add(0, headerEntity);

        AdapterMultiItemEntity contentEntity = new AdapterMultiItemEntity(TYPE_CONTENT);
        contentEntity.setDynamicBean(dynamicEntity);
        contentEntity.setUserId(mUserId);
        mItemEntityList.add(1, contentEntity);

        AdapterMultiItemEntity praiseEntity = new AdapterMultiItemEntity(TYPE_PRAISE);
        praiseEntity.setDynamicBean(dynamicEntity);
        praiseEntity.setUserId(mUserId);
        mItemEntityList.add(2, praiseEntity);
        startCommentIndex = INDEX_START_COMMENT;
        commentIndex = 0;
        // 加载评论数据
        updateDiscussData(dynamicEntity);
        mAdapter.setNewData(mItemEntityList);
    }

    /**
     * 更新评论的数据
     *
     * @param dynamicEntity 数据
     */
    private void updateDiscussData(DynamicEntity dynamicEntity) {
        List<CommentEntity> commentList = dynamicEntity.getCommentList();
        AdapterMultiItemEntity commentEntity;
        if (commentList != null && commentList.size() > 0) {
            for (CommentEntity entity : commentList) {
                entity.setId(commentIndex++);
                entity.setTotalComment(dynamicEntity.getCommentTotal());
                commentEntity = new AdapterMultiItemEntity(TYPE_COMMENT);
                commentEntity.setCommentEntity(entity);
                commentEntity.setUserId(mUserId);
                mItemEntityList.add(startCommentIndex++, commentEntity);
            }
        } else {
            // 用于显示评论条数为 0
            commentEntity = new AdapterMultiItemEntity(TYPE_COMMENT);
            CommentEntity entity = new CommentEntity.Builder().build();
            entity.setTotalComment(0);
            entity.setId(0);
            commentEntity.setUserId(mUserId);
            commentEntity.setCommentEntity(entity);
        }
        mRefreshLayout.finishRefresh();
    }

    @Override
    public void addNewComment(CommentEntity commentEntity) {
        resetEmojiconEditText();
        AdapterMultiItemEntity entity;
        commentEntity.setId(commentIndex++);
        int total;
        if (mItemEntityList.size() >= INDEX_START_COMMENT) {
            total = mItemEntityList.get(INDEX_START_COMMENT).getCommentEntity().getTotalComment();
        } else {
            total = 1;
        }
        commentEntity.setTotalComment(total);
        entity = new AdapterMultiItemEntity(TYPE_COMMENT);
        entity.setUserId(mUserId);
        entity.setCommentEntity(commentEntity);
        mItemEntityList.add(startCommentIndex++, entity);
        mAdapter.notifyItemInserted(mItemEntityList.size() - 1);
        mRecyclerView.smoothScrollToPosition(mItemEntityList.size() - 1);
    }

    @Override
    public void updatePraiseView(int position, DynamicEntity entity) {
        entity.setRefreshType(DynamicEntity.RefreshType.REFRESH_STATE_PRAISE);
        mItemEntityList.get(position).setDynamicBean(entity);
        // 刷新头像
        mAdapter.notifyItemChanged(position + mAdapter.getHeaderLayoutCount());
    }

    @Override
    public void updateAttentionState(int position, int attentionType) {
        DynamicEntity dynamicBean = mItemEntityList.get(position).getDynamicBean();
        dynamicBean.setRefreshType(DynamicEntity.RefreshType.REFRESH_STATE_ATTENTION);
        dynamicBean.setAttentionStatus(attentionType);
        // 刷新关注状态
        mAdapter.notifyItemChanged(position + mAdapter.getHeaderLayoutCount());
    }

    @Override
    public void showToastOfPrize(int type, int valueStr) {
        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
        TextView textView = ToastUtils.showCustomShort(R.layout.layout_toast_custom_view)
                .findViewById(R.id.toast_message_tv);
        textView.setText(String.format(TOAST_CONTENT_PRAISE, valueStr));
    }


    @Override
    public void onBackClicked(View v) {
        mInputHelper.onBackspaceEmojicon();
    }

    @Override
    public void onEmojiconClicked(Emojicon emojicon) {
        mInputHelper.inputEmojicon(emojicon);
    }
}
