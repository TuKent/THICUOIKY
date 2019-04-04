package com.example.spendingmanagementapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewpagerAdapter adapter;
    TextView SoDu;
    TextView TongSoDu;
    SQLiteChiTieu sqLiteChiTieu;
    SharedPreferences sharedPreferences;
    int ID;


    @Override
    public boolean navigateUpTo(Intent upIntent) {
        return super.navigateUpTo(upIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnInIt();
        tabLayout.setupWithViewPager(viewPager);
        adapter = new ViewpagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new PhieuChiTieuFragment(),"Phiếu Chi Tiêu");
        adapter.AddFragment(new LichSuFragment(),"Lịch Sử Chi Tiêu");
        viewPager.setAdapter(adapter);
        sqLiteChiTieu = new SQLiteChiTieu(this);
        sharedPreferences = getSharedPreferences("Tu", Context.MODE_PRIVATE);
        ID = sharedPreferences.getInt("ID",0);
        Date c = Calendar.getInstance().getTime();
        TongSoDu.setText(String.valueOf(sqLiteChiTieu.SumAll(c.getMonth(),c.getYear())));
    }
    private void OnInIt()
    {
        tabLayout = findViewById(R.id.Tablayout);
        viewPager = findViewById(R.id.Viewpager);
        SoDu = findViewById(R.id.tv_sodu);
        TongSoDu = findViewById(R.id.tv_tongsodu);
    }
    @Override
    protected void onResume() {
        super.onResume();
        SoDu.setText(String.valueOf(sqLiteChiTieu.GetSoDu(ID)));
    }
}
