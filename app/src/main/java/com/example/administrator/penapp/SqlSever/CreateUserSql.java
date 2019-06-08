package com.example.administrator.penapp.SqlSever;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class CreateUserSql extends SQLiteOpenHelper {
    public CreateUserSql(@Nullable Context context) {
        super(context, "User", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       sqLiteDatabase.execSQL("create table User(id INTEGER PRIMARY KEY autoincrement,name varchar(20),phone varchar(20),password  varchar(20),Email varcher(40))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
