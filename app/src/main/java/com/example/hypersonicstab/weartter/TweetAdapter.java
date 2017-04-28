package com.example.hypersonicstab.weartter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private ArrayList<String> mDataList;

    public TweetAdapter(Context context, ArrayList<String> dataList) {
        super();
        mLayoutInflater = LayoutInflater.from(context);
        mDataList = dataList;
    }

    @Override
    public TweetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 1
        View v = mLayoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    // 4
    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 3
        String data = (String) mDataList.get(position);

        holder.text.setText(data);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public ViewHolder(View v) {
            super(v);
            // 2
            text = (TextView) v.findViewById(android.R.id.text1);
        }
    }
}