package app.roana0229.org.screentracker;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class ScreenTrackingLifecycleHandler extends FragmentManager.FragmentLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    private final ScreenTrackingCallBack callBack;

    private long activityStartedTime;
    private long fragmentStartedTime;

    public ScreenTrackingLifecycleHandler(ScreenTrackingCallBack callBack) {
        this.callBack = callBack;
    }


    // ================================================================
    // Application.ActivityLifecycleCallbacks
    // ================================================================

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (!(activity instanceof AppCompatActivity)) {
            return;
        }

        ((AppCompatActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(this, true);
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (activity instanceof TrackingMarker) {
            activityStartedTime = System.currentTimeMillis();
            callBack.trackStarted((TrackingMarker) activity);
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        if (activity instanceof TrackingMarker) {
            long exposureTime = System.currentTimeMillis() - activityStartedTime;
            callBack.trackEnded((TrackingMarker) activity, exposureTime);
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (!(activity instanceof AppCompatActivity)) {
            return;
        }

        ((AppCompatActivity) activity).getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(this);
    }


    // ================================================================
    // FragmentManager.FragmentLifecycleCallbacks
    // ================================================================

    @Override
    public void onFragmentPreAttached(FragmentManager fm, Fragment f, Context context) {
        super.onFragmentPreAttached(fm, f, context);
    }

    @Override
    public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
        super.onFragmentAttached(fm, f, context);
    }

    @Override
    public void onFragmentPreCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        super.onFragmentPreCreated(fm, f, savedInstanceState);
    }

    @Override
    public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        super.onFragmentCreated(fm, f, savedInstanceState);
    }

    @Override
    public void onFragmentActivityCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        super.onFragmentActivityCreated(fm, f, savedInstanceState);
    }

    @Override
    public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
        super.onFragmentViewCreated(fm, f, v, savedInstanceState);
    }

    @Override
    public void onFragmentStarted(FragmentManager fm, Fragment f) {
        super.onFragmentStarted(fm, f);
    }

    @Override
    public void onFragmentResumed(FragmentManager fm, Fragment f) {
        super.onFragmentResumed(fm, f);
        if (f instanceof TrackingMarker) {
            if (!(f instanceof DialogFragment)) {
                if (f.getView() == null || f.getView().getParent() == null) {
                    return;
                }

                if (f.getView().getParent() instanceof ViewPager) {
                    ViewPager viewPager = (ViewPager) f.getView().getParent();
                    if (!(viewPager instanceof TrackingViewPager)) {
                        return;
                    }
                    if (viewPager.indexOfChild(f.getView()) != viewPager.getCurrentItem()) {
                        return;
                    }

                    ((TrackingViewPager) viewPager).resume();
                }
            }

            fragmentStartedTime = System.currentTimeMillis();
            callBack.trackStarted((TrackingMarker) f);
        }
    }

    @Override
    public void onFragmentPaused(FragmentManager fm, Fragment f) {
        super.onFragmentPaused(fm, f);
        if (f instanceof TrackingMarker) {
            if (!(f instanceof DialogFragment)) {
                if (f.getView() == null || f.getView().getParent() == null) {
                    return;
                }

                if (f.getView().getParent() instanceof ViewPager) {
                    ViewPager viewPager = (ViewPager) f.getView().getParent();
                    if (!(viewPager instanceof TrackingViewPager)) {
                        return;
                    }
                    if (viewPager.indexOfChild(f.getView()) != viewPager.getCurrentItem()) {
                        return;
                    }
                }
            }

            long exposureTime = System.currentTimeMillis() - fragmentStartedTime;
            callBack.trackEnded((TrackingMarker) f, exposureTime);
        }
    }

    @Override
    public void onFragmentStopped(FragmentManager fm, Fragment f) {
        super.onFragmentStopped(fm, f);
    }

    @Override
    public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {
        super.onFragmentSaveInstanceState(fm, f, outState);
    }

    @Override
    public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
        super.onFragmentViewDestroyed(fm, f);
    }

    @Override
    public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
    }

    @Override
    public void onFragmentDetached(FragmentManager fm, Fragment f) {
        super.onFragmentDetached(fm, f);
    }

}
