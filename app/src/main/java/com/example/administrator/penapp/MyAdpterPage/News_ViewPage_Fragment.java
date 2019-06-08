package com.example.administrator.penapp.MyAdpterPage;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.administrator.penapp.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class News_ViewPage_Fragment  extends Fragment {
     private  News_ViewPage_Adapter news_adapter;
     Bitmap image_bitmap;
     String image_url;
     Bitmap yasuo_bitmap;
     ImageView image;
     boolean bitmap_true=true;
   Handler mHandler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           switch (msg.what) {
               case 1:



                   image.post(new Runnable() {
                       @Override
                       public void run() {

                           yasuo_bitmap=Bitmap.createScaledBitmap(image_bitmap,480,800,true);
                           image.setImageBitmap(yasuo_bitmap);
                           android.graphics.Matrix matrix=new android.graphics.Matrix();
                           matrix.reset();
                           float wv = image.getWidth();
                           float hv = image.getHeight();
                           Log.e("注意长度",wv+"");
                           float wi = image.getDrawable().getIntrinsicWidth();
                           float hi = image.getDrawable().getIntrinsicHeight();

                           float width = wv;
                           float height = hv;
                           if (wi / wv > hi / hv) {
                               matrix.setScale(hv / hi, hv / hi);
                               width = wi * hv / hi;
                           } else {
                               matrix.setScale(wv / wi, wv / wi);
                               height= hi * wv / wi;
                           }

                           matrix.preTranslate((wv - width) / 2, (hv - height) / 2);
                           image.setScaleType(ImageView.ScaleType.MATRIX);
                           image.setImageMatrix(matrix);

                       }
                   });
               break;
           }
       }
   };




    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       super.onCreateView(inflater, container, savedInstanceState);
        View f_v = inflater.inflate(R.layout.news_original_layout, container, false);
        image=(ImageView)f_v.findViewById(R.id.image);
//        BitmapFactory.decodeByteArray(getArguments().getByteArray("image"),0,getArguments().getByteArray("image").length);
        new Thread(new Runnable() {
            @Override
            public void run() {
                image_url = getArguments().getString("imageurl");
                image_bitmap = getHttpBitmap(image_url);
                Message msg=new Message();
                msg.what=1;
                mHandler.sendMessage(msg);
            }
        }).start();




        TextView text = (TextView)f_v.findViewById(R.id.name);
        text.setText(getArguments().getString("name"));

        TextView more = (TextView)f_v.findViewById(R.id.more);
        TextView neirong=(TextView)f_v.findViewById(R.id.news_neirong);
        neirong.setText(getArguments().getString("news_neirong"));
        more.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (news_adapter != null) {
                    news_adapter.remove(News_ViewPage_Fragment.this);
                    news_adapter.notifyDataSetChanged();
                }
                return true;
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (news_adapter != null) {
                    int select = (int) (Math.random() * 4);


                    int[] resD = {R.mipmap.bg_nina, R.mipmap.bg_niju, R.mipmap.bg_yuki, R.mipmap.bg_kero};
                    String[] resS = {"Nina", "Niju", "Yuki", "Kero"};

                    News_ViewPage_Fragment newP = new News_ViewPage_Fragment();
                    Bundle b = new Bundle();
                    b.putInt("image", resD[select]);
                    b.putString("name", resS[select]);
                    newP.setArguments(b);
                    news_adapter.add(newP);

                }
            }
        });
      return  f_v;
    }
    public void setAdapter(News_ViewPage_Adapter catsAdapter) {
        news_adapter = catsAdapter;
    }
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

}
