package com.example.pabilicki.mubalootest;


import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private String TAG = "pbBilu.ExpandableListAdapter";

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        Log.d(TAG, "getChild: ");
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        Log.d(TAG, "getChildId: ");
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        Log.d(TAG, "getChildView: ");
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_team_member, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.tv_team_member_name);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.d(TAG, "getChildrenCount: ");
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        Log.d(TAG, "getGroup: ");
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        Log.d(TAG, "getGroupCount: ");
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        Log.d(TAG, "getGroupId: ");
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Log.d(TAG, "getGroupView: ");
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_team, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.tv_team_name);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        Log.d(TAG, "hasStableIds: ");
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        Log.d(TAG, "isChildSelectable: " + groupPosition + "/" + childPosition);
        return true;
    }

}
