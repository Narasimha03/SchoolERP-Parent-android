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

public class AdminDashboardNoticeBoardAdapter extends RecyclerView.Adapter<AdminDashboardNoticeBoardAdapter.MyViewHolder> {


        private List<AdminDashboardNoticeBoardClass> adminNoticeList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView adminNoticeText;
            public TextView adminNoticeTime;
            public TextView adminNoticeDate;
            public TextView adminNoticeSub;

            public MyViewHolder(View view) {
                super(view);
                adminNoticeText = view.findViewById(R.id.admin_notice_board_text_view);
                adminNoticeTime = view.findViewById(R.id.admin_notice_board_time);
                adminNoticeDate = view.findViewById(R.id.admin_notice_board_date);
                adminNoticeSub = view.findViewById(R.id.admin_notice_board_sub);
            }
        }


        public AdminDashboardNoticeBoardAdapter(List<AdminDashboardNoticeBoardClass> adminNoticeList) {
            this.adminNoticeList = adminNoticeList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.admin_dashboard_notice_board_text_layout, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            AdminDashboardNoticeBoardClass adminNoticeBind = adminNoticeList.get(position);
            holder.adminNoticeDate.setText(adminNoticeBind.getAdminNoticeDate());
            holder.adminNoticeTime.setText(adminNoticeBind.getAdminNoticeTime());
            holder.adminNoticeSub.setText(adminNoticeBind.getAdminNoticeSub());
            holder.adminNoticeText.setText(adminNoticeBind.getAdminNoticeText());
        }

        @Override
        public int getItemCount() {
            return adminNoticeList.size();
        }
    }
