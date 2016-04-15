package com.example.android.movieapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Vector;
import java.util.concurrent.ExecutionException;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityMovieAppFragment extends Fragment {

    public MainActivityMovieAppFragment() {
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(getActivity());
        String unitType = sharedPrefs.getString(getString(R.string.pref_sortmethod_key), "mostPopular");
        // Toast.makeText(getActivity(), unitType, Toast.LENGTH_LONG).show();
        return true;


//        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_activity_movie_app, container, false);
        try {
            FetchMoviesTask fetchMoviesTask = new FetchMoviesTask();
            SharedPreferences sharedPrefs =
                    PreferenceManager.getDefaultSharedPreferences(getActivity());
            String sortType = sharedPrefs.getString(getString(R.string.pref_sortmethod_key), "mostPopular");
            Toast.makeText(getActivity(), "YYYYY   " + sortType, Toast.LENGTH_LONG).show();

            String output = fetchMoviesTask.execute(sortType.trim()).get();
            //Toast.makeText(getActivity(), "XXXXXXXXXXXXXXXXX   " + output, Toast.LENGTH_LONG).show();

            MovieParser movieParser = new MovieParser();

            final Vector<Movie> allMovieToView = movieParser.parseMovieAPIOutput(output);

            GridView gridView = (GridView) rootView.findViewById(R.id.gridviewMoviesImages);
            gridView.setAdapter(null);
            gridView.setAdapter(new GridViewMovieImages(getActivity(), allMovieToView));

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent i = new Intent(getActivity(), detailedMovieActivity.class);
                    i.putExtra("post_path", allMovieToView.get(position).getPoster_path());
                    i.putExtra("overview", allMovieToView.get(position).getOverview());
                    i.putExtra("original_title", allMovieToView.get(position).getOriginal_title());
                    i.putExtra("release_date", allMovieToView.get(position).getRelease_date());
                    i.putExtra("vote_count", allMovieToView.get(position).getVote_count());
                    i.putExtra("key", allMovieToView.get(position).getKey());

                    startActivity(i);
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return rootView;
    }

}
