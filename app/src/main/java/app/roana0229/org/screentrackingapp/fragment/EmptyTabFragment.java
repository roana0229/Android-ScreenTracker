package app.roana0229.org.screentrackingapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.roana0229.org.screentrackingapp.R;

public class EmptyTabFragment extends Fragment {

    public static EmptyTabFragment newInstance() {
        EmptyTabFragment tabContentFragment = new EmptyTabFragment();
        return tabContentFragment;
    }

    public EmptyTabFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_empty, container, false);
        return view;
    }


}
