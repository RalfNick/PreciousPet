package com.ralf.module_community.util;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.ralf.module_community.R;
import com.ralf.module_community.mvp.ui.view.CommentInputView;
import com.rockerhieu.emojicon.EmojiconEditText;
import com.rockerhieu.emojicon.EmojiconsFragment;
import com.rockerhieu.emojicon.emoji.Emojicon;

import io.reactivex.observers.DefaultObserver;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommentInputHelper
 * @email -
 * @date 2019/03/08 上午11:05
 **/
public class CommentInputHelper {

    private static final String TAG = CommentInputHelper.class.getSimpleName();

    private EmotionInputDetector mDetector;
    private CommentInputView mCommentView;
    private EmojiconEditText mEmojiconEditText;
    private FragmentActivity mActivity;
    private View mContentView;
    private FrameLayout mRelativeLayout;
    private ImageView mEmojiconImage, mSearchHuatiImage;
    private Button mEmojiconButton;
    private CommentInputHelperViewCallBack mCommentInputHelperViewCallBack;
    private CommentEmojiconImageCallBack mEmojiconImageCallBack;
    private CommentSendButtonCallBack mSendButtonCallBack;
    private SearchHuatiCallBack mSearchHuatiCallBack;
    private String inputContent;
    private boolean config = false;
    private boolean isShowSearchImage = false;
    private RelativeLayout mConfigLayout;
    private RelativeLayout mSend_discucuss_layout;
    private String hintText = "";
    public static boolean isResponse = false;//是否是回复类型(频道帖子 回复用)

    private CommentInputHelper() {
    }

    public static CommentInputHelper with(FragmentActivity activity, CommentInputView mView) {
        CommentInputHelper help = new CommentInputHelper();
        help.mCommentView = mView;
        help.mActivity = activity;
        return help;
    }

    public CommentInputHelper bindContentView(View contentView) {
        this.mContentView = contentView;
        return this;
    }

    //绑定自定义输入框
    public CommentInputHelper bindEditText(EmojiconEditText editText) {
        this.mEmojiconEditText = editText;
        return this;
    }

    //是否启用默认配置
    public CommentInputHelper bindConfig(boolean isConfig) {
        this.config = isConfig;
        return this;
    }

    public CommentInputHelper bindIsShowSearchImag(boolean isShow) {
        this.isShowSearchImage = isShow;
        return this;
    }

    public CommentInputHelper build() {
        initEmotionView();
        return this;
    }

    public CommentInputHelper setEmojiconImage(Drawable drawable) {
        ImageView imageView = mCommentView.getmEmojiconImage();
        imageView.setImageDrawable(drawable);
        return this;
    }

    public CommentInputHelper setSendButtonBackGrouud(Drawable drawable) {
        Button button = mCommentView.getmSendBtn();
        button.setBackground(drawable);
        return this;
    }

    private void checkText() {
        EditTextMaxLength.setMaxLength(mEmojiconEditText, EditTextMaxLength.PingLunAndHuiFu);
        RxTextView.textChanges(mEmojiconEditText)
                .skip(1)
                .filter(charSequence -> charSequence != null)
                .subscribe(new DiscussObserver());

    }

    public void setEmojiconImageClick(CommentEmojiconImageCallBack imageClick) {
        mEmojiconImageCallBack = imageClick;
    }

    public void setSendButtonClick(CommentSendButtonCallBack buttonClick) {
        mSendButtonCallBack = buttonClick;
    }

    public void setSearchButtonClick(SearchHuatiCallBack buttonClick) {
        this.mSearchHuatiCallBack = buttonClick;
    }


