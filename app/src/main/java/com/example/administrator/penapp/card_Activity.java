package com.example.administrator.penapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.penapp.MyDialog.Dialog_card;
import com.example.administrator.penapp.MyDialog.Dialog_start;
import com.example.administrator.penapp.api_get_js.BaseActivity;
import com.example.administrator.penapp.api_get_js.NetConn;
import com.google.gson.JsonObject;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class card_Activity extends BaseActivity {
  Button bt_card_qd;
  EditText card_et;
  String card_number;
  String card_str_url;
  String card_brithday;
  String card_dizhi;
  String card_sex;
    private KProgressHUD hud;

    HashMap<String,String> pmap;



    @Override
    protected int intlayout() {
        return R.layout.ic_card_layout;
    }

    @Override
    protected void initlabel() {
        card_et=(EditText)findViewById(R.id.card_number_et);

        bt_card_qd=(Button)findViewById(R.id.caxun_bt_qq);

        pmap=new HashMap<>();

        bt_card_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card_number=card_et.getText().toString();
                Log.e("initlabel: ", card_number);
                card_str_url="http://apis.juhe.cn/idcard/index";
                pmap.put("cardno",card_number);
                pmap.put("dtype","json");
                pmap.put("key","60a2faec6b397e1586901079941444ef");
                card_str_url=card_str_url+"?"+urlencode(pmap);
                Net(card_str_url,netConn);

                hud = KProgressHUD.create(card_Activity.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .show();
                scheduleDismiss();

            }
        });

    }
    NetConn netConn=new NetConn() {
        @Override
        public void netok(String json) {
            Log.e("json字符串" ,json);
            try {
                JSONObject jsonObject=new JSONObject(json);
                card_brithday=jsonObject.getJSONObject("result").getString("birthday");
                card_dizhi=jsonObject.getJSONObject("result").getString("area");
                card_sex=jsonObject.getJSONObject("result").getString("sex");


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void netErroe(String errorMap) {

        }
    };
    private void scheduleDismiss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hud.dismiss();
                Dialog_card dialog_card=new Dialog_card(card_Activity.this,card_sex,card_brithday,card_dizhi);
                dialog_card.show();
            }
        }, 2000);
    }

}
