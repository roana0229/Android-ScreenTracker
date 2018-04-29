package app.roana0229.org.android_screentracker_sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

import app.roana0229.org.android_screentracker_sample.Navigator;
import app.roana0229.org.android_screentracker_sample.R;
import app.roana0229.org.screentracker.TrackingMarker;

public class CompleteActivity extends AppCompatActivity implements TrackingMarker {

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, CompleteActivity.class);
        return intent;
    }

    @Override
    public String getScreenName() {
        return this.getClass().getSimpleName();
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
