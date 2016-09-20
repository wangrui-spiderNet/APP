package com.example.hwaphon.sign.tool;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hwaphon.sign.R;
import com.example.hwaphon.sign.model.Content;

/**
 * Created by hwaphon on 3/18/2016.
 */
public class MyViewHolder extends RecyclerView.ViewHolder{

    public LinearLayout left,right;
    public TextView robot,user;
    public MyViewHolder(View itemView) {
        super(itemView);
        left = (LinearLayout) itemView.findViewById(R.id.activity_chat_leftlayout);
        right = (LinearLayout) itemView.findViewById(R.id.activity_chat_rightlayout);
        robot = (TextView) itemView.findViewById(R.id.activity_chat_lefttext);
        user = (TextView) itemView.findViewById(R.id.activity_chat_righttext);
    }
}
