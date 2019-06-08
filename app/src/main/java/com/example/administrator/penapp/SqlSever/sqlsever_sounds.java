package com.example.administrator.penapp.SqlSever;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class sqlsever_sounds {

    CreateSoundsSql mysql;

    public sqlsever_sounds(CreateSoundsSql sql)
    {

        mysql=sql;
    }
    public long sql_ly(String sounds_name,String sounds_path,String time,String text,String title)
    {
        SQLiteDatabase sqLiteDatabase=mysql.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("sounds_name",sounds_name);
        values.put("sounds_paths",sounds_path);
        values.put("time",time);
        values.put("sounds_title",title);
        values.put("sounds_text",text);


        long insert=sqLiteDatabase.insert("SoundRecorder",null,values);
        sqLiteDatabase.close();
        return  insert;
    }
    public long select_sounds()
    {
        SQLiteDatabase db = mysql.getReadableDatabase();
        int number_sounds=0;
        Cursor cursor = db.query("SoundRecorder",null,null,null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                number_sounds++;

            }
        }

        return  number_sounds++;

    }
    public List<String> time_select() {
        SQLiteDatabase db = mysql.getReadableDatabase();
        List<String> time_list=new ArrayList<>();
        Cursor cursor = db.query("SoundRecorder",null,null,null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                time_list.add(cursor.getString(cursor.getColumnIndex("time")));

            }
        }

        return  time_list;

    }
    public String sounds_name_select(String id) {
        SQLiteDatabase db = mysql.getReadableDatabase();
        String sounds_name="无";
        Cursor cursor = db.query("SoundRecorder", new String[]{"sounds_name"}, "id=?", new String[]{id}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                sounds_name = cursor.getString(cursor.getColumnIndex("sounds_name"));

            }
        }

        return  sounds_name;

    }
    public String sounds_time_select(String id) {
        SQLiteDatabase db = mysql.getReadableDatabase();
        String sounds_time="无";
        Cursor cursor = db.query("SoundRecorder", new String[]{"sounds_time"}, "id=?", new String[]{id}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                sounds_time = cursor.getString(cursor.getColumnIndex("sounds_time"));

            }
        }

        return  sounds_time;

    }
    public String sounds_path_select(String id) {
        SQLiteDatabase db = mysql.getReadableDatabase();
        String sounds_paths="无";
        Cursor cursor = db.query("SoundRecorder", new String[]{"sounds_paths"}, "id=?", new String[]{id}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                sounds_paths = cursor.getString(cursor.getColumnIndex("sounds_paths"));

            }
        }

        return  sounds_paths;

    }
    public String time_select(String id) {
        SQLiteDatabase db = mysql.getReadableDatabase();
        String time="无";
        Cursor cursor = db.query("SoundRecorder", new String[]{"time"}, "id=?", new String[]{id}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                time = cursor.getString(cursor.getColumnIndex("time"));

            }
        }

        return  time;

    }
    public String title_select(String id) {
        SQLiteDatabase db = mysql.getReadableDatabase();
        String title="无";
        Cursor cursor = db.query("SoundRecorder", new String[]{"sounds_title"}, "id=?", new String[]{id}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                title = cursor.getString(cursor.getColumnIndex("sounds_title"));

            }
        }

        return  title;

    }



}
