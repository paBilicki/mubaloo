package com.example.pabilicki.mubalootest.loader;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pabilicki.mubalootest.R;
import com.example.pabilicki.mubalootest.data.Team;
import com.example.pabilicki.mubalootest.data.TeamMember;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Adapter used in order to populate the expandable list with the result from the TeamListLoader
 *
 * @author Piotr Aleksander Bilicki
 */

public class TeamListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> teamName = new ArrayList<>();
    private HashMap<String, List<TeamMember>> teamMembers = new HashMap<>();

    public TeamListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public TeamMember getChild(int groupPosition, int childPosititon) {
        return this.teamMembers.get(teamName.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        TeamMember child = getChild(groupPosition, childPosition);
        final String teamMemberText = child.getFirstName() + " " + child.getLastName();
        TextView tvTeamMember = null;

        LayoutInflater inflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.row_team_member, null);
            convertView.setTag(child.getRole());

            tvTeamMember = (TextView) convertView.findViewById(R.id.tv_team_member_name);

            if (getChild(groupPosition, childPosition).isTeamLead()) {

                convertView = inflater.inflate(R.layout.row_team_leader, null);
                convertView.setTag(child.getRole());
                tvTeamMember = (TextView) convertView.findViewById(R.id.tv_team_leader_name);
            }

        } else {

            if (getChild(groupPosition, childPosition).isTeamLead()) {
                if (!getChild(groupPosition, childPosition).getRole().equals(convertView.getTag())) {
                    convertView = inflater.inflate(R.layout.row_team_leader, null);
                    convertView.setTag(child.getRole());
                }
                tvTeamMember = (TextView) convertView.findViewById(R.id.tv_team_leader_name);
            } else {
                if (!getChild(groupPosition, childPosition).getRole().equals(convertView.getTag())) {
                    convertView = inflater.inflate(R.layout.row_team_member, null);
                    convertView.setTag(child.getRole());
                }
                tvTeamMember = (TextView) convertView.findViewById(R.id.tv_team_member_name);
            }
        }
        tvTeamMember.setText(teamMemberText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.teamMembers.get(this.teamName.get(groupPosition))
                .size();
    }

    @Override
    public String getGroup(int groupPosition) {
        return this.teamName.get(groupPosition);
    }

    @Override
    public int getGroupCount() {

        return this.teamMembers.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String teamName = getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_team, null);
        }
        ImageView imgTeamLogo = (ImageView) convertView.findViewById(R.id.img_team_logo);
        switch (teamName) {
            case Team.TEAM_IOS:
                imgTeamLogo.setImageResource(R.drawable.logo_ios);
                break;

            case Team.TEAM_ANDROID:
                imgTeamLogo.setImageResource(R.drawable.logo_android);
                break;

            case Team.TEAM_WEB:
                imgTeamLogo.setImageResource(R.drawable.logo_web);
                break;

            case Team.TEAM_DESIGN:
                imgTeamLogo.setImageResource(R.drawable.logo_design);
                break;

            default:
                imgTeamLogo.setImageResource(R.drawable.logo_placeholder);
                break;
        }


//        if (teamName.equals(Team.TEAM_IOS)) {
//            imgTeamLogo.setImageResource(R.drawable.logo_ios);
//        } else if (teamName.equals(Team.TEAM_ANDROID)) {
//            imgTeamLogo.setImageResource(R.drawable.logo_android);
//        } else if (teamName.equals(Team.TEAM_WEB)) {
//            imgTeamLogo.setImageResource(R.drawable.logo_web);
//        } else if (teamName.equals(Team.TEAM_DESIGN)) {
//            imgTeamLogo.setImageResource(R.drawable.logo_design);
//        } else {
//            imgTeamLogo.setImageResource(R.drawable.logo_placeholder);
//        }

        TextView tvTeamName = (TextView) convertView.findViewById(R.id.tv_team_name);
        tvTeamName.setText(teamName);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setData(List<Team> teams) {
        for (Team t : teams) {
            teamName.add(t.getTeamName());
            teamMembers.put(t.getTeamName(), t.getMembers());
        }
        notifyDataSetChanged();
    }

}
