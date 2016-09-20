package com.example.hwaphon.sign.tool;

/**
 * Created by hwaphon on 3/10/2016.
 */
public final class GlobeManager {

    public GlobeManager() {
    }

    public static abstract class UserInfo {
        public static final String ID = "id";
        public static final String USERNAME = "username";
        public static final String USERPASSWORD = "password";
        public static final String TABLENAME = "usertable";
        public static final String DATABASENAME = "userdatabse";
        public static final String PHONENUMBER = "phone";

        public static final String CREATE_TABLE = "create table " + TABLENAME + "("
                + ID + " integer primary key autoincrement,"
                + USERNAME + " text,"
                + PHONENUMBER + " text,"
                + USERPASSWORD + " text)";
    }

    public static String PHONEKEY = "phonekey";

    public static abstract class RecentUsers {

        public static final String ID = "id";
        public static final String RECENTNAME = "recentname";
        public static final String RECENTPASSWORD = "recentpassword";
        public static final String ONLYKEY = "key";
        public static final String TABLENAME = "recenttable";

        public static final String CREATE_TABLE= "create table "+ TABLENAME +"("
                +ID + " integer primary key autoincrement,"
                +RECENTNAME + " text,"
                +RECENTPASSWORD +" text,"
                +ONLYKEY + " text)";
    }
}
