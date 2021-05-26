package org.techtown.mywordapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String NAME = "WORDAPP.db";
    public static int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists VOCA("
                + " _id integer PRIMARY KEY autoincrement, "
                + " english text, "
                + " korean text, "
                + " memo text)";
        db.execSQL(sql);
    }

    public void onOpen(SQLiteDatabase db){
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > 1) {
            db.execSQL("DROP TABLE IF EXISTS VOCA");
        }
    }
}
