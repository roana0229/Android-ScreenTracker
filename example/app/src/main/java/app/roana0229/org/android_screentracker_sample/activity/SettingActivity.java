package app.roana0229.org.android_screentracker_sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import app.roana0229.org.android_screentracker_sample.R;
import app.roana0229.org.android_screentracker_sample.fragment.SettingFragment;
import app.roana0229.org.android_screentracker_sample.model.SettingContent;

public class SettingActivity extends AppCompatActivity implements SettingFragment.SettingCallbacks {

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, SettingFragment.newInstance(null), SettingFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void selectedSettingItem(SettingContent.SettingItem settingItem) {
        if (settingItem == null) {
            getSupportFragmentManager().popBackStack(SettingFragment.class.getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, SettingFragment.newInstance(settingItem))
                    .addToBackStack(SettingFragment.class.getSimpleName())
                    .commit();
        }


    }
}
