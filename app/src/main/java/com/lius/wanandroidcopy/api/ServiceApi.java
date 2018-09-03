package com.lius.wanandroidcopy.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lius.wanandroidcopy.model.ArticleList;
import com.lius.wanandroidcopy.model.BannerBean;
import com.lius.wanandroidcopy.model.ResponseData;
import com.lius.wanandroidcopy.model.UserBean;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.Converter;
import com.lzy.okrx2.adapter.ObservableBody;

import java.lang.reflect.Type;
import java.util.List;
import io.reactivex.Observable;

import okhttp3.Response;

public class ServiceApi {

    public static final String IS_LOGIN_KEY = "isLogin";

    public static final String BASE_URL = "http://wanandroid.com/";
    public static final String bannerUrl = BASE_URL + "banner/json"; //首页banner
    public static final String homeArticleUrl = BASE_URL + "article/list/%d/json";//1.1 首页文章列表
    public static final String loginUrl = BASE_URL + "user/login";//登录

    private static Gson gson = new Gson();

    /**
     * 登录
     */
    public static Observable<ResponseData<UserBean>> login(String username, String password) {
        return OkGo.<ResponseData<UserBean>>post(loginUrl)
              .params("username", username)
              .params("password", password)
              .converter(new Converter<ResponseData<UserBean>>() {
                  @Override
                  public ResponseData<UserBean> convertResponse(Response response) throws Throwable {
                      Type type = new TypeToken<ResponseData<UserBean>>() {

                      }.getType();
                      return gson.fromJson(response.body().string(), type);
                  }
              }).adapt(new ObservableBody<ResponseData<UserBean>>());
    }

    /**
     * 获取banner数据
     */
    public static Observable<ResponseData<List<BannerBean>>> getBannerData(){
        return OkGo.<ResponseData<List<BannerBean>>>get(bannerUrl)
                .converter(new Converter<ResponseData<List<BannerBean>>>() {
                    @Override
                    public ResponseData<List<BannerBean>> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<List<BannerBean>>>() {
                        }.getType();
                        return gson.fromJson(response.body().string(),type);
                    }
                })
                .adapt(new ObservableBody<>());
    }

    /**
     * 获取首页文章列表
     */
    public static Observable<ResponseData<ArticleList>> getHomeArticle(int page) {
        return OkGo.<ResponseData<ArticleList>>get(String.format(homeArticleUrl, page))
                .converter(new Converter<ResponseData<ArticleList>>() {
                    @Override
                    public ResponseData<ArticleList> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<ArticleList>>() {

                        }.getType();
                        return gson.fromJson(response.body().string(), type);
                    }
                })
                .adapt(new ObservableBody<>());
    }
}
