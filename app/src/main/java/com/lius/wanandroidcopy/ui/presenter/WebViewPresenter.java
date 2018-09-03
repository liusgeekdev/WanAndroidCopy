package com.lius.wanandroidcopy.ui.presenter;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lius.wanandroidcopy.ui.base.BasePresenter;
import com.lius.wanandroidcopy.ui.view.CommonWebView;

public class WebViewPresenter extends BasePresenter<CommonWebView> {
    public void setWebView(WebView webView, String url) {
        ProgressBar progressBar = getMvpView().getProgressBar();

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);// 支持JS
        settings.setBuiltInZoomControls(false);// 显示放大缩小按钮
        settings.setUseWideViewPort(true);// 支持双击放大缩小
        settings.setSupportZoom(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setLoadsImagesAutomatically(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                System.out.println("网页开始加载");
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                System.out.println("网页加载结束");
                progressBar.setVisibility(View.GONE);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                System.out.println("onProgressChanged");
                progressBar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                getMvpView().setTitle(title);
                super.onReceivedTitle(view, title);
            }
        });

        webView.loadUrl(url);
    }
}
