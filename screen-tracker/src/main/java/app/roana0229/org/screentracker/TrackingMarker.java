package app.roana0229.org.screentracker;

import java.util.HashMap;

public interface TrackingMarker {
    String getScreenName();

    HashMap<String, Object> getScreenParameter();
}
