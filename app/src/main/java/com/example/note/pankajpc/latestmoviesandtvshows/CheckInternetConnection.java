package com.example.note.pankajpc.latestmoviesandtvshows;

import java.io.IOException;

/**
 * Created by Pankaj PC on 02-19-2017.
 */

public class CheckInternetConnection {

    public static boolean checkInternet()
    {
        Boolean internetStatus=false;
        String command = "ping -c 1 google.com";
        try {
            internetStatus = (Runtime.getRuntime().exec (command).waitFor() == 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return internetStatus;
    }
}
