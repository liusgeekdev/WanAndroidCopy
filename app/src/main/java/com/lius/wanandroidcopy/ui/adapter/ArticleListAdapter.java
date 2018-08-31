package com.lius.wanandroidcopy.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lius.wanandroidcopy.R;
import com.lius.wanandroidcopy.model.ArticleBean;
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

        } else if (type == 1) {
            
        }
    }
}
