package com.rainmecka.mymovieDB;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.rainmecka.recycyclerexercise.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity
        implements MovieAdapter.ListItemClickListener {

    final static String MOVIEDB_BASE_URL = "https://api.themoviedb.org/3/movie/";

    final static String PARAM_QUERY = "api_key";

    public static List<MovieClass> mMovieDataArrayList = new ArrayList<>();

    private GridView gridview;

    private SharedPreferences sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Context context = getActivity
//        sharedpref = this.getSharedPreferences(getString(R.string.preference_file_key)
//                ,this.MODE_PRIVATE);

        String movieurl = getResources().getString(R.string.preference_file_key);

        URL movieDbQueryURL = makeMovieDBUrl(movieurl);

        new FetchMovieTask(this, new MovieDBQueryTaskCompleteListener()).execute(movieDbQueryURL);

        gridview = (GridView) findViewById(R.id.gridview);
    }


    public URL makeMovieDBUrl(String sortType) {
        Uri top_rated_uri = Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                .appendPath(sortType)
                .appendQueryParameter(PARAM_QUERY, getString(R.string.api_key))
                .build();

        URL top_rated_url = null;

        try {
            top_rated_url = new URL(top_rated_uri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return top_rated_url;
    }

    public class MovieDBQueryTaskCompleteListener implements AsyncHelper
            .AsyncTaskCompleteListener<MovieClass> {

        @Override
        public void onTaskComplete(List<MovieClass> result) {
            // do something with the result

            mMovieDataArrayList = result;
            gridview.setAdapter(new ImageAdapter(MainActivity.this, mMovieDataArrayList));

            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int clickedOptionItem = item.getItemId();

        String optUrl;

        if (clickedOptionItem == R.id.pop_popularity) {
            //Refresh by popularity
            optUrl = getString(R.string.sort_popular);
        }else if (clickedOptionItem == R.id.pop_ratings){
            //Refresh by Ratings
            optUrl = getString(R.string.sort_top_rated);
        }else{
            // Sort by Ratings
            optUrl = getString(R.string.sort_top_rated);
        }
        sharedpref = this.getSharedPreferences(getString(R.string.preference_file_key)
                ,this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref.edit();

        editor.putString(getString(R.string.preference_file_key),optUrl);

        editor.commit();

        URL movieDbQueryURL = makeMovieDBUrl(optUrl);

        new FetchMovieTask(this, new MovieDBQueryTaskCompleteListener()).execute(movieDbQueryURL);

        ImageAdapter newImgAdapt = new ImageAdapter(MainActivity.this, mMovieDataArrayList);

        newImgAdapt.swapData(mMovieDataArrayList);

        gridview.setAdapter(newImgAdapt);

        return true;
        }

        //return super.onOptionsItemSelected(item);
    }





