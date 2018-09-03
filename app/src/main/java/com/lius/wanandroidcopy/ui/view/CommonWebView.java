package com.lius.wanandroidcopy.ui.view;

import android.widget.ProgressBar;

import com.lius.wanandroidcopy.ui.base.BaseMvpView;

public interface CommonWebView extends BaseMvpView {
    void setTitle(String title);
    ProgressBar getProgressBar();
}
