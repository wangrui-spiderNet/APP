package com.example.hwaphon.sign.tool;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.example.hwaphon.sign.database.UserDatabase;
import com.example.hwaphon.sign.model.User;

import java.security.PublicKey;

/**
 * Created by hwaphon on 3/10/2016.
 */
public class UserDatabaseManager {

    private Context mContext;
    private static UserDatabaseManager mManager;
    private UserDatabase mDatabase;
    private static SQLiteDatabase mSQLiteDatabase;

    private UserDatabaseManager(Context context) {
        mContext = context;
        mDatabase = new UserDatabase(mContext, GlobeManager.UserInfo.DATABASENAME, null, 1);
        mSQLiteDatabase = mDatabase.getWritableDatabase();
    }

    public static UserDatabaseManager newInstance(Context context) {
        if (mManager == null) {
            mManager = new UserDatabaseManager(context);
        }
        InitRecent();
        return mManager;
    }

    public static void addUser(User user) {

        ContentValues values = new ContentValues();

        values.put(GlobeManager.UserInfo.USERNAME, user.getName());
        values.put(GlobeManager.UserInfo.USERPASSWORD, user.getPassword());
        values.put(GlobeManager.UserInfo.PHONENUMBER, user.getPhone());
        mSQLiteDatabase.insert(GlobeManager.UserInfo.TABLENAME, null, values);
    }

    public static void updateUserPassword(User user) {

        ContentValues values = new ContentValues();
        values.put(GlobeManager.UserInfo.USERPASSWORD, user.getPassword());
        mSQLiteDatabase.update(GlobeManager.UserInfo.TABLENAME, values, GlobeManager.UserInfo.USERNAME + " = ?", new String[]{user.getName()});
    }

    public static void updateUserPhone(User user) {
        ContentValues values = new ContentValues();
        values.put(GlobeManager.UserInfo.PHONENUMBER, user.getPhone());
        mSQLiteDatabase.update(GlobeManager.UserInfo.TABLENAME, values, GlobeManager.UserInfo.USERNAME + " = ?", new String[]{user.getName()});
    }

    public static void deleteUser(User user) {

        mSQLiteDatabase.delete(GlobeManager.UserInfo.TABLENAME, GlobeManager.UserInfo.USERNAME + " = ?", new String[]{user.getName()});

    }

    public static User QueryUsername(String username) {

        Cursor cursor = mSQLiteDatabase.query(GlobeManager.UserInfo.TABLENAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(GlobeManager.UserInfo.USERNAME));
                if (name.equals(username)) {
                    String password = cursor.getString(cursor.getColumnIndex(GlobeManager.UserInfo.USERPASSWORD));
                    String phone = cursor.getString(cursor.getColumnIndex(GlobeManager.UserInfo.PHONENUMBER));
                    User user = new User(name, password);
                    user.setPhone(phone);
                    return user;
                }
            } while (cursor.moveToNext());
        }
        return null;
    }

    public static User QueryByPhone(String phone) {

        Cursor cursor = mSQLiteDatabase.query(GlobeManager.UserInfo.TABLENAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String temp = cursor.getString(cursor.getColumnIndex(GlobeManager.UserInfo.PHONENUMBER));

                if (temp.equals(phone)) {
                    String name = cursor.getString(cursor.getColumnIndex(GlobeManager.UserInfo.USERNAME));
                    String password = cursor.getString(cursor.getColumnIndex(GlobeManager.UserInfo.USERPASSWORD));

                    User user = new User(name, password);
                    return user;
                }
            } while (cursor.moveToNext());


        }
        return null;
    }

    public static void InitRecent() {

        ContentValues values = new ContentValues();

        values.put(GlobeManager.RecentUsers.ONLYKEY, GlobeManager.RecentUsers.ONLYKEY);
        values.put(GlobeManager.RecentUsers.RECENTNAME, "");
        values.put(GlobeManager.RecentUsers.RECENTPASSWORD, "");
        mSQLiteDatabase.insert(GlobeManager.RecentUsers.TABLENAME, null, values);
    }

    public static void Remember(User user) {

        ContentValues values = new ContentValues();
        values.put(GlobeManager.RecentUsers.RECENTNAME, user.getName());
        values.put(GlobeManager.RecentUsers.RECENTPASSWORD, user.getPassword());

        mSQLiteDatabase.update(GlobeManager.RecentUsers.TABLENAME, values, GlobeManager.RecentUsers.ONLYKEY + " = ?", new String[]{GlobeManager.RecentUsers.ONLYKEY});
    }

    public static User getRecent(){

        Cursor cursor = mSQLiteDatabase.query(GlobeManager.RecentUsers.TABLENAME,null,null,null,null,null,null);
        cursor.moveToFirst();

        String name = cursor.getString(cursor.getColumnIndex(GlobeManager.RecentUsers.RECENTNAME));
        String password = cursor.getString(cursor.getColumnIndex(GlobeManager.RecentUsers.RECENTPASSWORD));

        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(password)){
            return null;
        } else {
            User user = new User(name,password);
            return user;
        }
    }
}
