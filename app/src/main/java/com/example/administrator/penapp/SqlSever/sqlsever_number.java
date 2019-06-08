package com.example.administrator.penapp.SqlSever;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class sqlsever_number {
    CreateNumberSql mysql;

    public sqlsever_number(CreateNumberSql sql)
    {
        mysql=sql;
    }
    public int select_number() {
        SQLiteDatabase db = mysql.getReadableDatabase();
        int number_sounds = 0;
        Cursor cursor = db.query("Sounds_number", null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                number_sounds++;

            }
        }
        return  number_sounds;
    }
}
