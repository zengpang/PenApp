package com.example.administrator.penapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.penapp.SqlSever.CreateUserSql;
import com.example.administrator.penapp.SqlSever.SqliteSever;
import com.example.administrator.penapp.when_page.drActivity;

public class zc_acitivity extends AppCompatActivity {
    EditText username;
    EditText Email;
    EditText password;
    EditText number;
    long zt;
    SqliteSever sqliteSever;
    CreateUserSql sql;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zc_layout);
        username=(EditText) findViewById(R.id.zc_username);
        Email=(EditText)findViewById(R.id.zc_Email);
        password=(EditText)findViewById(R.id.zc_password);
        number=(EditText)findViewById(R.id.zc_number);
         sql=new CreateUserSql(zc_acitivity.this);




    }

    public void bt_zc_zc(View view) {
        sqliteSever=new SqliteSever(username.getText().toString(),password.getText().toString(),Email.getText().toString(),number.getText().toString(),sql);

        zt=sqliteSever.sql_zc();
        if(zt>0)
        {
            Toast.makeText(zc_acitivity.this,"注册成功",Toast.LENGTH_LONG).show();
            startActivity(new Intent(zc_acitivity.this,drActivity.class));
            overridePendingTransition(R.anim.anim_activity_in,R.anim.anim_activity_out);
        }
        else
        {
            Toast.makeText(zc_acitivity.this,"注册失败",Toast.LENGTH_LONG).show();
        }


    }
}
