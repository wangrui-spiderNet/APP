package com.example.hwaphon.sign.tool;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hwaphon.sign.R;
import com.example.hwaphon.sign.model.Content;

import java.util.List;

/**
 * Created by hwaphon on 3/18/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

    private List<Content> mContents;
    public MyAdapter(List<Content> contents){
        mContents = contents;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chat,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (mContents.get(position).getFlag() == Content.ROBOT){
            holder.left.setVisibility(View.VISIBLE);
            holder.right.setVisibility(View.INVISIBLE);
            holder.robot.setText(mContents.get(position).getContent());
        } else{
            holder.left.setVisibility(View.INVISIBLE);
            holder.right.setVisibility(View.VISIBLE);
            holder.user.setText(mContents.get(position).getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mContents.size();
    }
}
