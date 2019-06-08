package com.example.administrator.penapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.penapp.api_get_js.AppConfig_url;
import com.example.administrator.penapp.api_get_js.BaseActivity;
import com.example.administrator.penapp.api_get_js.NetConn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class cidian_Activity extends BaseActivity {
    EditText text_fanyi;
    String fanyi;
    String jieguo_text;
    HashMap<String,String> pmap;
    TextView text_xianshi;
    String strurl="http://dict.youdao.com/suggest";

    @Override
    protected int intlayout() {
        return R.layout.cidian_layout;
    }

    @Override
    protected void initlabel() {
        text_fanyi=(EditText)findViewById(R.id.et_fanyi_text);
        fanyi=text_fanyi.getText().toString();
        text_xianshi=(TextView)findViewById(R.id.fanyi_text);
        pmap=new HashMap<>();

    }
    NetConn netConn=new NetConn() {
        @Override
        public void netok(String json) {
            JSONObject jsonObject= null;
            try {
                jsonObject = new JSONObject(json).getJSONObject("data");
                JSONArray jsonArray=jsonObject.getJSONArray("entries");
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);

                    jieguo_text=jsonObject1.getString("explain");


                }
                Log.e("jieguo_text",jieguo_text);
                text_xianshi.setText(jieguo_text);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void netErroe(String errorMap) {

        }
    };
    public void bt_fanyi(View view) {
        fanyi=text_fanyi.getText().toString();
        pmap.put("q",fanyi);
        pmap.put("le","eng");
        pmap.put("num","1");
        pmap.put("doctype","json");
        strurl="http://dict.youdao.com/suggest";
        strurl = strurl+"?"+urlencode(pmap);
        Log.e("bt_fanyi: ",strurl );
        Net(strurl,netConn);
    }
}
