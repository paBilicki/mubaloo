package com.example.pabilicki.mubalootest.Loader;

/**
 * Created by piotr on 20.06.2016.
 */

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.example.pabilicki.mubalootest.DataStructure.TeamMember;

import java.util.List;

public class TeamMemberListFragment extends ListFragment implements
        LoaderManager.LoaderCallbacks<List<TeamMember>> {
    private TeamMemberListAdapter mListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new TeamMemberListAdapter(getActivity());
        setListAdapter(mListAdapter);
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
    public void onLoadFinished(Loader<List<TeamMember>> listLoader, List<TeamMember> teamMembers) {
        // our loader got results back so lets set the data
        mListAdapter.setData(teamMembers);

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
    public Loader<List<TeamMember>> onCreateLoader(int i, Bundle bundle) {
        // call our loader to load json objects over the internet
        return new TeamMemberListLoader(getActivity());
    }

    @Override
    public void onLoaderReset(Loader<List<TeamMember>> listLoader) {
    }


}