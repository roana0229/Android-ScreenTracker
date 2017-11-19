package app.roana0229.org.screentrackingapp;

import android.content.Context;
import android.content.Intent;

import app.roana0229.org.screentrackingapp.activity.CompleteActivity;
import app.roana0229.org.screentrackingapp.activity.DetailActivity;
import app.roana0229.org.screentrackingapp.activity.HomeActivity;
import app.roana0229.org.screentrackingapp.activity.SettingActivity;
import app.roana0229.org.screentrackingapp.model.DummyContent;

public class Navigator {

    private Context context;

    public Navigator(Context context) {
        this.context = context;
    }

    public void showHome() {
        // TODO: Tracking要素を注入する
        Intent intent = HomeActivity.getIntent(context);
        context.startActivity(intent);
    }

    public void showDetail(DummyContent.DummyItem item) {
        // TODO: Tracking要素を注入する
        Intent intent = DetailActivity.getIntent(context, item);
        context.startActivity(intent);
    }

    public void showComplete() {
        // TODO: Tracking要素を注入する
        Intent intent = CompleteActivity.getIntent(context);
        context.startActivity(intent);
    }

    public void showSetting() {
        // TODO: Tracking要素を注入する
        Intent intent = SettingActivity.getIntent(context);
        context.startActivity(intent);
    }
}
