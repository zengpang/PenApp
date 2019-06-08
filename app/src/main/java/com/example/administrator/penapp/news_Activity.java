package com.example.administrator.penapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.penapp.MyAdpterPage.News_ViewPage_Adapter;
import com.example.administrator.penapp.MyAdpterPage.News_ViewPage_Fragment;
import com.example.administrator.penapp.api_get_js.BaseActivity;
import com.example.administrator.penapp.api_get_js.NetConn;
import com.xgc1986.parallaxPagerTransformer.ParallaxPagerTransformer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class news_Activity extends BaseActivity {
   ViewPager page_main;
    View first;
    View Second;
    View third;
    View fifth;
    View fouth;
    News_ViewPage_Adapter News_Adapter;
    ArrayList<View> ViewsList;
    HashMap<String,String> pmap;
    String news_url="http://v.juhe.cn/toutiao/index";
    String app_key="2507cdf6e5893f002f3f3d9300cc005d";
    List<String> list_image_url;
    List<String> list_newstitle_text;
    ArrayList<String> list_first_image;
    ArrayList<String> list_first_title;
    ArrayList<String> list_first_author_name;
    Bitmap bitmap_1;
    Intent intent_get;
    byte[] bitmap_array_1;
    String news_get_url;


    @Override
    protected int intlayout() {
        return R.layout.news_layout;
    }

    @Override
    protected void initlabel() {

//        page_main=(ViewPager)findViewById(R.id.news_main_vewpage);
//        ViewsList=new ArrayList<>();
//        first=getLayoutInflater().inflate(R.layout.news_first_layout,null);
//        Second=getLayoutInflater().inflate(R.layout.news_second_layout,null);
//        third=getLayoutInflater().inflate(R.layout.news_third_layout,null);
//        fifth=getLayoutInflater().inflate(R.layout.news_fifth_layout,null);
//        fouth=getLayoutInflater().inflate(R.layout.news_fourth_layout,null);
//        ViewsList.add(first);
//        ViewsList.add(Second);
//        ViewsList.add(third);
//        ViewsList.add(fifth);
//        ViewsList.add(fouth);
//        page_main.setAdapter(new Main_Adapter(ViewsList));
//        page_main.setPageTransformer(false,new ParallaxPagerTransformer(R.id.Re_news_first));
        intent_get=getIntent();

        list_image_url=new ArrayList<>();
        list_newstitle_text=new ArrayList<>();
        list_first_image=new ArrayList<>();
        list_first_title=new ArrayList<>();
        list_first_author_name=new ArrayList<>();
        list_first_image=intent_get.getStringArrayListExtra("first_list_image_date");
        list_first_title=intent_get.getStringArrayListExtra("first_list_date");
        list_first_author_name=intent_get.getStringArrayListExtra("first_list_author_name");



        page_main = (ViewPager) findViewById(R.id.news_main_vewpage);
        page_main.setBackgroundColor(0xFF000000);
        pmap=new HashMap<>();
        pmap.put("key",app_key);
        news_get_url=news_url+"?"+urlencode(pmap);

        Log.e("initlabel: ", news_get_url);

        ParallaxPagerTransformer pt = new ParallaxPagerTransformer((R.id.image));
        pt.setBorder(20);
        //pt.setSpeed(0.2f);
        page_main.setPageTransformer(false, pt);

        News_Adapter = new News_ViewPage_Adapter(getSupportFragmentManager());
        News_Adapter.setPage(page_main); //only for this transformer

        Bundle bNina = new Bundle();





        bNina.putString("imageurl", list_first_image.get(0));
        bNina.putString("name", list_first_author_name.get(0));
        bNina.putString("news_neirong",list_first_title.get(0));
        News_ViewPage_Fragment pfNina = new News_ViewPage_Fragment();
        pfNina.setArguments(bNina);
        News_Adapter.add(pfNina);


        Bundle bNiju = new Bundle();
        bNiju.putString("imageurl", list_first_image.get(1));
        bNiju.putString("name", list_first_author_name.get(1));
        bNiju.putString("news_neirong",list_first_title.get(1));
        News_ViewPage_Fragment pfNiju = new News_ViewPage_Fragment();
        pfNiju.setArguments(bNiju);

        Bundle bYuki = new Bundle();
        bYuki.putString("imageurl", list_first_image.get(2));
        bYuki.putString("name", list_first_author_name.get(2));
        bYuki.putString("news_neirong",list_first_title.get(2));
        News_ViewPage_Fragment pfYuki = new News_ViewPage_Fragment();
        pfYuki.setArguments(bYuki);

        Bundle bKero = new Bundle();
        bKero.putString("imageurl", list_first_image.get(3));
        bKero.putString("name", list_first_author_name.get(3));
        bKero.putString("news_neirong",list_first_title.get(3));
        News_ViewPage_Fragment pfKero = new News_ViewPage_Fragment();
        pfKero.setArguments(bKero);


        News_Adapter.add(pfNiju);
        News_Adapter.add(pfYuki);
        News_Adapter.add(pfKero);
        page_main.setAdapter(News_Adapter);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().show();
        }
    }

    NetConn netConn=new NetConn() {
        @Override
        public void netok(String json) {
            Log.e("json字符串" ,"123");
            try {

                JSONObject jsonObject=new JSONObject(json);
                JSONArray jsonArray=jsonObject.getJSONObject("result").getJSONArray("data");
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    list_image_url.add(jsonObject1.getString("thumbnail_pic_s"));
                    Log.e("json字符串_1" ,jsonObject1.getString("thumbnail_pic_s"));
                    list_newstitle_text.add(jsonObject1.getString("title"));


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void netErroe(String errorMap) {

        }
    };

    public static Bitmap getHttpBitmap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setConnectTimeout(0);
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();

        return super.onOptionsItemSelected(item);
    }



    }

