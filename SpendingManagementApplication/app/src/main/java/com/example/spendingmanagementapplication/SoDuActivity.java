package com.example.spendingmanagementapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SoDuActivity extends AppCompatActivity {
    EditText NhapSoDu;
    Button ThemSoDu;
    SQLiteChiTieu sqLiteChiTieu;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_du);
        OnInIt();
        sqLiteChiTieu = new SQLiteChiTieu(this);
        sharedPreferences = getSharedPreferences("Tu", Context.MODE_PRIVATE);
        final int ID = sharedPreferences.getInt("ID",0);
        ThemSoDu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int SoDu = sqLiteChiTieu.GetSoDu(ID);
                int ThemSoDu = Integer.parseInt(NhapSoDu.getText().toString());
                SoDu += ThemSoDu;
                sqLiteChiTieu.Update(ID,SoDu);
                finish();
            }
        });
    }

    private void OnInIt()
    {
        NhapSoDu = findViewById(R.id.edt_sodu_nhapsodu);
        ThemSoDu = findViewById(R.id.btn_sodu_themtien);
    }
}
