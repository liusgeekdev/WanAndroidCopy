package com.lius.wanandroidcopy.ui.view;

import com.lius.wanandroidcopy.model.ArticleList;
import com.lius.wanandroidcopy.model.HotKeyBean;
import com.lius.wanandroidcopy.model.ResponseData;
import com.lius.wanandroidcopy.ui.base.BaseMvpView;

import java.util.List;

public interface SearchView extends BaseMvpView {
    void searchSuccess(ResponseData<ArticleList> data);
    void searchError(String msg);
    void loadMoreSuccess(ResponseData<ArticleList> data);
    void loadMoreError(String msg);
    void hotKeySuccess(ResponseData<List<HotKeyBean>> data);
    void hotKeyError(String msg);
}
