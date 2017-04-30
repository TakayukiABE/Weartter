package com.example.hypersonicstab.weartter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;


public class PinActivity extends WearableActivity {

    AsyncTwitter twitter = AsyncTwitterFactory.getSingleton();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        setAmbientEnabled();

        EditText editText = (EditText) (findViewById(R.id.editText));
        Button button = (Button) findViewById(R.id.button);

        twitter.setOAuthConsumer("eODFykb6OPzkzZAhJ2rNRdt0t", "KbXC7YQQWm3JSj20ufN57aqTvsHhx1YDeb4uiByJuxEZy6mPNp");
        final PinTask pinTask = new PinTask();
        pinTask.execute();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask task = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] params) {

                        try {
                            EditText editText = (EditText) (findViewById(R.id.editText));
                            Log.d("onClick", "onClick");
                            Log.d("pin", editText.getText().toString());
                            //AccessToken accessToken = twitter.getOAuthAccessToken(pinTask.requestToken);
                            Log.d("requeseToken", String.valueOf(pinTask.requestToken));
                            AccessToken accessToken = twitter.getOAuthAccessToken(pinTask.requestToken, editText.getText().toString());
                            Log.d("accessToken", String.valueOf(accessToken));
                            twitter.verifyCredentials();

                            SharedPreferences dataStore = getSharedPreferences("account1", MODE_PRIVATE);
                            SharedPreferences.Editor editor = dataStore.edit();
                            editor.putString("token", accessToken.getToken());
                            editor.putString("tokenSecret", accessToken.getTokenSecret());
                            editor.commit();
                            Intent intent = new Intent(PinActivity.this, MainActivity.class);
                            startActivity(intent);
                        } catch (TwitterException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
                task.execute();
            }
        });
    }
}
