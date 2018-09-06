package com.lius.wanandroidcopy.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lius.wanandroidcopy.R;
import com.lius.wanandroidcopy.model.ArticleList;
import com.lius.wanandroidcopy.model.ResponseData;
import com.lius.wanandroidcopy.ui.adapter.ArticleListAdapter;
import com.lius.wanandroidcopy.ui.base.BaseFragment;
import com.lius.wanandroidcopy.ui.presenter.TypePresenter;
import com.lius.wanandroidcopy.ui.view.TypeView;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;

public class TypeFragment extends BaseFragment<TypePresenter, TypeView> implements TypeView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.tag_flowlayout)
    TagFlowLayout tagFlowLayout;
    @BindView(R.id.recycle_type)
    RecyclerView recycleType;

    private ArticleListAdapter mAdapter;

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
        mPresenter.getMoreData();
    }

    @Override
    public TabLayout getTabLayout() {
        return tabLayout;
    }

    @Override
    public TagFlowLayout getFlowLayout() {
        return tagFlowLayout;
    }

    @Override
    public void initView(View rootView) {
        recycleType.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ArticleListAdapter(getContext(), null, 0);
        mAdapter.setOnLoadMoreListener(this,recycleType);
        recycleType.setAdapter(mAdapter);
        mPresenter.getTagData();
        recycleType.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    tagFlowLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void getRefreshDataSuccess(ResponseData<ArticleList> articleListResponseData) {
        if (articleListResponseData.getData().getDatas().size() != 0) {
            mAdapter.setNewData(articleListResponseData.getData().getDatas());
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
    public void showProgress(String msg) {

    }

    @Override
    public void hideProgress() {

    }
}
