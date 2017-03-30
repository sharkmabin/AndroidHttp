package com.infrastructure.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.infrastructure.R;


/**
 * Created by yx on 16/4/3.
 */

public class TabMenuView extends LinearLayout implements View.OnClickListener{

    private ImageView mTabImage;
    private TextView mTabLable;

    public TabMenuView(Context context) {
        super(context);
        initView(context);
    }

    public TabMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TabMenuView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context){
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        LayoutInflater.from(context).inflate(R.layout.include_tab_menu,this,true);
        mTabImage=(ImageView)findViewById(R.id.ivTab);
        mTabLable=(TextView)findViewById(R.id.tvTab);

    }

    public void initData(TabMenuItem tabItem){

        mTabImage.setImageResource(tabItem.imageResId);
        mTabLable.setText(tabItem.lableResId);
    }

    @Override
    public void onClick(View v) {

    }
    public void onDataChanged(int badgeCount) {
        //  TODO notify new message, change the badgeView
    }
}
