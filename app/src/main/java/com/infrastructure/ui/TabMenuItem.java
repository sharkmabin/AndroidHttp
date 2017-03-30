package com.infrastructure.ui;


import com.infrastructure.fragment.BaseFragment;

/**
 * Created by yx on 16/4/3.
 */
public class TabMenuItem {

    /**
     * icon
     */
    public int imageResId;
    /**
     * 文本
     */
    public int lableResId;


    public Class<? extends BaseFragment>tagFragmentClz;

    public TabMenuItem(int imageResId, int lableResId) {
        this.imageResId = imageResId;
        this.lableResId = lableResId;
    }


    public TabMenuItem(int imageResId, int lableResId, Class<? extends BaseFragment> tagFragmentClz) {
        this.imageResId = imageResId;
        this.lableResId = lableResId;
        this.tagFragmentClz = tagFragmentClz;
    }
}



