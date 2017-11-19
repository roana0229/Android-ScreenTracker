package app.roana0229.org.screentrackingapp.tracking;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class TrackingLogger {

    private static final String TAG = TrackingLogger.class.getSimpleName();

    private static final TrackingLogger instance = new TrackingLogger();
    private int pvCount;
    private String session;
    private Screen sentOneBeforeScreen;

    public static TrackingLogger getInstance() {
        return instance;
    }

    private TrackingLogger() {
        pvCount = 0;
        session = UUID.randomUUID().toString().replaceAll("-", "");
    }

    public void sendScreen(@NonNull Screen screen) {
        sendScreen(screen, sentOneBeforeScreen, null);
    }

    public void sendScreen(@NonNull Screen screen, @Nullable Screen beforeScreen) {
        sendScreen(screen, beforeScreen, null);
    }

    // TODO: Screenをこのフレームワークで持たないようにするために、interfaceで受け取る
    public void sendScreen(@NonNull Screen screen, @Nullable Screen beforeScreen, @Nullable JSONObject params) {
        pvCount += 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(beforeScreen != null ? screenLogString(beforeScreen, pvCount - 1) : "null");
        stringBuilder.append(" -> ");
        stringBuilder.append(screenLogString(screen, pvCount));
        stringBuilder.append(paramsLogString(params));

        log(stringBuilder.toString());

        sentOneBeforeScreen = screen;
    }

    public void sendEvent(@NonNull Screen screen, @NonNull Event event) {
        sendEvent(screen, event, null);
    }

    // TODO: Eventをこのフレームワークで持たないようにするために、interfaceで受け取る
    public void sendEvent(@NonNull Screen screen, @NonNull Event event, @Nullable JSONObject params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(eventLogString(screen, event));
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

    private String screenLogString(@NonNull Screen screen, int pvCount) {
        return String.format("%s(pv:%d)", screen.name(), pvCount);
    }

    private String eventLogString(@NonNull Screen screen, @NonNull Event event) {
        return String.format("%s(event:%s)", screen.name(), event.name());
    }

    private String paramsLogString(@Nullable JSONObject json) {
        if (json != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("\n");
                stringBuilder.append(String.format("params:%s", json.toString(2)));
                return stringBuilder.toString();
            } catch (JSONException e) {
                Log.e(TAG, "Tracking Params ParseError", e);
            }
        }
        return "";
    }

}
