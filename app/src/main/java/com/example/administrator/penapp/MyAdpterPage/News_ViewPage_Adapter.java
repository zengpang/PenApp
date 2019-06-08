package com.example.administrator.penapp.MyAdpterPage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

public class News_ViewPage_Adapter extends FragmentStatePagerAdapter  {
    private ArrayList<News_ViewPage_Fragment> mFragemnt;
    private ViewPager mpage;
    public News_ViewPage_Adapter(FragmentManager fm) {
        super(fm);
        mFragemnt=new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragemnt.get(position);
    }

    @Override
    public int getCount() {
        return mFragemnt.size();
    }
    public void add(News_ViewPage_Fragment newfragment)
    {
        newfragment.setAdapter(this);
        mFragemnt.add(newfragment);
    }
    public void remove(int i)
    {
        mFragemnt.remove(i);
        notifyDataSetChanged();
    }
    public void remove(News_ViewPage_Fragment newsfragment)
    {
        mFragemnt.remove(newsfragment);
        int pos=mpage.getCurrentItem();
        notifyDataSetChanged();
        mpage.setAdapter(this);

    }
    public int getItemPostion(Object object)
    {
        return  POSITION_NONE;
    }
    public  void setPage(ViewPager page)
    {
        mpage=page;
    }
}
