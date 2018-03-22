package com.example.harish.proschoolforparentadvanced.suresh.studentprofile;

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
 * Created by JANI on 17-06-2017.
 */

public class AttendanceReportsByMonthAdapter extends RecyclerView.Adapter<AttendanceReportsByMonthAdapter.ViewHolder>{

    Context mContext;
    List<StudentAttandenceModel> evalMarksList;
    ArrayList<StudentAttandenceModel> evalMarksArrayList;

    public AttendanceReportsByMonthAdapter(Context mContext, ArrayList<StudentAttandenceModel> evalMarksList){
        this.mContext = mContext;
        this.evalMarksList = evalMarksList;
        this.evalMarksArrayList = new ArrayList<StudentAttandenceModel>();
        this.evalMarksArrayList.addAll(evalMarksList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendance_month_list_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StudentAttandenceModel evalMarks = evalMarksList.get(position);
        if (evalMarks != null){
            holder.studentAttDate.setText(evalMarks.getStdName());
            holder.statusPresent.setText(evalMarks.getStdAttendance());

        }
    }

    @Override
    public int getItemCount() {
        return evalMarksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View container;
        public TextView studentAttDate,statusPresent;
        public ViewHolder(View itemView) {
            super(itemView);
            studentAttDate = (TextView) itemView.findViewById(R.id.studentAttDate);
            statusPresent=(TextView)itemView.findViewById(R.id.studentAttStatus);
        }
    }
}

