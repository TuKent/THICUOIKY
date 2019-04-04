package com.example.spendingmanagementapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class ThemChiTieuActivity extends AppCompatActivity {
    EditText AddSotien;
    EditText AddLoaichitieu;
    EditText AddDiadiem;
    EditText AddThoigian;
    EditText AddGhichu;
    Button SaveChitieu;
    Button Thoigian;
    DatePickerDialog datePickerDialog;
    String mSotien;
    String mLoaichitieu;
    String mDiadiem;
    String mThoigian;
    String mGhichu;
    SQLiteChiTieu mySQLiteChiTieu;
    int ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_chi_tieu);
        OnInIt();
        OnCheck();
        mySQLiteChiTieu = new SQLiteChiTieu(ThemChiTieuActivity.this);
        SaveChitieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OnCheck())
                {
                    if(PhieuChiTieuFragment.ModeList.size() > 0)
                    {
                       ID = PhieuChiTieuFragment.ModeList.get(PhieuChiTieuFragment.ModeList.size()- 1).getID() + 1;
                       mySQLiteChiTieu.Insert(ID, Integer.parseInt(mSotien), mLoaichitieu, mDiadiem, mThoigian, mGhichu);
                        Toast.makeText(ThemChiTieuActivity.this,"Thêm thành công !!",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ThemChiTieuActivity.this,MainActivity.class));
                        finish();
                    }
                    if(PhieuChiTieuFragment.ModeList.size() == 0)
                    {
                        ID = 0;
                        mySQLiteChiTieu.Insert(ID, Integer.parseInt(mSotien), mLoaichitieu, mDiadiem, mThoigian, mGhichu);
                        Toast.makeText(ThemChiTieuActivity.this,"Thêm thành công !!",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(ThemChiTieuActivity.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
        });
        Thoigian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog  = new DatePickerDialog(ThemChiTieuActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        AddThoigian .setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }
    private void OnInIt()
    {
        AddSotien = findViewById(R.id.add_Sotien);
        AddLoaichitieu = findViewById(R.id.add_Loaichitieu);
        AddDiadiem = findViewById(R.id.add_Diadiem);
        AddThoigian = findViewById(R.id.add_Thoigian);
        AddGhichu = findViewById(R.id.add_Ghichu);
        SaveChitieu = findViewById(R.id.btn_savechitieu);
        Thoigian = findViewById(R.id.btn_thoigian);

    }
    private boolean OnCheck()
    {
        mSotien = AddSotien.getText().toString();
        mLoaichitieu = AddLoaichitieu.getText().toString();
        mDiadiem = AddDiadiem.getText().toString();
        mThoigian = AddThoigian.getText().toString();
        mGhichu = AddGhichu.getText().toString();
        if (mSotien.trim().equals(""))
        {
            AddSotien.setError("Can be null");
        }
        if (mLoaichitieu.trim().equals(""))
        {
            AddLoaichitieu.setError("Can be null");
            return false;
        }
        if (mDiadiem.trim().equals(""))
        {
            AddDiadiem.setError("Can be null");
            return false;
        }
        if (mThoigian.trim().equals(""))
        {
            AddThoigian.setError("Can be null");
            return false;
        }
        if (mThoigian.trim().equals(""))
        {
            AddThoigian.setError("Can be null");
            return false;
        }
        if (mGhichu.trim().equals(""))
        {
            AddGhichu.setError("");
            return  false;
        }
        return true;
    }

}
