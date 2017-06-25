package com.rainmecka.mymovieDB;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by yemi on 15/04/2017.
 */

public class MovieClass implements Parcelable {
    public String poster_path;
    public String adultGenre;
    public String overview;
    public String release_date;
    public String genre_ids;
    public int movie_id;

    public String backdrop_path;
    public String popularity;
    public int vote_count;
    public boolean video;
    public String vote_average;
    public String image_string_url;
    public String original_title;


    MovieClass(){

    }

    protected MovieClass(Parcel in) {
        poster_path = in.readString();
        adultGenre = in.readString();
        overview = in.readString();
        original_title = in.readString();
        image_string_url = in.readString();
        release_date = in.readString();
        vote_average = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poster_path);
        dest.writeString(adultGenre);
        dest.writeString(overview);
        dest.writeString(original_title);
        dest.writeString(image_string_url);
        dest.writeString(release_date);
        dest.writeString(vote_average);

    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieClass> CREATOR;

    static {
        CREATOR = new Creator<MovieClass>() {
            @Override
            public MovieClass createFromParcel(Parcel in) {
                return new MovieClass(in);
            }

            @Override
            public MovieClass[] newArray(int size) {
                return new MovieClass[size];
            }
        };
    }

}
