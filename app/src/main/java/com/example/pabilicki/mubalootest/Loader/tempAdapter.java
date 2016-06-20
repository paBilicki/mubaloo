package com.example.pabilicki.mubalootest.Loader;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.pabilicki.mubalootest.DataStructure.Team;
import com.example.pabilicki.mubalootest.DataStructure.TeamMember;
import com.example.pabilicki.mubalootest.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by piotr on 20.06.2016.
 */
public class tempAdapter extends BaseExpandableListAdapter {
    private String TAG = "pbBilu.tempAdapter";

    private List<String> _listDataHeader; // header titles
    private ArrayList<Team> allTeams;
    private Context context;
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;


    public tempAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        Log.d(TAG, "getGroupCount: ");
        return allTeams.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.d(TAG, "getChildrenCount: ");
        return allTeams.get(groupPosition).getMembers().size();
    }

    @Override
    public Team getGroup(int groupPosition) {
        Log.d(TAG, "getGroup: ");
        return allTeams.get(groupPosition);
    }

    @Override
    public TeamMember getChild(int groupPosition, int childPosition) {
        Log.d(TAG, "getChild: ");
        return this.allTeams.get(groupPosition).getMembers().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        Log.d(TAG, "getGroupId: ");
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPositioni, int childPosition) {
        Log.d(TAG, "getChildId: ");
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        Log.d(TAG, "hasStableIds: ");
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Log.d(TAG, "getGroupView: ");
        String teamName = getGroup(groupPosition).getTeamName();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_team, null);
        }

        TextView tvTeamName = (TextView) convertView
                .findViewById(R.id.tv_team_name);
//        tvTeamName.setTypeface(null, Typeface.BOLD);
        tvTeamName.setText(teamName);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        Log.d(TAG, "getChildView: ");
        TeamMember child = getChild(groupPosition, childPosition);
        final String childText = child.getFirstName() + " " + child.getLastName();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_team_member, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.tv_team_member_name);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        
        return true;
    }

    public void setData(ArrayList<Team> data) {
        Log.d(TAG, "setData: ");
        this.allTeams = data;
    }
}
