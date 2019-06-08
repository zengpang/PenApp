package com.example.administrator.penapp.api_get_js;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.penapp.R;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract  class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(intlayout());
        initlabel();

    }
    protected abstract int intlayout();
    protected abstract void initlabel();
    public void Net(final String strurl, final NetConn netConn)
    {

        new Thread(new Runnable() {
            @Override
            public void run() {

                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder()
                        .url(strurl)
                        .build();
                Call call=client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                          netErroe("GET失败了",netConn);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responstr=response.body().string();
                         netok(responstr,netConn);
                    }
                });
            }
        }).start();
    }
    private void netok(final String json,final NetConn netConn)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                netConn.netok(json);

            }
        });

    }
    private void netErroe(final String json,final  NetConn netConn)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                netConn.netErroe(json);
            }
        });
    }
    public static String urlencode(HashMap<String, String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
