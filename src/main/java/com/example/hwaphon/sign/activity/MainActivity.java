package com.example.hwaphon.sign.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hwaphon.sign.R;
import com.example.hwaphon.sign.inter.GetDataListener;
import com.example.hwaphon.sign.model.Content;
import com.example.hwaphon.sign.tool.HttpData;
import com.example.hwaphon.sign.tool.MyAdapter;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hwaphon on 3/12/2016.
 */
public class MainActivity extends AppCompatActivity{

    private TextView mTextView;
    private EditText Input_et;
    private Button Send_bt;
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private List<Content> mContents;
    private HttpData mHttpData;
    private GetDataListener mListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContents = new ArrayList<>();
        Input_et = (EditText) findViewById(R.id.activity_main_input);
        Send_bt = (Button) findViewById(R.id.activity_main_send);

        Send_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = Input_et.getText().toString();
                if (TextUtils.isEmpty(text)){
                    Toast.makeText(MainActivity.this, "Nothing to send", Toast.LENGTH_SHORT).show();
                }else {
                    Input_et.setText("");
                    Content content = new Content(text,Content.USER);
                    mContents.add(content);
                    mAdapter.notifyDataSetChanged();
                    mRecyclerView.scrollToPosition(mContents.size()-1);
                    mListener = new GetDataListener() {
                        @Override
                        public void getText(String data) {
                            parseData(data);
                        }
                    };
                    String url = "http://www.tuling123.com/openapi/api?key=93bb00ab7c80a0ed328440d348253b6a&info="+text;
                    mHttpData = (HttpData) new HttpData(url,mListener).execute();
                }
            }
        });
        mTextView = (TextView) findViewById(R.id.activity_main_logout);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter = new MyAdapter(mContents));
        Content content = new Content("你好啊，今天天气真不错~",Content.ROBOT);
        mContents.add(content);
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this,R.style.MyAlertDialog);
        dialog.setTitle("Exit now?");
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

    private void hideKeyboard(){
        ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(mRecyclerView.getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


    private void parseData(String data) {
        try {
            JSONObject object = new JSONObject(data);

            String text = object.getString("text");

            Content content = new Content(text,Content.ROBOT);
            mContents.add(content);
            mAdapter.notifyDataSetChanged();
            mRecyclerView.scrollToPosition(mContents.size()-1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
