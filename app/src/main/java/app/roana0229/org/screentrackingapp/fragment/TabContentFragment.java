package app.roana0229.org.screentrackingapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.roana0229.org.screentrackingapp.model.DummyContent;
import app.roana0229.org.screentrackingapp.model.DummyContent.DummyItem;
import app.roana0229.org.screentrackingapp.adapter.ItemAdapter;
import app.roana0229.org.screentrackingapp.Navigator;
import app.roana0229.org.screentrackingapp.R;

public class TabContentFragment extends Fragment {

    private final static String BUNDLE_KEY_SECTION = "bundle_key_section";

    public static TabContentFragment newInstance(int section) {
        TabContentFragment tabContentFragment = new TabContentFragment();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_KEY_SECTION, section);
        tabContentFragment.setArguments(args);
        return tabContentFragment;
    }

    public TabContentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_content, container, false);

        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(String.format("Section %d", getArguments().getInt(BUNDLE_KEY_SECTION)));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new ItemAdapter(DummyContent.ITEMS) {
            @Override
            protected void onItemClick(int position, DummyItem item) {
                new Navigator(getContext()).showDetail(item);
            }
        });
        return view;
    }


}
