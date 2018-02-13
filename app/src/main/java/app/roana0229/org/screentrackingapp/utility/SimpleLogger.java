package app.roana0229.org.screentrackingapp.utility;

import android.util.Log;

public class SimpleLogger {

    private static final String TAG = SimpleLogger.class.getSimpleName();

    public static void log(Object o) {
        StackTraceElement[] stack = new Throwable().getStackTrace();
        String method = stack[1].getMethodName();
        int line = stack[1].getLineNumber();
        Log.d(TAG, String.format("L:%d:%s %s", line, method, o.getClass().getSimpleName()));
    }

}
