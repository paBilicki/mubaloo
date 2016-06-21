package com.example.pabilicki.mubalootest.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pabilicki.mubalootest.R;
import com.example.pabilicki.mubalootest.data.TeamMember;


/**
 * Created by piotr on 20.06.2016.
 */
public class TeamMemberDetailActivity extends BaseActionBarActivity {

    private TextView tvCeo, tvTeamName;
    private ImageView imgTeamLogo;
    private String ceo, teamName;
    private TeamMemberDetailFragment detailsFragment;
    private static final String TEAM_IOS = "iOS";
    private static final String TEAM_ANDROID = "Android";
    private static final String TEAM_WEB = "Web";
    private static final String TEAM_DESIGN = "Design";
    public static final String KEY_CEO = "ceo";
    public static final String KEY_TEAM_NAME = "teamName";
    public static final String KEY_TEAM_MEMBER_SERIALIZABLE = "teamMember";


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
        ceo = getIntent().getStringExtra(KEY_CEO);
        tvCeo.setText(ceo);

        // displaying team row
        imgTeamLogo = (ImageView) findViewById(R.id.img_team_logo_details);
        tvTeamName = (TextView) findViewById(R.id.tv_team_name_details);
        teamName = getIntent().getStringExtra(KEY_TEAM_NAME);
        tvTeamName.setText(teamName);

        // choosing prepared logo for one of the current teams
        // with a place holder predicted for new ones
        if (teamName.equals(TEAM_IOS)) {
            imgTeamLogo.setImageResource(R.drawable.logo_ios);
        } else if (teamName.equals(TEAM_ANDROID)) {
            imgTeamLogo.setImageResource(R.drawable.logo_android);
        } else if (teamName.equals(TEAM_WEB)) {
            imgTeamLogo.setImageResource(R.drawable.logo_web);
        } else if (teamName.equals(TEAM_DESIGN)) {
            imgTeamLogo.setImageResource(R.drawable.logo_design);
        } else {
            imgTeamLogo.setImageResource(R.drawable.logo_placeholder);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        TeamMember teamMember = (TeamMember) getIntent().getSerializableExtra(KEY_TEAM_MEMBER_SERIALIZABLE);
        detailsFragment.populateFragment(teamMember);
    }
}
