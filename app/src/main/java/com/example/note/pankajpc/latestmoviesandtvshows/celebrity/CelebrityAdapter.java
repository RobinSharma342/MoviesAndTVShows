package com.example.note.pankajpc.latestmoviesandtvshows.celebrity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.note.pankajpc.latestmoviesandtvshows.R;
import com.example.note.pankajpc.latestmoviesandtvshows.pojo.Celebrity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Pankaj PC on 01-29-2017.
 */

public class CelebrityAdapter extends RecyclerView.Adapter<CelebrityAdapter.ViewHolder> {
    Context context;
    public View v;
    ViewHolder vh;
    List<Celebrity> celebrityList;

    public CelebrityAdapter(Context context, List<Celebrity> celebrityList) {
        this.context = context;
        this.celebrityList = celebrityList;
    }


    @Override
    public CelebrityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.celebrity_single_row, parent, false);
        vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CelebrityAdapter.ViewHolder holder, int position) {
        Celebrity celeb = celebrityList.get(position);
        holder.celebName.setText(celeb.getName());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + celeb.getProfilePath()).into(holder.celebImage);

    }

    @Override
    public int getItemCount() {
        return celebrityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView celebName;
        public ImageView celebImage;


        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().post(celebrityList.get(getAdapterPosition()));
                }
            });

            celebName = (TextView) itemView.findViewById(R.id.celebrityName);
            celebImage = (ImageView) itemView.findViewById(R.id.celebrityImage);


        }

    }
}
