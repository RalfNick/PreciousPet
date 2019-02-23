//package com.ralf.module_community.mvp.ui.provider;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.chad.library.adapter.base.provider.BaseItemProvider;
//import com.jess.arms.http.imageloader.ImageConfig;
//import com.jess.arms.http.imageloader.ImageLoader;
//import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
//import com.jess.arms.utils.ArmsUtils;
//import com.jess.arms.utils.SizeUtils;
//import com.jess.arms.utils.StringUtils;
//import com.jess.arms.utils.device_util.ScreenUtils;
//import com.ralf.module_community.R;
//import com.ralf.module_community.constant.MultiItemType;
//import com.ralf.module_community.entity.AdapterMultiItemEntity;
//import com.ralf.module_community.entity.DynamicEntity;
//import com.ralf.module_community.widget.player.BitmapIconEffect;
//import com.ralf.module_community.widget.player.GSYVideoGLViewCustomRender;
//import com.ralf.module_community.widget.player.SimpleCoverVideo;
//import com.ralf.pet_provider.common.PicturePreviewActivity;
//import com.rockerhieu.emojicon.EmojiconTextView;
//import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
//import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Ralf(wanglixin)
// * DESCRIPTION
// * @name ItemContentProvider
// * @email -
// * @date 2018/12/27 下午1:18
// **/
//public class ItemContentProvider extends BaseItemProvider<AdapterMultiItemEntity, BaseViewHolder> {
//
//    private static final String TAG = ItemContentProvider.class.getSimpleName();
//    /**
//     * 图片的路径
//     */
//    private List<String> mUrlList = new ArrayList<>();
//    private ImageLoader mImageLoader;
//    private GSYVideoGLViewCustomRender mGSYVideoGLViewCustomRender;
//    private BitmapIconEffect mCustomBitmapIconEffect;
//    private GSYVideoOptionBuilder mVideoOptionBuilder;
//    private SimpleCoverVideo mVideoPlayer;
//
//    @Override
//    public int viewType() {
//        return MultiItemType.TYPE_CONTENT;
//    }
//
//    @Override
//    public int layout() {
//        return R.layout.item_featured_content1_layout;
//    }
//
//    @Override
//    public void convert(BaseViewHolder helper, AdapterMultiItemEntity data, int position) {
//        mVideoPlayer = helper.getView(R.id.video_item_player);
//        ImageView imageView = helper.getView(R.id.item_content_iv);
//        EmojiconTextView textView = helper.getView(R.id.item_content_comment_tv);
//        DynamicEntity dynamicBean = data.getDynamicBean();
//        setStateContent(textView, dynamicBean.getTalk());
//        int width = dynamicBean.getWidth();
//        int height = dynamicBean.getHigh();
//        String resPath = dynamicBean.getDynamicsPath();
//        String videoPath = dynamicBean.getVideoPrintscreen();
//        // 类型：1-视频  0-图片
//        int type = dynamicBean.getType();
//        if (type == 1) {
//            imageView.setVisibility(View.GONE);
//            mVideoPlayer.setVisibility(View.VISIBLE);
//            setVideoContent(resPath, width, height, videoPath, position);
//        } else {
//            imageView.setVisibility(View.VISIBLE);
//            mVideoPlayer.setVisibility(View.GONE);
//            setImageContent(imageView, width, height, resPath);
//        }
//    }
//
//    /**
//     * 设置播放器
//     *
//     * @param resPath   封面
//     * @param width     宽
//     * @param height    高
//     * @param videoPath 资源路径
//     * @param position  位置
//     */
//    private void setVideoContent(String resPath, int width, int height, String videoPath, int position) {
//
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.transToHeight(width, height));
//        ImageView imageView = new ImageView(mContext);
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        mVideoPlayer.setLayoutParams(params);
//        if (imageView.getParent() != null) {
//            ViewGroup viewGroup = (ViewGroup) imageView.getParent();
//            viewGroup.removeView(imageView);
//        }
//        mVideoOptionBuilder = new GSYVideoOptionBuilder();
//        mImageLoader = ArmsUtils.obtainAppComponentFromContext(mContext).imageLoader();
//        ImageConfig imageConfig = ImageConfigImpl.builder()
//                .imageView(imageView)
//                .url(resPath)
//                .isCircle(false)
//                .build();
//        mImageLoader.loadImage(mContext, imageConfig);
//        mVideoOptionBuilder.setIsTouchWiget(false)
//                .setThumbImageView(imageView)
//                .setUrl(videoPath)
//                .setCacheWithPlay(false)
//                .setLockLand(true)
//                .setPlayTag(TAG)
//                .setPlayPosition(position)
//                .build(mVideoPlayer);
//        setWaterPrint();
//        // 隐藏 title
//        mVideoPlayer.getTitleTextView().setVisibility(View.GONE);
//        // 设置返回键
//        mVideoPlayer.getBackButton().setVisibility(View.GONE);
//        // 设置全屏按键功能
//        mVideoPlayer.getFullscreenButton().setVisibility(View.GONE);
//    }
//
//    /**
//     * 水印图效果
//     */
//    private void setWaterPrint() {
//        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.video_image);
//        mGSYVideoGLViewCustomRender = new GSYVideoGLViewCustomRender();
//        mCustomBitmapIconEffect = new BitmapIconEffect(bitmap, SizeUtils.dp2px(50), SizeUtils.dp2px(50), 0.6f);
//        mGSYVideoGLViewCustomRender.setBitmapEffect(mCustomBitmapIconEffect);
//        mVideoPlayer.setCustomGLRenderer(mGSYVideoGLViewCustomRender);
//        mVideoPlayer.setGLRenderMode(GSYVideoGLView.MODE_RENDER_SIZE);
//    }
//
//    /**
//     * 设置图片
//     *
//     * @param imageView ImageView
//     * @param width     宽
//     * @param height    高
//     * @param imagePath 图片路径
//     */
//    private void setImageContent(ImageView imageView, int width, int height, String imagePath) {
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.transToHeight(width, height));
//        imageView.setLayoutParams(params);
//        mImageLoader = ArmsUtils.obtainAppComponentFromContext(mContext).imageLoader();
//        ImageConfig imageConfig = ImageConfigImpl.builder()
//                .imageView(imageView)
//                .url(imagePath)
//                .isCircle(false)
//                .build();
//        mImageLoader.loadImage(mContext, imageConfig);
//        // 设置点击事件
//        mUrlList.clear();
//        mUrlList.add(imagePath);
//        mUrlList.add(imagePath);
//        String[] urlArr = new String[mUrlList.size()];
//        mUrlList.toArray(urlArr);
//        imageView.setOnClickListener(v ->
//                PicturePreviewActivity.startPreViewPicActivity(mContext, urlArr, 0));
//    }
//
//    /**
//     * 设置状态内容
//     *
//     * @param textView tv
//     * @param content  内容
//     */
//    private void setStateContent(EmojiconTextView textView, String content) {
//        if (StringUtils.isEmpty(content)) {
//            textView.setVisibility(View.GONE);
//        } else {
//            textView.setText(content);
//            textView.setVisibility(View.VISIBLE);
//        }
//    }
//}