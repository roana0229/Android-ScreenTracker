package app.roana0229.org.screentrackingapp.tracking;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

import app.roana0229.org.screentrackingapp.utility.TrackingLogger;

public class ScreenTrackingApplication extends Application {

    public ScreenTrackingApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ScreenTrackingLifecycleHandler(new ScreenTrackingCallBack() {
            @Override
            public void trackStarted(@NonNull String screenName, @Nullable HashMap<String, Object> parameter) {
                TrackingLogger.getInstance().sendScreen(screenName, parameter);
            }

            @Override
            public void trackEnded(@NonNull String screenName, @Nullable HashMap<String, Object> parameter, long exposureTime) {
                TrackingLogger.getInstance().sendExposure(screenName, exposureTime);
            }
        }));
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        super.registerComponentCallbacks(callback);
    }

    @Override
    public void unregisterComponentCallbacks(ComponentCallbacks callback) {
        super.unregisterComponentCallbacks(callback);
    }

    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.registerActivityLifecycleCallbacks(callback);
    }

    @Override
    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.unregisterActivityLifecycleCallbacks(callback);
    }

    @Override
    public void registerOnProvideAssistDataListener(OnProvideAssistDataListener callback) {
        super.registerOnProvideAssistDataListener(callback);
    }

    @Override
    public void unregisterOnProvideAssistDataListener(OnProvideAssistDataListener callback) {
        super.unregisterOnProvideAssistDataListener(callback);
    }
}
