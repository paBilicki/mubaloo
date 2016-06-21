package com.example.pabilicki.mubalootest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pabilicki.mubalootest.DataStructure.BackupSQL;
import com.example.pabilicki.mubalootest.DataStructure.Ceo;
import com.example.pabilicki.mubalootest.DataStructure.Team;
import com.example.pabilicki.mubalootest.DataStructure.TeamMember;
import com.example.pabilicki.mubalootest.Loader.TeamListLoader;
import com.example.pabilicki.mubalootest.Loader.TeamMemberDetailActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<List<Team>> {
    private String TAG = "pbBilu.MainActivity";
    ExpandableListView expListView;
    private List<String> teamName = new ArrayList<>();
    private HashMap<String, List<TeamMember>> teamMembers = new HashMap<>();
    private Ceo ceo;
    ExpandableListAdapter mListAdapter;
    private final Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        Toast.makeText(MainActivity.this, "Internet Connection:" + SplashScreen.internetConnection, Toast.LENGTH_SHORT).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        expListView = (ExpandableListView) findViewById(R.id.lv_expandable);
//        mListAdapter = new ExpandableListAdapter(this, new ArrayList<Team>());
        mListAdapter = new ExpandableListAdapter(MainActivity.this);
//        expListView.setAdapter(mListAdapter);

        getSupportLoaderManager().initLoader(1, null, this).forceLoad();

        mHandler.post(new Runnable() {
            public void run() {
                expListView.setOnGroupClickListener(new OnGroupClickListener() {

                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v,
                                                int groupPosition, long id) {
//                        Toast.makeText(getApplicationContext(),
//                                "Group Clicked " + teamName.get(groupPosition),
//                                Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

                // Listview Group expanded listener
                expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

                    @Override
                    public void onGroupExpand(int groupPosition) {
//                        Toast.makeText(getApplicationContext(),
//                                teamName.get(groupPosition) + " Expanded",
//                                Toast.LENGTH_SHORT).show();
                    }
                });

                // Listview Group collasped listener
                expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

                    @Override
                    public void onGroupCollapse(int groupPosition) {
//                        Toast.makeText(getApplicationContext(),
//                                teamName.get(groupPosition) + " Collapsed",
//                                Toast.LENGTH_SHORT).show();

                    }
                });

                // Listview on child click listener
                expListView.setOnChildClickListener(new OnChildClickListener() {

                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v,
                                                int groupPosition, int childPosition, long id) {


                        Intent intent = new Intent(MainActivity.this, TeamMemberDetailActivity.class);

                        TeamMember teamMember = teamMembers.get(teamName.get(groupPosition)).get(childPosition);
                        intent.putExtra("ceo", ceo.getFirstName() + " " + ceo.getLastName());
                        intent.putExtra("teamName", teamName.get(groupPosition));
                        intent.putExtra("Name", teamMember.getFirstName() + " " + teamMember.getLastName());
                        intent.putExtra("Role", teamMember.getRole());
                        intent.putExtra("ProfileImageURL", teamMember.getProfileImageURL());
                        intent.putExtra("Description", teamMember.getDescription());

                        startActivity(intent);

                        return false;
                    }
                });
            }});



    }

    @Override
    public Loader<List<Team>> onCreateLoader(int i, Bundle bundle) {
        Log.d(TAG, "onCreateLoader: ");
        return new TeamListLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<List<Team>> loader, List<Team> teams) {
//        mListAdapter = new ExpandableListAdapter(MainActivity.this, teams);
        Log.d(TAG, "onLoadFinished: teams: " + teams.size());
        mListAdapter.setData(teams);
        expListView.setAdapter(mListAdapter);
        for (Team t : teams) {
            Log.d(TAG, "setData: " + t.getTeamName());
            teamName.add(t.getTeamName());
            teamMembers.put(t.getTeamName(), t.getMembers());
        }
        TextView tvCeo = (TextView)findViewById(R.id.tv_ceo_name);
        ceo = BackupSQL.getCeo();
        tvCeo.setText(ceo.getFirstName() + " " + ceo.getLastName());

    }

    @Override
    public void onLoaderReset(Loader<List<Team>> loader) {
        Log.d(TAG, "onLoaderReset: ");
        mListAdapter.setData(new ArrayList<Team>());
    }
}
