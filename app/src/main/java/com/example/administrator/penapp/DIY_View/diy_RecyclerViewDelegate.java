package com.example.administrator.penapp.DIY_View;

import com.mingle.adapter.MenuRVAdapter;
import com.mingle.sweetpick.RecyclerViewDelegate;

public class diy_RecyclerViewDelegate extends RecyclerViewDelegate {
    private MenuRVAdapter mMenuRVAdapter;
    public diy_RecyclerViewDelegate(boolean dragEnable) {
        super(dragEnable);
    }
    public void notifyDataSetChanged() {
        mMenuRVAdapter.notifyDataSetChanged();
    }
}
