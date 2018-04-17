package app.roana0229.org.android_screentracker_sample.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.HashMap;

import app.roana0229.org.screentracker.TrackingMarker;


public class TutorialDialogFragment extends DialogFragment implements TrackingMarker {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext(), getTheme())
                .setTitle("title")
                .setMessage("Hello DialogFragment")
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }

    @Override
    public String getScreenName() {
        return "購入チュートリアルダイアログ";
    }

    @Override
    public HashMap<String, Object> getScreenParameter() {
        return null;
    }
}
