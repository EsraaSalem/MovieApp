package com.example.android.movieapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


/**
 * Created by Esraa on 24-Apr-16.
 */

public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        if (null == savedInstanceState) {
            DetailedFragment detailedFragment = new DetailedFragment();
            detailedFragment.setArguments(extras);
            getSupportFragmentManager().beginTransaction().add(R.id.detailedContainer,
                    detailedFragment).addToBackStack("").commit();
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //TODO Check navigation here
        //getMenuInflater().inflate(R.menu.menu_main_activity_movie_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }


}
