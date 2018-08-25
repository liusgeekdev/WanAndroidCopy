package com.lius.wanandroidcopy.ui.base;

/**
 * @author: Chris Liu
 * @date: 2018/8/19  22:30
 */
public abstract class BasePresenter<V extends BaseMvpView> {
    public V mvpView;

    public void attachView(V view) {
        this.mvpView = view;
    }

    public void detachView() {
        this.mvpView = null;
    }

    //判断view是否为空
    public boolean isAttachView() {
        return mvpView != null;
    }

    //返回目标View
    public V getMvpView() {
        return mvpView;
    }
}
