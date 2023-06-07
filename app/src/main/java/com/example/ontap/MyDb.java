package com.example.ontap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDb extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "taxi.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TAXI = "Taxi";
    private static final String COLUMN_ID = "Id";
    private static final String COLUMN_SO_XE = "SoXe";
    private static final String COLUMN_QUANG_DUONG = "QuangDuong";
    private static final String COLUMN_DON_GIA = "DonGia";
    private static final String COLUMN_PHAN_TRAM_KM = "PhanTramKhuyenMai";

    public MyDb(@Nullable Context context,@Nullable String DATABASE_NAME, @Nullable SQLiteDatabase.CursorFactory factory, int DATABASE_VERSION)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TAXI_TABLE = "CREATE TABLE " + TABLE_TAXI + "("
                // AUTOINCREMENT để tăng tự động
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_SO_XE + " TEXT,"
                + COLUMN_QUANG_DUONG + " REAL,"
                + COLUMN_DON_GIA + " REAL,"
                + COLUMN_PHAN_TRAM_KM + " INTEGER"
                + ")";
        db.execSQL(CREATE_TAXI_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xử lý nâng cấp cơ sở dữ liệu nếu có phiên bản mới
        db.execSQL("drop table if exists "+ TABLE_TAXI);
        onCreate(db);
    }

    public ArrayList<Taxi> getAllTaxi()
    {
        ArrayList<Taxi> list = new ArrayList<>();
        // Sql lấy tất cả Taxi sắp xếp theo SoXe với thứ tự bảng chữ cái bằng order by
        // COLLATE NOCASE được sử dụng để sắp xếp dữ liệu theo thứ tự không phân biệt chữ hoa, chữ thường.
        String sql = "Select * from " + TABLE_TAXI + " ORDER BY " + COLUMN_SO_XE + " COLLATE NOCASE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor!=null)
        {
            while (cursor.moveToNext())
            {
                Taxi taxi = new Taxi(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getFloat(2),
                        cursor.getInt(3),
                        cursor.getInt(4));

                list.add(taxi);
            }
        }
        return list;
    }

    // Thêm một bản ghi mới
    public void addTaxi(Taxi taxi)
    {
        // Mở db để ghi
        SQLiteDatabase db = this.getWritableDatabase();
        // Đối tượng đóng gói dữ liệu để insert
        ContentValues values = new ContentValues();
        // Đặt các giá trị vào các cột
        values.put(COLUMN_ID, taxi.getCOLUMN_ID());
        values.put(COLUMN_SO_XE, taxi.getCOLUMN_SO_XE());
        values.put(COLUMN_QUANG_DUONG, taxi.getCOLUMN_QUANG_DUONG());
        values.put(COLUMN_DON_GIA, taxi.getCOLUMN_DON_GIA());
        values.put(COLUMN_PHAN_TRAM_KM, taxi.getCOLUMN_PHAN_TRAM_KM());
        // Insert
        db.insert(TABLE_TAXI, null, values);
        // Xóa dữ liệu trong values khi thực hiện nhiều insert mà dùng chung values để đóng gói, tránh trùng lặp dữ liệu
        values.clear();
        // Đóng kết nối db
        db.close();
    }

    public void updateTaxi(int id, Taxi taxi)
    {
        // Mở CSDL
        SQLiteDatabase db = this.getWritableDatabase();
        // Đóng gói các giá trị mới
        ContentValues values = new ContentValues();
        values.put(COLUMN_SO_XE, taxi.getCOLUMN_SO_XE());
        values.put(COLUMN_QUANG_DUONG, taxi.getCOLUMN_QUANG_DUONG());
        values.put(COLUMN_DON_GIA, taxi.getCOLUMN_DON_GIA());
        values.put(COLUMN_PHAN_TRAM_KM, taxi.getCOLUMN_PHAN_TRAM_KM());
        // Số lượng hàng đã thay đổi
        int rowsAffected = db.update(TABLE_TAXI, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
        if (rowsAffected == 0) {
            System.out.println("Không có hàng nào được cập nhật");
        } else {
            System.out.println("Cập nhật thành công hàng có ID là:" + id);
        }
    }

    public void DeleteTaxi(int id)
    {
        // Mở CSDL
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TAXI, COLUMN_ID + " = ?", new String[] {String.valueOf(id)});
        db.close();
    }

}
