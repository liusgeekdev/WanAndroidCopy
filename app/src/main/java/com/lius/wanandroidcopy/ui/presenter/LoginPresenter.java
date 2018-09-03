package com.lius.wanandroidcopy.ui.presenter;

import com.lius.wanandroidcopy.api.ServiceApi;
import com.lius.wanandroidcopy.helper.RxObserver;
import com.lius.wanandroidcopy.model.ResponseData;
import com.lius.wanandroidcopy.model.UserBean;
import com.lius.wanandroidcopy.ui.base.BasePresenter;
import com.lius.wanandroidcopy.ui.view.LoginView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginView> {
    public void login() {
        ServiceApi.login(getMvpView().getUserName(), getMvpView().getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<UserBean>>() {
                    @Override
                    public void _onNext(ResponseData<UserBean> userBeanResponseData) {

                    }

                    @Override
                    public void _onError(String errorMseeage) {

                    }
                })
    }
}
