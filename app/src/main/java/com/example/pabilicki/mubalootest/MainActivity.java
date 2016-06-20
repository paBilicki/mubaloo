package com.example.pabilicki.mubalootest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import com.example.pabilicki.mubalootest.Loader.TeamMemberDetailActivity;
import com.example.pabilicki.mubalootest.Loader.TeamMemberListFragment;


public class MainActivity extends FragmentActivity {
    private String TAG = "pbBilu.MainActivity";
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toast.makeText(MainActivity.this, "Internet Connection:" + SplashScreen.internetConnection, Toast.LENGTH_SHORT).show();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new TeamMemberListFragment(getApplicationContext()), null)
                    .commit();
        }
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lv_expandable);

//        // preparing list data
//        prepareListData();
//
//
//        // setting list adapter
//        expListView.setAdapter(listAdapter);
//
//        // Listview Group click listener
//        expListView.setOnGroupClickListener(new OnGroupClickListener() {
//
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v,
//                                        int groupPosition, long id) {
//                 Toast.makeText(getApplicationContext(),
//                 "Group Clicked " + listDataHeader.get(groupPosition),
//                 Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//
//        // Listview Group expanded listener
//        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // Listview Group collasped listener
//        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
//
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        //set listAdapter
//        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
//        expListView.setAdapter(listAdapter);
//
//        // Listview on child click listener
//        expListView.setOnChildClickListener(new OnChildClickListener() {
//
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v,
//                                        int groupPosition, int childPosition, long id) {
//
//
//                Intent intent = new Intent(MainActivity.this, TeamMemberDetailActivity.class);
//
//                intent.putExtra("ceo", "Mark Manson");
//                intent.putExtra("teamName", "Android");
//                intent.putExtra("teamMemberName", "Olly Berry");
//                intent.putExtra("teamMemberRole", "Android Team Lead");
//
//                Toast.makeText(
//                        getApplicationContext(),
//                        listDataHeader.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(
//                                listDataHeader.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT)
//                        .show();
//                return false;
//            }
//        });
//
//
//    }
//
//    /*
//     * Preparing the list data
//     */
//    private void prepareListData() {
//        Log.d(TAG, "prepareListData: ");
//        listDataHeader = new ArrayList<String>();
//        listDataChild = new HashMap<String, List<String>>();
//
//        // Adding child data
//        listDataHeader.add("iOS");
//        listDataHeader.add("Android");
//        listDataHeader.add("Web");
//        listDataHeader.add("Design");
//
//        // Adding child data
//        List<String> iOS = new ArrayList<String>();
//        iOS.add("Olly Berry");
//        iOS.add("James Frost");
//        iOS.add("Liam Nichols");
//        iOS.add("Chris Watson");
//        iOS.add("Richard Turton");
//        iOS.add("Matt Collis");
//        iOS.add("David Gibson");
//        iOS.add("Tom Guy");
//
//        List<String> Android = new ArrayList<String>();
//        Android.add("The Conjuring");
//        Android.add("Despicable Me 2");
//        Android.add("Turbo");
//        Android.add("Grown Ups 2");
//        Android.add("Red 2");
//
//        List<String> Web = new ArrayList<String>();
//        Web.add("2 Guns");
//        Web.add("The Smurfs 2");
//        Web.add("The Spectacular Now");
//
//
//        List<String> Design = new ArrayList<String>();
//        Design.add("2 Guns");
//        Design.add("The Smurfs 2");
//        Design.add("The Spectacular Now");
//
//        listDataChild.put(listDataHeader.get(0), iOS); // Header, Child data
//        listDataChild.put(listDataHeader.get(1), Android);
//        listDataChild.put(listDataHeader.get(2), Web);
//        listDataChild.put(listDataHeader.get(3), Design);
    }
}
