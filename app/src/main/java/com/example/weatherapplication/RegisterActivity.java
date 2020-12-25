package com.example.weatherapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapplication.db.User;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText new_user;
    private EditText new_password;
    private EditText confirm_password;

    private Button save_btn;
    private Button back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        new_user = findViewById(R.id.new_user);
        new_password = findViewById(R.id.new_password);
        confirm_password = findViewById(R.id.confirm_password);

        save_btn = findViewById(R.id.save_btn);
        back_btn = findViewById(R.id.back_btn);

        save_btn.setOnClickListener(this);
        back_btn.setOnClickListener(this);

        /*禁止EditText自动弹出软键盘*/
//        confirm_password.setInputType(InputType.TYPE_NULL);
//        new_user.setInputType(InputType.TYPE_NULL);
//        new_password.setInputType(InputType.TYPE_NULL);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_btn:
                save();
                break;
            case R.id.back_btn:
                Intent intent_back = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent_back);
                break;
        }
    }

    private void save() {

        if ((new_user.getText().toString().equals("")) ||
                (new_password.getText().toString().equals("")) ||
                (confirm_password.getText().toString().equals(""))) {
            Toast.makeText(this, "请设置用户名或密码", Toast.LENGTH_SHORT).show();
        } else if (!(new_password.getText().toString()).equals(confirm_password.getText().toString())) {
            Toast.makeText(this, "设置密码不一致，请重新设置密码", Toast.LENGTH_SHORT).show();
        } else {
            User user = new User();
            user.setUser(new_user.getText().toString());
            user.setPassword(new_password.getText().toString());
            user.save();

            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            Intent intent_save = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent_save);
        }
    }
}
