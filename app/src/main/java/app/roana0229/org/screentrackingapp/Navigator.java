package app.roana0229.org.screentrackingapp;

import android.content.Context;
import android.content.Intent;

import app.roana0229.org.screentrackingapp.activity.CompleteActivity;
import app.roana0229.org.screentrackingapp.activity.DetailActivity;
import app.roana0229.org.screentrackingapp.activity.HomeActivity;
import app.roana0229.org.screentrackingapp.activity.SettingActivity;
import app.roana0229.org.screentrackingapp.model.DummyContent;

public class Navigator {

    private final Context context;

    public Navigator(Context context) {
        this.context = context;
    }

    public void showHome() {
        Intent intent = HomeActivity.getIntent(context);
        context.startActivity(intent);
    }

    public void showDetail(DummyContent.DummyItem item) {
        Intent intent = DetailActivity.getIntent(context, item);
        context.startActivity(intent);
    }

    public void showComplete() {
        Intent intent = CompleteActivity.getIntent(context);
        context.startActivity(intent);
    }

    public void showSetting() {
        Intent intent = SettingActivity.getIntent(context);
        context.startActivity(intent);
    }
}
