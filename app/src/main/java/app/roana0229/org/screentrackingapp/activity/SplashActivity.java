package app.roana0229.org.screentrackingapp.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.roana0229.org.screentrackingapp.Navigator;
import app.roana0229.org.screentrackingapp.R;
import app.roana0229.org.screentrackingapp.tracking.Screen;
import app.roana0229.org.screentrackingapp.tracking.TrackingLogger;

public class SplashActivity extends AppCompatActivity {

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

    @Override
    protected void onResume() {
        super.onResume();
        TrackingLogger.getInstance().sendScreen(Screen.SPLASH);
    }
}
