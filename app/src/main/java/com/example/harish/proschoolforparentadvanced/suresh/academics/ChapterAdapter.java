package com.example.harish.proschoolforparentadvanced.suresh.academics;

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

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {

    Context mContext;
    List<Chapters> chaptersList;
    ArrayList<Chapters> chaptersArrayList;

    public ChapterAdapter(Context mContext, ArrayList<Chapters> chaptersList){
        this.mContext = mContext;
        this.chaptersList = chaptersList;
        this.chaptersArrayList = new ArrayList<Chapters>();
        this.chaptersArrayList.addAll(chaptersList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chapter_list_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Chapters chapters = chaptersList.get(position);
        if (chapters != null){
            holder.name.setText(chapters.getTitle());
            holder.code.setText(chapters.getCode());
            //holder.subject.setText(chapters.getSubject_id());
            holder.topics.setText(chapters.getNoTopics());
            holder.description.setText(chapters.getDesc());
        }
    }

    @Override
    public int getItemCount() {
        return chaptersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View container;
        public TextView name, code, subject, topics, description;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            code = (TextView) itemView.findViewById(R.id.code);
            //subject = (TextView) itemView.findViewById(R.id.subject);
            topics = (TextView) itemView.findViewById(R.id.topics);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
