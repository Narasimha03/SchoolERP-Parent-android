package com.example.harish.proschoolforparentadvanced.suresh.feecollection;

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

public class FeeCollectionAdapter extends RecyclerView.Adapter<FeeCollectionAdapter.ViewHolder>{

    Context mContext;
    List<FeeCollection> evalMarksList;
    ArrayList<FeeCollection> evalMarksArrayList;

    public FeeCollectionAdapter(Context mContext, ArrayList<FeeCollection> evalMarksList){
        this.mContext = mContext;
        this.evalMarksList = evalMarksList;
        this.evalMarksArrayList = new ArrayList<FeeCollection>();
        this.evalMarksArrayList.addAll(evalMarksList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fee_collection_list_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FeeCollection evalMarks = evalMarksList.get(position);
        if (evalMarks != null){
            holder.student.setText(evalMarks.getFeeType());
            holder.exam.setText(evalMarks.getFeeCategory());
            holder.subject.setText(evalMarks.getPaymentMode());
            holder.paper.setText(evalMarks.getAmount());
            holder.marks.setText(evalMarks.getFine());
            String percent = evalMarks.getDiscount();
            holder.percent.setText(percent);
            holder.conduct.setText(evalMarks.getTotal());
            holder.feePaid.setText(evalMarks.getFeePaid());

        }
    }

    @Override
    public int getItemCount() {
        return evalMarksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View container;
        public TextView student, exam, subject, paper, marks, percent, conduct,feePaid;
        public ViewHolder(View itemView) {
            super(itemView);
            student = (TextView) itemView.findViewById(R.id.student);
            exam = (TextView) itemView.findViewById(R.id.exam);
            subject = (TextView) itemView.findViewById(R.id.subject);
            paper = (TextView) itemView.findViewById(R.id.paper);
            marks = (TextView) itemView.findViewById(R.id.marks);
            percent = (TextView) itemView.findViewById(R.id.percent);
            conduct = (TextView) itemView.findViewById(R.id.conduct);
            feePaid=(TextView)itemView.findViewById(R.id.feePaid);
        }
    }
}
