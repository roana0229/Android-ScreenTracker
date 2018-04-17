package app.roana0229.org.android_screentracker_sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import app.roana0229.org.android_screentracker_sample.Navigator;
import app.roana0229.org.android_screentracker_sample.R;
import app.roana0229.org.android_screentracker_sample.dialog.TutorialDialogFragment;
import app.roana0229.org.android_screentracker_sample.fragment.EmptyTabFragment;
import app.roana0229.org.android_screentracker_sample.fragment.TabFragment;
import app.roana0229.org.android_screentracker_sample.utility.TrackingLogger;
import app.roana0229.org.screentracker.TrackingViewPager;

public class HomeActivity extends AppCompatActivity {

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private TrackingViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        TrackingViewPager mViewPager = (TrackingViewPager) findViewById(R.id.container);
        mViewPager.setOffscreenPageLimit(mSectionsPagerAdapter.getCount());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setTrackingCallBack(TrackingLogger.getInstance().getCallBack());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                new Navigator(this).showSetting();
                return true;
            case R.id.action_dialog:
                new TutorialDialogFragment().show(getSupportFragmentManager(), "");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position < getCount() - 1) {
                return TabFragment.newInstance(position);
            } else {
                return EmptyTabFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
