package com.lius.wanandroidcopy.ui.fragment;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lius.wanandroidcopy.R;
import com.lius.wanandroidcopy.model.ArticleList;
import com.lius.wanandroidcopy.model.BannerBean;
import com.lius.wanandroidcopy.model.ResponseData;
import com.lius.wanandroidcopy.ui.activity.WebViewActivity;
import com.lius.wanandroidcopy.ui.adapter.ArticleListAdapter;
import com.lius.wanandroidcopy.ui.base.BaseFragment;
import com.lius.wanandroidcopy.ui.presenter.HomePresenter;
import com.lius.wanandroidcopy.ui.view.HomeView;
import com.lius.wanandroidcopy.util.ImageLoaderManager;

import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

public class HomeFragment extends BaseFragment<HomePresenter,HomeView> implements HomeView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycle_home)
    RecyclerView recycleHome;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private ArticleListAdapter mAdapter;
    private BGABanner bgaBanner;

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
        recycleHome.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ArticleListAdapter(getContext(), null, 0);
        recycleHome.setAdapter(mAdapter);

        swipeRefresh.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this, recycleHome);

        View view = View.inflate(getActivity(), R.layout.layout_banner, null);
        bgaBanner = view.findViewById(R.id.banner);
        mAdapter.addHeaderView(view);
        onRefresh();
    }

    @Override
    public void showRefreshView(Boolean refresh) {
        swipeRefresh.setRefreshing(refresh);
    }

    @Override
    public void getBannerDataSuccess(ResponseData<List<BannerBean>> bannerBeanResponseData) {
        bgaBanner.setData(R.layout.item_banner, bannerBeanResponseData.getData(), null);
        bgaBanner.setAdapter(new BGABanner.Adapter<View, BannerBean>() {
            @Override
            public void fillBannerItem(BGABanner banner, View itemView, BannerBean model, int position) {
                ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
                ImageLoaderManager.LoadImage(getContext(), model.getImagePath(), imageView);
            }
        });
        bgaBanner.setDelegate(new BGABanner.Delegate() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, @Nullable Object model, int position) {
                WebViewActivity.startAction(getActivity(), ((BannerBean)model).getUrl());
            }
        });
    }

    @Override
    public void getRefreshDataSuccess(ResponseData<ArticleList> articleResponseData) {
        if (articleResponseData.getData().getDatas().size() != 0) {
            mAdapter.setNewData(articleResponseData.getData().getDatas());
        } else {
            Toast(getResources().getString(R.string.no_data));
        }
    }

    @Override
    public void getMoreDataSuccess(ResponseData<ArticleList> articleListResponseData) {
        if (articleListResponseData.getData().getDatas().size() != 0) {
            mAdapter.addData(articleListResponseData.getData().getDatas());
            mAdapter.loadMoreComplete();
        } else {
            mAdapter.loadMoreEnd();
        }
    }

    @Override
    public void getDataError(String errorMessage) {
        Toast(errorMessage);
    }

    @Override
    public void onRefresh() {
        mPresenter.getBannerData();
        mPresenter.getRefreshData();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getMoreData();
    }

    @Override
    public void showProgress(String msg) {

    }

    @Override
    public void hideProgress() {

    }
}
