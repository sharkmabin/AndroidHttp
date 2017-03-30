package com.infrastructure.listener;


import com.infrastructure.fragment.BaseFragment;

public interface ITabClickListener {


    void onMenuItemClick();

    BaseFragment getFragment();
}