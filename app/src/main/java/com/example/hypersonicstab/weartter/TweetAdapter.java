package com.example.hypersonicstab.weartter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private ArrayList<String> mDataList;
    public List<twitter4j.Status> statuses = null;

    public TweetAdapter(Context context, ArrayList<String> dataList) {
        super();
        mLayoutInflater = LayoutInflater.from(context);
        mDataList = dataList;
    }
    public TweetAdapter(Context context, List<twitter4j.Status> statuses) {
        super();
        mLayoutInflater = LayoutInflater.from(context);
        this.statuses = statuses;
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
        if (statuses == null) {
            return 5;
        } else {
            return statuses.size();
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 3
        if (statuses == null) {
            //String data = (String) mDataList.get(position);
            //holder.text.setText(data);
            Log.d("statu is null", "null");
        } else {
            Log.d("onBind not null", "onbind");
            String text = (String) statuses.get(position).getText();
            holder.text.setText(text);
        }

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