package com.example.harish.proschoolforparentadvanced.suresh.noticeboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.harish.proschoolforparentadvanced.R;

import java.util.List;

/**
 * Created by harish on 07-11-2017.
 */

public class AdminDashboardEventAdapter extends RecyclerView.Adapter<AdminDashboardEventAdapter.MyViewHolder> {


        private List<AdminDashboardEventClass> adminEventList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView adminEventDate;
            public TextView adminEventText;

            public MyViewHolder(View view) {
                super(view);
                adminEventDate = view.findViewById(R.id.admin_event_date_view);
                adminEventText = view.findViewById(R.id.admin_event_text_view);
            }
        }


        public AdminDashboardEventAdapter(List<AdminDashboardEventClass> adminEventList) {
            this.adminEventList = adminEventList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.admin_dashboard_event_text_layout, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            AdminDashboardEventClass eventBind = adminEventList.get(position);
            holder.adminEventDate.setText(eventBind.getAdminEventDate());
            holder.adminEventText.setText(eventBind.getAdminEventText());
        }

        @Override
        public int getItemCount() {
            return adminEventList.size();
        }
    }
