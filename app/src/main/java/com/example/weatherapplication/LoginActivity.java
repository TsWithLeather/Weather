package com.example.weatherapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.litepal.crud.DataSupport;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText user;
    private EditText password;

    private Button Login_btn;
    private Button Register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.user);
        password = findViewById(R.id.password);

        Login_btn = findViewById(R.id.login_btn);
        Register_btn = findViewById(R.id.register_btn);

        Login_btn.setOnClickListener(this);
        Register_btn.setOnClickListener(this);

        /*禁止EditText自动弹出软键盘*/
//        user.setInputType(InputType.TYPE_NULL);
//        password.setInputType(InputType.TYPE_NULL);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                login();
                break;
            case R.id.register_btn:
                Intent intent_register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent_register);
                break;
            default:
                break;
        }
    }

    private void login() {
        String username = user.getText().toString();
        String pwd = password.getText().toString();
        if ((username.equals("")) || (pwd.equals(""))) {
            Toast.makeText(this, "请输入账号或密码", Toast.LENGTH_SHORT).show();
        } else {
            Cursor cursor = DataSupport.findBySQL("select * from User where user = " + '"' + username + '"' + "and password = " + '"' + pwd + '"');
            Log.d("LoginActivity", username + pwd);
            if (cursor.getCount() == 0) {
                Toast.makeText(LoginActivity.this, "很遗憾，账号或密码错误", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    Log.i("cccccccccc", cursor.getString(1) + " " + cursor.getString(2));
                    Intent intent_login = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent_login);
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
