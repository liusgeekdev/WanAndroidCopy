package com.lius.wanandroidcopy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.lius.wanandroidcopy.R;
import com.lius.wanandroidcopy.api.ServiceApi;
import com.lius.wanandroidcopy.model.ResponseData;
import com.lius.wanandroidcopy.model.UserBean;
import com.lius.wanandroidcopy.ui.base.BaseActivity;
import com.lius.wanandroidcopy.ui.presenter.LoginPresenter;
import com.lius.wanandroidcopy.ui.view.LoginView;
import com.lius.wanandroidcopy.util.SPUtils;
import com.lius.wanandroidcopy.widget.IconFontTextView;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter,LoginView> implements LoginView {

    @BindView(R.id.et_username)
    TextInputEditText etUsername;
    @BindView(R.id.username_layout)
    TextInputLayout usernameLayout;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.password_layout)
    TextInputLayout passwordLayout;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.icon_close)
    IconFontTextView iconClose;

    private String username;
    private String password;

    public static void startAction(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.icon_close, R.id.btn_register, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_close:
                finish();
                break;
            case R.id.btn_register:
                if (checkText()) {
                    mPresenter.register();
                }
                break;
            case R.id.btn_login:
                if (checkText()) {
                    mPresenter.login();
                }
                break;
        }
    }

    private boolean checkText() {
        boolean flag = true;
        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast("用户名和密码不能为空");
            flag = false;
        } else if (password.length() < 6) {
            Toast("密码必须大于6位");
            flag = false;
        }
        return flag;
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void loginSuccess(ResponseData<UserBean> userBeanResponseData) {
        if (userBeanResponseData.getErrorCode() != 0) {
            Toast(userBeanResponseData.getErrorMsg());
        } else {
            SPUtils.setBoolean(LoginActivity.this, ServiceApi.IS_LOGIN_KEY, true);
            SPUtils.setString(LoginActivity.this, ServiceApi.USERNAME_KEY, username);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    @Override
    public void loginError(String errorMessage) {
        Toast(errorMessage);
    }

    @Override
    public void registerSuccess(ResponseData<UserBean> userBeanResponseData) {
        if (userBeanResponseData.getErrorCode() != 0) {
            Toast(userBeanResponseData.getErrorMsg());
        } else {
            Toast("注册成功");
            mPresenter.login();
        }
    }

    @Override
    public void registerError(String errorMessage) {
        Toast(errorMessage);
    }
}
