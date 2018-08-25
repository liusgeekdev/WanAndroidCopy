package com.lius.wanandroidcopy.ui.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.lius.wanandroidcopy.R;
import com.lius.wanandroidcopy.util.T;

import butterknife.ButterKnife;

/**
 * @author: Chris Liu
 * @date: 2018/8/19  22:30
 */
public abstract class BaseActivity<P extends BasePresenter<V>,V extends BaseMvpView>
        extends AppCompatActivity implements BaseMvpView {
    private ProgressDialog progressDialog;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是否使用MVP模式
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);//之后所有的子类都要实现对应的接口
        }

        //子类不需要设置布局ID,也不再需要使用ButterKnife.bind()
        setContentView(provideContentViewId());
        ButterKnife.bind(this);
        excuteStateBar();

        initView();
        initData();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in , R.anim.slide_left_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    public void initView() {

    }

    public void initData() {

    }

    //用于创建Presenter(由子类实现)
    protected abstract P createPresenter();

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    //解决4.4设置状态栏颜色之后,布局内容嵌入状态栏位置问题
    private void excuteStateBar() {
        ViewGroup mContentView = (ViewGroup) getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置ContentView的FitsSystemWindows,
            //而是设置ContentView的第一个子View,预留出系统View的空间.
            mChildView.setFitsSystemWindows(true);
        }
    }

    @Override
    public void showProgress(String msg) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(msg);//设置进度条加载内容
        if (! progressDialog.isShowing())
            progressDialog.show();

    }

    @Override
    public void hideProgress() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();//presenter与view断开连接
        }
    }

    public void Toast(String msg) {
        T.showShort(this,msg);
    }
}
