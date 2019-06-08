package com.example.administrator.penapp.SqlSever;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class CreateSoundsSql extends SQLiteOpenHelper {
    public CreateSoundsSql(@Nullable Context context) {
        super(context, "SoundRecorder", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table SoundRecorder(id INTEGER PRIMARY KEY autoincrement,sounds_name varchar(20),sounds_paths  varchar(80),time varchar(80),sounds_title varchar(80),sounds_text varchar(300) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
