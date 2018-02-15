package app.roana0229.org.screentrackingapp.tracking;

import java.util.HashMap;

public interface TrackingMarker {
    String getScreenName();

    HashMap<String, Object> getScreenParameter();
}
