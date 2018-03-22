package com.example.harish.proschoolforparentadvanced;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by harish on 27-10-2017.
 */


public class QuickDashboardRecyclerViewAdapter extends RecyclerView.Adapter<QuickDashboardRecyclerViewAdapter.ViewHolder> {

    ArrayList mValues;
    Context mContext;
    protected ItemListener mListener;

    public QuickDashboardRecyclerViewAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        public ImageView imageView;
        public ImageView imageViewMain;
        QuickDashboardDataModel item;
        public RelativeLayout relativeLayout;
        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            textView = (TextView)v.findViewById(R.id.textView);
          //  imageViewMain = v.findViewById(R.id.imageViewMain);
            imageView = (ImageView)v.findViewById(R.id.imageViewIcon);

            relativeLayout = (RelativeLayout)v.findViewById(R.id.imageViewMain);

        }

        public void setData(QuickDashboardDataModel item) {
            this.item = item;
            textView.setText(item.text);
           // imageViewMain.setImageResource(item.image);
           imageView.setImageResource(item.image);
           relativeLayout.setBackgroundResource(item.drawable);



        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, parent, false);

        return new ViewHolder(view);
    }




    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(QuickDashboardDataModel item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData((QuickDashboardDataModel) mValues.get(position));

       // QuickDashboardDataModel info = (QuickDashboardDataModel) mValues.get(position);
     //   holder.textView.setText(info.getText());
      //  holder.imageView.setImageResource(info.getImage());
      //  holder.imageViewMain.setImageResource(info.getDrawable());

    }

}