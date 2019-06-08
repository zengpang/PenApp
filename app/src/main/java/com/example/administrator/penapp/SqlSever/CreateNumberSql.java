package com.example.administrator.penapp.SqlSever;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class CreateNumberSql extends SQLiteOpenHelper {
    public CreateNumberSql(@Nullable Context context) {
        super(context, "Sounds_number", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Sounds_number(id INTEGER PRIMARY KEY autoincrement,number varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
