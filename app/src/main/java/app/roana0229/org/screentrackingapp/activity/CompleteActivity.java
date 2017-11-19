package app.roana0229.org.screentrackingapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import app.roana0229.org.screentrackingapp.Navigator;
import app.roana0229.org.screentrackingapp.R;

public class CompleteActivity extends AppCompatActivity {

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, CompleteActivity.class);
        return intent;
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

    @Override
    public void onBackPressed() {
    }
}
