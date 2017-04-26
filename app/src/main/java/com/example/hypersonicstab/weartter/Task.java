package com.example.hypersonicstab.weartter;

import android.os.AsyncTask;
import android.util.Log;

import com.example.hypersonicstab.weartter.MainActivity;

import java.util.List;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class Task extends AsyncTask<Integer, Integer, Integer> {
    private MainActivity mainActivity;
    private Twitter twitter;

    // コンストラクター
    public Task(MainActivity activity, Twitter twitter){
        mainActivity = activity;
        this.twitter = twitter;
    }


    // 非同期処理
    @Override
    protected Integer doInBackground(Integer... params) {

        List<twitter4j.Status> statuses = null;
        try {
            statuses = twitter.getHomeTimeline();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        for (twitter4j.Status status : statuses) {
            Log.d("status", status.getUser().getName() + ":" +
                    status.getText());
        }
        /*
        // 10秒数える処理
        do{
            try {
                //　1sec sleep
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            Log.d("debug",""+params[0]);
            params[0]++;
            // 途中経過を返す
            publishProgress(params[0]);

        }while(params[0]<10);

        return params[0] ;
        */
        return 0;
    }

    // 途中経過をメインスレッドに返す
    @Override
    protected void onProgressUpdate(Integer... progress) {
        //progressDialog_.incrementProgressBy(progress[0]);

    }

    // 非同期処理が終了後、結果をメインスレッドに返す
    @Override
    protected void onPostExecute(Integer result) {

    }
}