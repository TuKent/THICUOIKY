package com.example.spendingmanagementapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText RgtUserName;
    EditText RgtPassWord;
    EditText RgtRePassWord;
    EditText RgtSoDu;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final SQLiteChiTieu dbManager = new SQLiteChiTieu(this);
        OnInIt();
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OnCheck())
                {
                    if (RgtPassWord.getText().toString().equals(RgtRePassWord.getText().toString()))
                    {
                        dbManager.InsertUser(RgtUserName.getText().toString(),RgtPassWord.getText().toString(), Integer.parseInt(RgtSoDu.getText().toString()));
                        Toast.makeText(Register.this,"Đăng ký thành công",Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Register.this,"Đăng ký thất bại",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    private void OnInIt()
    {
        RgtUserName = findViewById(R.id.edt_rgt_username);
        RgtPassWord = findViewById(R.id.edt_rgt_password);
        RgtRePassWord = findViewById(R.id.edt_rgt_repassword);
        RgtSoDu = findViewById(R.id.edt_rgt_sodu);
        Register = findViewById(R.id.btn_add_register);
    }
    private boolean OnCheck()
    {
        if(RgtUserName.getText().toString().length() < 1 )
        {
            RgtUserName.setError("Can be null");
            return  false;
        }
        if(RgtPassWord.getText().toString().length() < 1)
        {
            RgtPassWord.setError("Can be null");
            return  false;
        }
        if(RgtRePassWord.getText().toString().length() < 1)
        {
            RgtRePassWord.setError("Can be null");
            return  false;
        }
        if(RgtSoDu.getText().toString().length() < 1)
        {
            RgtSoDu.setError("Can be null");
            return false;
        }
        return true;
    }
}
