package com.example.ahmedsaifaldeen.popularmoviesstage1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private final static String POPULAR_MOVIES =
            "https://api.themoviedb.org/3/movie/popular?api_key=YOUR KEY";

    private final static String RATED_MOVIES =
            "https://api.themoviedb.org/3/movie/top_rated?api_key=YOUR KEY";

    public static StringBuffer ahmedUrl = new StringBuffer(POPULAR_MOVIES);

    static RecyclerView recyclerView;
    static ProgressBar mainProgress;
    static ImageView noInternetImage;

    static String POSTER_PATH = "poster_path";
    static String ORIGINAL_TITLE = "original_title";
    static String OVERVIEW = "overview";
    static String RELEASED_DATE = "release_date";
    static String VOTE_AVARAGE = "vote_average";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        mainProgress = findViewById(R.id.main_progress);
        noInternetImage = findViewById(R.id.no_internet);


        getSupportActionBar().setTitle("Home");
        getSupportLoaderManager().initLoader(0, null, MainActivity.this).forceLoad();

    }


    @Override
    protected void onResume() {
        super.onResume();
        mainProgress.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new myTask(this, String.valueOf(ahmedUrl));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

        if (data != null && !data.isEmpty()) {
            updateUI(data);
            Toast.makeText(this, "Wait...", Toast.LENGTH_SHORT).show();

            recyclerView.setVisibility(View.VISIBLE);
            noInternetImage.setVisibility(View.GONE);
        } else {
            noInternetImage.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(this, "NO INTERNET CONNECTION \n\n PLEASE CHECK YOUR WIFI", Toast.LENGTH_LONG).show();

        }
        mainProgress.setVisibility(View.GONE);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        noInternetImage.setVisibility(View.GONE);
    }

    private void updateUI(String s) {

        ArrayList<Movies> newsList = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(s);
            Log.v("JSON", s);
            JSONArray r = root.getJSONArray("results");


            for (int i = 0; i < r.length(); i++) {

                JSONObject newsOject = r.getJSONObject(i);
                String posterPath = newsOject.getString(POSTER_PATH);
                String originalTitle = newsOject.getString(ORIGINAL_TITLE);
                String overview = newsOject.getString(OVERVIEW);
                String releaseDate = newsOject.getString(RELEASED_DATE);
                String voteAverage = newsOject.getString(VOTE_AVARAGE);

                Log.i("poster", originalTitle);
                Movies movies = new Movies(originalTitle, posterPath, overview, releaseDate, voteAverage);

                newsList.add(movies);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MoviesAdapter adapter = new MoviesAdapter(this, R.layout.movies_item, newsList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.top_rated:
                ahmedUrl = new StringBuffer(RATED_MOVIES);

                getSupportLoaderManager().restartLoader(0, null, MainActivity.this).forceLoad();
                break;
            case R.id.popular:
                ahmedUrl = new StringBuffer(POPULAR_MOVIES);

                getSupportLoaderManager().restartLoader(0, null, MainActivity.this).forceLoad();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

