package com.example.harish.proschoolforparentadvanced.suresh.dashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.harish.proschoolforparentadvanced.R;
import com.example.harish.proschoolforparentadvanced.suresh.examinations.ExamPaper;
import com.example.harish.proschoolforparentadvanced.suresh.examinations.ExamPaperAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JANI on 17-06-2017.
 */

public class TimeTableDashBoardAdapter extends RecyclerView.Adapter<TimeTableDashBoardAdapter.ViewHolder>{

    Context mContext;
    List<TimeTableDashBoardModel> examPaperList;
    ArrayList<TimeTableDashBoardModel> examPaperArrayList;

    public TimeTableDashBoardAdapter(Context mContext, ArrayList<TimeTableDashBoardModel> examPaperList){
        this.mContext = mContext;
        this.examPaperList = examPaperList;
        this.examPaperArrayList = new ArrayList<TimeTableDashBoardModel>();
        this.examPaperArrayList.addAll(examPaperList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.parent_curriculum_text_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TimeTableDashBoardModel examPaper = examPaperList.get(position);
        if (examPaper != null){
            holder.time.setText(examPaper.getTime());
            holder.subject.setText(examPaper.getSubject());

        }
    }

    @Override
    public int getItemCount() {
        return examPaperList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View container;
        public TextView time, subject;
        public ViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.tvTime);
            subject = (TextView) itemView.findViewById(R.id.tvSubjects);

        }
    }
}
