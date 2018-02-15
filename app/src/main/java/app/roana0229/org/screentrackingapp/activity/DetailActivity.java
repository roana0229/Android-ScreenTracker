package app.roana0229.org.screentrackingapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import app.roana0229.org.screentrackingapp.R;
import app.roana0229.org.screentrackingapp.fragment.DetailFragment;
import app.roana0229.org.screentrackingapp.model.DummyContent;

public class DetailActivity extends AppCompatActivity {

    private static final String BUNDLE_KEY_ITEM = "bundle_key_item";

    public static Intent getIntent(Context context, DummyContent.DummyItem item) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(BUNDLE_KEY_ITEM, item);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, DetailFragment.newInstance((DummyContent.DummyItem) getIntent().getSerializableExtra(BUNDLE_KEY_ITEM)))
                .commit();
    }

}
