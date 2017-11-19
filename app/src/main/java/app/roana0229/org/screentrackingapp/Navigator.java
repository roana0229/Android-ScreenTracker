package app.roana0229.org.screentrackingapp;

import android.content.Context;
import android.content.Intent;

public class Navigator {

    private Context context;

    public Navigator(Context context) {
        this.context = context;
    }

    public void showHome() {
        Intent intent = HomeActivity.getIntent(context);
        context.startActivity(intent);
    }
}
