package com.example.android.movieapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class detailedMovieActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_movie);

        String original_title = getIntent().getExtras().getString("original_title");
        String overview=getIntent().getExtras().getString("overview");
        String post_path =getIntent().getExtras().getString("post_path");
        String release_date =getIntent().getExtras().getString("release_date");
        int vote_count =getIntent().getExtras().getInt("vote_count");
        //String key = getIntent().getExtras().getString("key");

        ImageView imageView = (ImageView) findViewById(R.id.movieImgId);

        TextView tvTitle = (TextView) findViewById(R.id.movieTitle);
        TextView tvOverview = (TextView) findViewById(R.id.overview);
        TextView tvReleaseDate = (TextView) findViewById(R.id.relaese_date);
        TextView tvVoteCount = (TextView) findViewById(R.id.vote_count);
        tvTitle.setText("Movie title: " + original_title);
        Picasso.with(getApplicationContext()).load("http://image.tmdb.org/t/p/w500" + post_path).into(imageView);
        tvReleaseDate.setText("Release date: " + release_date);
        tvVoteCount.setText("Vote count: "+String.valueOf(vote_count));
        tvOverview.setText("Description: "+overview);


//        String result = "";
//        ///TODO:call getTrailers
//        TrailersFetch trailersFetch = new TrailersFetch();
//        try {
//             result = trailersFetch.callMoviesTrailers(key);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        final Vector<String> trailersUrls = trailersFetch.getTrailersURLs(result);
//        Toast.makeText(getApplicationContext(),trailersUrls.toString(),Toast.LENGTH_LONG).show();
//

        Button btn = (Button)findViewById(R.id.aa);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //https://www.youtube.com/watch?v=5b6AGSh4dVE
                watchYoutubeVideo("7d_jQycdQGo");
            }
        });


    }

    public void watchYoutubeVideo(String id){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + id));
            startActivity(intent);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detailed_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
