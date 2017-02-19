package com.example.note.pankajpc.latestmoviesandtvshows;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Pankaj PC on 02-19-2017.
 */

public class NoInternetDialog extends DialogFragment {

    DialogClickEvent dce;

    public interface DialogClickEvent{

         void onPositiveButtonClick();
         void onNegativeButtonClick();

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        dce = (DialogClickEvent) getActivity();
        builder.setCancelable(false);
        builder.setMessage("No Internet Connection").setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dce.onPositiveButtonClick();

            }
        });
        return builder.create();
    }
}
