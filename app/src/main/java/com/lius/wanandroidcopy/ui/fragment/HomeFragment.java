package com.lius.wanandroidcopy.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lius.wanandroidcopy.R;
import com.lius.wanandroidcopy.model.ArticleList;
import com.lius.wanandroidcopy.model.BannerBean;
import com.lius.wanandroidcopy.model.ResponseData;
import com.lius.wanandroidcopy.ui.base.BaseFragment;
import com.lius.wanandroidcopy.ui.presenter.HomePresenter;
import com.lius.wanandroidcopy.ui.view.HomeView;

import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomePresenter,HomeView> implements HomeView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycle_home)
    RecyclerView recycleHome;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View rootView) {

    }

    @Override
    public void showRefreshView(Boolean refresh) {

    }

    @Override
    public void getBannerDataSuccess(ResponseData<List<BannerBean>> bannerBeanResponseData) {

    }

    @Override
    public void getRefreshDataSuccess(ResponseData<ArticleList> articleResponseData) {

    }

    @Override
    public void getMoreDataSuccess(ResponseData<ArticleList> articleListResponseData) {

    }

    @Override
    public void getDataError(String errorMessage) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void showProgress(String msg) {

    }

    @Override
    public void hideProgress() {

    }
}
