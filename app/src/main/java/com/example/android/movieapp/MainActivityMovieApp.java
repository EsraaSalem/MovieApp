package com.example.android.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

//import android.support.v7.app.AppCompatActivity;

public class MainActivityMovieApp extends AppCompatActivity implements NameListener{

    boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_activity_movie_app);

        FrameLayout flPanel2 = (FrameLayout) findViewById(R.id.flPanel_Two);
        if (null == flPanel2) {
            mTwoPane = false;
        } else {
            mTwoPane = true;
        }


        if(savedInstanceState == null)
        {
            MainActivityMovieAppFragment mainActivityMovieAppFragment = new MainActivityMovieAppFragment();
            mainActivityMovieAppFragment.setNameListener(this);

            getSupportFragmentManager().beginTransaction().add(R.id.mainactivityLayout,
                    mainActivityMovieAppFragment).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_movie_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setSelectedName(Movie selectedMovie) {

        if (!mTwoPane) {
            Intent i = new Intent(this, DetailedActivity.class);
            i.putExtra("post_path", selectedMovie.getPoster_path());
            i.putExtra("overview", selectedMovie.getOverview());
            i.putExtra("original_title", selectedMovie.getOriginal_title());
            i.putExtra("release_date", selectedMovie.getRelease_date());

            i.putExtra("vote_count", String.valueOf(selectedMovie.getVote_count()));
            i.putExtra("key", selectedMovie.getKey());
            //String.valueOf(selectedMovie.getVote_average())
            i.putExtra("vote_average", "1.3");
            startActivity(i);
        } else
        {

//            DetailedFragment detailsFragment= new DetailedFragment();
//            Bundle extras= new Bundle();
//            extras.putString("post_path", selectedMovie.getPoster_path());
//            extras.putString("overview", selectedMovie.getOverview());
//            extras.putString("original_title", selectedMovie.getOriginal_title());
//            extras.putString("release_date", selectedMovie.getRelease_date());
//            extras.putString("vote_count", String.valueOf(selectedMovie.getVote_count()));
//            extras.putString("key", selectedMovie.getKey());
//            extras.putString("vote_average", String.valueOf(selectedMovie.getVote_average()));
//            detailsFragment.setArguments(extras);
//            getFragmentManager().beginTransaction().replace(R.id.flPanel_Two,
//                    detailsFragment).commit();




            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            DetailedFragment fragment = new DetailedFragment();
            Bundle extras= new Bundle();
            extras.putString("post_path", selectedMovie.getPoster_path());
            extras.putString("overview", selectedMovie.getOverview());
            extras.putString("original_title", selectedMovie.getOriginal_title());
            extras.putString("release_date", selectedMovie.getRelease_date());
            extras.putString("vote_count", String.valueOf(selectedMovie.getVote_count()));
            extras.putString("key", selectedMovie.getKey());
            extras.putString("vote_average", String.valueOf(selectedMovie.getVote_average()));
            fragment.setArguments(extras);

            fragmentTransaction.replace(R.id.flPanel_Two, fragment);
            fragmentTransaction.commit();
        }

    }
}
