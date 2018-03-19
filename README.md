# ScreenTracker

`Activity`, `Fragment`, `ViewPager` track library

![demo](https://raw.githubusercontent.com/roana0229/ScreenTrackingApp/master/demo.gif)

## Usage

#### 1. Import this library

Project Structure > + icon > Import .JAR/.AAR Package > Select [screen-track-trigger-1.0.aar](https://raw.githubusercontent.com/roana0229/ScreenTrackingApp/master/screen-track-trigger-1.0.aar) file


#### 2. Regist `ScreenTrackingCallBack`

Track `Activity`, `Fragment`

```
public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ScreenTrackingLifecycleHandler(new ScreenTrackingCallBack() {
            @Override
            public void trackStarted(@NonNull TrackingMarker trackingMarker) {
                  // resume screen
            }

            @Override
            public void trackEnded(@NonNull TrackingMarker trackingMarker, long exposureTime) {
                  // pause screen
            }
        }));
    }

}
```

Track `ViewPager`

```
public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        TrackingViewPager viewPager = (TrackingViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(/* only FragmentPagerAdapter */);
        viewPager.setTrackingCallBack(new ScreenTrackingCallBack() {
            @Override
            public void trackStarted(@NonNull TrackingMarker trackingMarker) {
                  // resume screen
            }

            @Override
            public void trackEnded(@NonNull TrackingMarker trackingMarker, long exposureTime) {
                  // pause screen
            }
        });
    }

}
```

#### 3. Implements `TrackingMarker`

```
public class SampleActivity extends AppCompatActivity implements TrackingMarker {

    @Override
    public String getScreenName() {
        return "Sample Screen";
    }

    @Override
    public HashMap<String, Object> getScreenParameter() {
        return null;
    }

}
```
