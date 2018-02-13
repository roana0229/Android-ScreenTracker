package app.roana0229.org.screentrackingapp.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

import app.roana0229.org.screentrackingapp.Navigator;
import app.roana0229.org.screentrackingapp.R;
import app.roana0229.org.screentrackingapp.tracking.TrackingMarker;

public class SplashActivity extends AppCompatActivity implements TrackingMarker {

    @Override
    public String getScreenName() {
        return "スプラッシュ";
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
