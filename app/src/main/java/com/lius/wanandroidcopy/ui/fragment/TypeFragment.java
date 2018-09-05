package com.lius.wanandroidcopy.ui.fragment;

import android.support.design.widget.TabLayout;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lius.wanandroidcopy.R;
import com.lius.wanandroidcopy.model.ArticleList;
import com.lius.wanandroidcopy.model.ResponseData;
import com.lius.wanandroidcopy.ui.base.BaseFragment;
import com.lius.wanandroidcopy.ui.presenter.TypePresenter;
import com.lius.wanandroidcopy.ui.view.TypeView;
import com.zhy.view.flowlayout.TagFlowLayout;

public class TypeFragment extends BaseFragment<TypePresenter, TypeView> implements TypeView, BaseQuickAdapter.RequestLoadMoreListener {

    public static TypeFragment newInstance() {
        return new TypeFragment();
    }

    @Override
    protected TypePresenter createPresenter() {
        return new TypePresenter(getContext());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_type;
    }


    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public TabLayout getTabLayout() {
        return null;
    }

    @Override
    public TagFlowLayout getFlowLayout() {
        return null;
    }

    @Override
    public void getRefreshDataSuccess(ResponseData<ArticleList> articleListResponseData) {

    }

    @Override
    public void getMoreDataSuccess(ResponseData<ArticleList> articleListResponseData) {

    }

    @Override
    public void getDataError(String errorMessage) {

    }

    @Override
    public void showProgress(String msg) {

    }

    @Override
    public void hideProgress() {

    }
}
