package com.lius.wanandroidcopy.ui.view;

import com.lius.wanandroidcopy.model.ResponseData;
import com.lius.wanandroidcopy.model.UserBean;
import com.lius.wanandroidcopy.ui.base.BaseMvpView;

public interface LoginView extends BaseMvpView {
    String getUserName();
    String getPassword();
    void loginSuccess(ResponseData<UserBean> userBeanResponseData);
    void loginError(String errorMessage);
    void registerSuccess(ResponseData<UserBean> userBeanResponseData);
    void registerError(String errorMessage);
}
