package app.roana0229.org.screentrackingapp.tracking;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

public interface ScreenTrackingCallBack {
    void trackStarted(@NonNull String screenName, @Nullable HashMap<String, Object> parameter);

    void trackEnded(@NonNull String screenName, @Nullable HashMap<String, Object> parameter, long exposureTime);
}
