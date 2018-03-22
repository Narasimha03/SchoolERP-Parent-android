package com.example.harish.proschoolforparentadvanced.suresh.studentprofile;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.harish.proschoolforparentadvanced.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by JANI on 17-06-2017.
 */

public class AllDayStudentMonthAdapter extends RecyclerView.Adapter<AllDayStudentMonthAdapter.ViewHolder>{

    Context mContext;
    List<StudentGraphModel> evalMarksList;
    ArrayList<StudentGraphModel> evalMarksArrayList;

    public AllDayStudentMonthAdapter(Context mContext, ArrayList<StudentGraphModel> evalMarksList){
        this.mContext = mContext;
        this.evalMarksList = evalMarksList;
        this.evalMarksArrayList = new ArrayList<StudentGraphModel>();
        this.evalMarksArrayList.addAll(evalMarksList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendance_section_list_graph_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StudentGraphModel evalMarks = evalMarksList.get(position);
      /*  Glide.with(mContext)
                .load(evalMarks.getImageDisplay())
                .into(holder.imgUser);
        holder.imgUser.setVisibility(View.VISIBLE);*/
        if (evalMarks != null){
            holder.studentName.setText(evalMarks.getStudentName());

            //holder.mChart.setOnChartValueSelectedListener((OnChartValueSelectedListener) mContext);
            holder.mChart.setDrawGridBackground(false);
            holder.mChart.getDescription().setEnabled(false);
            holder.mChart.setScaleEnabled(false);

            holder.mChart.setPinchZoom(false);
            holder.mChart.setTouchEnabled(false);
            holder.mChart.setDrawBarShadow(false);
            holder.mChart.setDrawValueAboveBar(true);
            holder.mChart.setHighlightFullBarEnabled(false);
            holder. mChart.setVisibleYRangeMaximum(100, YAxis.AxisDependency.LEFT);
            //mChart.setVisibleXRangeMaximum(100);
            holder.mChart.getAxisLeft().setEnabled(false);
            holder.mChart.getAxisRight().setDrawGridLines(false);
            holder. mChart.getAxisRight().setDrawZeroLine(true);
            holder.  mChart.getAxisLeft().setLabelCount(0, false);
            holder. mChart.getAxisLeft().setTextSize(20f);
            holder. mChart.setDrawGridBackground(false);

            XAxis xAxis = holder.mChart.getXAxis();
            xAxis.setCenterAxisLabels(true);
            xAxis.setDrawAxisLine(false);
            xAxis.setGranularity(1f);

            xAxis.setGranularityEnabled(true);
            xAxis.setLabelRotationAngle(0f);
            xAxis.setDrawGridLines(false);
            xAxis.setEnabled(false);

            YAxis rightYAxis =   holder.mChart.getAxisRight();
            rightYAxis.setEnabled(false);

            holder.mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            holder.mChart.setDrawValueAboveBar(false);
            //  new BarEntry(5, new float[]{85, 7, 8});
            ArrayList<BarEntry> yValues = new ArrayList<>();
            yValues.add(new BarEntry(5, new float[]{Float.parseFloat(evalMarks.getsPresent()), Float.parseFloat(evalMarks.getsAbsent()), Float.parseFloat(evalMarks.getsOnleave())}));           /* yValues.add(new BarEntry(5, new float[]{85, 7, 8}));
            yValues.add(new BarEntry(15, new float[]{87, 7, 6}));
            yValues.add(new BarEntry(25, new float[]{80, 10, 10}));
            yValues.add(new BarEntry(35, new float[]{79, 11, 10}));*/



            BarDataSet set = new BarDataSet(yValues, "");
            set.setAxisDependency(YAxis.AxisDependency.RIGHT);
            BarData data = new BarData(set);
            data.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    if(value > 0) {
                        //return Math.round(value) + "/"+evalMarks.getTotalCount();
                        return Math.round(value) + "";

                    }
                    else
                    {
                        return "";
                    }
                }
            });
            data.setValueTextSize(15f);
            set.setColors(Color.rgb(95, 182, 156), Color.rgb(209, 190, 168), Color.rgb(110, 127, 128));
            set.setStackLabels(new String[]{
                    "Present", "Absent", "Leave"
            });          //  holder.statusPresent.setText(evalMarks.getStdAttendance());

            data.setBarWidth(3f); // set custom bar width
            holder.mChart.setData(data);
            holder. mChart.setFitBars(true); // make the x-axis fit exactly all bars
            holder.mChart.invalidate(); // refresh

            holder.mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
            holder.mChart.setScaleXEnabled(false);

            Legend legend = holder.mChart.getLegend();
            legend.setEnabled(true);
            legend.setTextSize(10);
            legend.setFormSize(10);
            legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
            legend.setStackSpace(10);


        }


    }



    @Override
    public int getItemCount() {
        return evalMarksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View container;
        private HorizontalBarChart mChart;

        public TextView studentName, statusPresent;
        public CircleImageView imgUser;

        public ViewHolder(View itemView) {
            super(itemView);
            studentName = (TextView) itemView.findViewById(R.id.studentName);
            statusPresent = (TextView) itemView.findViewById(R.id.studentAttStatus);
            //   imgUser = itemView.findViewById(R.id.profile_image);


            mChart = (HorizontalBarChart) itemView.findViewById(R.id.student_horizontal);

        }
    }
}

