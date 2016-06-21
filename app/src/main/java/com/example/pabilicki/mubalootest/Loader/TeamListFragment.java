package com.example.pabilicki.mubalootest.Loader;

/**
 * Created by piotr on 20.06.2016.
 */

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.example.pabilicki.mubalootest.DataStructure.Team;
import com.example.pabilicki.mubalootest.ExpandableListAdapter;

import java.util.List;

public class TeamListFragment extends ListFragment implements
        LoaderManager.LoaderCallbacks<List<Team>> {
    private ExpandableListAdapter mListAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // mListAdapter = new ExpandableListAdapter(getContext(), teams);
        //setListAdapter(mListAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mListAdapter.isEmpty()) {
            getLoaderManager().initLoader(0, null, this).forceLoad();
        } else {
            setListShown(true);
        }
    }

    @Override
    public void onLoadFinished(Loader<List<Team>> listLoader, List<Team> teams) {
        // our loader got results back so lets set the data


        mListAdapter.setData(teams);

        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setEmptyText("Error");
        setListShown(false);
    }

    @Override
    public Loader<List<Team>> onCreateLoader(int i, Bundle bundle) {
        // call our loader to load json objects over the internet
        return new TeamListLoader(getActivity());
    }

    @Override
    public void onLoaderReset(Loader<List<Team>> listLoader) {
    }


}