package com.example.pabilicki.mubalootest.Loader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pabilicki.mubalootest.DataStructure.TeamMember;
import com.example.pabilicki.mubalootest.R;

import java.util.List;

/**
 * Created by piotr on 17.06.2016.
 */
public class TeamMemberListAdapter extends ArrayAdapter<TeamMember> {

    private LayoutInflater mLayoutInflater;

    public TeamMemberListAdapter(Context context) {
//        super(context, R.layout.row_item);
        super(context, R.layout.row_team);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            // inflate the view if it is null
            convertView = mLayoutInflater.inflate(R.layout.row_item, parent, false);
            // store our views in a viewholder because findviewbyid is expensive
            viewHolder = new ViewHolder();
//            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
//            viewHolder.idNr = (TextView) convertView.findViewById(R.id.id_nr);
//            viewHolder.role = (TextView) convertView.findViewById(R.id.role);
            viewHolder.logo = (ImageView) convertView.findViewById(R.id.img_team_logo);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        } else {
            // we just saved having to do all our lookups :)
            viewHolder = (ViewHolder) convertView.getTag();
        }

        TeamMember teamMember = getItem(position);
        if (teamMember != null) {
            // set the text in our views
//            viewHolder.name.setText(teamMember.getFirstName() + " " + teamMember.getLastName());
//            viewHolder.idNr.setText(String.valueOf(teamMember.getId()));
//            viewHolder.role.setText(teamMember.getRole());
            viewHolder.logo.setImageResource(R.drawable.logo_android);
            viewHolder.name.setText(teamMember.getFirstName() + " " + teamMember.getLastName());

        }
        return convertView;
    }

    public void setData(List<TeamMember> data) {
        if (data != null) {
            clear();
            // we add each item individually instead of using addAll() for backward compatibility
            for (TeamMember teamMember : data) {
                add(teamMember);
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
