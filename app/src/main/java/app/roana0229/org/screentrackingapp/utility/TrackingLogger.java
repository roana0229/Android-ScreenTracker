package app.roana0229.org.screentrackingapp.utility;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.UUID;

import app.roana0229.org.screentracktrigger.tracking.ScreenTrackingCallBack;
import app.roana0229.org.screentracktrigger.tracking.TrackingMarker;


public class TrackingLogger {

    private static final String TAG = TrackingLogger.class.getSimpleName();
    private static final TrackingLogger instance = new TrackingLogger();

    private final ScreenTrackingCallBack callBack = new ScreenTrackingCallBack() {
        @Override
        public void trackStarted(@NonNull TrackingMarker trackingMarker) {
            TrackingLogger.getInstance().sendScreen(trackingMarker);
        }

        @Override
        public void trackEnded(@NonNull TrackingMarker trackingMarker, long exposureTime) {
            TrackingLogger.getInstance().sendExposure(trackingMarker, exposureTime);
        }
    };

    private String session;
    private int pvCount;
    private TrackingInfo prevTrackingInfo;

    public static TrackingLogger getInstance() {
        return instance;
    }

    private TrackingLogger() {
        init();
    }

    private void init() {
        session = UUID.randomUUID().toString().replaceAll("-", "");
        pvCount = 0;
        prevTrackingInfo = null;
    }

    public ScreenTrackingCallBack getCallBack() {
        return callBack;
    }

    public void sendScreen(@NonNull TrackingMarker trackingMarker) {
        sendScreen(trackingMarker, -1);
    }

    public void sendScreen(@NonNull TrackingMarker trackingMarker, long exposureTime) {
        pvCount += 1;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(">================================================================\n");
        stringBuilder.append(sessionLogString());
        stringBuilder.append("\n");

        stringBuilder.append(prevTrackingInfo != null ? screenLogString(prevTrackingInfo.getTrackingMarker().getScreenName(), prevTrackingInfo.getPvCount()) : "");
        stringBuilder.append(" -> ");
        stringBuilder.append(screenLogString(trackingMarker.getScreenName(), pvCount));
        if (exposureTime >= 0) {
            stringBuilder.append(screenExposureLogString(exposureTime));
        }
        stringBuilder.append(paramsLogString(trackingMarker.getScreenParameter()));

        Log.i(TAG, stringBuilder.toString());

        if (isStackTrackingMarker(trackingMarker)) {
            prevTrackingInfo = new TrackingInfo(trackingMarker, pvCount);
        }
    }

    public void sendExposure(@NonNull TrackingMarker trackingMarker, long exposureTime) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(screenLogString(trackingMarker.getScreenName(), pvCount));
        stringBuilder.append(screenExposureLogString(exposureTime));

        stringBuilder.append("\n<================================================================");
        Log.i(TAG, stringBuilder.toString());
    }

    private boolean isStackTrackingMarker(TrackingMarker trackingMarker) {
        if (trackingMarker instanceof DialogFragment) {
            return false;
        }
        return true;
    }

    private static class TrackingInfo {

        private final TrackingMarker trackingMarker;
        private final int pvCount;

        private TrackingInfo(TrackingMarker trackingMarker, int pvCount) {
            this.trackingMarker = trackingMarker;
            this.pvCount = pvCount;
        }

        private TrackingMarker getTrackingMarker() {
            return trackingMarker;
        }

        private int getPvCount() {
            return pvCount;
        }
    }


    // ================================================================
    // Pretty String Method
    // ================================================================

    private String sessionLogString() {
        return String.format("session: %s", session);
    }

    private String screenLogString(@NonNull String screenName, int pvCount) {
        return String.format("%s(pv:%d)", screenName, pvCount);
    }

    private String screenExposureLogString(long exposureTime) {
        return String.format(" exposureTime: %dms", exposureTime);
    }

    private String paramsLogString(@Nullable HashMap<String, Object> hashMap) {
        if (hashMap != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("\n");
                stringBuilder.append(String.format("params:%s", new JSONObject(hashMap).toString(2)));
                return stringBuilder.toString();
            } catch (JSONException e) {
                Log.e(TAG, "Tracking Params ParseError", e);
            }
        }
        return "";
    }

}
