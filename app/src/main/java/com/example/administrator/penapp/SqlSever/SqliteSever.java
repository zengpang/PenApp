package com.example.administrator.penapp.SqlSever;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SqliteSever  {
      String name;
    String password;
    String Email;
    String phone_number;
      CreateUserSql mysql;
    public  SqliteSever(String username,String userpassword,String userEmail,String usernumber,CreateUserSql sql)
    {
        name=username;
        password=userpassword;
        Email=userEmail;
        phone_number=usernumber;
        mysql=sql;

    }


    public  long sql_zc()
    {
        SQLiteDatabase sqLiteDatabase=mysql.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("phone",phone_number);
        values.put("Email",Email);
        values.put("password",password);

        long insert=sqLiteDatabase.insert("User",null,values);
        sqLiteDatabase.close();
        return  insert;
    }
    public String clic_select() {
        SQLiteDatabase db = mysql.getReadableDatabase();
        String select_password="无";
        Cursor cursor = db.query("User", new String[]{"password"}, "name=?", new String[]{name}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                 select_password = cursor.getString(cursor.getColumnIndex("password"));

            }
        }

        return  select_password;

    }

}
