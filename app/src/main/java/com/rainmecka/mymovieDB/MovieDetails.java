package com.rainmecka.mymovieDB;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rainmecka.recycyclerexercise.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MovieDetails extends AppCompatActivity {

    private String mMovie;
    private TextView mMovieDisplay;
    private TextView mOverviewDisplay;
    private ImageView mMoviePosterView;
    private TextView mRelease_Date;
    private TextView mVote_Average;
    private String imgUrl;
    private String mOverview;
    private String release_Date;
    private String vote_average;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mMovieDisplay = (TextView) findViewById(R.id.tv_movie_title);
        mOverviewDisplay = (TextView)findViewById(R.id.tv_overview);
        mRelease_Date = (TextView)findViewById(R.id.tv_release_date);
        mVote_Average = (TextView)findViewById(R.id.tv_vote_average);


        mMoviePosterView = new ImageView(MovieDetails.this);
        mMoviePosterView = (ImageView) findViewById(R.id.iv_movie);

        //LayoutParams lp = fooView.getLayoutParams();

        //mMoviePosterView.setLayoutParams(new GridView.LayoutParams(500,700));
        mMoviePosterView.setScaleType(ImageView.ScaleType.CENTER_CROP);

     //   imageLoader.displayImage(url, imageView);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("MovieClass")) {
                MovieClass mymovieobject = getIntent().getParcelableExtra("MovieClass");
                mMovie = mymovieobject.original_title;
                mOverview = mymovieobject.overview;
                release_Date = getString(R.string.release_date) + mymovieobject.release_date;
                vote_average = getString(R.string.vote_average) + mymovieobject.vote_average;

                mMovieDisplay.setText(mMovie);
                mOverviewDisplay.setText(mOverview);
                mRelease_Date.setText(release_Date);
                mVote_Average.setText(vote_average);

                mMovieDisplay.setTypeface(null, Typeface.BOLD);
                //mOverviewDisplay.setTypeface(null, Typeface.BOLD);
                //mRelease_Date.setTypeface(null, Typeface.BOLD);
                //mVote_Average.setTypeface(null, Typeface.BOLD);



                imgUrl = getString(R.string.image_base_url) +  getString(R.string.image_base_dimen) +
                        mymovieobject.poster_path;
                Glide.with(this)
                        .load(imgUrl)
                        .into(mMoviePosterView);


//                Picasso.with(MovieDetails.this) //Context
//                        .load(imgUrl) //URL/FILE
//                        .into(mMoviePosterView);//an ImageView Object to show the loaded image;


            }
        }
    }
}

