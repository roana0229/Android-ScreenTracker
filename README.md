# Android-ScreenTracker [ ![Download](https://api.bintray.com/packages/roana0229/Android-Library/Android-ScreenTracker/images/download.svg) ](https://bintray.com/roana0229/Android-Library/Android-ScreenTracker/)

The minimum library to track screen `Activity`, `Fragment`, `ViewPager`.  
Hook screen `onResume`, `onPause`. And hook screen only `ViewPager` `onPageSelected`.  
If you use for iOS, see [iOS-ScreenTracker](https://github.com/roana0229/iOS-ScreenTracker).

![demo](https://raw.githubusercontent.com/roana0229/ScreenTrackingApp/master/demo.gif)

## Usage

#### 1. Import this library

```.gradle
implementation ('org.roana0229.app:screentracker:1.0.+') {
    exclude group: 'com.android.support', module: 'appcompat-v7'
}
```

[Look at the sample code](https://github.com/roana0229/Android-ScreenTracker/blob/master/example/app/build.gradle#L22)

#### 2. Regist `ScreenTrackingCallBack`

##### Track `Activity`, `Fragment`

```.java
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

[Look at the sample code](https://github.com/roana0229/Android-ScreenTracker/blob/master/example/app/src/main/java/app/roana0229/org/android_screentracker_sample/ScreenTrackingApplication.java#L19)

##### Track `ViewPager`

```.java
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

[Look at the sample code](https://github.com/roana0229/Android-ScreenTracker/blob/master/example/app/src/main/java/app/roana0229/org/android_screentracker_sample/activity/HomeActivity.java#L47)

#### 3. Implements `TrackingMarker`

```.java
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

[Look at the sample code](https://github.com/roana0229/Android-ScreenTracker/blob/master/example/app/src/main/java/app/roana0229/org/android_screentracker_sample/activity/SplashActivity.java#L13)

If you are tracking everything, you should write `implements TrackingMarker` in `BaseActivity or BaseFragment`.

## Sample Tracking Logger Utility

[Look at the sample code](https://github.com/roana0229/Android-ScreenTracker/blob/master/example/app/src/main/java/app/roana0229/org/android_screentracker_sample/utility/TrackingLogger.java)
