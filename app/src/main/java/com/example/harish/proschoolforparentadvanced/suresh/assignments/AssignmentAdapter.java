package com.example.harish.proschoolforparentadvanced.suresh.assignments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.harish.proschoolforparentadvanced.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JANI on 03-06-2017.
 */

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Assignments> assignmentList;
    private List<Assignments> listAssignment;

    public AssignmentAdapter(Context ctx, ArrayList<Assignments> assignmentList){
        this.mContext = ctx;
        this.listAssignment = assignmentList;
        this.assignmentList = new ArrayList<Assignments>();
        this.assignmentList.addAll(listAssignment);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.assignment_list_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Assignments assignments = listAssignment.get(position);
        if (assignments != null){
            holder.title.setText(assignments.getName());
            holder.date.setText(assignments.getDue_date());
            holder.type.setText(assignments.getLesson_id());
            holder.available.setText(assignments.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return listAssignment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View container;
        public TextView title, date, type, available;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            date = (TextView) itemView.findViewById(R.id.date);
            type = (TextView) itemView.findViewById(R.id.type);
            available = (TextView) itemView.findViewById(R.id.available);
        }
    }
}
