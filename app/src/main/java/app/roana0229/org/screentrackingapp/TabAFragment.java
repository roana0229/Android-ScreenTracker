package app.roana0229.org.screentrackingapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabAFragment extends Fragment {

    public TabAFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_a, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        TrackingLogger.getInstance().sendScreen(Screen.TAB_A);
    }
}
