package com.lius.wanandroidcopy.ui.fragment;

import android.text.GetChars;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lius.wanandroidcopy.R;
import com.lius.wanandroidcopy.api.ServiceApi;
import com.lius.wanandroidcopy.ui.activity.AboutActivity;
import com.lius.wanandroidcopy.ui.activity.CollectActivity;
import com.lius.wanandroidcopy.ui.activity.LoginActivity;
import com.lius.wanandroidcopy.ui.base.BaseFragment;
import com.lius.wanandroidcopy.ui.base.BasePresenter;
import com.lius.wanandroidcopy.util.SPUtils;
import com.lius.wanandroidcopy.util.T;

import butterknife.BindView;
import butterknife.OnClick;

public class UserFragment extends BaseFragment {

    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.btn_collect)
    Button btnCollect;
    @BindView(R.id.btn_about)
    Button btnAbout;
    @BindView(R.id.btn_login)
    Button btnLogin;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_user;
    }

    @Override
    public void initView(View rootView) {
        if (SPUtils.getBoolean(getContext(), ServiceApi.IS_LOGIN_KEY, false)) {
            btnLogin.setText(R.string.exit_login);
            tvUsername.setText(SPUtils.getString(getContext(), ServiceApi.USERNAME_KEY, ""));
        } else {
            btnLogin.setText(R.string.click_login);
            tvUsername.setText(R.string.no_login);
        }
    }

    @OnClick({R.id.btn_collect, R.id.btn_about, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_collect:
                if (!SPUtils.getBoolean(getContext(), ServiceApi.IS_LOGIN_KEY, false)) {
                    T.showShort(getContext(), "请先登录");
                    LoginActivity.startAction(getContext());
                    return;
                }
                CollectActivity.startAction(getActivity());
                break;
            case R.id.btn_about:
                AboutActivity.startAction(getActivity());
                break;
            case R.id.btn_login:
                if (SPUtils.getBoolean(getContext(), ServiceApi.IS_LOGIN_KEY, false)) {
                    SPUtils.setBoolean(getContext(), ServiceApi.IS_LOGIN_KEY, false);
                    btnLogin.setText(R.string.click_login);
                    tvUsername.setText(R.string.no_login);
                } else {
                    LoginActivity.startAction(getActivity());
                }
                break;
        }
    }
}
