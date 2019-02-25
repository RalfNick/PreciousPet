package com.ralf.module_community.mvp.ui.provider;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.jess.arms.http.imageloader.ImageConfig;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.SizeUtils;
import com.jess.arms.utils.StringUtils;
import com.jess.arms.utils.device_util.ScreenUtils;
import com.orhanobut.logger.Logger;
import com.ralf.module_community.R;
import com.ralf.module_community.action.click.SpanTextClick;
import com.ralf.module_community.action.click.TextClickType;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.constant.MultiItemType;
import com.ralf.module_community.entity.AdapterMultiItemEntity;
import com.ralf.module_community.entity.CommentEntity;
import com.ralf.module_community.entity.DynamicEntity;
import com.ralf.module_community.entity.PraiseEntity;
import com.ralf.module_community.mvp.ui.view.FeaturedPersonView;
import com.ralf.module_community.mvp.ui.view.PraiseAnimView;
import com.ralf.module_community.widget.player.BitmapIconEffect;
import com.ralf.module_community.widget.player.GSYVideoGLViewCustomRender;
import com.ralf.module_community.widget.player.SimpleCoverVideo;
import com.ralf.pet_provider.common.PicturePreviewActivity;
import com.ralf.pet_provider.router.RouterConfig;
import com.rockerhieu.emojicon.EmojiconTextView;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ItemContentProvider
 * @email -
 * @date 2018/12/27 下午1:18
 **/
public class ItemContentProvider extends BaseItemProvider<AdapterMultiItemEntity, BaseViewHolder> {

    private static final String TAG = ItemContentProvider.class.getSimpleName();
    /**
     * 图片的路径
     */
    private List<String> mUrlList = new ArrayList<>();
    private ImageLoader mImageLoader;
    private GSYVideoGLViewCustomRender mGSYVideoGLViewCustomRender;
    private BitmapIconEffect mCustomBitmapIconEffect;
    private GSYVideoOptionBuilder mVideoOptionBuilder;
    private SimpleCoverVideo mVideoPlayer;

    /**
     * 总共的评论数3，最多显示3条，超过3条显示更多
     */
    private static final int TOTAL_COMMENT_NUM = 3;
    private String mMoreCommentsDes = "查看更多%s条评论";

    private FeaturedPersonView mPersonView;
    /**
     * 取消点赞 Gif 动画时长
     */
    private int mDuration;

    @Override
    public int viewType() {
        return MultiItemType.TYPE_FOOTER;
    }

    @Override
    public int layout() {
        return R.layout.item_featured_content_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, AdapterMultiItemEntity data, int position) {
        // 设置图片或者视频
        setImageOrVideo(helper, data, position);

        DynamicEntity entity = data.getDynamicBean();
        setPraiseView(helper, entity);
        RadioButton giftButton = helper.getView(R.id.item_content_gift_rb);
        RadioButton commentButton = helper.getView(R.id.item_content_comment_rb);
        RadioButton shareButton = helper.getView(R.id.item_content_share_rb);
        helper.addOnClickListener(R.id.item_content_support_rb);
        helper.addOnClickListener(R.id.item_content_gift_rb);
        helper.addOnClickListener(R.id.item_content_comment_rb);
        helper.addOnClickListener(R.id.item_content_share_rb);
        // 评论消息显示
        convertCommentsDetail(helper, entity);
        // 查看更多评论
        convertMoreComment(helper, entity);
    }

