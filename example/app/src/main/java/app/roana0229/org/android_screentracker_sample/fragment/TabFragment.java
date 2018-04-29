package app.roana0229.org.android_screentracker_sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

import app.roana0229.org.android_screentracker_sample.Navigator;
import app.roana0229.org.android_screentracker_sample.R;
import app.roana0229.org.android_screentracker_sample.adapter.ItemAdapter;
import app.roana0229.org.android_screentracker_sample.model.DummyContent;
import app.roana0229.org.screentracker.TrackingMarker;

public class TabFragment extends Fragment implements TrackingMarker {

    private final static String BUNDLE_KEY_SECTION = "bundle_key_section";

    private int section;

    public static TabFragment newInstance(int section) {
        TabFragment tabContentFragment = new TabFragment();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_KEY_SECTION, section);
        tabContentFragment.setArguments(args);
        return tabContentFragment;
    }

    public TabFragment() {
    }

    @Override
    public String getScreenName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public HashMap<String, Object> getScreenParameter() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("tab_index", section);
        return hashMap;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            section = getArguments().getInt(BUNDLE_KEY_SECTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);

        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(String.format("Section %d", getArguments().getInt(BUNDLE_KEY_SECTION)));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new ItemAdapter(DummyContent.ITEMS) {
            @Override
            protected void onItemClick(int position, DummyContent.DummyItem item) {
                new Navigator(getContext()).showDetail(item);
            }
        });
        return view;
    }


}
