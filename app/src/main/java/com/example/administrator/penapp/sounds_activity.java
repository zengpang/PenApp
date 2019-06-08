package com.example.administrator.penapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class sounds_activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soundrecording_layout);
//        ImageView gif_background=(ImageView)findViewById(R.id.gif_backgrpund);
//        RequestOptions gif_options=new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
//        Glide.with(sounds_activity.this).load(R.drawable.sounds_background).apply(gif_options).into(gif_background);
    }
}
