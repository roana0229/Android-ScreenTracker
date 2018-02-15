package app.roana0229.org.screentrackingapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

import app.roana0229.org.screentrackingapp.Navigator;
import app.roana0229.org.screentrackingapp.R;
import app.roana0229.org.screentrackingapp.tracking.TrackingMarker;

public class CompleteActivity extends AppCompatActivity implements TrackingMarker {

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, CompleteActivity.class);
        return intent;
    }

    @Override
    public String getScreenName() {
        return "完了";
    }

    @Override
    public HashMap<String, Object> getScreenParameter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Navigator(CompleteActivity.this).showHome();
            }
        }, 2000);
    }

}
