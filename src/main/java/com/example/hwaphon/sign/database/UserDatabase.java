package com.example.hwaphon.sign.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hwaphon.sign.tool.GlobeManager;

/**
 * Created by hwaphon on 3/10/2016.
 */
public class UserDatabase extends SQLiteOpenHelper{

    public UserDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(GlobeManager.UserInfo.CREATE_TABLE);
        sqLiteDatabase.execSQL(GlobeManager.RecentUsers.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
