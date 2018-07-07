package com.example.ahmedsaifaldeen.popularmoviesstage1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class myTask extends AsyncTaskLoader<String> {
    String Link;

    public myTask(@NonNull Context context, String Link) {
        super(context);
       this.Link=Link;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        MainActivity.mainProgress.setVisibility(View.VISIBLE);
       MainActivity.recyclerView.setVisibility(View.GONE);

    }

    @Nullable
    @Override
    public String loadInBackground() {
        StringBuilder JsonData = new StringBuilder();
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {

            // this is the URL for the API
            // to make the API dynamic*/
            //String urlString = "https://api.themoviedb.org/3/movie/popular?api_key=20ce804c891dad55ad95cbf6159591a8";

            String urlString=Link;

            Log.i("Ah",urlString);
            Log.v("NETWORK_URL", urlString);

            // the same code in all requests
            URL url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                JsonData.append(line);
                line = reader.readLine();
            }
            Log.v("AsyncTask", "Connected" + httpURLConnection.getResponseCode());
        } catch (Exception e) {
            e.printStackTrace();
            Log.v("AsyncTask", e.getMessage());
        } finally {
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        // return the result (json string)
        return JsonData.toString();
    }

}
