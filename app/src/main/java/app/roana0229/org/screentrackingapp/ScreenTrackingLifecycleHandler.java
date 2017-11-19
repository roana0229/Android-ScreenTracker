package app.roana0229.org.screentrackingapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


public class ScreenTrackingLifecycleHandler extends FragmentManager.FragmentLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = ScreenTrackingLifecycleHandler.class.getSimpleName();

    // ================================================================
    // Application.ActivityLifecycleCallbacks
    // ================================================================

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        ((AppCompatActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(this, true);
        log(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        log(activity);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        log(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        log(activity);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        log(activity);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        log(activity);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ((AppCompatActivity) activity).getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(this);
        log(activity);
    }

    // ================================================================
    // FragmentManager.FragmentLifecycleCallbacks
    // ================================================================


    @Override
    public void onFragmentPreAttached(FragmentManager fm, Fragment f, Context context) {
        super.onFragmentPreAttached(fm, f, context);
        log(f);
    }

    @Override
    public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
        super.onFragmentAttached(fm, f, context);
        log(f);
    }

    @Override
    public void onFragmentPreCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        super.onFragmentPreCreated(fm, f, savedInstanceState);
        log(f);
    }

    @Override
    public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        super.onFragmentCreated(fm, f, savedInstanceState);
        log(f);
    }

    @Override
    public void onFragmentActivityCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        super.onFragmentActivityCreated(fm, f, savedInstanceState);
        log(f);
    }

    @Override
    public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
        super.onFragmentViewCreated(fm, f, v, savedInstanceState);
        log(f);
    }

    @Override
    public void onFragmentStarted(FragmentManager fm, Fragment f) {
        super.onFragmentStarted(fm, f);
        log(f);
    }

    @Override
    public void onFragmentResumed(FragmentManager fm, Fragment f) {
        super.onFragmentResumed(fm, f);
        log(f);
    }

    @Override
    public void onFragmentPaused(FragmentManager fm, Fragment f) {
        super.onFragmentPaused(fm, f);
        log(f);
    }

    @Override
    public void onFragmentStopped(FragmentManager fm, Fragment f) {
        super.onFragmentStopped(fm, f);
        log(f);
    }

    @Override
    public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {
        super.onFragmentSaveInstanceState(fm, f, outState);
        log(f);
    }

    @Override
    public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
        super.onFragmentViewDestroyed(fm, f);
        log(f);
    }

    @Override
    public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
        super.onFragmentDestroyed(fm, f);
        log(f);
    }

    @Override
    public void onFragmentDetached(FragmentManager fm, Fragment f) {
        super.onFragmentDetached(fm, f);
        log(f);
    }

    private void log(Activity activity) {
        StackTraceElement[] stack = new Throwable().getStackTrace();
        String method = stack[1].getMethodName();
        int line = stack[1].getLineNumber();
        Log.d(TAG, String.format("L:%d:%s %s", line, method, activity.getClass().getSimpleName()));
    }

    private void log(Fragment fragment) {
        StackTraceElement[] stack = new Throwable().getStackTrace();
        String method = stack[1].getMethodName();
        int line = stack[1].getLineNumber();
        Log.d(TAG, String.format("L:%d:%s %s", line, method, fragment.getClass().getSimpleName()));
    }

}
