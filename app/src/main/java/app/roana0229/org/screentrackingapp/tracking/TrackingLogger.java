package app.roana0229.org.screentrackingapp.tracking;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.UUID;

public class TrackingLogger {

    private static final String TAG = TrackingLogger.class.getSimpleName();

    private static final TrackingLogger instance = new TrackingLogger();
    private int pvCount;
    private String session;
    private String sentOneBeforeScreenName;

    public static TrackingLogger getInstance() {
        return instance;
    }

    private TrackingLogger() {
        pvCount = 0;
        session = UUID.randomUUID().toString().replaceAll("-", "");
    }

    public void sendScreen(@NonNull String screenName) {
        sendScreen(screenName, null);
    }

    // TODO: Screenをこのフレームワークで持たないようにするために、interfaceで受け取る
    public void sendScreen(@NonNull String screenName, @Nullable HashMap<String, Object> params) {
        pvCount += 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(sentOneBeforeScreenName != null ? screenLogString(sentOneBeforeScreenName, pvCount - 1) : "");
        stringBuilder.append(" -> ");
        stringBuilder.append(screenLogString(screenName, pvCount));
        stringBuilder.append(paramsLogString(params));

        log(stringBuilder.toString());

        sentOneBeforeScreenName = screenName;
    }

    public void sendEvent(@NonNull String screenName, @NonNull Event event) {
        sendEvent(screenName, event, null);
    }

    // TODO: Eventをこのフレームワークで持たないようにするために、interfaceで受け取る
    public void sendEvent(@NonNull String screenName, @NonNull Event event, @Nullable HashMap<String, Object> params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(eventLogString(screenName, event));
        stringBuilder.append(paramsLogString(params));
        log(stringBuilder.toString());
    }

    private void log(@NonNull String string) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(">");
        stringBuilder.append(divide());
        stringBuilder.append("\n");
        stringBuilder.append(sessionLogString());
        stringBuilder.append("\n");
        stringBuilder.append(string);
        stringBuilder.append("\n");
        stringBuilder.append("<");
        stringBuilder.append(divide());
        Log.i(TAG, stringBuilder.toString());
    }

    private String divide() {
        return "================================================================";
    }

    private String sessionLogString() {
        return String.format("session: %s", session);
    }

    private String screenLogString(@NonNull String screenName, int pvCount) {
        return String.format("%s(pv:%d)", screenName, pvCount);
    }

    private String eventLogString(@NonNull String screenName, @NonNull Event event) {
        return String.format("%s(event:%s)", screenName, event.name());
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
