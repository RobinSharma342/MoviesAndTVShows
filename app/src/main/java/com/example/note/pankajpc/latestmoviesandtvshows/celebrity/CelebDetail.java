package com.example.note.pankajpc.latestmoviesandtvshows.celebrity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.note.pankajpc.latestmoviesandtvshows.R;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.PersonData;

/**
 * Created by Pankaj PC on 02-18-2017.
 */
public class CelebDetail extends AppCompatActivity {

    private Context mContext;
    private ImageView mPoster;
    private TextView mCelebName, mOverview, mMovieGenres, mReleaseDate, mBornDate,mBioTitle,mBioGraphy,mBorn,mRatingText,mPlaceOfBirthText,mPlaceOfBirth;
    private RatingBar mRating;
    private String mGenres = "";
    private PersonData personData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personData = (PersonData) getIntent().getSerializableExtra("celebdetail");

        setContentView(R.layout.movie_detail);
        mContext = this;




        mPoster = (ImageView) findViewById(R.id.poster);
        mCelebName = (TextView) findViewById(R.id.movieTitle);
        mBioTitle = (TextView) findViewById(R.id.storyLine);
        mBioGraphy = (TextView) findViewById(R.id.movieOverview);
        mBorn = (TextView) findViewById(R.id.Genres);
        mBornDate = (TextView) findViewById(R.id.movieGenres);
        mRatingText = (TextView) findViewById(R.id.textView6);
        mPlaceOfBirthText = (TextView) findViewById(R.id.textView8);
        mPlaceOfBirth = (TextView) findViewById(R.id.releaseDate);
        mRating = (RatingBar) findViewById(R.id.rating);

        mRatingText.setVisibility(View.GONE);
        mRating.setVisibility(View.GONE);

        Glide.with(mContext).load("https://image.tmdb.org/t/p/w500" + personData.getProfilePath()).into(mPoster);
        mCelebName.setText(personData.getName());
        mBioGraphy.setText(personData.getBiography());
        mBornDate.setText(personData.getBirthday());
        mPlaceOfBirth.setText(personData.getPlaceOfBirth());
        mBioTitle.setText("BioGraphy");
        mBorn.setText("Birth Day");
        mPlaceOfBirthText.setText("Place of Birth");









    }


}
