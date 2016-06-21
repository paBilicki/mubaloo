package com.example.pabilicki.mubalootest.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pabilicki.mubalootest.R;
import com.example.pabilicki.mubalootest.data.TeamMember;

/**
 * Created by piotr on 21.06.2016.
 */
public class TeamMemberDetailFragment extends Fragment {
    private TextView tvTeamMemberName, tvTeamMemberRole, tvTeamMemberDescription;
    private ImageView imgProfileImg, imgCptArmband, imgDetailsDefault;
    private String teamMemberName, teamMemberRole, teamMemberURL, teamMemberDescription;
    private LinearLayout memberDetailedRow, memberDetailedDescription;
    private TeamMember teamMember;
    private String TAG = "pbBilu.TeamMemberDetailFragment";
    private static final String PLACEHOLDER_URL = "http://developers.mub.lu/resources/profilePlaceholder.png";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.team_member_details_fragment, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (teamMember != null) {
            outState.putSerializable(TeamMemberDetailActivity.KEY_TEAM_MEMBER_SERIALIZABLE, teamMember);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        memberDetailedRow = (LinearLayout) getView().findViewById(R.id.member_detail_row);
        memberDetailedDescription = (LinearLayout) getView().findViewById(R.id.member_detail_description);

        imgDetailsDefault = (ImageView) getView().findViewById(R.id.img_profile_details_default);
        imgProfileImg = (ImageView) getView().findViewById(R.id.img_profile_details);
        imgCptArmband = (ImageView) getView().findViewById(R.id.img_cpt_armband_details);

        tvTeamMemberName = (TextView) getView().findViewById(R.id.tv_team_member_name_details);
        tvTeamMemberRole = (TextView) getView().findViewById(R.id.tv_team_member_role_details);
        tvTeamMemberDescription = (TextView) getView().findViewById(R.id.tv_team_member_description_details);

        TeamMember lastDisplay = null;

        // Restoring last team member
        if ((savedInstanceState != null) && (savedInstanceState.containsKey(TeamMemberDetailActivity.KEY_TEAM_MEMBER_SERIALIZABLE))) {
            lastDisplay = (TeamMember) savedInstanceState.getSerializable(TeamMemberDetailActivity.KEY_TEAM_MEMBER_SERIALIZABLE);
            populateFragment(lastDisplay);
        }
    }


    public void populateFragment(TeamMember teamMember) {
        this.teamMember = teamMember;
        hideDefault();
        showDetails();


        // taking details of person
        teamMemberName = teamMember.getFirstName() + " " + teamMember.getLastName();
        teamMemberRole = teamMember.getRole();
        teamMemberURL = teamMember.getProfileImageURL();
        teamMemberDescription = teamMember.getDescription();


        // setting TextViews with taken details
        tvTeamMemberName.setText(teamMemberName);
        tvTeamMemberRole.setText(teamMemberRole);
        tvTeamMemberDescription.setText(teamMemberDescription);


        // checking if a person is a team leader
        if (teamMember.isTeamLead()) {
            imgCptArmband.setVisibility(View.VISIBLE);
        } else {
            imgCptArmband.setVisibility(View.GONE);
        }


        // checking what img display for a person
        if (teamMemberURL.equals(PLACEHOLDER_URL)) {
            imgProfileImg.setImageResource(R.drawable.profile_placeholder);
        } else {
            // here should be loaded picture of a member if different than a placeHolder.png
            imgProfileImg.setImageResource(R.drawable.profile_placeholder);
        }
    }

    public void resetDetails() {
        imgDetailsDefault.setVisibility(View.VISIBLE);
        memberDetailedRow.setVisibility(View.GONE);
        memberDetailedDescription.setVisibility(View.GONE);
    }


    public void showDetails() {
        memberDetailedRow.setVisibility(View.VISIBLE);
        memberDetailedDescription.setVisibility(View.VISIBLE);
    }


    public void hideDefault() {
        imgDetailsDefault.setVisibility(View.GONE);
    }
}
