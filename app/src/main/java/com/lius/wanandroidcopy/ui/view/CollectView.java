package com.lius.wanandroidcopy.ui.view;

import com.lius.wanandroidcopy.model.ArticleList;
import com.lius.wanandroidcopy.model.ResponseData;
import com.lius.wanandroidcopy.ui.base.BaseMvpView;

public interface CollectView extends BaseMvpView {
    void getRefreshDataSuccess(ResponseData<ArticleList> articleListResponseData);
    void getMoreDataSuccess(ResponseData<ArticleList> articleListResponseData);
    void getDataError(String errorMessage);
}
