package app.roana0229.org.screentrackingapp.utility;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.UUID;

import hugo.weaving.DebugLog;

@DebugLog
public class TrackingLogger {

    private static final String TAG = TrackingLogger.class.getSimpleName();

    private static final TrackingLogger instance = new TrackingLogger();
    private String session;
    private int pvCount;
    private String prevScreenName;

    public static TrackingLogger getInstance() {
        return instance;
    }

    private TrackingLogger() {
        init();
    }

    public void init() {
        session = UUID.randomUUID().toString().replaceAll("-", "");
        pvCount = 0;
        prevScreenName = null;
    }

    public void sendScreen(@NonNull String screenName, @Nullable HashMap<String, Object> params) {
        sendScreen(screenName, params, -1);
    }

    public void sendScreen(@NonNull String screenName, @Nullable HashMap<String, Object> params, long exposureTime) {
        pvCount += 1;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prevScreenName != null ? screenLogString(prevScreenName, pvCount - 1) : "");
        stringBuilder.append(" -> ");
        stringBuilder.append(screenLogString(screenName, pvCount));
        if (exposureTime >= 0) {
            stringBuilder.append(screenExposureLogString(exposureTime));
        }
        stringBuilder.append(paramsLogString(params));

        log(stringBuilder.toString());

        prevScreenName = screenName;
    }

    public void sendExposure(@NonNull String screenName, long exposureTime) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(screenLogString(screenName, pvCount));
        stringBuilder.append(screenExposureLogString(exposureTime));
        log(stringBuilder.toString());
    }

    private void log(@NonNull String string) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(">================================================================");
        stringBuilder.append("\n");
        stringBuilder.append(sessionLogString());
        stringBuilder.append("\n");
        stringBuilder.append(string);
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
