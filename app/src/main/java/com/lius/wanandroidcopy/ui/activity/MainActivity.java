package com.lius.wanandroidcopy.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.lius.wanandroidcopy.R;
import com.lius.wanandroidcopy.ui.adapter.FragPagerAdapter;
import com.lius.wanandroidcopy.ui.base.BaseActivity;
import com.lius.wanandroidcopy.ui.base.BasePresenter;
import com.lius.wanandroidcopy.ui.fragment.HomeFragment;
import com.lius.wanandroidcopy.ui.fragment.TypeFragment;
import com.lius.wanandroidcopy.ui.fragment.UserFragment;
import com.lius.wanandroidcopy.widget.IconFontTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.icon_search)
    IconFontTextView iconSearch;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.icon_home)
    IconFontTextView iconHome;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.icon_type)
    IconFontTextView iconType;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.icon_user)
    IconFontTextView iconUser;
    @BindView(R.id.tv_user)
    TextView tvUser;

    private List<Fragment> mFragments = new ArrayList<>();


    @Override
    public void initView() {
        setTabColor(iconHome, tvHome);
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(TypeFragment.newInstance());
        mFragments.add(UserFragment.newInstance());

        viewPager.setAdapter(new FragPagerAdapter(getSupportFragmentManager(), mFragments));
        viewPager.setCurrentItem(0, false);
        viewPager.setOffscreenPageLimit(3);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setTabColor(iconHome, tvHome);
                        iconSearch.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        setTabColor(iconType, tvType);
                        iconSearch.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        setTabColor(iconUser, tvUser);
                        iconSearch.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.icon_search, R.id.ll_home, R.id.ll_type, R.id.ll_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_search:
                SearchActivity.startAction(this);
                break;
            case R.id.ll_home:
                viewPager.setCurrentItem(0);
                break;
            case R.id.ll_type:
                viewPager.setCurrentItem(1);
                break;
            case R.id.ll_user:
                viewPager.setCurrentItem(2);
                break;
        }
    }

    private void setTabColor(IconFontTextView icon, TextView text) {
        iconHome.setTextColor(getResources().getColor(R.color.tab_nor_color));
        tvHome.setTextColor(getResources().getColor(R.color.tab_nor_color));
        iconType.setTextColor(getResources().getColor(R.color.tab_nor_color));
        tvType.setTextColor(getResources().getColor(R.color.tab_nor_color));
        iconUser.setTextColor(getResources().getColor(R.color.tab_nor_color));
        tvUser.setTextColor(getResources().getColor(R.color.tab_nor_color));
        icon.setTextColor(getResources().getColor(R.color.tab_sel_color));
        text.setTextColor(getResources().getColor(R.color.tab_sel_color));
    }
}
