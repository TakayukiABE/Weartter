package com.example.hypersonicstab.weartter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class IconGetTask extends AsyncTask {
    ImageView imageView;
    Bitmap bitmap;
    String urlString;

    public IconGetTask(ImageView imageView, String iconUrlString) {
        this.urlString = iconUrlString;
        this.imageView = imageView;
    }
    @Override
    protected Object doInBackground(Object[] params) {
        HttpsURLConnection connection = null;
        InputStream inputStream = null;


        try{

            URL url = null;
            try {
                url = new URL(urlString);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            connection = (HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            inputStream = connection.getInputStream();

            bitmap = BitmapFactory.decodeStream(inputStream);

        }catch (MalformedURLException exception){

        }catch (IOException exception){

        } finally {
            if (connection != null){
                connection.disconnect();
            }
            try{
                if (inputStream != null){
                    inputStream.close();
                }
            }catch (IOException exception){
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        imageView.setImageBitmap(bitmap);
    }
}
