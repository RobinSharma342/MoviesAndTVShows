package com.example.note.pankajpc.latestmoviesandtvshows;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.TopRatedMoviesList;

import java.util.List;

/**
 * Created by Pankaj PC on 01-29-2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    static Context context;
    public View v;
    ViewHolder vh;
    List<TopRatedMoviesList> moviesLists;
    private OnItemClickListenerRecyclerview mItemClickListenerRecyclerview;

    public MovieAdapter(Context context, List<TopRatedMoviesList> moviesLists) {
        this.context = context;
        this.moviesLists = moviesLists;
    }

    public interface OnItemClickListenerRecyclerview {

        public void onItemClick(View v, int position);
    }

    public void setOnItemClickListenerRecyclerview(OnItemClickListenerRecyclerview listenerRecyclerview) {
        mItemClickListenerRecyclerview = listenerRecyclerview;

    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.movie_single_row, parent, false);
        vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        TopRatedMoviesList topRatedMoviesList = moviesLists.get(position);
        holder.movieTitle.setText(topRatedMoviesList.getTitle());
        holder.rating.setText(topRatedMoviesList.getVoteAverage().toString());
        holder.releaseDate.setText(topRatedMoviesList.getReleaseDate());
        holder.textsno.setText("" + (position + 1) + ".");
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + topRatedMoviesList.getPosterPath()).into(holder.imageMovie);

    }

    @Override
    public int getItemCount() {
        return moviesLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView movieTitle, rating, textsno, releaseDate;
        public ImageView imageMovie;


        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListenerRecyclerview.onItemClick(view, getAdapterPosition());
                }
            });
            imageMovie = (ImageView) itemView.findViewById(R.id.imageMovie);
            movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
            rating = (TextView) itemView.findViewById(R.id.rating);
            textsno = (TextView) itemView.findViewById(R.id.textsno);
            releaseDate = (TextView) itemView.findViewById(R.id.releaseDate);


        }

    }
}
