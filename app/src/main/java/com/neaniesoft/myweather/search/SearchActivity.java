package com.neaniesoft.myweather.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.neaniesoft.myweather.R;
import com.neaniesoft.myweather.prefs.LocationPrefs;

public class SearchActivity extends AppCompatActivity implements SearchFragment.SearchFragmentListener {

    SearchPresenter mSearchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        if (searchFragment == null) {
            searchFragment = SearchFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment, searchFragment)
                    .commit();
        }

        mSearchPresenter = new SearchPresenterImpl(searchFragment, new LocationPrefs(getApplicationContext()));
    }

    @Override
    public void finishRequested() {
        finish();
    }
}
