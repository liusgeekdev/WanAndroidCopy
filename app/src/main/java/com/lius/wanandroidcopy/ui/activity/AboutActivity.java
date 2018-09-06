package com.lius.wanandroidcopy.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.lius.wanandroidcopy.R;
import com.lius.wanandroidcopy.ui.base.BaseActivity;
import com.lius.wanandroidcopy.ui.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.aboutVersion)
    TextView aboutVersion;
    @BindView(R.id.aboutContent)
    TextView aboutContent;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_about;
    }

    public static void startAction(Context context) {
        context.startActivity(new Intent(context, AboutActivity.class));
    }

    @SuppressLint("ResourceType")
    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_return));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle(R.string.about);
        try {
            aboutVersion.setText(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        aboutContent.setText(Html.fromHtml(getString(R.string.about_content)));
        aboutContent.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
