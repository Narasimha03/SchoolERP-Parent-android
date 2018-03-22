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

public class EvalMarkAdapter extends RecyclerView.Adapter<EvalMarkAdapter.ViewHolder>{

    Context mContext;
    List<EvalMarks> evalMarksList;
    ArrayList<EvalMarks> evalMarksArrayList;

    public EvalMarkAdapter(Context mContext, ArrayList<EvalMarks> evalMarksList){
        this.mContext = mContext;
        this.evalMarksList = evalMarksList;
        this.evalMarksArrayList = new ArrayList<EvalMarks>();
        this.evalMarksArrayList.addAll(evalMarksList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.eval_marks_list_adapter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EvalMarks evalMarks = evalMarksList.get(position);
        if (evalMarks != null){
            holder.student.setText(evalMarks.getStudent_id());
            holder.exam.setText(evalMarks.getExam());
            holder.subject.setText(evalMarks.getSubject());
            holder.paper.setText(evalMarks.getExam_paper_id());
            holder.marks.setText(evalMarks.getMarks());
            String percent = evalMarks.getPercentage() + "%";
            holder.percent.setText(percent);
            holder.conduct.setText(evalMarks.getConduct());
        }
    }

    @Override
    public int getItemCount() {
        return evalMarksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View container;
        public TextView student, exam, subject, paper, marks, percent, conduct;
        public ViewHolder(View itemView) {
            super(itemView);
            student = (TextView) itemView.findViewById(R.id.student);
            exam = (TextView) itemView.findViewById(R.id.exam);
            subject = (TextView) itemView.findViewById(R.id.subject);
            paper = (TextView) itemView.findViewById(R.id.paper);
            marks = (TextView) itemView.findViewById(R.id.marks);
            percent = (TextView) itemView.findViewById(R.id.percent);
            conduct = (TextView) itemView.findViewById(R.id.conduct);
        }
    }
}
