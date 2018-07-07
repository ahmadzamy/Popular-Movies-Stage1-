package com.example.ahmedsaifaldeen.popularmoviesstage1;

public class Movies {


    private String original_title;
    private String movie_poster_image;
    private String overview;
    private String vote_average;
    private String release_date;

    public Movies() {
    }

    public Movies(String original_title, String movie_poster_image, String overview, String vote_average, String release_date) {

        this.original_title = original_title;
        this.movie_poster_image = movie_poster_image;
        this.overview = overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
    }


    public String getOriginal_title() {

        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getMovie_poster_image() {
        return movie_poster_image;
    }

    public void setMovie_poster_image(String movie_poster_image) {
        this.movie_poster_image = movie_poster_image;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }


}
