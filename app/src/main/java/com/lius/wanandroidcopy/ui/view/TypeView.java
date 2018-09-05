package com.lius.wanandroidcopy.ui.view;

import android.support.design.widget.TabLayout;

import com.lius.wanandroidcopy.model.ArticleList;
import com.lius.wanandroidcopy.model.ResponseData;
import com.lius.wanandroidcopy.ui.base.BaseMvpView;
import com.zhy.view.flowlayout.TagFlowLayout;

public interface TypeView extends BaseMvpView {
    TabLayout getTabLayout();
    TagFlowLayout getFlowLayout();
    void getRefreshDataSuccess(ResponseData<ArticleList> articleListResponseData);
    void getMoreDataSuccess(ResponseData<ArticleList> articleListResponseData);
    void getDataError(String errorMessage);
}
