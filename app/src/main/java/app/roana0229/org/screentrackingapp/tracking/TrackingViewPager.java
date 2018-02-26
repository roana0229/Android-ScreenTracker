package app.roana0229.org.screentrackingapp.tracking;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;


public class TrackingViewPager extends ViewPager {

    private ScreenTrackingCallBack callBack;
    private long fragmentStartedTime;
    private int prevPosition;

    public TrackingViewPager(Context context) {
        this(context, null);
    }

    public TrackingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);
                initTracking();
                return true;
            }
        });
    }

    public void setTrackingCallBack(ScreenTrackingCallBack callBack) {
        this.callBack = callBack;
    }

    public void resume() {
        fragmentStartedTime = System.currentTimeMillis();
        prevPosition = getCurrentItem();
    }

    private void initTracking() {
        resume();
        addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                trackEnded();
                resume();
                trackStarted();
            }
        });
    }

    private void trackStarted() {
        Fragment fragment = getFragment(prevPosition);
        if (fragment == null || !(fragment instanceof TrackingMarker)) {
            return;
        }

        if (callBack != null) {
            TrackingMarker trackingMarker = (TrackingMarker) fragment;
            callBack.trackStarted(trackingMarker);
        }
    }

    private void trackEnded() {
        Fragment fragment = getFragment(prevPosition);
        if (fragment == null || !(fragment instanceof TrackingMarker)) {
            return;
        }

        long exposureTime = System.currentTimeMillis() - fragmentStartedTime;
        if (callBack != null) {
            TrackingMarker trackingMarker = (TrackingMarker) fragment;
            callBack.trackEnded(trackingMarker, exposureTime);
        }
    }

    private Fragment getFragment(int index) {
        FragmentManager supportFragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
        return supportFragmentManager.findFragmentByTag("android:switcher:" + getId() + ":" + index);
    }

}
