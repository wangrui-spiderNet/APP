package com.example.hwaphon.sign.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hwaphon.sign.R;
import com.example.hwaphon.sign.model.User;
import com.example.hwaphon.sign.tool.UserDatabaseManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.username_et)
    EditText usernameEt;
    @Bind(R.id.password_et)
    EditText passwordEt;
    @Bind(R.id.login_bt)
    Button loginBt;
    @Bind(R.id.forgot_tv)
    TextView forgotTv;
    @Bind(R.id.register_tv)
    TextView registerTv;

    private UserDatabaseManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mManager = UserDatabaseManager.newInstance(this);
        User user = UserDatabaseManager.getRecent();
        if (null != user){
            usernameEt.setText(user.getName());
            passwordEt.setText(user.getPassword());
        }
    }

    @OnClick({R.id.login_bt, R.id.forgot_tv, R.id.register_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_bt:
                String name = usernameEt.getText().toString();
                String password = passwordEt.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(this, "ID empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "Password empty", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(name, password);
                    if (judge(user)) {
                        UserDatabaseManager.Remember(user);
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                break;
            case R.id.forgot_tv:
                Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.register_tv:
                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }

    private boolean judge(User user) {

        String name = user.getName();
        String password = user.getPassword();

        User temp = mManager.QueryUsername(name);
        if (null == temp) {
            Toast.makeText(this, "ID not exist", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.equals(temp.getPassword())) {
            return true;
        } else {
            Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this,R.style.MyAlertDialog);

        dialog.setMessage("Exit now?");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        dialog.show();
    }

}
