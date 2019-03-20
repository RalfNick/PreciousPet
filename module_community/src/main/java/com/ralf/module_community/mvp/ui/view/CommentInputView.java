package com.ralf.module_community.mvp.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ralf.module_community.R;
import com.rockerhieu.emojicon.EmojiconEditText;
import com.rockerhieu.emojicon.EmojiconsFragment;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommentInputView
 * @email -
 * @date 2019/03/08 上午10:38
 **/
public class CommentInputView extends LinearLayout {

    private static final String TAG = "CommentView";

    private EmojiconEditText mEmojiconEditText;
    private LinearLayout editTextLayout;
    private ImageView mEmojiconImage, searchHuatiImage;
    private ImageView edit_emojicon_image;
    private Button mSendBtn;
    private RelativeLayout emojicon_relative_layout;
    private RelativeLayout send_discucuss_layout;
    private FrameLayout mEmojiconsLayout;
    private Context activity;

    public CommentInputView(Context context) {
        super(context);
        initView();
    }

    public CommentInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CommentInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public CommentInputView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    public Context getCommentView() {
        return activity;
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_comment_input_view, this);
        activity = view.getContext();
        mEmojiconEditText = view.findViewById(R.id.emojicon_edit_text);
        editTextLayout = view.findViewById(R.id.edit_text_layout);
        mEmojiconImage = view.findViewById(R.id.emojicon_image);
        mSendBtn = view.findViewById(R.id.emojicon_btn);
        mEmojiconsLayout = view.findViewById(R.id.comment_emojicons_layout);
        emojicon_relative_layout = view.findViewById(R.id.emojicon_relative_layout);
        edit_emojicon_image = view.findViewById(R.id.edit_emojicon_image);
        send_discucuss_layout = view.findViewById(R.id.send_discucuss_layout);
        searchHuatiImage = view.findViewById(R.id.search_huati_image);
    }

    private void setEmojiconFragment(boolean useSystemDefault) {
        ((FragmentActivity) activity).getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.comment_emojicons_layout, EmojiconsFragment.newInstance(useSystemDefault))
                .commit();
        Log.d(TAG, "setEmojiconFragment: ");
    }

    public FrameLayout getEmotionView() {
        if (mEmojiconsLayout != null) {
            return mEmojiconsLayout;
        } else {
            return null;
        }
    }
    public ImageView getSearchHuatiImage(){
        if (searchHuatiImage != null){
            return searchHuatiImage;
        }
        else {
            return null;
        }
    }

    public EmojiconEditText getmEmojiconEditText() {
        if (mEmojiconEditText != null) {
            return mEmojiconEditText;
        } else {
            return null;
        }
    }

    public RelativeLayout getConfigLayout(){
        if (emojicon_relative_layout != null){
            return emojicon_relative_layout;
        }else {
            return null;
        }
    }
    public RelativeLayout getSend_discucuss_layout(){
        if (send_discucuss_layout != null){
            return send_discucuss_layout;
        }else {
            return null;
        }
    }

    public Button getmSendBtn() {
        if (mSendBtn != null) {
            return mSendBtn;
        } else {
            return null;
        }
    }

    public ImageView getmEmojiconImage() {
        if (mEmojiconImage != null) {
            return mEmojiconImage;
        } else {
            return null;
        }
    }

    public ImageView getEdit_emojicon_image(){
        if (edit_emojicon_image != null){
            return edit_emojicon_image;
        }else {
            return null;
        }
    }
}
