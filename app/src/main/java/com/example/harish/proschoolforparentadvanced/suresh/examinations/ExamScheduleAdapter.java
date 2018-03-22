package com.example.harish.proschoolforparentadvanced.suresh.examinations;

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
 * Created by JANI on 16-06-2017.
 */

public class ExamScheduleAdapter extends RecyclerView.Adapter<ExamScheduleAdapter.ViewHolder>{

    Context mContext;
    List<ExamSchedule> examScheduleList;
    ArrayList<ExamSchedule> examScheduleArrayList;

    public ExamScheduleAdapter(Context mContext, ArrayList<ExamSchedule> examScheduleList){
        this.mContext = mContext;
        this.examScheduleList = examScheduleList;
        this.examScheduleArrayList = new ArrayList<ExamSchedule>();
        this.examScheduleArrayList.addAll(examScheduleList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exam_list_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ExamSchedule examSchedule = examScheduleList.get(position);
        if (examSchedule != null){
            holder.title.setText(examSchedule.getExam_title());
            holder.date.setText(examSchedule.getFrom_date());
        }
    }

    @Override
    public int getItemCount() {
        return examScheduleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View container;
        public TextView title, date;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
