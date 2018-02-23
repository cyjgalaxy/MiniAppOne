package com.example.cyjga.miniappone;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Context mContext;
    private ArrayList<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        // data to display
        movieList = Movie.getMoviesFromFile("movies.json", this);

        // create the adapter
        MovieAdapter adapter = new MovieAdapter(this, movieList);

        // find the listview in the layout
        // set the adapter to listview
        mListView = findViewById(R.id.movie_list_view);
        mListView.setAdapter(adapter);


        // 1. each row should be clickable
        // when the row is clicked,
        // the intent is created and send

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Movie selectedMovie = movieList.get(position);

                // create my intent package
                // add all the information needed for detail page
                // startActivity with that intent

                //explicit
                // from, to
                Intent detailIntent = new Intent(mContext, MovieDetailActivity.class);
                // put title and instruction URL
                detailIntent.putExtra("title", selectedMovie.title);
                detailIntent.putExtra("url", selectedMovie.url);
                detailIntent.putExtra("description", selectedMovie.description);
                detailIntent.putExtra("poster", selectedMovie.poster);
                detailIntent.putExtra("position", position);


                startActivityForResult(detailIntent, 1);
                //             startActivity(detailIntent);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String strEditText = data.getExtras().getString("editTextValue");
                int pos = data.getExtras().getInt("position");
                TextView hasseen = mListView.getChildAt(pos).findViewById(R.id.movie_list_hasseen);
                int colorCode = ContextCompat.getColor(mContext, R.color.colorPrimaryDark);
                switch (strEditText) {
                    case "Seen":
                        movieList.get(pos).updateMovieSeenStatus(1);
                        hasseen.setText(R.string.alreadyseen);
                        colorCode = ContextCompat.getColor(mContext, R.color.colorAccent3);
                        break;
                    case "Want":
                        movieList.get(pos).updateMovieSeenStatus(2);
                        hasseen.setText(R.string.wanttosee);
                        colorCode = ContextCompat.getColor(mContext, R.color.colorAccent);
                        break;
                    case "NotLike":
                        movieList.get(pos).updateMovieSeenStatus(3);
                        hasseen.setText(R.string.donotlike);
                        colorCode = ContextCompat.getColor(mContext, R.color.colorAccent2);
                        break;
                    default:
                        movieList.get(pos).updateMovieSeenStatus(0);
                        hasseen.setText(R.string.hasseen);
                        colorCode = ContextCompat.getColor(mContext, R.color.colorPrimaryDark);
                        break;
                }
                hasseen.setTextColor(colorCode);
            }
        }
    }
}
