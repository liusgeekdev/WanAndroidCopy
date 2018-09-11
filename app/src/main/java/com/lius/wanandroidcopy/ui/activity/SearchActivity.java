package com.lius.wanandroidcopy.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lius.wanandroidcopy.R;
import com.lius.wanandroidcopy.model.ArticleList;
import com.lius.wanandroidcopy.model.HotKeyBean;
import com.lius.wanandroidcopy.model.ResponseData;
import com.lius.wanandroidcopy.ui.base.BaseActivity;
import com.lius.wanandroidcopy.ui.presenter.SearchPresenter;
import com.lius.wanandroidcopy.ui.view.SearchView;
import com.lius.wanandroidcopy.widget.IconFontTextView;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity<SearchPresenter, SearchView> implements SearchView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.recycle_search)
    RecyclerView recycleSearch;
    @BindView(R.id.icon_return)
    IconFontTextView iconReturn;
    @BindView(R.id.icon_clean_input)
    IconFontTextView iconClean;
    @BindView(R.id.tag_flowlayout)
    TagFlowLayout mFlowLayout;

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {

    }

    @Override
    public void searchSuccess(ResponseData<ArticleList> data) {

    }

    @Override
    public void searchError(String msg) {

    }

    @Override
    public void loadMoreSuccess(ResponseData<ArticleList> data) {

    }

    @Override
    public void loadMoreError(String msg) {

    }

    @Override
    public void hotKeySuccess(ResponseData<List<HotKeyBean>> data) {

    }

    @Override
    public void hotKeyError(String msg) {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
