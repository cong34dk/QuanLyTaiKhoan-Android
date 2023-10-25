package com.example.demosqlitequery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "utehy.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng trong cơ sở dữ liệu
        String createTableQuery = "CREATE TABLE IF NOT EXISTS tbTaikhoan (id INTEGER PRIMARY KEY AUTOINCREMENT, TenDangNhap TEXT, MatKhau TEXT);";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(String TenDangNhap, String MatKhau) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insertQuery = "INSERT INTO tbTaikhoan (TenDangNhap, MatKhau) VALUES ('" + TenDangNhap + "', '" + MatKhau + "');";
        db.execSQL(insertQuery);
        db.close();
    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM tbTaikhoan";
        return db.rawQuery(selectQuery, null);
    }
}
