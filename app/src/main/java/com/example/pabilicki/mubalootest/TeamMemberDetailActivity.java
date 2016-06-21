package com.example.pabilicki.mubalootest;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pabilicki.mubalootest.DataStructure.TeamMember;

import java.io.InputStream;

/**
 * Created by piotr on 20.06.2016.
 */
public class TeamMemberDetailActivity extends FragmentActivity {

    private TextView tvCeo, tvTeamName, tvTeamMemberName, tvTeamMemberRole, tvTeamMemberDescription;
    private ImageView imgTeamLogo, imgProfileImg, imgCptArmband;
    private String ceo, teamName, teamMemberName, teamMemberRole, teamMemberURL, teamMemberDescription;
    private String TAG = "pbBilu.TeamMemberDetailActivity";
    private TeamMemberDetailFragment detailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_member_details);

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);



        detailsFragment = new TeamMemberDetailFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_fragment_details, detailsFragment)
                .commit();

        tvCeo = (TextView) findViewById(R.id.tv_ceo_name_details);
        tvTeamName = (TextView) findViewById(R.id.tv_team_name_details);

        imgTeamLogo = (ImageView) findViewById(R.id.img_team_logo_details);
        imgProfileImg = (ImageView) findViewById(R.id.img_profile_details);
        imgCptArmband = (ImageView) findViewById(R.id.img_cpt_armband_details);

        ceo = getIntent().getStringExtra("ceo");
        teamName = getIntent().getStringExtra("teamName");
        teamMemberName = getIntent().getStringExtra("Name");

        tvCeo.setText(ceo);
        tvTeamName.setText(teamName);


        if (teamName.equals("iOS")) {
            imgTeamLogo.setImageResource(R.drawable.logo_ios);
        } else if (teamName.equals("Android")) {
            imgTeamLogo.setImageResource(R.drawable.logo_android);
        } else if (teamName.equals("Web")) {
            imgTeamLogo.setImageResource(R.drawable.logo_web);
        } else if (teamName.equals("Design")) {
            imgTeamLogo.setImageResource(R.drawable.logo_design);
        } else {
            imgTeamLogo.setImageResource(R.drawable.logo_placeholder);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        TeamMember teamMember = (TeamMember) getIntent().getSerializableExtra("teamMember");
        detailsFragment.populateFragment(teamMember);
    }
}
