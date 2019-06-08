package com.example.administrator.penapp.ListAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.administrator.penapp.R;

public class SoundrecordingList extends BaseAdapter {
    Context context;

    public SoundrecordingList(Context mcontext)
    {
        context=mcontext;
    }
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view1;
        if(convertView==null)
        {
           view1=View.inflate(context, R.layout.main_item_sounds,null);

        }
        else
        {
         view1=convertView;

        }
        return view1;
    }
}
