package com.infrastructure.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by yx on 16/4/3.
 */
public class TabLayout extends LinearLayout implements View.OnClickListener{

    private ArrayList<TabMenuItem> tabs;
    private OnTabClickListener listener;
    private View selectView;
    private int tabCount;

    public TabLayout(Context context) {
        super(context);
        initView();
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView(){
        setOrientation(HORIZONTAL);


    }

    public void setCurrentTab(int i) {
        if (i < tabCount && i >= 0) {
            View view = getChildAt(i);
        if (selectView != view) {
            view.setSelected(true);
            if (selectView != null) {
                selectView.setSelected(false);
            }
            selectView = view;
        }
        }
    }

    public void onDataChanged(int i, int badgeCount) {
        if (i < tabCount && i >= 0) {
            TabMenuView view = (TabMenuView) getChildAt(i);
            view.onDataChanged(badgeCount);
        }
    }

    public void initData(ArrayList<TabMenuItem>tabs, OnTabClickListener listener){
        this.tabs=tabs;
        this.listener=listener;
        LayoutParams params=new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight=1;
        if(tabs!=null&&tabs.size()>0){
            tabCount=tabs.size();
            TabMenuView mTabMenuView =null;
            for(int i=0;i< tabs.size();i++){
                mTabMenuView =new TabMenuView(getContext());
                mTabMenuView.setTag(tabs.get(i));
                mTabMenuView.initData(tabs.get(i));
                mTabMenuView.setOnClickListener(this);
                addView(mTabMenuView,params);
            }

        }else{
            throw new IllegalArgumentException("tabs can not be empty");
        }

    }


    @Override
    public void onClick(View v) {

        listener.onTabClick((TabMenuItem) v.getTag());

    }
    public interface OnTabClickListener{

        void onTabClick(TabMenuItem tabItem);
    }
}
