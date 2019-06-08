package com.example.administrator.penapp;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.penapp.MyDialog.Dialog_start;
import com.example.administrator.penapp.api_get_js.BaseActivity;
import com.example.administrator.penapp.api_get_js.NetConn;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;

public class start_activity extends BaseActivity {
    Button cx;
    EditText et_xz;
    Dialog_start start;
    String health;
    HashMap<String,String>pmap;
    String color;
    String summay;
    String et_text;
    private KProgressHUD hud;
    String start_url="http://web.juhe.cn:8080/constellation/getAll";
    String appkey="20e5ed379de32f410addad07c5291ddb";


    @Override
    protected int intlayout() {
        return R.layout.start_layout;
    }

    @Override
    protected void initlabel() {
       et_xz=(EditText)findViewById(R.id.et_xinzuo);
       cx=(Button)findViewById(R.id.bt_chaxun);



       cx.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               et_text=et_xz.getText().toString();
              if (et_text=="")
              {
                  Toast.makeText(start_activity.this,"请填写星座",Toast.LENGTH_LONG).show();

              }
              else
              {
                  pmap=new HashMap<>();

                  pmap.put("consName",et_text);
                  pmap.put("type","today");
                  pmap.put("key",appkey);

                  start_url = start_url+"?"+urlencode(pmap);
                 Net(start_url,netConn);
                  hud = KProgressHUD.create(start_activity.this)
                         .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                  .show();
                scheduleDismiss();


              }
           }
       });

    }
    NetConn netConn=new NetConn() {
        @Override
        public void netok(String json) {
            Log.e("星座获得的json",json);
            try {
                JSONObject jsonObject=new JSONObject(json);
                health=jsonObject.getString("health");
                color=jsonObject.getString("color");
                summay=jsonObject.getString("summary");


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
                start=new Dialog_start(start_activity.this,health,color,summay);
                start.show();
            }
        }, 2000);
    }
}
