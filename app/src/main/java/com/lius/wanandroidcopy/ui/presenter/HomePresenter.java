package com.lius.wanandroidcopy.ui.presenter;


import com.lius.wanandroidcopy.api.ServiceApi;
import com.lius.wanandroidcopy.helper.RxObserver;
import com.lius.wanandroidcopy.model.ArticleList;
import com.lius.wanandroidcopy.model.BannerBean;
import com.lius.wanandroidcopy.model.ResponseData;
import com.lius.wanandroidcopy.ui.base.BasePresenter;
import com.lius.wanandroidcopy.ui.view.HomeView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomeView> {
    private int mCurrentPage;

    /**
     * 获取Banner数据
     */
    public void getBannerData() {
        ServiceApi.getBannerData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<List<BannerBean>>>() {
                    @Override
                    public void _onNext(ResponseData<List<BannerBean>> listResponseData) {
                        getMvpView().getBannerDataSuccess(listResponseData);
                    }

                    @Override
                    public void _onError(String errorMseeage) {
                        getMvpView().getDataError(errorMseeage);
                    }
                });
    }

    /**
     * 刷新
     */
    public void getRefreshData() {
        mCurrentPage = 0;
        ServiceApi.getHomeArticle(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<ArticleList>>() {
                    @Override
                    public void _onSubscribe(Disposable d) {
                        getMvpView().showRefreshView(true);
                    }

                    @Override
                    public void _onComplete() {
                        getMvpView().showRefreshView(false);
                    }

                    @Override
                    public void _onNext(ResponseData<ArticleList> articleListResponseData) {
                        getMvpView().getRefreshDataSuccess(articleListResponseData);
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getMvpView().getDataError(errorMessage);
                        getMvpView().showRefreshView(false);
                    }
                });
    }

    /**
     * 加载更多
     */
    public void getMoreData() {
        mCurrentPage++;
        ServiceApi.getHomeArticle(mCurrentPage)
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
