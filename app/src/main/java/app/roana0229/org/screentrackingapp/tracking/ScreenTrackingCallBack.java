package app.roana0229.org.screentrackingapp.tracking;


import android.support.annotation.NonNull;

public interface ScreenTrackingCallBack {
    void trackStarted(@NonNull TrackingMarker trackingMarker);

    void trackEnded(@NonNull TrackingMarker trackingMarker, long exposureTime);
}
