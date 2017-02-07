package com.example.note.pankajpc.latestmoviesandtvshows.navigationdrawer;

/**
 * Created by Pankaj PC on 01-28-2017.
 */

public class NavigationDrawerModel {
    public int NavIcon;
    public String NavDescription;

    public NavigationDrawerModel(String navDescription, int navIcon) {
        NavDescription = navDescription;
        NavIcon = navIcon;
    }

    public String getNavDescription() {
        return NavDescription;
    }

    public void setNavDescription(String navDescription) {
        NavDescription = navDescription;
    }

    public int getNavIcon() {
        return NavIcon;
    }

    public void setNavIcon(int navIcon) {
        NavIcon = navIcon;
    }
}
