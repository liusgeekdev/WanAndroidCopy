package com.lius.wanandroidcopy.ui.view;

import com.lius.wanandroidcopy.model.ArticleList;
import com.lius.wanandroidcopy.model.BannerBean;
import com.lius.wanandroidcopy.model.ResponseData;
import com.lius.wanandroidcopy.ui.base.BaseMvpView;

import java.util.List;

public interface HomeView extends BaseMvpView {
    void showRefreshView(Boolean refresh);
    void getBannerDataSuccess(ResponseData<List<BannerBean>> bannerBeanResponseData);
    void getRefreshDataSuccess(ResponseData<ArticleList> articleResponseData);
    void getMoreDataSuccess(ResponseData<ArticleList> articleListResponseData);
    void getDataError(String errorMessage);
}
