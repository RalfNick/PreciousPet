package com.ralf.module_service.mvp.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ralf.module_service.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ServicePopupWindow
 * @email -
 * @date 2019/06/25 16:41
 **/
public class ServicePopupWindow extends PopupWindow {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private PopupWindowAdapter mAdapter;
    private View mRootView;
    private List<PopupItem> mItemList = new ArrayList<>();
    private ItemClickListener mItemClickListener;

    public ServicePopupWindow(Context context, View contentView) {
        super(contentView);
        mContext = context;
        mRootView = contentView;
        initPopupWindow();
    }

    public ServicePopupWindow(Context context, View contentView, int width, int height) {
        super(contentView, width, height);
        mContext = context;
        mRootView = contentView;
        initPopupWindow();
    }

    public ServicePopupWindow(Context context, View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
        mContext = context;
        mRootView = contentView;
        initPopupWindow();
    }

    private void initPopupWindow() {
        mRecyclerView = mRootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new PopupWindowAdapter(R.layout.item_popup_window_layout, mItemList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view, position);
            }
        });
        mRootView.findViewById(R.id.pop_window_cancel).setOnClickListener(view -> dismiss());
        mAdapter.notifyDataSetChanged();
    }

    public void setPopupWindowData(List<PopupItem> popupItems) {
        if (popupItems == null || popupItems.size() < 1) {
            return;
        }
        mItemList.clear();
        mItemList.addAll(popupItems);
        mAdapter.notifyDataSetChanged();
    }

    private void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        /**
         * 条目点击事件
         *
         * @param view     被点击的view
         * @param position 点击的位置，用于后面设置点击的位置
         */
        void onItemClick(View view, int position);
    }

    private class PopupWindowAdapter extends BaseQuickAdapter<PopupItem, BaseViewHolder> {

        PopupWindowAdapter(int layoutResId, @Nullable List<PopupItem> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, PopupItem item) {
            helper.setText(R.id.item_popup_title, item.item);
        }
    }

    public static class PopupItem {

        private String item;
        private int typeId;

        public PopupItem(String item, int typeId) {
            this.item = item;
            this.typeId = typeId;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }
    }

    public static class Builder {

        private int width;
        private int height;
        private boolean focusable;
        private int layoutId;
        private Context mContext;
        private View view;
        private boolean outsideTouchable;
        private int animationStyle;
        private boolean clipEnable;
        private PopupWindow.OnDismissListener mDismissListener;
        private int softInputMode;
        private ServicePopupWindow mPopupWindow;
        private ItemClickListener mItemClickListener;
        private Drawable mDrawable;
        private float alpha;
        private List<PopupItem> popupItems;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder focusable(boolean focusable) {
            this.focusable = focusable;
            return this;
        }

        public Builder view(View view) {
            this.view = view;
            return this;
        }

        public Builder layoutId(@LayoutRes int layoutId) {
            this.layoutId = layoutId;
            return this;
        }

        public Builder outsideTouchable(boolean outsideTouchable) {
            this.outsideTouchable = outsideTouchable;
            return this;
        }

        public Builder animationStyle(int animationStyle) {
            this.animationStyle = animationStyle;
            return this;
        }

        public Builder clipEnable(boolean clipEnable) {
            this.clipEnable = clipEnable;
            return this;
        }

        public Builder softInputMode(int softInputMode) {
            this.softInputMode = softInputMode;
            return this;
        }

        public Builder drawable(Drawable drawable) {
            this.mDrawable = drawable;
            return this;
        }

        public Builder alpha(float alpha) {
            this.alpha = alpha;
            return this;
        }

        public Builder popupItems(List<PopupItem> popupItems) {
            this.popupItems = popupItems;
            return this;
        }

        public Builder dismissListener(PopupWindow.OnDismissListener dismissListener) {
            this.mDismissListener = dismissListener;
            return this;
        }

        public Builder itemClickListener(ItemClickListener itemClickListener) {
            this.mItemClickListener = itemClickListener;
            return this;
        }

        public ServicePopupWindow build() {
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(layoutId, null);
            }
            if (view == null) {
                throw new NullPointerException("the view can not be null");
            }
            mPopupWindow = new ServicePopupWindow(mContext, view, width, height, focusable);
            mPopupWindow.setInputMethodMode(softInputMode);
            mPopupWindow.setTouchable(outsideTouchable);
            mPopupWindow.setClippingEnabled(clipEnable);
            mPopupWindow.setOnDismissListener(mDismissListener);
            mPopupWindow.setAnimationStyle(animationStyle);
            mPopupWindow.setItemClickListener(mItemClickListener);
            mPopupWindow.setBackgroundDrawable(mDrawable);
            mPopupWindow.setPopupWindowData(popupItems);
//            view.setAlpha(alpha);
            return mPopupWindow;
        }
    }
}
