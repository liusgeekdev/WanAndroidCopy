package com.lius.wanandroidcopy.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lius.wanandroidcopy.R;
import com.lius.wanandroidcopy.api.ServiceApi;
import com.lius.wanandroidcopy.helper.RxObserver;
import com.lius.wanandroidcopy.model.ArticleBean;
import com.lius.wanandroidcopy.model.ResponseData;
import com.lius.wanandroidcopy.ui.activity.LoginActivity;
import com.lius.wanandroidcopy.ui.activity.WebViewActivity;
import com.lius.wanandroidcopy.util.SPUtils;
import com.lius.wanandroidcopy.util.T;
import com.lius.wanandroidcopy.widget.IconFontTextView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ArticleListAdapter extends BaseQuickAdapter<ArticleBean,BaseViewHolder> {

    private Context mContext;
    //普通文章列表0;收藏列表1(因为收藏列表返回文章数据无collect,同时也不需要判断)
    private int type;

    public ArticleListAdapter(Context context, @Nullable List<ArticleBean> data, int type) {
        super(R.layout.item_article, data);
        mContext = context;
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder holder, ArticleBean item) {
        holder.setText(R.id.tv_author, item.getAuthor())
                .setText(R.id.tv_time, item.getNiceDate())
                .setText(R.id.tv_title, Html.fromHtml(item.getTitle()))
                .setText(R.id.tv_type, item.getChapterName());

        IconFontTextView tvCollect = holder.getView(R.id.icon_collect);
        if (type == 0) {
            //普通文章列表
            if (item.isCollect()) {
                tvCollect.setText(mContext.getResources().getText(R.string.ic_collect_sel));
                tvCollect.setTextColor(mContext.getResources().getColor(R.color.app_blue));
            }else {
                tvCollect.setText(mContext.getResources().getText(R.string.ic_collect_nor));
                tvCollect.setTextColor(mContext.getResources().getColor(R.color.tab_nor_color));
            }
        } else if (type == 1){
            //收藏列表
            tvCollect.setText(mContext.getResources().getText(R.string.ic_collect_sel));
            tvCollect.setTextColor(mContext.getResources().getColor(R.color.app_blue));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.startAction(mContext, item.getLink());
            }
        });

        holder.getView(R.id.icon_collect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (!SPUtils.getBoolean(mContext, ServiceApi.IS_LOGIN_KEY, false)) {
                   T.showShort(mContext, "请先登录");
                   LoginActivity.startAction(mContext);
                   return;
               }
               if (type == 0) {
                   //普通文章列表
                   if (item.isCollect()) {
                       cancelCollect(item, tvCollect);
                   } else {
                       collect(item, tvCollect);
                   }
               } else if (type == 1) {
                   //收藏列表
                   cancelCollect2(item, tvCollect, holder.getAdapterPosition());
               }
            }
        });

    }

    /**
     * 收藏文章
     */
    private void collect(ArticleBean bean, TextView tvCollect) {
        ServiceApi.collectArticle(bean.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObserver<ResponseData<String>>() {
                    @Override
                    public void _onNext(ResponseData<String> stringResponseData) {
                        if (stringResponseData.getErrorCode() == 0) {
                            T.showShort(mContext, "收藏成功");
                            bean.setCollect(true);
                            tvCollect.setText(mContext.getResources().getText(R.string.ic_collect_sel));
                            tvCollect.setTextColor(mContext.getResources().getColor(R.color.app_blue));
                        } else {
                            T.showShort(mContext, stringResponseData.getErrorMsg());
                        }
                    }

                    @Override
                    public void _onError(String errorMseeage) {
                        T.showShort(mContext, errorMseeage);
                    }
                });
    }

    /**
     * 取消收藏(文章列表)
     */
    private void cancelCollect(ArticleBean bean, TextView tvCollect) {
        ServiceApi.unCollectArticle(bean.getId())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new RxObserver<ResponseData<String>>() {
            @Override
            public void _onNext(ResponseData<String> stringResponseData) {
                if (stringResponseData.getErrorCode() == 0) {
                    T.showShort(mContext, "取消收藏成功");
                    bean.setCollect(false);
                    tvCollect.setText(mContext.getResources().getText(R.string.ic_collect_nor));
                    tvCollect.setTextColor(mContext.getResources().getColor(R.color.tab_nor_color));
                } else {
                    T.showShort(mContext, stringResponseData.getErrorMsg());
                }
            }

            @Override
            public void _onError(String errorMseeage) {
                T.showShort(mContext, errorMseeage);
            }
        });
    }

    /**
     * 取消收藏(我的收藏页面)
     */
    private void cancelCollect2(ArticleBean bean, TextView tvCollect, int position) {
       ServiceApi.unCollectArticle2(bean.getId(), bean.getOriginId())
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new RxObserver<ResponseData<String>>() {
                   @Override
                   public void _onNext(ResponseData<String> stringResponseData) {
                       if (stringResponseData.getErrorCode() == 0) {
                           T.showShort(mContext, "取消收藏成功");
                           getData().remove(position);
                           if (position != 0) {
                               notifyItemChanged(position);
                           } else {
                               notifyDataSetChanged();
                           }
                       } else {
                           T.showShort(mContext, stringResponseData.getErrorMsg());
                       }
                   }

                   @Override
                   public void _onError(String errorMseeage) {
                        T.showShort(mContext, errorMseeage);
                   }
               });
    }
}
