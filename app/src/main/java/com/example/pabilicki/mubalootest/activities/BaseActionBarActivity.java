package com.example.pabilicki.mubalootest.activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pabilicki.mubalootest.R;

/**
 * Created by piotr on 21.06.2016.
 */
public class BaseActionBarActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpActionBar();
    }

    private void setUpActionBar() {
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_action_bar, null);
        actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
    }

}
