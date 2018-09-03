package com.lius.wanandroidcopy.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lius.wanandroidcopy.R;
import com.lius.wanandroidcopy.api.ServiceApi;
import com.lius.wanandroidcopy.model.ArticleBean;
import com.lius.wanandroidcopy.ui.activity.WebViewActivity;
import com.lius.wanandroidcopy.util.SPUtils;
import com.lius.wanandroidcopy.util.T;
import com.lius.wanandroidcopy.widget.IconFontTextView;

import java.util.List;

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
        }else if (type == 1){
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
            }
        });

    }
}
