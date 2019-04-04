package com.example.spendingmanagementapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText UserName;
    EditText Password;
    Button Login;
    Button Register;
    SharedPreferences.Editor Editor;
    SharedPreferences sharedPreferences;
    SQLiteChiTieu sqLiteChiTieu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        OnInIt();
        sharedPreferences = getSharedPreferences("Tu",Context.MODE_PRIVATE);
        CheckLogin();
        final SQLiteChiTieu dbManager = new SQLiteChiTieu(this);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OnCheck())
                {
                    boolean check = dbManager.CheckLogin(UserName.getText().toString(), Password.getText().toString());
                    if(check)
                    {

                        Editor = getSharedPreferences("Tu", Context.MODE_PRIVATE).edit();
                        Editor.putInt("ID",dbManager.GetIDLogin(UserName.getText().toString(), Password.getText().toString()));
                        Editor.putBoolean("login",true);
                        Editor.apply();
                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Login.this, MainActivity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(Login.this,"Đăng nhập thất bại", Toast.LENGTH_LONG).show();;
                    }
                }
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
    }

    private void OnInIt() {
        UserName = findViewById(R.id.edt_username);
        Password = findViewById(R.id.edt_password);
        Login = findViewById(R.id.btn_login);
        Register = findViewById(R.id.btn_register);

    }
    private boolean OnCheck()
    {
        if ( UserName.getText().toString().length() < 1)
        {
            UserName.setError("Fill UserName, Please !! ");
            return  false;
        }
        if (Password.getText().toString().length() < 1)
        {
            UserName.setError("Fill Password, Please !! ");
            return  false;
        }
        return true;
    }
    void CheckLogin()
    {
        if (sharedPreferences.getBoolean("login",false))
        {
            startActivity(new Intent(Login.this,MainActivity.class));
        }
    }
}
