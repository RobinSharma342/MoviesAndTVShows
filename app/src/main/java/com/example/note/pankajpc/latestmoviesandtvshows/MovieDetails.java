package com.example.note.pankajpc.latestmoviesandtvshows;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.note.pankajpc.latestmoviesandtvshows.pojo.TopRatedMoviesList;

import java.util.List;

public class MovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);
        TextView tv = (TextView)findViewById(R.id.textView);
        List<TopRatedMoviesList> moviesLists = (List<TopRatedMoviesList>) getIntent().getSerializableExtra("mylist");
        tv.setText(moviesLists.get(0).getTitle());

    }
}
