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
 * Created by JANI on 17-06-2017.
 */

public class ExamPaperAdapter extends RecyclerView.Adapter<ExamPaperAdapter.ViewHolder>{

    Context mContext;
    List<ExamPaper> examPaperList;
    ArrayList<ExamPaper> examPaperArrayList;

    public ExamPaperAdapter(Context mContext, ArrayList<ExamPaper> examPaperList){
        this.mContext = mContext;
        this.examPaperList = examPaperList;
        this.examPaperArrayList = new ArrayList<ExamPaper>();
        this.examPaperArrayList.addAll(examPaperList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exam_paper_list_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ExamPaper examPaper = examPaperList.get(position);
        if (examPaper != null){
            holder.name.setText(examPaper.getStatus());
            holder.date.setText(examPaper.getDate());
            holder.paper.setText(examPaper.getExam_paper_title());
            holder.marks.setText(examPaper.getMax_marks());
            holder.start_time.setText(examPaper.getStart_time());
            holder.end_time.setText(examPaper.getEnd_time());
        }
    }

    @Override
    public int getItemCount() {
        return examPaperList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View container;
        public TextView name, date, paper, marks, start_time, end_time;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            date = (TextView) itemView.findViewById(R.id.date);
            paper = (TextView) itemView.findViewById(R.id.paper);
            marks = (TextView) itemView.findViewById(R.id.marks);
            start_time = (TextView) itemView.findViewById(R.id.start_time);
            end_time = (TextView) itemView.findViewById(R.id.end_time);
        }
    }
}
