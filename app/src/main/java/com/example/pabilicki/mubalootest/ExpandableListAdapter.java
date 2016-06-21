package com.example.pabilicki.mubalootest;


import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pabilicki.mubalootest.DataStructure.Team;
import com.example.pabilicki.mubalootest.DataStructure.TeamMember;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private String TAG = "pbBilu.ExpandableListAdapter";

    private Context context;
    private List<String> teamName = new ArrayList<>();
    private HashMap<String, List<TeamMember>> teamMembers = new HashMap<>();

    public ExpandableListAdapter(Context context) {
        Log.d(TAG, "ExpandableListAdapter: constructor");
        this.context = context;
    }

    @Override
    public TeamMember getChild(int groupPosition, int childPosititon) {
        Log.d(TAG, "getChild: ");
        return this.teamMembers.get(teamName.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
//        Log.d(TAG, "getChildId: ");
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        Log.d(TAG, "getChildView: " + groupPosition + "/" + childPosition);
        TeamMember child = getChild(groupPosition, childPosition);
        final String teamMemberText = child.getFirstName() + " " + child.getLastName();
        TextView tvTeamMember = null;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_team_member, null);
            tvTeamMember = (TextView) convertView.findViewById(R.id.tv_team_member_name);
            if (getChild(groupPosition, childPosition).getRole().contains("Team Lead")) {
                convertView = infalInflater.inflate(R.layout.row_team_leader, null);
                tvTeamMember = (TextView) convertView.findViewById(R.id.tv_team_leader_name);
            }
        }
        tvTeamMember.setText(teamMemberText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
//        Log.d(TAG, "getChildrenCount: ");
        return this.teamMembers.get(this.teamName.get(groupPosition))
                .size();
    }

    @Override
    public String getGroup(int groupPosition) {
//        Log.d(TAG, "getGroup: ");
        return this.teamName.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
//        Log.d(TAG, "getGroupCount: ");

        return this.teamMembers.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
//        Log.d(TAG, "getGroupId: ");
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Log.d(TAG, "getGroupView: ");
        String teamName = getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_team, null);
        }
        ImageView imgTeamLogo = (ImageView)convertView.findViewById(R.id.img_team_logo);
        if (teamName.equals("iOS")){
            imgTeamLogo.setImageResource(R.drawable.logo_ios);
        }else if (teamName.equals("Android")){
            imgTeamLogo.setImageResource(R.drawable.logo_android);
        }else if (teamName.equals("Web")){
            imgTeamLogo.setImageResource(R.drawable.logo_web);
        }else if (teamName.equals("Design")){
            imgTeamLogo.setImageResource(R.drawable.logo_design);
        }else{
            imgTeamLogo.setImageResource(R.drawable.logo_placeholder);
        }

        TextView tvTeamName = (TextView) convertView.findViewById(R.id.tv_team_name);
        tvTeamName.setText(teamName);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
//        Log.d(TAG, "hasStableIds: ");
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        Log.d(TAG, "isChildSelectable: " + groupPosition + "/" + childPosition);
        return true;
    }

    public void setData(List<Team> teams) {
        for (Team t : teams) {
            Log.d(TAG, "setData: " + t.getTeamName());
            teamName.add(t.getTeamName());
            teamMembers.put(t.getTeamName(), t.getMembers());
        }
        notifyDataSetChanged();
    }

}
