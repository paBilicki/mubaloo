package com.example.pabilicki.mubalootest.Loader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pabilicki.mubalootest.R;

/**
 * Created by piotr on 20.06.2016.
 */
public class TeamMemberDetailActivity extends Activity {

    private TextView tvCeo, tvTeamName, tvTeamMemberName, tvTeamMemberRole;
    private ImageView teamLogo, profileImg, cptArmband;
    private String ceo, teamName, teamMemberName, teamMemberRole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_member_details);

        tvCeo = (TextView) findViewById(R.id.tv_ceo_name_detailed);
        tvTeamName = (TextView) findViewById(R.id.tv_team_name);
        tvTeamMemberName = (TextView) findViewById(R.id.tv_team_member_name);
        tvTeamMemberRole = (TextView) findViewById(R.id.tv_team_member_role);
        teamLogo = (ImageView) findViewById(R.id.img_team_logo);
        profileImg = (ImageView) findViewById(R.id.img_profile);
        cptArmband = (ImageView) findViewById(R.id.img_cpt_armband);

        ceo = getIntent().getStringExtra("ceo");
        teamName = getIntent().getStringExtra("teamName");
        teamMemberName = getIntent().getStringExtra("teamMemberName");
        teamMemberRole = getIntent().getStringExtra("teamMemberRole");

    }
}
