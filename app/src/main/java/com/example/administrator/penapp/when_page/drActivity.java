package com.example.administrator.penapp.when_page;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.penapp.R;
import com.example.administrator.penapp.SqlSever.CreateUserSql;
import com.example.administrator.penapp.SqlSever.SqliteSever;
import com.example.administrator.penapp.ViewPage_Main;
import com.example.administrator.penapp.zc_acitivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/25 0025.
 */

public class drActivity extends AppCompatActivity {

    Context context;
    ImageView iv_gif;
    EditText username;
    EditText password;
    SqliteSever sqliteSever;
    CreateUserSql sql;
    CheckBox rember;
    SharedPreferences sp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dr_layout);
       context=drActivity.this;
       iv_gif=(ImageView)findViewById(R.id.dr_gif);
       username=(EditText)findViewById(R.id.dr_username_et);
       password=(EditText)findViewById(R.id.dr_password_et);
        sql=new CreateUserSql(drActivity.this);
        rember=(CheckBox) findViewById(R.id.dr_remberpassword_et);
        sp=getSharedPreferences("rember",0);
        String name_remberdate=sp.getString("username","");
        String password_remberdate=sp.getString("password","");
        boolean check_date=sp.getBoolean("check_rember", Boolean.parseBoolean(""));
        if(!name_remberdate.equals(null)&&!password_remberdate.equals(null))
        {
            username.setText(name_remberdate);
            password.setText(password_remberdate);
            rember.setChecked(check_date);

        }



        RequestOptions gif_options=new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).load(R.drawable.dr_1).apply(gif_options).into(iv_gif);






    }

    public void dr_bt(View view) {
        sqliteSever=new SqliteSever(username.getText().toString(),null,null,null,sql);
        Log.e("dr_bt: ",password.getText().toString() );
        sp = getSharedPreferences("rember", 0);
        SharedPreferences.Editor editor=sp.edit();
        if( rember.isChecked())
        {

            editor.putString("username",username.getText().toString());
            editor.putString("password",password.getText().toString());
            editor.putBoolean("check_rember",true);
            editor.commit();


        }
        else
        {
            editor.remove("username");
            editor.remove("password");
            editor.putBoolean("check_rember",false);
            editor.commit();
        }
        if(password.getText().toString().equals( sqliteSever.clic_select())) {
           String gongyong_username=username.getText().toString();

            startActivity(new Intent(drActivity.this, ViewPage_Main.class).putExtra("username",gongyong_username));
            overridePendingTransition(R.anim.anim_activity_in, R.anim.anim_activity_out);
        }
        else
        {
            Log.e("dr_bt: ",sqliteSever.clic_select() );
            Toast.makeText(drActivity.this,"密码错误",Toast.LENGTH_SHORT).show();

        }
    }

    public void bt_dr_zc(View view) {
        startActivity(new Intent(drActivity.this,zc_acitivity.class));
        overridePendingTransition(R.anim.anim_activity_in,R.anim.anim_activity_out);
    }
}
