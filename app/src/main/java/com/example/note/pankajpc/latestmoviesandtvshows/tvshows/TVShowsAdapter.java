package com.example.note.pankajpc.latestmoviesandtvshows.tvshows;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.note.pankajpc.latestmoviesandtvshows.R;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.TVShows;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Pankaj PC on 01-29-2017.
 */

public class TVShowsAdapter extends RecyclerView.Adapter<TVShowsAdapter.ViewHolder> {
     Context context;
    public View v;
    ViewHolder vh;
    List<TVShows> showsList;
    private OnItemClickListenerRecyclerview mItemClickListenerRecyclerview;

    public TVShowsAdapter(Context context, List<TVShows> showsList) {
        this.context = context;
        this.showsList = showsList;
    }


    public interface OnItemClickListenerRecyclerview {

        public void onItemClick(View v, int position);
    }

    public void setOnItemClickListenerRecyclerview(OnItemClickListenerRecyclerview listenerRecyclerview) {
        mItemClickListenerRecyclerview = listenerRecyclerview;

    }

    @Override
    public TVShowsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.movie_single_row, parent, false);
        vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TVShowsAdapter.ViewHolder holder, int position) {
        TVShows tvShows = showsList.get(position);
        holder.movieTitle.setText(tvShows.getName());
        holder.rating.setText(tvShows.getVoteAverage().toString());
        holder.releaseDate.setText(tvShows.getFirstAirDate());
        holder.textsno.setText("" + (position + 1) + ".");
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + tvShows.getPosterPath()).fitCenter().into(holder.imageMovie);

    }

    @Override
    public int getItemCount() {
        return showsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView movieTitle, rating, textsno, releaseDate;
        public ImageView imageMovie;


        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  EventBus.getDefault().post(showsList.get(getAdapterPosition()));
                    //    mItemClickListenerRecyclerview.onItemClick(view, getAdapterPosition());
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
