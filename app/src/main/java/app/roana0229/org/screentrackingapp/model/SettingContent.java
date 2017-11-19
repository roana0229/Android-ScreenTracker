package app.roana0229.org.screentrackingapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingContent {

    public static final List<SettingItem> ITEMS = new ArrayList<>();
    public static final Map<String, List<SettingItem>> DETAIL_ITEM_MAP = new HashMap();

    private static final int COUNT = 5;
    private static final int DETAIL_COUNT = 3;

    static {
        for (int i = 1; i <= COUNT; i++) {
            ITEMS.add(createDummyItem(String.valueOf(i)));
            List<SettingItem> list = new ArrayList<>();
            for (int j = 1; j <= DETAIL_COUNT; j++) {
                list.add(createDummyItem(String.valueOf(i) + " - " + String.valueOf(j)));
            }
            DETAIL_ITEM_MAP.put(String.valueOf(i), list);
        }
    }

    private static SettingItem createDummyItem(String content) {
        return new SettingItem(content, "Config " + content);
    }

    public static class SettingItem implements Serializable {
        public final String id;
        public final String content;

        public SettingItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
