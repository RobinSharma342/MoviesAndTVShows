package com.example.note.pankajpc.latestmoviesandtvshows;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pankaj PC on 01-28-2017.
 */

public class NavigationDrawerAdapter extends ArrayAdapter<NavigationDrawerModel> {
    Context context;
    List<NavigationDrawerModel> navigationDrawerModels;
    int layoutResource;

    public NavigationDrawerAdapter(Context context, int resource, List<NavigationDrawerModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResource = resource;
        this.navigationDrawerModels = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DrawerItemHolder drawerHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DrawerItemHolder();
            view = inflater.inflate(layoutResource, parent, false);
            drawerHolder.ItemName = (TextView) view.findViewById(R.id.drawer_itemName);
            drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);
            view.setTag(drawerHolder);
        } else {
            drawerHolder = (DrawerItemHolder) view.getTag();
        }

        NavigationDrawerModel dItem = navigationDrawerModels.get(position);
        drawerHolder.icon.setImageResource(dItem.getNavIcon());
        drawerHolder.ItemName.setText(dItem.getNavDescription());

        return view;
    }

    private static class DrawerItemHolder {
        TextView ItemName;
        ImageView icon;
    }

    }
