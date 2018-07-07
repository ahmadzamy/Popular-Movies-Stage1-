package com.example.ahmedsaifaldeen.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import static com.example.ahmedsaifaldeen.popularmoviesstage1.MainActivity.ORIGINAL_TITLE;
import static com.example.ahmedsaifaldeen.popularmoviesstage1.MainActivity.OVERVIEW;
import static com.example.ahmedsaifaldeen.popularmoviesstage1.MainActivity.POSTER_PATH;
import static com.example.ahmedsaifaldeen.popularmoviesstage1.MainActivity.RELEASED_DATE;
import static com.example.ahmedsaifaldeen.popularmoviesstage1.MainActivity.VOTE_AVARAGE;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    Context mContext;
    int resource;
    ArrayList<Movies> moviesArrayList;
   private static String BASE_IMAGE_URL="https://image.tmdb.org/t/p/w500";

    public MoviesAdapter(Context mContext, int resource, ArrayList<Movies> movies) {
        this.mContext = mContext;
        this.resource = resource;
        this.moviesArrayList = movies;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.progressBar.setVisibility(View.VISIBLE);
        Glide.with(mContext)
                .load(BASE_IMAGE_URL + moviesArrayList.get(position).getMovie_poster_image())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.image);

        holder.name.setText(moviesArrayList.get(position).getOriginal_title());

        Log.i("myImage", String.valueOf("https://image.tmdb.org/t/p/origina" + moviesArrayList.get(position).getMovie_poster_image()));
        Log.i("myTitle", String.valueOf(moviesArrayList.get(position).getOriginal_title()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movies item = moviesArrayList.get(holder.getAdapterPosition());
                Intent intent=new Intent(mContext,DetailsActivity.class);

                intent.putExtra(ORIGINAL_TITLE,item.getOriginal_title());
                intent.putExtra(OVERVIEW,item.getOverview());
                intent.putExtra(POSTER_PATH,item.getMovie_poster_image());
                intent.putExtra(RELEASED_DATE,item.getRelease_date());
                intent.putExtra(VOTE_AVARAGE,item.getVote_average());
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return moviesArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        Movies movies = new Movies();
        ImageView image;
        TextView name;
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.poster_image);
            name = itemView.findViewById(R.id.movie_title);
            progressBar = itemView.findViewById(R.id.image_progress);
        }
    }
}
