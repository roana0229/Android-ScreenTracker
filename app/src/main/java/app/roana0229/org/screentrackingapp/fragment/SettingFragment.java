package app.roana0229.org.screentrackingapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

import app.roana0229.org.screentrackingapp.R;
import app.roana0229.org.screentrackingapp.adapter.SettingAdapter;
import app.roana0229.org.screentrackingapp.model.SettingContent;
import app.roana0229.org.screentracktrigger.tracking.TrackingMarker;

public class SettingFragment extends Fragment implements TrackingMarker {

    public interface SettingCallbacks {
        void selectedSettingItem(SettingContent.SettingItem settingItem);
    }

    private final static String BUNDLE_KEY_ITEM = "bundle_key_item";

    private SettingContent.SettingItem mItem;

    public static Fragment newInstance(SettingContent.SettingItem item) {
        SettingFragment settingFragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_KEY_ITEM, item);
        settingFragment.setArguments(args);
        return settingFragment;
    }

    public SettingFragment() {
    }

    @Override
    public String getScreenName() {
        return mItem == null ? "設定一覧" : "設定詳細";
    }

    @Override
    public HashMap<String, Object> getScreenParameter() {
        if (mItem == null) {
            return null;
        }

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("setting_content", mItem.content);
        return hashMap;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mItem = (SettingContent.SettingItem) getArguments().getSerializable(BUNDLE_KEY_ITEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        List<SettingContent.SettingItem> list = mItem == null ? SettingContent.ITEMS : SettingContent.DETAIL_ITEM_MAP.get(mItem.id);
        recyclerView.setAdapter(new SettingAdapter(list) {
            @Override
            protected void onItemClick(int position, SettingContent.SettingItem item) {
                if (mItem == null) {
                    ((SettingCallbacks) getActivity()).selectedSettingItem(item);
                } else {
                    ((SettingCallbacks) getActivity()).selectedSettingItem(null);
                }
            }
        });

        return view;
    }

}
