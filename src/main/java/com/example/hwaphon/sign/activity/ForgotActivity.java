package com.example.hwaphon.sign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hwaphon.sign.R;
import com.example.hwaphon.sign.model.User;
import com.example.hwaphon.sign.tool.GlobeManager;
import com.example.hwaphon.sign.tool.UserDatabaseManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hwaphon on 3/11/2016.
 */
public class ForgotActivity extends AppCompatActivity {


    @Bind(R.id.cancle)
    TextView cancle;
    @Bind(R.id.next)
    TextView next;
    @Bind(R.id.number)
    EditText number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.cancle, R.id.next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancle:
                Intent intent = new Intent(ForgotActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.next:
                String phone = number.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(this, "Please input number", Toast.LENGTH_SHORT).show();
                } else {
                    User user = UserDatabaseManager.QueryByPhone(phone);
                    if (user == null) {
                        Toast.makeText(this, "not exist", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent1 = new Intent(ForgotActivity.this, InfoActivity.class);
                        intent1.putExtra(GlobeManager.PHONEKEY, phone);
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
        Intent intent = new Intent(ForgotActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
