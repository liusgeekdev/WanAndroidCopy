package com.lius.wanandroidcopy.ui.presenter;

import com.lius.wanandroidcopy.api.ServiceApi;
import com.lius.wanandroidcopy.helper.RxObserver;
import com.lius.wanandroidcopy.model.ArticleList;
import com.lius.wanandroidcopy.model.HotKeyBean;
import com.lius.wanandroidcopy.model.ResponseData;
import com.lius.wanandroidcopy.ui.base.BasePresenter;
import com.lius.wanandroidcopy.ui.view.SearchView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter extends BasePresenter<SearchView> {
    private int mCurrentPage;

    public void getHotKey() {
        ServiceApi.hotKey()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<List<HotKeyBean>>>() {
                    @Override
                    public void _onNext(ResponseData<List<HotKeyBean>> listResponseData) {
                        getMvpView().hotKeySuccess(listResponseData);
                    }

                    @Override
                    public void _onError(String errorMseeage) {
                        getMvpView().hotKeyError(errorMseeage);
                    }
                });

    }

    public void search(String key){
        mCurrentPage = 0;
        ServiceApi.queryArticle(mCurrentPage,key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<ArticleList>>() {
                    @Override
                    public void _onNext(ResponseData<ArticleList> data) {
                        getMvpView().searchSuccess(data);
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getMvpView().searchError(errorMessage);
                    }
                });
    }

    public void loadMore(String key){
        mCurrentPage ++;
        ServiceApi.queryArticle(mCurrentPage,key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<ArticleList>>() {
                    @Override
                    public void _onNext(ResponseData<ArticleList> data) {
                        getMvpView().loadMoreSuccess(data);
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getMvpView().loadMoreError(errorMessage);
                    }
                });
    }
}
