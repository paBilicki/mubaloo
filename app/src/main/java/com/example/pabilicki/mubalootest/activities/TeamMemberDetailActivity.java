package com.example.pabilicki.mubalootest.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pabilicki.mubalootest.R;
import com.example.pabilicki.mubalootest.data.DataModel;
import com.example.pabilicki.mubalootest.data.Team;
import com.example.pabilicki.mubalootest.data.TeamMember;
import com.example.pabilicki.mubalootest.loader.TeamMemberDetailFragment;


/**
 * A view containing information about the CEO, Team and a Fragment with details
 * of a team member chosen from the expandable list
 *
 * @author Piotr Aleksander Bilicki
 */
public class TeamMemberDetailActivity extends BaseActionBarActivity {
    private TextView tvCeo, tvTeamName;
    private ImageView imgTeamLogo;
    private String ceo, teamName;
    private TeamMemberDetailFragment detailsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_member_details);


        detailsFragment = new TeamMemberDetailFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_fragment_details, detailsFragment)
                .commit();

        // displaying ceo row
        tvCeo = (TextView) findViewById(R.id.tv_ceo_name_details);
        ceo = getIntent().getStringExtra(DataModel.PARAM_CEO);
        tvCeo.setText(ceo);

        // displaying team row
        imgTeamLogo = (ImageView) findViewById(R.id.img_team_logo_details);
        tvTeamName = (TextView) findViewById(R.id.tv_team_name_details);
        teamName = getIntent().getStringExtra(DataModel.KEY_TEAM_NAME);
        tvTeamName.setText(teamName);

        // choosing prepared logo for one of the current teams
        // with a place holder predicted for new ones
        if (teamName.equals(Team.TEAM_IOS)) {
            imgTeamLogo.setImageResource(R.drawable.logo_ios);
        } else if (teamName.equals(Team.TEAM_ANDROID)) {
            imgTeamLogo.setImageResource(R.drawable.logo_android);
        } else if (teamName.equals(Team.TEAM_WEB)) {
            imgTeamLogo.setImageResource(R.drawable.logo_web);
        } else if (teamName.equals(Team.TEAM_DESIGN)) {
            imgTeamLogo.setImageResource(R.drawable.logo_design);
        } else {
            imgTeamLogo.setImageResource(R.drawable.logo_placeholder);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        TeamMember teamMember = (TeamMember) getIntent().getSerializableExtra(DataModel.PARAM_TEAM_MEMBER_SERIALIZABLE);
        detailsFragment.populateFragment(teamMember);
    }
}
