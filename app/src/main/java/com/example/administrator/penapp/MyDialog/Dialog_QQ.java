package com.example.administrator.penapp.MyDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.penapp.R;

public class Dialog_QQ extends Dialog {
    public static  String qq_number;
    SharedPreferences sp;



    public Dialog_QQ(@NonNull Context context,View layout) {
        super(context);
        setContentView(layout);
        Window window=getWindow();
        WindowManager.LayoutParams params=window.getAttributes();
        TextView bt_close=(TextView) findViewById(R.id.qq_dialog_close);
        TextView bt_qd=(TextView)findViewById(R.id.qq_dialog_qd);
        final EditText ed_qq_number=(EditText)findViewById(R.id.qq_dialog_et);
        bt_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        sp=context.getSharedPreferences("number",0);
        bt_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=sp.edit();
                qq_number=ed_qq_number.getText().toString();
                editor.putString("qq_number",qq_number);
                editor.commit();

                dismiss();
            }
        });
    }
}
