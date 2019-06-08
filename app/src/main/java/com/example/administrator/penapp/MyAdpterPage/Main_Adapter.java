package com.example.administrator.penapp.MyAdpterPage;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Main_Adapter extends PagerAdapter {
    ArrayList<View> views=new ArrayList<>();

    public Main_Adapter( ArrayList<View> mviews)
    {
        views=mviews;

    }
    @Override
    public int getCount() {
        return views.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v_view=views.get(position);

        container.addView(v_view);
        return v_view;
    }

    @Override
    public boolean isViewFromObject(
            @NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView(views.get(position));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
