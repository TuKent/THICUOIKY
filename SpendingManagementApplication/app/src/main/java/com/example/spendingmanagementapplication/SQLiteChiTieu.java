package com.example.spendingmanagementapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SQLiteChiTieu extends SQLiteOpenHelper {
    // Tạo bảng Khoản Chi Tiêu
    private String TABLE_Name = "KhoanChiTieu";
    private String TABLE_a_id = "ID";
    private String TABLE_a_sotien = "Sotien";
    private String TABLE_a_loaichitieu = "Loaichitieu";
    private String TABLE_a_diadiemchitieu = "Diadiemchitieu";
    private String TABLE_a_thoigianchitieu = "Thoigianchitieu";
    private String TABLE_a_ghichuchitieu = "Ghichuchitieu";
    // Tạo bảng Login
    private String TABLE_SignIn = "SignIn";
    private String TABLE_a_iduser = "IDUser";
    private String TABLE_a_username = "UserName";
    private String TABLE_a_password = "Passord";
    private String TABLE_a_sodu = "SoDu";
    private String TABLE_a_soduhientai = "SoDuHienTai";
    private String TABLE_a_tongsodu = "TongSoDu";
    // Tạo write và read database
    private SQLiteDatabase database;
    private SQLiteDatabase readdtb;
    private static final String DATABASE_NAME = "SQL.db";
    //Hàm khởi tạo database
    public SQLiteChiTieu(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
        database = this.getWritableDatabase();
        readdtb = this.getReadableDatabase();
    }

    public boolean CheckLogin(String username, String password)
    {
        String query = String.format("select * from %s where %s = '%s' and %s = '%s'", TABLE_SignIn, TABLE_a_username,username ,  TABLE_a_password, password);
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.getCount() > 0 )
        {
            return true;
        }
        cursor.close();
        return false;

    }
    // Lấy ID để thể hiện Số Dư
    public int GetIDLogin(String username, String password)
    {
        String query = String.format("select * from %s where %s = '%s' and %s = '%s'", TABLE_SignIn, TABLE_a_username, username, TABLE_a_password, password);
        Cursor cursor =  database.rawQuery(query, null);
        cursor.moveToFirst();
        int ID = cursor.getInt(0);
        cursor.close();
        return ID;
    }
    // Lấy số Dư ở bảng SIGN IN
    public int GetSoDu(int ID)
    {
        String query = String.format("select * from %s where %s = '%s' ", TABLE_SignIn, TABLE_a_iduser, ID);
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        int soDu = cursor.getInt(3);
        cursor.close();
        return soDu;
    }

    public void Update(int id, int sodu)
    {
        String query = " update " + TABLE_SignIn + " set " +  TABLE_a_sodu + "= '" + sodu + "' where " + TABLE_a_iduser  + "=" + id;
        database.execSQL(query);
    }
    // Tính tổng số dư
    public int SumAll(int month, int year)
    {
        int Sum = 0;
        String query = String.format("SELECT * FROM %s" , TABLE_Name);
        Cursor cursor = database.rawQuery(query,null);
        for (int i = 0; i < cursor.getCount(); i++)
        {
            cursor.moveToNext();
            Date c = new Date();
            try {
                c = new SimpleDateFormat("dd/MM/yyyy").parse(cursor.getString(4));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (c.getMonth() == month && c.getYear() == year)
            {
                Sum += cursor.getInt(1);
            }
        }
        return Sum;
    }

    //Thêm
    public  void Insert(int ID, int sotien, String loaichitieu, String diadiemchitieu, String thoigianchitieu, String ghichuchitieu)
    {
        String insert = String.format("insert into %s ( %s, %s, %s, %s, %s, %s) values(%s, %s, '%s', '%s', '%s', '%s')",
                TABLE_Name, TABLE_a_id,TABLE_a_sotien, TABLE_a_loaichitieu, TABLE_a_diadiemchitieu, TABLE_a_thoigianchitieu, TABLE_a_ghichuchitieu,
                ID, sotien, loaichitieu, diadiemchitieu,thoigianchitieu,ghichuchitieu);
        database.execSQL(insert);
    }
    // Đăng ký
    public void InsertUser(String UserName, String PassWord, int SoDu)
    {
        String insertuser = String.format("insert into %s ( %s, %s, %s) values ('%s','%s', %s)",
                TABLE_SignIn, TABLE_a_username, TABLE_a_password, TABLE_a_sodu,
                UserName, PassWord, SoDu);
        database.execSQL(insertuser);
    }
    // Lấy toàn bộ dữ liệu của Khoản Chi Tiêu
    public List<ChiTieuObject> getAll()
    {
        List<ChiTieuObject> List = new ArrayList<>();
        String query = "select * from " + TABLE_Name;
        Cursor cursor = readdtb.rawQuery(query,null);
            for (int i = 0; i < cursor.getCount();i++)
            {
                cursor.moveToNext();
                ChiTieuObject item = new ChiTieuObject();
                item.setID(cursor.getInt(0));
                item.setSoTienChiTieu(cursor.getInt(1));
                item.setLoaiChiTieu(cursor.getString(2));
                item.setDiaDiemChiTieu(cursor.getString(3));
                item.setTimeChiTieu(cursor.getString(4));
                item.setGhiChuChiTieu(cursor.getString(5));
                List.add(item);
            }
        cursor.close();
        return List;
    }
    // Lấy dữ liệu của bảng Sign In
    public UserObject getOne()
    {
        UserObject Item = new UserObject();
        String query = "select * from " + TABLE_SignIn;
        Cursor cursor = readdtb.rawQuery(query, null);
        Item.setIDUser(cursor.getInt(0));
        Item.setUserName(cursor.getString(1));
        Item.setPassWord(cursor.getString(2));
        Item.setSoDu(cursor.getInt(3));
        Item.setSoDuHienTai(cursor.getInt(4));
        Item.setTongSoDu(cursor.getInt(5));
        return Item;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String mquery = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY NOT NULL, %s INT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)", TABLE_Name, TABLE_a_id, TABLE_a_sotien, TABLE_a_loaichitieu, TABLE_a_diadiemchitieu, TABLE_a_thoigianchitieu,TABLE_a_ghichuchitieu);
        String signinquery = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY NOT NULL, %s TEXT, %s TEXT, %s INT, %s INT, %s INT)", TABLE_SignIn, TABLE_a_iduser, TABLE_a_username, TABLE_a_password, TABLE_a_sodu, TABLE_a_soduhientai, TABLE_a_tongsodu);
        db.execSQL(mquery);
        db.execSQL(signinquery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}