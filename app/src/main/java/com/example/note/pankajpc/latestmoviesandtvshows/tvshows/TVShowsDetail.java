package com.example.note.pankajpc.latestmoviesandtvshows.tvshows;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.note.pankajpc.latestmoviesandtvshows.R;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.TVShows;

import java.util.List;

public class TVShowsDetail extends AppCompatActivity {
    private Context mContext;
    private ImageView mPoster;
    private TextView mTitle, mOverview, mMovieGenres, mReleaseDate, textView8;
    private RatingBar mRating;
    private String mGenres = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        mContext = this;
        TVShows tvShows = (TVShows) getIntent().getSerializableExtra("SongDetailObject");


        mPoster = (ImageView) findViewById(R.id.poster);
        mTitle = (TextView) findViewById(R.id.movieTitle);
        mOverview = (TextView) findViewById(R.id.movieOverview);
        mMovieGenres = (TextView) findViewById(R.id.movieGenres);
        mRating = (RatingBar) findViewById(R.id.rating);
        mReleaseDate = (TextView) findViewById(R.id.releaseDate);
        textView8 = (TextView) findViewById(R.id.textView8);
        textView8.setText("First Air Date");


        Glide.with(mContext).load("https://image.tmdb.org/t/p/w500" + tvShows.getBackdropPath()).fitCenter().into(mPoster);
        mTitle.setText(tvShows.getName());
        mOverview.setText(tvShows.getOverview());
        mReleaseDate.setText(tvShows.getFirstAirDate());

        float vote = tvShows.getVoteAverage().floatValue();
        float x = ((vote / 10) * 5);
        mRating.setRating(x);


        List<Integer> genres = tvShows.getGenreIds();
        for (int i = 0; i < genres.size(); i++) {
            int y = genres.get(i);
            if((i==0) || (i==genres.size())){

            }
            else {
                mGenres = mGenres + "," ;
            }
            mGenres = mGenres + getGenres(y);

        }
        mMovieGenres.setText(mGenres);

    }

    private String getGenres(int y) {
        if (y == 10759) {
            return "Action & Adventure";

        }  else if (y == 16) {
            return "Animation";

        } else if (y == 35) {
            return "Comedy";

        } else if (y == 80) {
            return "Crime";

        } else if (y == 99) {
            return "Documentary";

        } else if (y == 18) {
            return "Drama";

        } else if (y == 10751) {
            return "Family";

        }
        else if (y == 10762) {
            return "Kids";

        }else if (y == 10763) {
            return "News";

        }else if (y == 10764) {
            return "Reality";

        }else if (y == 10765) {
            return "Sci-Fi & Fantasy";

        }else if (y == 10766) {
            return "Soap";

        }else if (y == 10767) {
            return "Talk";

        }else if (y == 10768) {
            return "War & Politics";

        }else if (y == 14) {
            return "Fantasy";

        } else if (y == 36) {
            return "History";

        } else if (y == 27) {
            return "Horror";

        } else if (y == 10402) {
            return "Music";

        } else if (y == 9648) {
            return "Mystery";

        } else if (y == 10749) {
            return "Romance";
        } else if (y == 878) {
            return "Science Fiction";
        } else if (y == 10770) {
            return "TV Movie";
        } else if (y == 53) {
            return "Thriller";
        } else if (y == 10752) {
            return "War";
        } else if (y == 37) {
            return "Western";
        }

            return "";
    }
}
