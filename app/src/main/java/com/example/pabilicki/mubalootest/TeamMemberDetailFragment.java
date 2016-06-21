package com.example.pabilicki.mubalootest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pabilicki.mubalootest.DataStructure.TeamMember;

/**
 * Created by piotr on 21.06.2016.
 */
public class TeamMemberDetailFragment extends Fragment {
    private TextView tvCeo, tvTeamName, tvTeamMemberName, tvTeamMemberRole, tvTeamMemberDescription;
    private ImageView imgTeamLogo, imgProfileImg, imgCptArmband;
    private String ceo, teamName, teamMemberName, teamMemberRole, teamMemberURL, teamMemberDescription;
    private String TAG = "pbBilu.TeamMemberDetailFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.team_member_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void populateFragment(TeamMember teamMember) {
        tvTeamName = (TextView) getView().findViewById(R.id.tv_team_name_details);
        tvTeamMemberName = (TextView) getView().findViewById(R.id.tv_team_member_name_details);
        tvTeamMemberRole = (TextView) getView().findViewById(R.id.tv_team_member_role_details);
        tvTeamMemberDescription = (TextView) getView().findViewById(R.id.tv_team_member_description_details);

        imgTeamLogo = (ImageView) getView().findViewById(R.id.img_team_logo_details);
        imgProfileImg = (ImageView) getView().findViewById(R.id.img_profile_details);
        imgCptArmband = (ImageView) getView().findViewById(R.id.img_cpt_armband_details);

        teamMemberName = teamMember.getFirstName() + " " + teamMember.getLastName();
        teamMemberRole = teamMember.getRole();
        teamMemberURL = teamMember.getProfileImageURL();
        teamMemberDescription = teamMember.getDescription();

        tvTeamMemberName.setText(teamMemberName);
        tvTeamMemberRole.setText(teamMemberRole);
        tvTeamMemberDescription.setText(teamMemberDescription);

        if (teamMemberRole.contains("Team Lead")) {
            imgCptArmband.setVisibility(View.VISIBLE);
        }

        if (teamMemberURL.equals("http://developers.mub.lu/resources/profilePlaceholder.png")) {
            imgProfileImg.setImageResource(R.drawable.profile_placeholder);
        } else {
            // TODO: 21.06.2016 downloading a resource
        }
    }
}
