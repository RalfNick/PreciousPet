package com.ralf.pet_provider.widget.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.utils.StringUtils;
import com.ralf.pet_provider.R;
import com.ralf.pet_provider.entity.DialogEntity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * 自定义对话框
 * @name CustomAlertDialog
 * @email -
 * @date 2019/01/19 下午7:48
 **/
public class CustomAlertDialog extends AlertDialog {

    private Context context;
    private String mTitleStr;
    private int mResId;
    private DialogAdapter mAdapter;
    private List<DialogEntity> mEntityList;
    private DialogItemClickListener mClickListener;

    public CustomAlertDialog(Context context, List<DialogEntity> entityList, String title, @IdRes int resId) {
        super(context, R.style.dialog_default_style);
        this.context = context;
        this.mEntityList = entityList;
        this.mResId = resId;
        this.mTitleStr = title;
    }

    public CustomAlertDialog(Context context, List<DialogEntity> entityList) {
        super(context, R.style.dialog_default_style);
        this.context = context;
        this.mEntityList = entityList;
    }

    public DialogItemClickListener getClickListener() {
        return mClickListener;
    }

    public void setClickListener(DialogItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(context).inflate(R.layout.dialog_layout_options, null);
        initView(rootView);
        setContentView(rootView);
    }

    private void initView(View rootView) {
        ImageButton imageButton = rootView.findViewById(R.id.dialog_title_button);
        TextView textView = rootView.findViewById(R.id.dialog_title_text_view);
        mAdapter = new DialogAdapter(R.layout.item_layout_dialog_option, mEntityList);
        if (!StringUtils.isEmpty(mTitleStr)) {
            textView.setText(mTitleStr);
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
        if (mResId > 0) {
            imageButton.setVisibility(View.VISIBLE);
            imageButton.setImageResource(mResId);
        } else {
            imageButton.setVisibility(View.GONE);
        }
        RecyclerView recyclerView = rootView.findViewById(R.id.dialog_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (mClickListener != null) {
                mClickListener.onItemClick(position);
            }
        });
    }

    /**
     * Dialog Adapter
     */
    class DialogAdapter extends BaseQuickAdapter<DialogEntity, BaseViewHolder> {

        DialogAdapter(int layoutResId, @Nullable List<DialogEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DialogEntity item) {
            String title = item.getTitle();
            Drawable drawable = item.getResId();
            ImageView imageView = helper.getView(R.id.dialog_item_icon_iv);
            TextView textView = helper.getView(R.id.dialog_item_text_view);
            if (!StringUtils.isEmpty(title)) {
                textView.setText(title);
            }
            if (drawable != null) {
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageDrawable(drawable);
            } else {
                imageView.setVisibility(View.GONE);
            }
        }
    }

    /**
     * Dialog Item Click
     */
    public interface DialogItemClickListener {

        /**
         * Item Click Event
         *
         * @param position position
         */
        void onItemClick(int position);
    }
}
