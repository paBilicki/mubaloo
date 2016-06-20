package com.example.pabilicki.mubalootest.Loader;

/**
 * Created by piotr on 20.06.2016.
 */

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.example.pabilicki.mubalootest.DataStructure.Team;
import com.example.pabilicki.mubalootest.DataStructure.TeamMember;

import java.util.ArrayList;
import java.util.List;

public class TeamMemberListFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<ArrayList<Team>> {
//    private TeamMemberListAdapter mListAdapter;
    private tempAdapter mListAdapter;
    private String TAG = "pbBilu.TeamMemberListFragment";
    protected Context context;

    public TeamMemberListFragment(){
    }

    public TeamMemberListFragment(Context context){
    this.context = context;
}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new tempAdapter(context);
        getLoaderManager().initLoader(0, null, new TeamMemberListFragment()).forceLoad();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mListAdapter.isEmpty()) {
            getLoaderManager().initLoader(0, null, null).forceLoad();
        }
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Team>> listLoader, ArrayList<Team> teamMembers) {
        // our loader got results back so lets set the data
        mListAdapter.setData(teamMembers);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Team>> loader) {

    }


    @Override
    public Loader<ArrayList<Team>> onCreateLoader(int i, Bundle bundle) {
        // call our loader to load json objects over the internet
        return new TeamMemberListLoader(context);
    }



}
