package com.lius.wanandroidcopy.ui.presenter;

import com.lius.wanandroidcopy.api.ServiceApi;
import com.lius.wanandroidcopy.helper.RxObserver;
import com.lius.wanandroidcopy.model.ArticleList;
import com.lius.wanandroidcopy.model.ResponseData;
import com.lius.wanandroidcopy.ui.base.BasePresenter;
import com.lius.wanandroidcopy.ui.view.CollectView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CollectPresenter extends BasePresenter<CollectView> {
    private int mCurrentPage;

    public void getRefreshData() {
        mCurrentPage = 0;
        ServiceApi.collectList(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<ArticleList>>() {
                    @Override
                    public void _onNext(ResponseData<ArticleList> articleListResponseData) {
                        getMvpView().getRefreshDataSuccess(articleListResponseData);
                    }

                    @Override
                    public void _onError(String errorMseeage) {
                        getMvpView().getDataError(errorMseeage);
                    }
                });
    }

    public void getMoreData() {
        mCurrentPage++;
        ServiceApi.collectList(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<ArticleList>>() {
                    @Override
                    public void _onNext(ResponseData<ArticleList> articleListResponseData) {
                        getMvpView().getMoreDataSuccess(articleListResponseData);
                    }

                    @Override
                    public void _onError(String errorMseeage) {
                        getMvpView().getDataError(errorMseeage);
                    }
                });
    }
}
