package com.example.administrator.penapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.etsy.android.grid.StaggeredGridView;
import com.example.administrator.penapp.DIY_View.DiyAdapter;
import com.example.administrator.penapp.DIY_View.Diylistdate;

import java.util.ArrayList;

public class souds_list_Activity extends AppCompatActivity {
    private StaggeredGridView mGridView;
    private boolean mHasRequestedMore;
    private DiyAdapter mAdapter;
    Context mContext;
    private ArrayList<String> mData;
    public static final String SAVED_DATA_KEY = "SAVED_DATA";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sounds_list_layout);
        mGridView = (StaggeredGridView) findViewById(R.id.sounds_list_grid_view);
        mContext=souds_list_Activity.this;

        LayoutInflater layoutInflater = getLayoutInflater();





        mAdapter = new DiyAdapter(mContext, R.id.txt_line1);
        if (savedInstanceState != null) {
            mData = savedInstanceState.getStringArrayList(SAVED_DATA_KEY);
        }

        if (mData == null) {
            mData = Diylistdate.generateSampleData(souds_list_Activity.this);
        }

        for (String data : mData) {
            mAdapter.add(data);
        }


        mGridView.setAdapter(mAdapter);


    }
}
