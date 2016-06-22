package com.example.pabilicki.mubalootest.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pabilicki.mubalootest.R;
import com.example.pabilicki.mubalootest.data.Ceo;
import com.example.pabilicki.mubalootest.data.DataModel;
import com.example.pabilicki.mubalootest.data.Team;
import com.example.pabilicki.mubalootest.data.TeamMember;
import com.example.pabilicki.mubalootest.loader.TeamListAdapter;
import com.example.pabilicki.mubalootest.loader.TeamListLoader;
import com.example.pabilicki.mubalootest.loader.TeamMemberDetailFragment;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * main view of the application displaying expandable list with teams and team members
 *
 * @author Piotr Aleksander Bilicki
 */
public class MainActivity extends BaseActionBarActivity implements LoaderManager.LoaderCallbacks<DataModel> {
    private TeamMemberDetailFragment teamMemberDetailFragment;
    private List<String> teamName = new ArrayList<>();
    private HashMap<String, List<TeamMember>> teamMembers = new HashMap<>();
    private Ceo ceo;
    ExpandableListView expListView;
    TeamListAdapter mListAdapter;
    Context context;
    LinearLayout llErrorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        context = this;

        expListView = (ExpandableListView) findViewById(R.id.lv_expandable);
        if (findViewById(R.id.team_member_detail_container) != null) {
            teamMemberDetailFragment = new TeamMemberDetailFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.team_member_detail_container, teamMemberDetailFragment)
                    .commit();
        }

        llErrorLayout = (LinearLayout) findViewById(R.id.ll_error_layout);
        llErrorLayout.setVisibility(View.GONE);

        mListAdapter = new TeamListAdapter(MainActivity.this);
        getSupportLoaderManager().initLoader(1, null, this).forceLoad();

        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                if (teamMemberDetailFragment != null) {
                    teamMemberDetailFragment.resetDetails();
                }
            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                TeamMember teamMember = teamMembers.get(teamName.get(groupPosition)).get(childPosition);

                if (teamMemberDetailFragment != null) {
                    teamMemberDetailFragment.populateFragment(teamMember);
                } else {
                    Intent intent = new Intent(MainActivity.this, TeamMemberDetailActivity.class);

                    intent.putExtra(DataModel.PARAM_CEO, ceo.getFirstName() + " " + ceo.getLastName());
                    intent.putExtra(DataModel.KEY_TEAM_NAME, teamName.get(groupPosition));
                    intent.putExtra(DataModel.PARAM_TEAM_MEMBER_SERIALIZABLE, teamMember);

                    startActivity(intent);
                }
                return false;
            }
        });


    }

    @Override
    public Loader<DataModel> onCreateLoader(int i, Bundle bundle) {
        return new TeamListLoader(MainActivity.this);
    }


    @Override
    public void onLoadFinished(Loader<DataModel> loader, DataModel dataModel) {
        if (dataModel != null) {
            llErrorLayout.setVisibility(View.GONE);
            List<Team> teams = dataModel.getAllTeams();

            mListAdapter.setData(teams);
            expListView.setAdapter(mListAdapter);
            for (Team t : teams) {
                teamName.add(t.getTeamName());
                teamMembers.put(t.getTeamName(), t.getMembers());
            }
            TextView tvCeo = (TextView) findViewById(R.id.tv_ceo_name);
            try {
                ceo = dataModel.getCeo();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            tvCeo.setText(ceo.getFirstName() + " " + ceo.getLastName());
        } else {
            TextView tvErrormsg = (TextView) findViewById(R.id.tv_error_msg);
            Button btnRetry = (Button) findViewById(R.id.btn_retry);
            Button btnQuit = (Button) findViewById(R.id.btn_quit);
            llErrorLayout.setVisibility(View.VISIBLE);

            tvErrormsg.setText("Could not load content to display");
            btnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, SplashScreen.class);
                    startActivity(intent);
                    llErrorLayout.setVisibility(View.GONE);
                    finish();
                }
            });

            btnQuit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });


        }
    }

    @Override
    public void onLoaderReset(Loader<DataModel> loader) {
        mListAdapter.setData(new ArrayList<Team>());
    }
}
