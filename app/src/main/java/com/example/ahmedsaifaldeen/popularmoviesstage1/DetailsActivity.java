package com.example.ahmedsaifaldeen.popularmoviesstage1;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import static com.example.ahmedsaifaldeen.popularmoviesstage1.MainActivity.ORIGINAL_TITLE;
import static com.example.ahmedsaifaldeen.popularmoviesstage1.MainActivity.OVERVIEW;
import static com.example.ahmedsaifaldeen.popularmoviesstage1.MainActivity.POSTER_PATH;
import static com.example.ahmedsaifaldeen.popularmoviesstage1.MainActivity.RELEASED_DATE;
import static com.example.ahmedsaifaldeen.popularmoviesstage1.MainActivity.VOTE_AVARAGE;

public class DetailsActivity extends AppCompatActivity {

    ImageView poster;
    TextView overView;
    TextView originalTitle;
    TextView votedAvarege;
    TextView releasedDate;

    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500";
    ProgressBar detailProgress;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        poster = findViewById(R.id.detail_image);
        overView = findViewById(R.id.detail_over_view);
        originalTitle = findViewById(R.id.detail_title);
        votedAvarege = findViewById(R.id.detail_voted_avarege);
        releasedDate = findViewById(R.id.detail_released_date);
        detailProgress = findViewById(R.id.detail_progress);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("More Information");
        String overViewText = getIntent().getStringExtra(OVERVIEW);
        String originalTitleText = getIntent().getStringExtra(ORIGINAL_TITLE);
        String votedAvaregeText = getIntent().getStringExtra(VOTE_AVARAGE);
        String posterImage = getIntent().getStringExtra(POSTER_PATH);
        String releasedDateText = getIntent().getStringExtra(RELEASED_DATE);

        overView.setText(overViewText);
        originalTitle.setText(originalTitleText);
        votedAvarege.setText(votedAvaregeText);
        releasedDate.setText(releasedDateText);

        Log.i("SS", BASE_IMAGE_URL + posterImage);

        detailProgress.setVisibility(View.VISIBLE);
       // poster.setVisibility(View.GONE);

        Glide.with(this)
                .load(BASE_IMAGE_URL + posterImage)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(DetailsActivity.this, "The image Can't be loaded", Toast.LENGTH_LONG).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        detailProgress.setVisibility(View.GONE);
                       // poster.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(poster);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
