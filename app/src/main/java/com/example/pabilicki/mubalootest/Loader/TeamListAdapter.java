package com.example.pabilicki.mubalootest.Loader;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pabilicki.mubalootest.DataStructure.Team;
import com.example.pabilicki.mubalootest.DataStructure.TeamMember;
import com.example.pabilicki.mubalootest.R;

import java.util.List;

/**
 * Created by piotr on 17.06.2016.
 */
public class TeamListAdapter extends ArrayAdapter<Team> {

    private LayoutInflater mLayoutInflater;
    private String TAG = "pbBilu.TeamMemberListAdapter";

    public TeamListAdapter(Context context) {
//        super(context, R.layout.row_item);
        super(context, R.layout.row_team);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView: ");
        ViewHolder viewHolder;

        if (convertView == null) {
            // inflate the view if it is null
            convertView = mLayoutInflater.inflate(R.layout.row_item, parent, false);
            // store our views in a viewholder because findviewbyid is expensive
            viewHolder = new ViewHolder();
            viewHolder.logo = (ImageView) convertView.findViewById(R.id.img_team_logo);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        } else {
            // we just saved having to do all our lookups :)
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Team team = getItem(position);
        if (team != null) {
            // set the text in our views
//
            viewHolder.logo.setImageResource(R.drawable.logo_android);
            viewHolder.name.setText(team.getTeamName());

        }
        return convertView;
    }

    public void setData(List<Team> data) {
        if (data != null) {
            clear();
            // we add each item individually instead of using addAll() for backward compatibility
            for (Team team : data) {
                add(team);
            }
        }
    }

    // this class holds our lookups
    private class ViewHolder {
        //        private TextView name;
//        private TextView idNr;
//        private TextView role;
        private ImageView logo;
        private TextView name;
    }
}
