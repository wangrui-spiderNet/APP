package com.example.hwaphon.sign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hwaphon.sign.R;
import com.example.hwaphon.sign.model.User;
import com.example.hwaphon.sign.tool.UserDatabaseManager;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hwaphon on 3/11/2016.
 */
public class RegisterActivity extends AppCompatActivity {

    @Bind(R.id.activity_register_username)
    EditText activityRegisterUsername;
    @Bind(R.id.activity_register_password)
    EditText activityRegisterPassword;
    @Bind(R.id.activity_register_phone)
    EditText activityRegisterPhone;
    @Bind(R.id.activity_register_back)
    TextView activityRegisterBack;
    @Bind(R.id.activity_register_register)
    Button activityRegisterRegister;

    private int flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.activity_register_back, R.id.activity_register_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_register_back:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.activity_register_register:
                flag = 0;
                String name = activityRegisterUsername.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(this, "ID can not be empty", Toast.LENGTH_SHORT).show();
                } else if (name.length() < 6 || name.length() > 16) {
                    Toast.makeText(this, "ID length should in 6~16", Toast.LENGTH_SHORT).show();
                } else {
                    flag = flag + 1;
                }
                String password = activityRegisterPassword.getText().toString();
                if (TextUtils.isEmpty(password) && flag >= 1) {
                    Toast.makeText(this, "password can not be empty", Toast.LENGTH_SHORT).show();
                } else if (flag >= 1 && password.length() < 6 || password.length() > 16) {
                    Toast.makeText(this, "password length should in 6~16", Toast.LENGTH_SHORT).show();
                } else {
                    flag = flag + 1;
                }
                if (flag >= 2) {
                    String phone = activityRegisterPhone.getText().toString();
                    if (!TextUtils.isEmpty(phone) && phone.length() < 11) {
                        Toast.makeText(this, "Inlegal phone number", Toast.LENGTH_SHORT).show();
                    } else {
                        User user = new User(name, password);
                        user.setPhone(phone);
                        UserDatabaseManager.addUser(user);
                        UserDatabaseManager.Remember(user);
                        Toast.makeText(this, "Welcome to join us", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent1);
                        finish();
                    }
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
