package com.example.hypersonicstab.weartter;

import android.os.AsyncTask;
import android.util.Log;

import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;


public class PinTask extends AsyncTask {
    AsyncTwitter twitter = AsyncTwitterFactory.getSingleton();
    RequestToken requestToken = null;
    AccessToken accessToken = null;

    @Override
    protected Object doInBackground(Object[] params) {
        try {
            requestToken = twitter.getOAuthRequestToken();
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        if (requestToken != null) {

            Log.d("requestToken", String.valueOf(requestToken));
            Log.d("URL", requestToken.getAuthorizationURL());
/*
            try {
                accessToken = twitter.getOAuthAccessToken();
            } catch (TwitterException e) {
                e.printStackTrace();
            }
*/
        }


        return null;
    }
}
