package com.example.administrator.penapp.DIY_View;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.administrator.penapp.SqlSever.CreateNumberSql;
import com.example.administrator.penapp.SqlSever.CreateSoundsSql;
import com.example.administrator.penapp.SqlSever.CreateUserSql;
import com.example.administrator.penapp.SqlSever.sqlsever_number;
import com.example.administrator.penapp.SqlSever.sqlsever_sounds;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Diylistdate {







    public static ArrayList<String> generateSampleData(Context mcontext) {
        CreateSoundsSql sql=new CreateSoundsSql(mcontext);

        sqlsever_sounds sounds_sql=new sqlsever_sounds(sql);


        long a=sounds_sql.select_sounds();
        final ArrayList<String> data = new ArrayList<String>();


        if(a==0)
        {
            data.add("暂且无新录音");
        }
        else {
            for (int i = 0; i < a; i++) {
                data.add( "录音 :");
            }
        }
        return data;
    }
    public static ArrayList<String> generateSampleData() {
        final ArrayList<String> data = new ArrayList<String>();


            data.add("暂且无新录音");






        return data;
    }
}
