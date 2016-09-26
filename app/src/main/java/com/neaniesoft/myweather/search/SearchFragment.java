package com.neaniesoft.myweather.search;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neaniesoft.myweather.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchFragment extends Fragment implements SearchView {

    private SearchPresenter mSearchPresenter;

    @BindView(R.id.searchview)
    android.widget.SearchView mSearchWidget;

    Unbinder mUnbinder;

    private SearchFragmentListener mSearchFragmentListener;

    public SearchFragment() {
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mSearchFragmentListener = (SearchFragmentListener) context;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Activity must implement SearchFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mSearchFragmentListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        mUnbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSearchWidget.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mSearchPresenter.searchRequested();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void setPresenter(@NonNull SearchPresenter presenter) {
        mSearchPresenter = presenter;
    }

    @Override
    public String getSearchQuery() {
        return mSearchWidget.getQuery().toString();
    }

    @Override
    public void finish() {
        mSearchFragmentListener.finishRequested();
    }

    @Override
    public void showErrorInvalidSearch() {
        if (getView() != null) {
            Snackbar.make(getView(), R.string.error_invalid_search, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public int checkLocationPermission() {
        return ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
    }

    @Override
    public void requestLocationPermission() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_LOCATION);
    }

    @OnClick(R.id.button_my_location)
    void onMyLocationButtonClicked() {
        mSearchPresenter.myLocationRequested();
    }

    interface SearchFragmentListener {
        void finishRequested();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mSearchPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