    /**
     * 设置图片或者视频
     *
     * @param helper   holder
     * @param data     entity
     * @param position 位置
     */
    private void setImageOrVideo(BaseViewHolder helper, AdapterMultiItemEntity data, int position) {
        mVideoPlayer = helper.getView(R.id.video_item_player);
        ImageView imageView = helper.getView(R.id.item_content_iv);
        EmojiconTextView textView = helper.getView(R.id.item_content_comment_tv);
        DynamicEntity dynamicBean = data.getDynamicBean();
        setStateContent(textView, dynamicBean.getTalk());
        int width = dynamicBean.getWidth();
        int height = dynamicBean.getHigh();
        String resPath = dynamicBean.getDynamicsPath();
        String videoPath = dynamicBean.getVideoPrintscreen();
        // 类型：1-视频  0-图片
        int type = dynamicBean.getType();
        if (type == 1) {
            imageView.setVisibility(View.GONE);
            mVideoPlayer.setVisibility(View.VISIBLE);
            setVideoContent(resPath, width, height, videoPath, position);
        } else {
            imageView.setVisibility(View.VISIBLE);
            mVideoPlayer.setVisibility(View.GONE);
            setImageContent(imageView, width, height, resPath);
        }
    }

    /*************************************video and image start************************************/
    /**
     * 设置播放器
     *
     * @param resPath   封面
     * @param width     宽
     * @param height    高
     * @param videoPath 资源路径
     * @param position  位置
     */
    private void setVideoContent(String resPath, int width, int height, String videoPath, int position) {

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.transToHeight(width, height));
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mVideoPlayer.setLayoutParams(params);
        if (imageView.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) imageView.getParent();
            viewGroup.removeView(imageView);
        }
        mVideoOptionBuilder = new GSYVideoOptionBuilder();
        mImageLoader = ArmsUtils.obtainAppComponentFromContext(mContext).imageLoader();
        ImageConfig imageConfig = ImageConfigImpl.builder()
                .imageView(imageView)
                .url(resPath)
                .isCircle(false)
                .build();
        mImageLoader.loadImage(mContext, imageConfig);
        mVideoOptionBuilder.setIsTouchWiget(false)
                .setThumbImageView(imageView)
                .setUrl(videoPath)
                .setCacheWithPlay(false)
                .setLockLand(true)
                .setPlayTag(TAG)
                .setPlayPosition(position)
                .build(mVideoPlayer);
        setWaterPrint();
        // 隐藏 title
        mVideoPlayer.getTitleTextView().setVisibility(View.GONE);
        // 设置返回键
        mVideoPlayer.getBackButton().setVisibility(View.GONE);
        // 设置全屏按键功能
        mVideoPlayer.getFullscreenButton().setVisibility(View.GONE);
    }

    /**
     * 水印图效果
     */
    private void setWaterPrint() {
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.video_image);
        mGSYVideoGLViewCustomRender = new GSYVideoGLViewCustomRender();
        mCustomBitmapIconEffect = new BitmapIconEffect(bitmap, SizeUtils.dp2px(50), SizeUtils.dp2px(50), 0.6f);
        mGSYVideoGLViewCustomRender.setBitmapEffect(mCustomBitmapIconEffect);
        mVideoPlayer.setCustomGLRenderer(mGSYVideoGLViewCustomRender);
        mVideoPlayer.setGLRenderMode(GSYVideoGLView.MODE_RENDER_SIZE);
    }

    /**
     * 设置图片
     *
     * @param imageView ImageView
     * @param width     宽
     * @param height    高
     * @param imagePath 图片路径
     */
    private void setImageContent(ImageView imageView, int width, int height, String imagePath) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.transToHeight(width, height));
        imageView.setLayoutParams(params);
        mImageLoader = ArmsUtils.obtainAppComponentFromContext(mContext).imageLoader();
        ImageConfig imageConfig = ImageConfigImpl.builder()
                .imageView(imageView)
                .url(imagePath)
                .isCircle(false)
                .build();
        mImageLoader.loadImage(mContext, imageConfig);
        // 设置点击事件
        mUrlList.clear();
        mUrlList.add(imagePath);
        mUrlList.add(imagePath);
        String[] urlArr = new String[mUrlList.size()];
        mUrlList.toArray(urlArr);
        imageView.setOnClickListener(v ->
                PicturePreviewActivity.startPreViewPicActivity(mContext, urlArr, 0));
    }

    /**
     * 设置状态内容
     *
     * @param textView tv
     * @param content  内容
     */
    private void setStateContent(EmojiconTextView textView, String content) {
        if (StringUtils.isEmpty(content)) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(content);
            textView.setVisibility(View.VISIBLE);
        }
    }

    /***************************************video and image end************************************/

    /**
     * @param helper ViewHolder
     * @param entity 数据
     */
    private void setPraiseView(BaseViewHolder helper, DynamicEntity entity) {
        Integer ownPraise = entity.getOwnPraise();
        Integer bePraiseTimes = entity.getBePraiseTimes();
        mPersonView = helper.getView(R.id.item_content_person_view);
        // 点赞总人数
        TextView personNumTv = helper.getView(R.id.item_content_person_num);
        helper.addOnClickListener(R.id.item_content_person_num);
        if (bePraiseTimes < 1) {
            personNumTv.setVisibility(View.GONE);
        } else {
            personNumTv.setVisibility(View.VISIBLE);
            personNumTv.setText(String.valueOf(bePraiseTimes));
        }
        setHeadPortraitData(entity);
        RadioButton supportButton = helper.getView(R.id.item_content_support_rb);
        ImageView disSupportIv = helper.getView(R.id.item_content_not_support_iv);
        PraiseAnimView praiseAnimView = helper.getView(R.id.item_content_praise_anim_view);

        supportButton.setChecked(ownPraise == 1);
        if (entity.getRefreshType() == DynamicEntity.RefreshType.REFRESH_STATE_PRAISE) {
            if (ownPraise == 1) {
                disSupportIv.setVisibility(View.GONE);
                praiseAnimView.bringToFront();
                praiseAnimView.startAnim();
            } else {
                disSupportIv.setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .asGif()
                        .load(Constant.GIF_REMOVE_PRAISE)
                        .listener(new RequestListener<GifDrawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                        Target<GifDrawable> target, boolean isFirstResource) {
                                disSupportIv.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GifDrawable resource, Object model,
                                                           Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                                // 计算动画时长
                                if (mDuration == 0) {
                                    mDuration = getGifDuration(resource);
                                }
                                disSupportIv.postDelayed(() -> disSupportIv.setVisibility(View.GONE), mDuration);
                                return false;
                            }
                        })
                        .into(disSupportIv);
            }
        }
    }

    /**
     * 计算 gif 的时长
     *
     * @param resource GifDrawable
     * @return
     */
    private int getGifDuration(GifDrawable resource) {
        try {
            Class gifStateClass = Class.forName("com.bumptech.glide.load.resource.gif.GifDrawable$GifState");
            Field frameLoaderField = gifStateClass.getDeclaredField("frameLoader");
            frameLoaderField.setAccessible(true);
            Object frameLoader = frameLoaderField.get(resource.getConstantState());

            Class frameLoaderClass = Class.forName("com.bumptech.glide.load.resource.gif.GifFrameLoader");
            Field gifDecoderField = frameLoaderClass.getDeclaredField("gifDecoder");
            gifDecoderField.setAccessible(true);
            GifDecoder gifDecoder = (GifDecoder) gifDecoderField.get(frameLoader);

            int duration = 0;
            for (int i = 0; i < resource.getFrameCount(); i++) {
                duration += gifDecoder.getDelay(i);
            }
            return duration;
        } catch (Exception e) {
            Logger.e("getGifDuration - " + e.getMessage());
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * 设置评论部分视图
     *
     * @param helper holder
     */
    private void convertCommentsDetail(BaseViewHolder helper, DynamicEntity entity) {
        LinearLayout commentLayout = helper.getView(R.id.item_content_comment_ll);
        EmojiconTextView firstMsgTv = helper.getView(R.id.item_content_comment_first);
        EmojiconTextView secondMsgTv = helper.getView(R.id.item_content_comment_second);
        EmojiconTextView thirdMsgTv = helper.getView(R.id.item_content_comment_third);

        List<CommentEntity> commentList = entity.getCommentList();
        if (commentList != null && commentList.size() > 0) {
            int size = commentList.size();
            if (size == 1) {
                secondMsgTv.setVisibility(View.GONE);
                thirdMsgTv.setVisibility(View.GONE);
            } else if (size == 2) {
                thirdMsgTv.setVisibility(View.GONE);
            }
        } else {
            commentLayout.setVisibility(View.GONE);
        }
        // 设置评论的数据，一共三条
        EmojiconTextView[] textViews = new EmojiconTextView[]{firstMsgTv, secondMsgTv, thirdMsgTv};
        int i = 0;
        if (commentList != null && commentList.size() > 0) {
            commentLayout.setVisibility(View.VISIBLE);
            for (CommentEntity commentEntity : commentList) {
                if (i == 3) {
                    break;
                }
                // 设置 Span 文本
                SpannableStringBuilder builder = getSpannableStringBuilder(commentEntity);
                textViews[i].setText(builder);
                textViews[i].setUseSystemDefault(false);
                textViews[i].setVisibility(View.VISIBLE);
                textViews[i].setMovementMethod(LinkMovementMethod.getInstance());
                i++;
            }
        }
    }

    /**
     * 获取 Span 文本
     *
     * @param commentEntity
     * @return
     */
    @NonNull
    private SpannableStringBuilder getSpannableStringBuilder(CommentEntity commentEntity) {
        String fromNickName = commentEntity.getNickName();
        String toNickname = commentEntity.getToNickName();
        String content = commentEntity.getContent();
        int fromId = commentEntity.getUserId();
        int toUserId = commentEntity.getToUserId();
        SpannableStringBuilder builder;
        int color = mContext.getResources().getColor(R.color.comment_nickname);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
        if (commentEntity.getToUserId() == 0) {
            builder = new SpannableStringBuilder(fromNickName);
            builder.setSpan(colorSpan, 0, fromNickName.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            builder.append(" : ");
        } else {
            // 回复的人的文本设置
            builder = new SpannableStringBuilder(fromNickName).append(" 回复 ");
            SpannableStringBuilder toBuilder = new SpannableStringBuilder(toNickname);
            toBuilder.setSpan(colorSpan, 0, toBuilder.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            toBuilder.setSpan(SpanTextClick.getClicker(toUserId, "", 0, TextClickType.TYPE_PERSON_NAME),
                    0, toBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append(toBuilder);
        }
        // click event
        builder.setSpan(SpanTextClick.getClicker(fromId, "", 0, TextClickType.TYPE_PERSON_NAME),
                0, fromNickName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // content click
        int beforeLen = builder.length();
        builder.append(content);
        builder.setSpan(SpanTextClick.getClicker(fromId, "", 0, TextClickType.TYPE_COMMENT_TEXT),
                beforeLen, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    /**
     * 查看更多评论
     *
     * @param helper holder
     * @param entity 数据
     */
    private void convertMoreComment(BaseViewHolder helper, DynamicEntity entity) {
        // 查看更多评论
        TextView msgMoreTv = helper.getView(R.id.item_content_comment_more);
        helper.addOnClickListener(R.id.item_content_comment_more);
        int commentNum = entity.getCommentTotal();
        if (commentNum > TOTAL_COMMENT_NUM) {
            msgMoreTv.setText(String.format(mMoreCommentsDes, commentNum));
            msgMoreTv.setVisibility(View.VISIBLE);
        } else {
            msgMoreTv.setVisibility(View.GONE);
        }
    }

    /**
     * 加载头像数据
     *
     * @param entity
     */
    private void setHeadPortraitData(DynamicEntity entity) {
        List<PraiseEntity> praiseList = entity.getPraiseList();
        Map<Integer, String> headDataMap = new LinkedHashMap<>();
        if (praiseList != null && praiseList.size() > 0) {
            for (PraiseEntity bean : praiseList) {
                headDataMap.put(bean.getUserId(), bean.getHeadPortrait());
            }
        }
        mPersonView.setClickListener(
                userId -> ARouter.getInstance()
                        .build(RouterConfig.UserModule.MASTER_INFO_PATH)
                        .withInt(RouterConfig.UserModule.KEY_USER_ID, userId)
                        .navigation()
        );
        mPersonView.setHeadPortaitData(headDataMap);
    }
}
