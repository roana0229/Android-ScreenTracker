package app.roana0229.org.screentrackingapp.utility;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.UUID;

import app.roana0229.org.screentrackingapp.tracking.TrackingMarker;


public class TrackingLogger {

    private static final String TAG = TrackingLogger.class.getSimpleName();

    private static final TrackingLogger instance = new TrackingLogger();
    private String session;
    private int pvCount;
    private int prePVCount;
    private String prevScreenName;

    public static TrackingLogger getInstance() {
        return instance;
    }

    private TrackingLogger() {
        init();
    }

    private void init() {
        session = UUID.randomUUID().toString().replaceAll("-", "");
        pvCount = 0;
        prevScreenName = null;
    }

    public void sendScreen(@NonNull TrackingMarker trackingMarker) {
        sendScreen(trackingMarker, -1);
    }

    public void sendScreen(@NonNull TrackingMarker trackingMarker, long exposureTime) {
        pvCount += 1;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(">================================================================");
        stringBuilder.append("\n");
        stringBuilder.append(sessionLogString());
        stringBuilder.append("\n");


        stringBuilder.append(prevScreenName != null ? screenLogString(prevScreenName, prePVCount) : "");
        stringBuilder.append(" -> ");
        stringBuilder.append(screenLogString(trackingMarker.getScreenName(), pvCount));
        if (exposureTime >= 0) {
            stringBuilder.append(screenExposureLogString(exposureTime));
        }
        stringBuilder.append(paramsLogString(trackingMarker.getScreenParameter()));


        Log.i(TAG, stringBuilder.toString());

        if (!(trackingMarker instanceof DialogFragment)) {
            prePVCount = pvCount;
            prevScreenName = trackingMarker.getScreenName();
        }
    }

    public void sendExposure(@NonNull TrackingMarker trackingMarker, long exposureTime) {
        StringBuilder stringBuilder = new StringBuilder();


        stringBuilder.append(screenLogString(trackingMarker.getScreenName(), pvCount));
        stringBuilder.append(screenExposureLogString(exposureTime));


        stringBuilder.append("\n");
        stringBuilder.append("<================================================================");
        Log.i(TAG, stringBuilder.toString());
    }

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
