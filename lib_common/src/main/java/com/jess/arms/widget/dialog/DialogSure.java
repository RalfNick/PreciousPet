package com.jess.arms.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.R;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name DialogSure
 * @email -
 * @date 2019/02/27 下午5:17
 **/
public class DialogSure extends Dialog {

    private Builder mBuilder;

    private DialogSure(Builder builder) {
        super(builder.context);
        this.mBuilder = builder;
    }

    private DialogSure(Builder builder, int themeResId) {
        super(builder.context, themeResId);
        this.mBuilder = builder;
    }

    private DialogSure(Builder builder, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(builder.context, cancelable, cancelListener);
        this.mBuilder = builder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View dialogView = LayoutInflater.from(mBuilder.context).inflate(R.layout.dialog_sure, null);
        initView(dialogView);
        setContentView(dialogView);
    }

    private void initView(View dialogView) {

        setCancelable(mBuilder.cancelable);
        TextView sureView = dialogView.findViewById(R.id.tv_sure);
        if (mBuilder.sureListener != null) {
            sureView.setOnClickListener(v -> mBuilder.sureListener.onSureClick(DialogSure.this));
        }
        TextView titleView = dialogView.findViewById(R.id.tv_title);
        if (TextUtils.isEmpty(mBuilder.title)) {
            titleView.setVisibility(View.GONE);
        } else {
            titleView.setVisibility(View.VISIBLE);
            titleView.setText(mBuilder.title);
            titleView.setTextIsSelectable(true);
        }
        TextView contentView = dialogView.findViewById(R.id.tv_content);

        if (TextUtils.isEmpty(mBuilder.content)) {
            contentView.setVisibility(View.GONE);
        } else {
            contentView.setVisibility(View.VISIBLE);
            contentView.setText(mBuilder.content);
            contentView.setMovementMethod(ScrollingMovementMethod.getInstance());
            contentView.setTextIsSelectable(true);
        }
        ImageView imageView = dialogView.findViewById(R.id.iv_logo);
        if (mBuilder.logoId > -1) {
            imageView.setImageResource(mBuilder.logoId);
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }
    }

    public static class Builder {

        private int logoId = -1;
        private String title;
        private String content;
        private Context context;
        private boolean cancelable = true;
        private int themeResId = -1;
        private float alpha = -1;
        private int gravity = -1;
        private DialogInterface.OnCancelListener cancelListener;
        private DialogSureListener sureListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder logo(@IdRes int resId) {
            this.logoId = resId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder themeId(int themeResId) {
            this.themeResId = themeResId;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder cancelable(boolean cancel) {
            this.cancelable = cancel;
            return this;
        }

        public Builder cancelListener(DialogInterface.OnCancelListener listener) {
            this.cancelListener = listener;
            return this;
        }

        public Builder alpha(float alpha) {
            this.alpha = alpha;
            return this;
        }

        public Builder gravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder sureListener(DialogSureListener sureListener) {
            this.sureListener = sureListener;
            return this;
        }

        public DialogSure build() {
            if (themeResId != -1) {
                return new DialogSure(this, themeResId);
            } else if (cancelListener != null) {
                return new DialogSure(this, cancelable, cancelListener);
            }
            return new DialogSure(this);
        }
    }

    public interface DialogSureListener {

        /**
         * 点击确定按钮回调
         *
         * @param dialogSure DialogSure
         */
        void onSureClick(DialogSure dialogSure);
    }

}
