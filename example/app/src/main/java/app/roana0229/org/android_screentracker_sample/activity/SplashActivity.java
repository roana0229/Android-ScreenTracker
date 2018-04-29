package app.roana0229.org.android_screentracker_sample.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

import app.roana0229.org.android_screentracker_sample.Navigator;
import app.roana0229.org.android_screentracker_sample.R;
import app.roana0229.org.screentracker.TrackingMarker;

public class SplashActivity extends AppCompatActivity implements TrackingMarker {

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
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Navigator(SplashActivity.this).showHome();
            }
        }, 2000);
    }

}