    private void initEmotionView() {
        mConfigLayout = mCommentView.getConfigLayout();
        mSend_discucuss_layout = mCommentView.getSend_discucuss_layout();
        mSearchHuatiImage = mCommentView.getSearchHuatiImage();
        // TODO: 2017/5/22
        if (config) {
            if (isShowSearchImage) {
                mSearchHuatiImage.setVisibility(View.VISIBLE);
            }
            mRelativeLayout = mCommentView.getEmotionView();
            mEmojiconImage = mCommentView.getEdit_emojicon_image();
            mConfigLayout.setVisibility(View.VISIBLE);
            mSend_discucuss_layout.setVisibility(View.GONE);

            mDetector = EmotionInputDetector.with(mActivity)
                    .setEmotionView(mRelativeLayout)
                    .bindToContent(mContentView)
                    .bindToEditText(mEmojiconEditText)
                    .bindToEmotionButton(mEmojiconImage)
                    .build();

            mSearchHuatiImage.setOnClickListener(view -> {
                hideKeyBoard();
                assert mSearchHuatiImage != null : "mSearchHuaTiCallBack is null, please implement setSearchButtonClick";
                mSearchHuatiCallBack.searchHuatiButtonClick(view);
            });
        } else {
            mConfigLayout.setVisibility(View.GONE);
            if (!isShowSearchImage) {
                mSearchHuatiImage.setVisibility(View.GONE);
            }
            mSend_discucuss_layout.setVisibility(View.VISIBLE);
            mEmojiconEditText = mCommentView.getmEmojiconEditText();
            mRelativeLayout = mCommentView.getEmotionView();
            mEmojiconImage = mCommentView.getmEmojiconImage();
            mEmojiconButton = mCommentView.getmSendBtn();

            mDetector = EmotionInputDetector.with(mActivity)
                    .setEmotionView(mRelativeLayout)
                    .bindToContent(mContentView)
                    .bindToEditText(mEmojiconEditText)
                    .bindToEmotionButton(mEmojiconImage)
                    .build();
            checkText();
            mEmojiconButton.setOnClickListener(v -> {
                hideKeyBoard();
                assert mSendButtonCallBack != null : "mSendButtonCallBack is null, please implement setSendButtonClick()";
                mSendButtonCallBack.callBackSendBoutton(v, inputContent);
                mEmojiconEditText.setText(null);
            });
        }
        setEmojiconFragment(false);
        mEmojiconEditText.setUseSystemDefault(false);
    }

    private void setEmojiconFragment(boolean useSystemDefault) {
        mActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.comment_emojicons_layout, EmojiconsFragment.newInstance(useSystemDefault))
                .commit();
        Log.d(TAG, "setEmojiconFragment: ");
    }

    public void showKeyBoard() {
        if (config) {
            mConfigLayout.setVisibility(View.VISIBLE);
            mEmojiconEditText.requestFocus();
            mDetector.showSoftInput();
        } else {
            if (mRelativeLayout.isShown()) {
                mRelativeLayout.setVisibility(View.GONE);
            }
            mConfigLayout.setVisibility(View.GONE);
            mEmojiconEditText.requestFocus();
            mDetector.showSoftInput();
        }
    }

    public void hideKeyBoard() {
        Log.d(TAG, "hideKeyBoard: ");
        if (config) {
            if (mConfigLayout.isShown()) {
                mConfigLayout.setVisibility(View.GONE);
                mRelativeLayout.setVisibility(View.GONE);
                mEmojiconEditText.clearFocus();
                setEditTextHint();
                mDetector.hideSoftInput();
            } else {
                mEmojiconEditText.clearFocus();
                setEditTextHint();
                mDetector.hideSoftInput();
            }
        } else {
            if (mRelativeLayout.isShown()) {
                mRelativeLayout.setVisibility(View.GONE);
                mEmojiconEditText.clearFocus();
                setEditTextHint();
                mDetector.hideSoftInput();
            } else {
                mEmojiconEditText.clearFocus();
                setEditTextHint();
                mDetector.hideSoftInput();
            }
        }

    }

    public void setHint(String stringHint) {
        if (!stringHint.equals("")) {
            hintText = stringHint;
        } else {
            hintText = "";
        }
    }

    private void setEditTextHint() {
        if (!hintText.equals("")) {
            mEmojiconEditText.setHint(hintText);
        }
    }

    public boolean recycle() {
        if (!mDetector.interceptBackPress()) {
            return true;
        }
        return false;
    }

    public void inputEmojicon(Emojicon emojicon) {
        if (EditTextMaxLength.isCheckEmojicon) {
            EmojiconsFragment.input(mEmojiconEditText, emojicon);
        }

    }

    public void onBackspaceEmojicon() {
        EmojiconsFragment.backspace(mEmojiconEditText);
    }

    public interface CommentInputHelperViewCallBack {
        void onEmojiconClicked();

        void onEmojiconBackspaceClicked();
    }

    public interface CommentEmojiconImageCallBack {
        void callBackEmojiconImage(View view);
    }

    public interface CommentSendButtonCallBack {
        void callBackSendBoutton(View view, String editTextContent);
    }


    public interface SearchHuatiCallBack {
        void searchHuatiButtonClick(View view);
    }

    /**
     * 评论文本框校验
     */
    private final class DiscussObserver extends DefaultObserver<CharSequence> {
        @Override
        public void onNext(CharSequence value) {
            CommentInputHelper.this.inputContent = value.toString().trim();
            Log.d(TAG, "onNext111111: " + inputContent);
            boolean startsWith = value.toString().startsWith(" ", 0);
            if (value.length() == 0 || startsWith) {
                CommentInputHelper.this.mEmojiconButton.setEnabled(false);
            } else {
                CommentInputHelper.this.mEmojiconButton.setEnabled(true);
            }
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    }

}
