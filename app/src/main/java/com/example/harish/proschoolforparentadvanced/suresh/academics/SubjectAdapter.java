package com.example.harish.proschoolforparentadvanced.suresh.academics;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.harish.proschoolforparentadvanced.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JANI on 07-06-2017.
 */

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder>{

    private Context mContext;
    private List<Subject> subjectList;
    private ArrayList<Subject> subjectArrayList;
    private ItemClickSubjectListener itemClickSubjectListener;


    public SubjectAdapter(Context mContext, ArrayList<Subject> subjectArrayList){
        this.mContext = mContext;
        this.subjectList = subjectArrayList;
        this.subjectArrayList = new ArrayList<Subject>();
        this.subjectArrayList.addAll(subjectList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_subjects_list_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Subject subject = subjectList.get(position);
        if (subject != null){
            holder.name.setText(subject.getName());
            // holder.code.setText(subject.getCode());
            //  holder.type.setText(subject.getType());
        }
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }


    public void setClickListener(ItemClickSubjectListener itemClickListener) {
        this.itemClickSubjectListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        public View container;
        public TextView name, code, type;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            //  code = (TextView) itemView.findViewById(R.id.code);
            //  type = (TextView) itemView.findViewById(R.id.geoLocation);
            name.setOnClickListener(this);
            name.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        }

        @Override
        public void onClick(View view) {
            if (itemClickSubjectListener != null) itemClickSubjectListener.onClickSubjectName(view, getAdapterPosition());

        }
    }

    public interface ItemClickSubjectListener {
        void onClickSubjectName(View view, int position);

    }
}
