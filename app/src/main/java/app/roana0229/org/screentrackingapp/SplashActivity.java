package app.roana0229.org.screentrackingapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Navigator(SplashActivity.this).showHome();
            }
        }, 1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TrackingLogger.getInstance().sendScreen(Screen.SPLASH);
    }
}
