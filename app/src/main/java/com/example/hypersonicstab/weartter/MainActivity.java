package com.example.hypersonicstab.weartter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.support.wearable.view.CurvedChildLayoutManager;
import android.support.wearable.view.WearableRecyclerView;
import android.support.wearable.view.drawer.WearableActionDrawer;
import android.support.wearable.view.drawer.WearableDrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterListener;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

public class MainActivity extends WearableActivity implements WearableActionDrawer.OnMenuItemClickListener {

    private String API_KEY = "";
    private String API_SECRET = "";

    private AccessToken accessToken;

    private String ACCESS_TOKEN = "";
    private String ACCESS_TOKEN_SECRET = "";

    private AsyncTwitter mTwitter;
    private Twitter twitter;
    private RequestToken mReqToken;

    private final TwitterListener mListener = new TwitterAdapter() {
        @Override
        public void gotOAuthRequestToken(RequestToken token) {
            mReqToken = token;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mReqToken.getAuthorizationURL()));
            startActivity(intent);
        }

        @Override
        public void gotOAuthAccessToken(AccessToken token) {
            //token.getToken()とtoken.getTokenSecret()を保存する
        }

        @Override
        public void updatedStatus(Status status) {
            Log.d("updatedStatus", "Status ID:" + status.getId());
        }

        @Override
        public void searched(QueryResult queryResult) {
            Log.d("search", "search");
            Log.d("result", String.valueOf(queryResult));
            for (Status status : queryResult.getTweets()) Log.d("searched", "Status ID:" + status.getId());
        }

    };

    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    private WearableDrawerLayout mContainerView;
    private TextView mTextView;
    private TextView mClockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            ApplicationInfo info
                    = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            API_KEY = info.metaData.getString("apiKey");
            API_SECRET = info.metaData.getString("apiSecret");
        }catch(PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        initViews();

        SharedPreferences account1 = getSharedPreferences("account1", MODE_PRIVATE);
        ACCESS_TOKEN = account1.getString("token", null);
        ACCESS_TOKEN_SECRET = account1.getString("tokenSecret", null);


mContainerView = (WearableDrawerLayout) findViewById(R.id.container);
        mTextView = (TextView) findViewById(R.id.text);
        mClockView = (TextView) findViewById(R.id.clock);

        mTwitter = AsyncTwitterFactory.getSingleton();
        mTwitter.addListener(mListener);
        //mTwitter.setOAuthConsumer(API_KEY, API_SECRET);
        accessToken = new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
        mTwitter.setOAuthAccessToken(accessToken);
        Log.d("onCreate", "onCreate");
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(API_KEY)
                .setOAuthConsumerSecret(API_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(API_KEY, API_SECRET);
        twitter.setOAuthAccessToken(accessToken);

        final Task task = new Task(MainActivity.this, accessToken);
        task.execute(0);

        final WearableRecyclerView recyclerView = (WearableRecyclerView) findViewById(R.id.recycler_view);
        final TweetAdapter adapter = new TweetAdapter(this, new ArrayList<String>() {{add("a"); add("b"); add("c"); add("d"); add("e"); add("f");}});
        recyclerView.setAdapter(adapter);
        recyclerView.setCenterEdgeItems(true);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final Task task = new Task(MainActivity.this, accessToken);
                task.execute();
                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
                manager.scrollToPosition(0);
                // 終わったらSwipeRefreshLayoutの状態をリセット
                // 非同期だったら、コールバックなどに書くといい
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });


        //mChildLayoutManager = new CircularChildLayoutManager(mContext);
        CurvedChildLayoutManager manager = new CurvedChildLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        //recyclerView.setAdapter();

        //mTwitter.getHomeTimeline();

        //searchTweet("");
    }

    public void callback(List<twitter4j.Status> statuses) {
        WearableRecyclerView recyclerView = (WearableRecyclerView) findViewById(R.id.recycler_view);
        TweetAdapter adapter = (TweetAdapter) recyclerView.getAdapter();
        adapter.statuses = statuses;
        Log.d("statuses", String.valueOf(statuses));
        Log.d("adapter.statuses", String.valueOf(adapter.statuses));
        adapter.notifyDataSetChanged();
        Log.d("dataSetChanged", "changed");


        //   TweetAdapter adapter = new TweetAdapter(this, statuses);
        //  recyclerView.setAdapter(adapter);
    }
    //検索する
    public void searchTweet(String text) {
        Query query = new Query();
        query.setQuery(text);
        query.setCount(80); //検索件数
        query.setResultType(Query.RECENT); //日付の新しいものから
        Log.d("searchTweet", "searchTweet");
        mTwitter.search(query);
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {
            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
            //mTextView.setTextColor(getResources().getColor(android.R.color.white));
            mClockView.setVisibility(View.VISIBLE);

            mClockView.setText(AMBIENT_DATE_FORMAT.format(new Date()));
        } else {
            mContainerView.setBackground(null);
            //mTextView.setTextColor(getResources().getColor(android.R.color.black));
            mClockView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        //Toast.makeText(this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, TweetActivity.class);
        startActivity(intent);
        return false;
    }

    private void initViews() {
        WearableDrawerLayout wearableDrawerLayout = (WearableDrawerLayout) findViewById(R.id.container);
        wearableDrawerLayout.peekDrawer(Gravity.BOTTOM);

        WearableActionDrawer wearableActionDrawer = (WearableActionDrawer) findViewById(R.id.bottom_action_drawer);
        wearableActionDrawer.setOnMenuItemClickListener(this);
    }
}
