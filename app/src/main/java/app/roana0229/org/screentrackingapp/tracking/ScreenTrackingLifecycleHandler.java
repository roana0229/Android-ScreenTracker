package app.roana0229.org.screentrackingapp.tracking;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.HashMap;


public class ScreenTrackingLifecycleHandler extends FragmentManager.FragmentLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    public interface ScreenTrackingCallBack {
        void track(@Nullable String prevScreenName, @NonNull String screenName, @Nullable HashMap<String, Object> parameter, int pvCount, long exposureTime);
    }

    private final static int CHECK_BACKGROUND_TIME = 2000;

    private final ScreenTrackingCallBack callBack;
    private final Handler handler;
    private final Runnable clearBackgroundRunner = new Runnable() {
        @Override
        public void run() {
            SimpleLogger.log(this);
            sentPrevScreenName = null;
        }
    };

    private long activityStartedTime;
    private long fragmentStartedTime;
    private int pvCount;
    private String sentPrevScreenName;

    public ScreenTrackingLifecycleHandler(ScreenTrackingCallBack callBack) {
        this.callBack = callBack;
        HandlerThread handlerThread = new HandlerThread(ScreenTrackingLifecycleHandler.class.getSimpleName() + "Thread");
        handlerThread.start();
        this.handler = new Handler(handlerThread.getLooper());
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
        if (activity instanceof TrackingMarker) {
            SimpleLogger.log(activity);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (activity instanceof TrackingMarker) {
            SimpleLogger.log(activity);
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        handler.removeCallbacks(clearBackgroundRunner);

        if (activity instanceof TrackingMarker) {
            SimpleLogger.log(activity);
            activityStartedTime = System.currentTimeMillis();
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        handler.postDelayed(clearBackgroundRunner, CHECK_BACKGROUND_TIME);

        if (activity instanceof TrackingMarker) {
            SimpleLogger.log(activity);
            long exposureTime = System.currentTimeMillis() - activityStartedTime;
            track((TrackingMarker) activity, exposureTime);
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (activity instanceof TrackingMarker) {
            SimpleLogger.log(activity);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        if (activity instanceof TrackingMarker) {
            SimpleLogger.log(activity);
        }
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (!(activity instanceof AppCompatActivity)) {
            return;
        }

        if (activity instanceof TrackingMarker) {
            SimpleLogger.log(activity);
        }
    }


    // ================================================================
    // FragmentManager.FragmentLifecycleCallbacks
    // ================================================================

    @Override
    public void onFragmentPreAttached(FragmentManager fm, Fragment f, Context context) {
        super.onFragmentPreAttached(fm, f, context);
        if (f instanceof TrackingMarker) {
            SimpleLogger.log(f);
        }
    }

    @Override
    public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
        super.onFragmentAttached(fm, f, context);
        if (f instanceof TrackingMarker) {
            SimpleLogger.log(f);
        }
    }

    @Override
    public void onFragmentPreCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        super.onFragmentPreCreated(fm, f, savedInstanceState);
        if (f instanceof TrackingMarker) {
            SimpleLogger.log(f);
        }
    }

    @Override
    public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        super.onFragmentCreated(fm, f, savedInstanceState);
        if (f instanceof TrackingMarker) {
            SimpleLogger.log(f);
        }
    }

    @Override
    public void onFragmentActivityCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        super.onFragmentActivityCreated(fm, f, savedInstanceState);
        if (f instanceof TrackingMarker) {
            SimpleLogger.log(f);
        }
    }

    @Override
    public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
        super.onFragmentViewCreated(fm, f, v, savedInstanceState);
        if (f instanceof TrackingMarker) {
            SimpleLogger.log(f);
        }
    }

    @Override
    public void onFragmentStarted(FragmentManager fm, Fragment f) {
        super.onFragmentStarted(fm, f);
        if (f instanceof TrackingMarker) {
            SimpleLogger.log(f);
        }
    }

    @Override
    public void onFragmentResumed(FragmentManager fm, Fragment f) {
        super.onFragmentResumed(fm, f);
        if (f instanceof TrackingMarker) {
            SimpleLogger.log(f);
            fragmentStartedTime = System.currentTimeMillis();
        }
    }

    @Override
    public void onFragmentPaused(FragmentManager fm, Fragment f) {
        super.onFragmentPaused(fm, f);
        if (f instanceof TrackingMarker) {
            SimpleLogger.log(f);
            long exposureTime = System.currentTimeMillis() - fragmentStartedTime;
            track((TrackingMarker) f, exposureTime);
        }
    }

    @Override
    public void onFragmentStopped(FragmentManager fm, Fragment f) {
        super.onFragmentStopped(fm, f);
        if (f instanceof TrackingMarker) {
            SimpleLogger.log(f);
        }
    }

    @Override
    public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {
        super.onFragmentSaveInstanceState(fm, f, outState);
        if (f instanceof TrackingMarker) {
            SimpleLogger.log(f);
        }
    }

    @Override
    public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
        super.onFragmentViewDestroyed(fm, f);
        if (f instanceof TrackingMarker) {
            SimpleLogger.log(f);
        }
    }

    @Override
    public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
        if (f instanceof TrackingMarker) {
            SimpleLogger.log(f);
        }
    }

    @Override
    public void onFragmentDetached(FragmentManager fm, Fragment f) {
        super.onFragmentDetached(fm, f);
        if (f instanceof TrackingMarker) {
            SimpleLogger.log(f);
        }
    }

    private void track(TrackingMarker trackingMarker, long exposureTime) {
        pvCount += 1;
        callBack.track(sentPrevScreenName, trackingMarker.getScreenName(), trackingMarker.getScreenParameter(), pvCount, exposureTime);
        sentPrevScreenName = trackingMarker.getScreenName();
    }

}
