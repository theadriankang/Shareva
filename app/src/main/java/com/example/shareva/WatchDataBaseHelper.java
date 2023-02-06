package com.example.shareva;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class WatchDataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "WatchListing.db";
    private static final String TABLE_NAME = "watch_data";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "WATCH";
    private static final String COL_3 = "WATCH_SIZE";
    private static final String COL_4 = "WATCH_TYPE";
    private static final String COL_5 = "WATCH_DESC";
    private static final String COL_6 = "WATCH_VERIFICATION";



    public WatchDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, WATCH TEXT, WATCH_SIZE TEXT, WATCH_TYPE TEXT, WATCH_DESC TEXT, WATCH_VERIFICATION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String watch, String size, String type, String desc, String verification) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, watch);
        contentValues.put(COL_3, size);
        contentValues.put(COL_4, type);
        contentValues.put(COL_5, desc);
        contentValues.put(COL_6, verification);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
